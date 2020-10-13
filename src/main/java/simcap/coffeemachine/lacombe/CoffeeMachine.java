package simcap.coffeemachine.lacombe;

import simcap.coffeemachine.lacombe.adapter.CoffeeMachineReportAdapter;
import simcap.coffeemachine.lacombe.adapter.MessageAdapter;
import simcap.coffeemachine.lacombe.adapter.OrderAdapter;
import simcap.coffeemachine.lacombe.interfaces.BeverageQuantityChecker;
import simcap.coffeemachine.lacombe.interfaces.EmailNotifier;

public class CoffeeMachine {

    private OrderAdapter orderAdapter;
    private MessageAdapter messageAdapter;
    private CoffeeMachineReportAdapter cmrAdapter;
    private final NotEnoughMoneyMessage notEnoughMoneyMessage = new NotEnoughMoneyMessage("Il vous manque ");

    private CoffeeMachineReport cmr;
    private final BeverageQuantityChecker beverageQuantityChecker;
    private final EmailNotifier emailNotifier;

    public CoffeeMachine(CoffeeMachineReport cmr, BeverageQuantityChecker beverageQuantityChecker,
                         EmailNotifier emailNotifier) {
        this.orderAdapter = new OrderAdapter();
        this.messageAdapter = new MessageAdapter();
        this.cmr = cmr;
        this.cmrAdapter = new CoffeeMachineReportAdapter(cmr);
        this.beverageQuantityChecker = beverageQuantityChecker;
        this.emailNotifier = emailNotifier;
    }

    public String getOrder(Order order) {
        return this.orderAdapter.formatOrder(order);
    }

    public String showMessage(Message message) {
        return this.messageAdapter.formatMessage(message);
    }

    public String payOrder(Order order, int money) {
        if (beverageQuantityChecker.isEmpty(order.getDrink().name())) {
            emailNotifier.notifyMissingDrink(order.getDrink().name());
            return this.showMessage(new ShortageMessage());
        }
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
