package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;

import static org.junit.Assert.assertEquals;

/**
 * @author Denys Grinchenko
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class InventoryTest {
    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

    private CoffeeMaker coffeeMaker;
    private Recipe espresso, mocha, macchiato, americano, capuccino;
    private Inventory inventory;

    @Before
    public void setUp() throws ContextException {
        coffeeMaker = new CoffeeMaker();
        inventory = coffeeMaker.checkInventory();

        inventory.setCoffee(15);
        inventory.setMilk(15);
        inventory.setSugar(15);
        inventory.setChocolate(15);

        espresso = new Recipe();
        espresso.setName("espresso");
        espresso.setPrice(50);
        espresso.setAmtCoffee(6);
        espresso.setAmtMilk(1);
        espresso.setAmtSugar(1);
        espresso.setAmtChocolate(0);

        mocha = new Recipe();
        mocha.setName("mocha");
        mocha.setPrice(100);
        mocha.setAmtCoffee(8);
        mocha.setAmtMilk(1);
        mocha.setAmtSugar(1);
        mocha.setAmtChocolate(2);

        macchiato = new Recipe();
        macchiato.setName("macchiato");
        macchiato.setPrice(40);
        macchiato.setAmtCoffee(7);
        macchiato.setAmtMilk(1);
        macchiato.setAmtSugar(2);
        macchiato.setAmtChocolate(0);

        americano = new Recipe();
        americano.setName("americano");
        americano.setPrice(40);
        americano.setAmtCoffee(7);
        americano.setAmtMilk(1);
        americano.setAmtSugar(2);
        americano.setAmtChocolate(0);

        capuccino = new Recipe();
        capuccino.setName("capuccino");
        capuccino.setPrice(150);
        capuccino.setAmtCoffee(25);
        capuccino.setAmtMilk(125);
        capuccino.setAmtSugar(2);
        capuccino.setAmtChocolate(0);
    }

    @Test
    public void addInventoryCoffee()throws Exception {
        coffeeMaker.addInventory(10, 0, 0, 0);
        assertEquals(coffeeMaker.checkInventory().getCoffee(), 25);
    }

    @Test
    public void addInventoryMilk()throws Exception {
        coffeeMaker.addInventory(0, 10, 0, 0);
        assertEquals(coffeeMaker.checkInventory().getMilk(), 25);
    }

    @Test
    public void addInventorySugar()throws Exception {
        coffeeMaker.addInventory(0, 0, 10, 0);
        assertEquals(coffeeMaker.checkInventory().getSugar(), 25);
    }

    @Test
    public void addInventoryChocolate()throws Exception {
        coffeeMaker.addInventory(0, 0, 0, 10);
        assertEquals(coffeeMaker.checkInventory().getChocolate(), 25);
    }

    @Test
    public void checkCoffeeInventory()throws Exception {
        assertEquals(coffeeMaker.checkInventory().getCoffee(),15);
    }

    @Test
    public void checkMilkInventory()throws Exception {
        assertEquals(coffeeMaker.checkInventory().getMilk(),15);
    }

    @Test
    public void checkSugarInventory()throws Exception {
        assertEquals(coffeeMaker.checkInventory().getSugar(),15);
    }

    @Test
    public void checkChocolateInventory()throws Exception {
        assertEquals(coffeeMaker.checkInventory().getChocolate(),15);
    }

    @Test
    public void CheckCoffeeInventoryAfterMakingCoffee()throws Exception {
        coffeeMaker.makeCoffee(espresso,200);
        assertEquals(coffeeMaker.checkInventory().getCoffee(),9);
    }

    @Test
    public void CheckMilkInventoryAfterMakingCoffee()throws Exception {
        coffeeMaker.makeCoffee(espresso,200);
        assertEquals(coffeeMaker.checkInventory().getMilk(),14);
    }

    @Test
    public void CheckSugarInventoryAfterMakingCoffee()throws Exception {
        coffeeMaker.makeCoffee(espresso,200);
        assertEquals(coffeeMaker.checkInventory().getSugar(),14);
    }

    @Test
    public void CheckChocolateInventoryAfterMakingCoffee()throws Exception {
        coffeeMaker.makeCoffee(mocha,200);
        assertEquals(coffeeMaker.checkInventory().getChocolate(),13);
    }
}
