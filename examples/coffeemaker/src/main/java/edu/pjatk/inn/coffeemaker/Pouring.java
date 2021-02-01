package edu.pjatk.inn.coffeemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface Pouring {

    public Context pourDrink(Context context) throws RemoteException, ContextException;
}