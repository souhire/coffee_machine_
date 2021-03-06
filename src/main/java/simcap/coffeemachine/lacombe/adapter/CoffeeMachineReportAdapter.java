package simcap.coffeemachine.lacombe.adapter;

import simcap.coffeemachine.lacombe.CoffeeMachineReport;
import simcap.coffeemachine.lacombe.Drinks;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachineReportAdapter {

    private CoffeeMachineReport cmr;

    public CoffeeMachineReportAdapter(CoffeeMachineReport cmr) {
        this.cmr = cmr;
    }

    public String formatReport() {
        Map<Drinks, Integer> map = cmr.getSales();
        final String[] retour = {""};
        map.forEach((key, value) -> {
            retour[0] += key.getDrink() + ":" + value + ";";
        });
        return retour[0] + cmr.getTotalBenefits();
    }

}
