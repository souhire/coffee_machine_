package simcap.coffeemachine.lacombe;

import simcap.coffeemachine.lacombe.adapter.MessageAdapter;
import simcap.coffeemachine.lacombe.adapter.OrderAdapter;

public class CoffeeMachine {

    private OrderAdapter orderAdapter;
    private MessageAdapter messageAdapter;
    private final NotEnoughMoneyMessage notEnoughMoneyMessage = new NotEnoughMoneyMessage("Il vous manque ");

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

    public String payOrder(Order order, int money) {
        int price = order.getDrink().getPrice();
        if (money >= price)
            return this.getOrder(order);
        notEnoughMoneyMessage.setDifference(price-money);
        return this.showMessage(notEnoughMoneyMessage);
    }

}
