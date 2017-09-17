/*
 * Copyright 2013 the original author or authors.
 * Copyright 2013 SorcerSoft.org.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sorcer.core.invoker;

import sorcer.core.context.model.ent.Entry;
import sorcer.core.context.model.ent.NeoFidelity;
import sorcer.service.*;
import sorcer.service.modeling.Activation;
import sorcer.service.modeling.Functionality;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Mike Sobolewski
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Activator extends ServiceInvoker<Double> implements Activation {

    private static final long serialVersionUID = 1L;

    {
        defaultName = "activator-";
    }

    protected ServiceFidelity<NeoFidelity> fidelities;

    // linear transformation of the input vector
    double bias = 0.0;

    // for step function
    double threshold = 0.0;

    Context<Float> weights;

    boolean rectified = false;

    public Activator() {
        super();
    }

    public Activator(String name) {
        super(name);
    }

    @Override
    public Double invoke(Context context, Arg... entries)
            throws RemoteException, InvocationException {
        try {
            return activate(entries);
        } catch (Exception e) {
            throw new InvocationException(e);
        }
    }

    @Override
    public Double invoke(Arg... entries) throws InvocationException, RemoteException,
            InvocationException {
        try {
            return activate(entries);
        } catch (EvaluationException e) {
            throw new InvocationException(e);
        }
    }

    public boolean isRectified() {
        return rectified;
    }

    public void setRectified(boolean rectified) {
        this.rectified = rectified;
    }

    public Context<Float> getWeights() {
        return weights;
    }

    public void setWeights(Context<Float> weights) {
        this.weights = weights;
    }

    public Double activate(Arg... entries) throws EvaluationException, RemoteException {
        List<String> names = args.getNames();
        for (Arg arg : entries) {
            if (arg instanceof Fidelity) {
                if (arg.getName().equals(name)) {
                    fidelities.setSelect(((Fidelity) arg).getPath());
                }
            } else if (arg instanceof Entry) {
                if (((Entry) arg).getType() == Functionality.Type.THRESHOLD
                        && name.equals(arg.getName())) {
                    threshold = (double) ((Entry) arg).getItem();
                } else if (((Entry) arg).getType() == Functionality.Type.BIAS
                        && name.equals(arg.getName())) {
                    bias = (double) ((Entry) arg).getItem();

                }
            }
        }

        if (fidelities != null && fidelities.getSelect() != null) {
            applyFidelity();
        }
        double sum = 0.0;
        for (String name : args.getNames()) {
            double in = (double) ((Entry)invokeContext.get(name)).getItem();
            double wt = (double) weights.get(name);
            sum = sum + (in * wt);
        }
        sum = sum + bias;

        if (rectified) {
            // if rectified linear soma
            if (sum < 0.0) {
                return 0.0;
            }
        } else if (threshold != 0.0) {
            if (sum > threshold) {
                return 1.0;
            } else {
                return -1.0;
            }
        }
        return sum;
    }

    public ServiceFidelity<NeoFidelity> getFidelities() {
        return fidelities;
    }

    public void setFidelities(ServiceFidelity<NeoFidelity> fidelities) {
        this.fidelities = fidelities;
    }

    private void applyFidelity() {
        NeoFidelity fi = fidelities.getSelect();
        if (fi.getWeights() != null) {
            weights = fi.getWeights();
        }
        if (fi.getArgs() != null && fi.getArgs().size() > 0) {
            args = fi.getArgs().argSet();
        }
        if (fi.getThreshold() != null) {
            threshold = fi.getThreshold();
        }
        if (fi.getBias() != null) {
            bias = fi.getBias();
        }
    }

}
