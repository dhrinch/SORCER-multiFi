package edu.pjatk.inn.coffeemaker.impl;

import edu.pjatk.inn.coffeemaker.Billing;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public class BillingImpl implements Billing {

    @Override
    public Context paymentMethod(Context context) throws RemoteException, ContextException {
        if(context.getValue("billing/method").equals("NFC")) {
            context.putValue("payment_method/nfc", true);
        }
        else if(context.getValue("billing/method").equals("face")) {
            context.putValue("payment_method/face", true);
        }
        else if(context.getValue("billing/method").equals("cash")) {
            context.putValue("payment_method/cash", true);
        }
        else if(context.getValue("billing/method").equals("card")) {
            context.putValue("payment_method/card", true);
        }
        else if(context.getValue("billing/method").equals("credit")) {
            context.putValue("payment_method/credit", true);
        }
        return context;
    }

    @Override
    public Context managePayment(Context context) throws RemoteException, ContextException {
        if (context.getValue("billing/cost") != null && context.getValue("billing/balance") != null) {
            if((Integer) context.getValue("billing/amt_received") > (Integer) context.getValue("billing/price")){
                context.putValue("billing/amt_resulting", (Integer) context.getValue("billing/amt_received") > (Integer) context.getValue("billing/price"));
                context.putValue("billing/amt_received", (Integer)context.getValue("billing/balance") - (Integer) context.getValue("billing/price"));
            }
            else{
                context.putValue("billing/amt_resulting", (Integer) context.getValue("billing/amt_received") > (Integer) context.getValue("billing/price"));
            }
        }
        else {
            context.putValue("billing/amt_resulting", false);
        }
        return context;
    }
}
