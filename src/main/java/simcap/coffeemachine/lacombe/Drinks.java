package simcap.coffeemachine.lacombe;

public enum Drinks {

    COFFEE("C",60),
    TEA("T", 40),
    CHOCHOLATE("H", 50),
    ORANGE("O", 60);

    private final String drink;
    private final int price;

    Drinks(String drink, int price) {

        this.drink = drink;
        this.price = price;

    }

    public String getDrink() {
        return this.drink;
    }
    public int getPrice() { return this.price; }
}
