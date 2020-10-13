package simcap.coffeemachine.lacombe;

import simcap.coffeemachine.lacombe.adapter.CoffeeMachineReportAdapter;
import simcap.coffeemachine.lacombe.adapter.MessageAdapter;
import simcap.coffeemachine.lacombe.adapter.OrderAdapter;

public class CoffeeMachine {

    private OrderAdapter orderAdapter;
    private MessageAdapter messageAdapter;
    private CoffeeMachineReportAdapter cmrAdapter;
    private final NotEnoughMoneyMessage notEnoughMoneyMessage = new NotEnoughMoneyMessage("Il vous manque ");

    private CoffeeMachineReport cmr;

    public CoffeeMachine(CoffeeMachineReport cmr) {
        this.orderAdapter = new OrderAdapter();
        this.messageAdapter = new MessageAdapter();
        this.cmr = cmr;
        this.cmrAdapter = new CoffeeMachineReportAdapter(cmr);
    }

    public String getOrder(Order order) {
        return this.orderAdapter.formatOrder(order);
    }

    public String showMessage(Message message) {
        return this.messageAdapter.formatMessage(message);
    }

    public String payOrder(Order order, int money) {
        int price = order.getDrink().getPrice();
        if (money >= price) {
            cmr.addSale(order);
            return this.getOrder(order);
        }
        notEnoughMoneyMessage.setDifference(price-money);
        return this.showMessage(notEnoughMoneyMessage);
    }

    public String getReport() {
        return this.cmrAdapter.formatReport();
    }
}
