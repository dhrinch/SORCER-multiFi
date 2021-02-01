package edu.pjatk.inn.coffeemaker.impl;

import edu.pjatk.inn.coffeemaker.Pouring;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public class PouringImpl implements Pouring {

    @Override
    public Context pourDrink(Context context) throws RemoteException, ContextException {
        String size = "medium";
        if(context.getValue("drink/size").equals("large")){
            size = "large";
            context.putValue("cup/size", size);
        }
        else if(context.getValue("drink/size").equals("medium")||context.getValue("drink/size").equals("")){
            context.putValue("cup/size", size);
        }
        else if(context.getValue("drink/size").equals("small")) {
            size = "small";
            context.putValue("cup/size", size);
        }
        return null;
    }
}
