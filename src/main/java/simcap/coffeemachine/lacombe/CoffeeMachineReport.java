package simcap.coffeemachine.lacombe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CoffeeMachineReport {

    private Map<Drinks, Integer> sales;

    public CoffeeMachineReport() {
        this.sales = new HashMap<>();
        for (Drinks drink : Drinks.values()) {
            this.sales.put(drink, 0);
        }
    }

    public void addSale(Order order) {
        int val = this.sales.get(order.getDrink());
        this.sales.put(order.getDrink(),val + 1);
    }

    public int getSpecificSale(Drinks drink) {
        return sales.get(drink);
    }

    public int getTotalBenefits() {
        AtomicInteger totalBenefits = new AtomicInteger();
        sales.forEach((key, value) -> {
            totalBenefits.addAndGet(key.getPrice() * value);
        });
        return totalBenefits.get();
    }

    public Map<Drinks, Integer> getSales() {
        return this.sales;
    }
}
