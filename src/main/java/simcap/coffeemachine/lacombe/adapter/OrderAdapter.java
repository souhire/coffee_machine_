package simcap.coffeemachine.lacombe.adapter;

import simcap.coffeemachine.lacombe.Order;

public class OrderAdapter {

    public String formatOrder(Order order) {
        return order.getDrink().getDrink() + (order.isExtraHot() ? "h" : "")
                + ":" + (order.getSugar() > 0 ? order.getSugar() + ":0" : ":");
    }

}
