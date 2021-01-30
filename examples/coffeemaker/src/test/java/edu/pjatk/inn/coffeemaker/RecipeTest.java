package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;

import static org.junit.Assert.*;

/**
 * @author Denys Grinchenko
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class RecipeTest {
    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

    private CoffeeMaker coffeeMaker;
    private Recipe espresso, mocha, macchiato, americano, capuccino;

    @Before
    public void setUp() throws ContextException {
        coffeeMaker = new CoffeeMaker();

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
    public void testAddRecipe() {
        assertTrue(coffeeMaker.addRecipe(espresso));
    }

    @Test
    public void addRecipe() throws Exception {
        coffeeMaker.addRecipe(macchiato);
        assertEquals(coffeeMaker.getRecipeForName("macchiato").getName(), "macchiato");
    }

    @Test
    public void addNonUniqueRecipe() throws Exception {
        coffeeMaker.addRecipe(mocha);
        coffeeMaker.addRecipe(mocha);
        assertFalse(coffeeMaker.addRecipe(mocha));
    }

    @Test
    public void addRecipeWithExistingName() throws Exception {
        Recipe lungo = new Recipe();
        lungo.setName("espresso");
        lungo.setPrice(70);
        lungo.setAmtCoffee(3);
        lungo.setAmtMilk(1);
        lungo.setAmtSugar(1);
        lungo.setAmtChocolate(0);
        coffeeMaker.addRecipe(espresso);
        assertFalse(coffeeMaker.addRecipe(lungo));
    }

    @Test
    public void addMoreThanThreeRecipes() throws Exception {
        coffeeMaker.addRecipe(capuccino);
        coffeeMaker.addRecipe(macchiato);
        coffeeMaker.addRecipe(espresso);
        assertFalse(coffeeMaker.addRecipe(mocha));
    }

    @Test
    public void deleteRecipebyName() throws Exception {
        coffeeMaker.addRecipe(espresso);
        assertTrue(coffeeMaker.deleteRecipe(coffeeMaker.getRecipeForName("espresso")));
    }

    @Test
    public void deleteRecipe()throws Exception {
        coffeeMaker.addRecipe(espresso);
        Recipe r = coffeeMaker.getRecipeForName(espresso.getName());
        coffeeMaker.deleteRecipe(coffeeMaker.getRecipeForName("espresso"));
        assertTrue(coffeeMaker.getRecipeForName("espresso") == null);
    }

    @Test
    public void noOldRecipeAfterEdit() throws Exception {
        coffeeMaker.addRecipe(espresso);
        Recipe lungo = new Recipe();
        lungo.setName("lungo");
        lungo.setPrice(70);
        lungo.setAmtCoffee(3);
        lungo.setAmtMilk(1);
        lungo.setAmtSugar(1);
        lungo.setAmtChocolate(0);
        coffeeMaker.editRecipe(espresso, lungo);
        assertTrue(coffeeMaker.getRecipeForName("espresso") == null);
    }

    @Test
    public void editRecipe() throws Exception {
        coffeeMaker.addRecipe(americano);
        Recipe lungo = new Recipe();
        lungo.setName("lungo");
        lungo.setPrice(70);
        lungo.setAmtCoffee(3);
        lungo.setAmtMilk(1);
        lungo.setAmtSugar(1);
        lungo.setAmtChocolate(0);
        coffeeMaker.editRecipe(americano, lungo);
        assertEquals(coffeeMaker.getRecipeForName("lungo").getName(),"lungo");
    }

    @Test
    public void changeRecipeNametoNew() throws Exception {
        Recipe lungo = new Recipe();
        lungo.setName("lungo");
        lungo.setPrice(70);
        lungo.setAmtCoffee(3);
        lungo.setAmtMilk(1);
        lungo.setAmtSugar(1);
        lungo.setAmtChocolate(0);
        coffeeMaker.addRecipe(espresso);
        assertTrue(coffeeMaker.editRecipe(espresso, lungo));
    }
}
