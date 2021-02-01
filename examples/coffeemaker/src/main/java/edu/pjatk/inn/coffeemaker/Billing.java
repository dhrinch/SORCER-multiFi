package edu.pjatk.inn.coffeemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface Billing {

    public Context paymentMethod(Context context) throws RemoteException, ContextException;
    public Context managePayment(Context context) throws  RemoteException, ContextException;
}
