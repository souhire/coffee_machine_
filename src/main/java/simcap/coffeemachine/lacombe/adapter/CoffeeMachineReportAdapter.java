package simcap.coffeemachine.lacombe.adapter;

import simcap.coffeemachine.lacombe.CoffeeMachineReport;
import simcap.coffeemachine.lacombe.Drinks;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachineReportAdapter {

    public String formatReport(CoffeeMachineReport cmr) {
        Map<Drinks, Integer> map = cmr.getSales();
        final String[] retour = {""};
        map.forEach((key, value) -> {
            retour[0] += key.getDrink() + ":" + value + ";";
        });
        return retour[0] + cmr.getTotalBenefits();
    }

}
