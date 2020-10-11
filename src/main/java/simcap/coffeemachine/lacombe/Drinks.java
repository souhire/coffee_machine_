package simcap.coffeemachine.lacombe;

public enum Drinks {

    COFFEE("C"),
    TEA("T"),
    CHOCHOLATE("H");

    private final String drink;

    Drinks(String drink) {
        this.drink = drink;
    }

    public String getDrink() {
        return this.drink;
    }
}
