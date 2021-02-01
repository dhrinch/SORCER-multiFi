package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.BillingImpl;
import edu.pjatk.inn.coffeemaker.impl.PouringImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.Context;
import sorcer.service.Job;
import sorcer.service.Task;

import static org.junit.Assert.assertEquals;
import static sorcer.co.operator.inVal;
import static sorcer.co.operator.val;
import static sorcer.eo.operator.*;
import static sorcer.mo.operator.value;
import static sorcer.so.operator.exert;

/**
 * @author Denys Grinchenko
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class PouringTest {

    @Test
    public void pourLargeCoffee() throws Exception{
    Task pourDrink = task("pourLarge",sig("pourDrink", PouringImpl.class), context(
            val("drink/size", "large")
            ));
    Context value = upcontext(exert(pourDrink));
    assertEquals(value(value, "cup/size"), "large");
}

    @Test
    public void pourDefaultSize() throws Exception{
        Task pourDrink = task("pourDefault",sig("pourDrink", PouringImpl.class), context(
                val("drink/size", "")
        ));
        Context value = upcontext(exert(pourDrink));
        assertEquals(value(value, "cup/size"), "medium");
    }

    @Test
    public void pourMediumCoffee() throws Exception{
        Task pourDrink = task("pourMedium",sig("pourDrink", PouringImpl.class), context(
                val("drink/size", "medium")
        ));
        Context value = upcontext(exert(pourDrink));
        assertEquals(value(value, "cup/size"), "medium");
    }

    @Test
    public void pourSmallCoffee() throws Exception{
        Task pourDrink = task("pourSmall",sig("pourDrink", PouringImpl.class), context(
                val("drink/size", "small")
        ));
        Context value = upcontext(exert(pourDrink));
        assertEquals(value(value, "cup/size"), "small");
    }

    @Test
    public void payWithCash() throws Exception{
        Task setBillingProvider = task("cashPayment",sig("paymentMethod", BillingImpl.class), context(
                val("billing/method", "cash")
        ));
        Context value = upcontext(exert(setBillingProvider));
        assertEquals(value(value, "payment_method/cash"), true);
    }

    @Test
    public void payWithWireless() throws Exception{
        Task setBillingProvider = task("cashPayment",sig("paymentMethod", BillingImpl.class), context(
                val("billing/method", "NFC")
        ));
        Context value = upcontext(exert(setBillingProvider));
        assertEquals(value(value, "payment_method/nfc"), true);
    }

    @Test
    public void payWithCard() throws Exception{
        Task setBillingProvider = task("cashPayment",sig("paymentMethod", BillingImpl.class), context(
                val("billing/method", "card")
        ));
        Context value = upcontext(exert(setBillingProvider));
        assertEquals(value(value, "payment_method/card"), true);
    }

    @Test
    public void payWithFace() throws Exception{
        Task setBillingProvider = task("cashPayment",sig("paymentMethod", BillingImpl.class), context(
                val("billing/method", "face")
        ));
        Context value = upcontext(exert(setBillingProvider));
        assertEquals(value(value, "payment_method/face"), true);
    }
}
