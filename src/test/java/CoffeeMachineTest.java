import org.junit.Before;
import org.junit.Test;
import simcap.coffeemachine.lacombe.CoffeeMachine;
import simcap.coffeemachine.lacombe.Drinks;
import simcap.coffeemachine.lacombe.Message;
import simcap.coffeemachine.lacombe.Order;

import static org.junit.Assert.assertEquals;

public class CoffeeMachineTest {

    CoffeeMachine coffeeMachine;

    @Before
    public void setUp() {
        coffeeMachine = new CoffeeMachine();
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
        assertEquals("Hh:1:", this.coffeeMachine.payOrder(order, 60));
    }

    @Test
    public void testExtraHotTea() {
        Order order = new Order(Drinks.TEA);
        order.setExtraHot();
        order.addSugar();
        order.addSugar();
        assertEquals("Th:2:", this.coffeeMachine.payOrder(order, 60));
    }

}
