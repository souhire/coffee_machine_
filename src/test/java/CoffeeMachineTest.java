import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import simcap.coffeemachine.lacombe.*;
import simcap.coffeemachine.lacombe.interfaces.BeverageQuantityChecker;
import simcap.coffeemachine.lacombe.interfaces.EmailNotifier;

import static org.junit.Assert.assertEquals;

public class CoffeeMachineTest {

    CoffeeMachine coffeeMachine;
    BeverageQuantityChecker beverageQuantityChecker = Mockito.mock(BeverageQuantityChecker.class);
    EmailNotifier emailNotifier = Mockito.mock(EmailNotifier.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        coffeeMachine = new CoffeeMachine(new CoffeeMachineReport(), beverageQuantityChecker, emailNotifier);
    }

    @Test
    public void testOneTeaOneSugarAndStick() {
        Order order = new Order(Drinks.TEA);
        order.addSugar();

        assertEquals("T:1:0", this.coffeeMachine.getOrder(order));
    }

    @Test
    public void testOneChocolateWhithoutSugar() {
        Order order = new Order(Drinks.CHOCHOLATE);

        assertEquals("H::", this.coffeeMachine.getOrder(order));
    }

    @Test
    public void testOneCoffeeTwoSugarsAndStick() {
        Order order = new Order(Drinks.COFFEE);
        order.addSugar();
        order.addSugar();

        assertEquals("C:2:0", this.coffeeMachine.getOrder(order));
    }

    @Test
    public void testShowMessage() {
        Message message = new Message("Ceci est un message");

        assertEquals("M:Ceci est un message", this.coffeeMachine.showMessage(message));
    }

    @Test
    public void testGetOrderWithEnoughMoney() {
        Order order = new Order(Drinks.COFFEE);

        assertEquals("C::", this.coffeeMachine.payOrder(order, 60));

        order.addSugar();
        order.addSugar();
        assertEquals("C:2:0", this.coffeeMachine.payOrder(order, 70));
    }

    @Test
    public void testGetOrderWithoutEnoughMoney() {
        Order order = new Order(Drinks.COFFEE);

        assertEquals("M:Il vous manque 30", this.coffeeMachine.payOrder(order, 30));

    }

    @Test
    public void testOrderOrangeJuice() {
        Order order = new Order(Drinks.ORANGE);

        assertEquals("O::", this.coffeeMachine.payOrder(order, 60));

    }

    @Test
    public void testExtraHotCoffee() {
        Order order = new Order(Drinks.COFFEE);
        order.setExtraHot();

        assertEquals("Ch::", this.coffeeMachine.payOrder(order, 60));
    }

    @Test
    public void testExtraHotChocolate() {
        Order order = new Order(Drinks.CHOCHOLATE);
        order.setExtraHot();
        order.addSugar();
        assertEquals("Hh:1:0", this.coffeeMachine.payOrder(order, 60));
    }

    @Test
    public void testExtraHotTea() {
        Order order = new Order(Drinks.TEA);
        order.setExtraHot();
        order.addSugar();
        order.addSugar();
        assertEquals("Th:2:0", this.coffeeMachine.payOrder(order, 60));
    }

    @Test
    public void testReport() {
        assertEquals("T:0;O:0;C:0;H:0;0", this.coffeeMachine.getReport());

        Order order = new Order(Drinks.COFFEE);
        this.coffeeMachine.payOrder(order, 60);
        assertEquals("T:0;O:0;C:1;H:0;60", this.coffeeMachine.getReport());

        order = new Order(Drinks.TEA);
        this.coffeeMachine.payOrder(order, 40);
        assertEquals("T:1;O:0;C:1;H:0;100", this.coffeeMachine.getReport());

    }

    @Test
    public void testShortage() {
        Mockito.when(this.beverageQuantityChecker.isEmpty(Mockito.any())).thenReturn(true);
        Order order = new Order(Drinks.COFFEE);
        assertEquals("M:Rupture, une notification va être envoyée",
                this.coffeeMachine.payOrder(order, 90));
    }

}
