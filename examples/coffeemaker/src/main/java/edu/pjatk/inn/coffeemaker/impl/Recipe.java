package edu.pjatk.inn.coffeemaker.impl;

import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @author   Sarah & Mike
 */

public class Recipe implements Serializable {
    private String name;
    private int price;
    private int amtCoffee;
    private int amtMilk;
    private int amtSugar;
    private int amtChocolate;

    /** Default constructor for the Recipe class, initializes empty object.
	 * @param name name of the recipe
	 * @param price price of the recipe
	 * @param amtCofee amount of coffee in the recipe
	 * @param amtMilk amount of milk in the recipe
	 * @param amtSugar amount of sugar in the recipe
	 * @param amtChocolate amount of chocolate in the recipe
	 */
    public Recipe() {
    	this.name = "";
    	this.price = 0;
    	this.amtCoffee = 0;
    	this.amtMilk = 0;
    	this.amtSugar = 0;
    	this.amtChocolate = 0;
    }
    
    /** Gets the amount of chocolate in the recipe
	 * @return An <code>Int</code> value of amtChocolate.
	 */
    public int getAmtChocolate() {
		return amtChocolate;
	}
    /**Sets the amount of chocolate in the recipe
	 * @param amtChocolate an <code>Int</code> value representing the amount of chocolate in the recipe. Must be equal or above zero.
	 */
    public void setAmtChocolate(int amtChocolate) {
		if (amtChocolate >= 0) {
			this.amtChocolate = amtChocolate;
		} 
	}
    /**Gets the amount of coffee in the recipe
	 * @return An <code>Int</code> value of amtCoffee.
	 */
    public int getAmtCoffee() {
		return amtCoffee;
	}
	/**Sets the amount of coffee in the recipe
	 * @param amtCoffee an <code>Int</code> value representing the amount of coffee in the recipe. Must be equal or above zero.
	 */
    public void setAmtCoffee(int amtCoffee) {
		if (amtCoffee >= 0) {
			this.amtCoffee = amtCoffee;
		} 
	}
	/**Gets the amount of milk in the recipe
	 * @return An <code>Int</code> value of amtMilk.
	 */
    public int getAmtMilk() {
		return amtMilk;
	}
	/**Sets the amount of milk in the recipe
	 * @param amtMilk an <code>Int</code> value representing the amount of milk in the recipe. Must be equal or above zero.
	 */
    public void setAmtMilk(int amtMilk) {
		if (amtMilk >= 0) {
			this.amtMilk = amtMilk;
		} 
	}
	/**Gets the amount of sugar in the recipe
	 * @return An <code>Int</code> value of amtSugar.
	 */
    public int getAmtSugar() {
		return amtSugar;
	}
	/**Sets the amount of sugar in the recipe
	 * @param amtSugar an <code>Int</code> value representing the amount of sugar in the recipe. Must be equal or above zero.
	 */
    public void setAmtSugar(int amtSugar) {
		if (amtSugar >= 0) {
			this.amtSugar = amtSugar;
		} 
	}
	/**Gets the name of the recipe
	 * @return A <code>String</code> value of name.
	 */
    public String getName() {
		return name;
	}
	/**Sets the name of the the recipe
	 * @param name a <code>String</code> value representing the name of this recipe. Must not be empty.
	 */
    public void setName(String name) {
    	if(name != null) {
    		this.name = name;
    	}
	}
	/**Gets the price of the recipe
	 * @return An <code>Int</code> value of price.
	 */
    public int getPrice() {
		return price;
	}
	/**Sets the amount of sugar in the recipe
	 * @param price an <code>Int</code> value representing the price of the recipe. Must be equal or above zero.
	 */
    public void setPrice(int price) {
		if (price >= 0) {
			this.price = price;
		} 
	}

	/**Checks whether the name of this recipe is already used by the existing recipe in the parameters
	 * @param r an instance of the <code>Recipe</code> object
	 * @return true if the name of the current recipe equals the name of the recipe in the parameters and false if it does not
	 */
    public boolean equals(Recipe r) {
        if((this.name).equals(r.getName())) {
            return true;
        }
        return false;
    }
    /**Overrides the toString method of the <code>Object</code> class to provide a string representation of the <code>Recipe</code> object
	 * @return a <code>String</code> representing name of the recipe
    */
    public String toString() {
    	return name;
    }

	/**Gets complete recipe instantiated with current values
	 * @param context current context
	 * @return this <code>Recipe</code> object, instantiated with parameters of this object's constructor
	 * @throws ContextException if unsuccessful
	 */
	static public Recipe getRecipe(Context context) throws ContextException {
		Recipe r = new Recipe();
		try {
			r.name = (String)context.getValue("key");
			r.price = (int)context.getValue("price");
			r.amtCoffee = (int)context.getValue("amtCoffee");
			r.amtMilk = (int)context.getValue("amtMilk");
			r.amtSugar = (int)context.getValue("amtSugar");
			r.amtChocolate = (int)context.getValue("amtChocolate");
		} catch (RemoteException e) {
			throw new ContextException(e);
		}
		return r;
	}

	/**Gets values of this recipe data fields and puts them into the <code>Context</code> object instance
	 * @param recipe this <code>Recipe</code> object
	 * @return an instance of the <code>Context</code> object
	 * @throws ContextException if unsuccessful
	 */
	static public Context getContext(Recipe recipe) throws ContextException {
		Context cxt = new ServiceContext();
		cxt.putValue("key", recipe.getName());
		cxt.putValue("price", recipe.getPrice());
		cxt.putValue("amtCoffee", recipe.getAmtCoffee());
		cxt.putValue("amtMilk", recipe.getAmtMilk());
		cxt.putValue("amtSugar", recipe.getAmtSugar());
		cxt.putValue("amtChocolate", recipe.getAmtChocolate());
		return cxt;
	}


}
