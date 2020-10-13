package simcap.coffeemachine.lacombe;

import simcap.coffeemachine.lacombe.adapter.MessageAdapter;
import simcap.coffeemachine.lacombe.adapter.OrderAdapter;

public class CoffeeMachine {

    private OrderAdapter orderAdapter;
    private MessageAdapter messageAdapter;

    public CoffeeMachine() {
        this.orderAdapter = new OrderAdapter();
        this.messageAdapter = new MessageAdapter();
    }

    public String getOrder(Order order) {
        return this.orderAdapter.formatOrder(order);
    }

    public String showMessage(Message message) {
        return this.messageAdapter.formatMessage(message);
    }

    public String payOrder() {
        return "";
    }

}
