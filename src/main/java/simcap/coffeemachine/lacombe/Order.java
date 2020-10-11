package simcap.coffeemachine.lacombe;

public class Order {

    private Drinks drink;
    private int sugar = 0;

    public Order(Drinks drink) {
        this.drink = drink;
    }

    public void addSugar() {
        if (this.sugar < 2) this.sugar++;
    }

    public Drinks getDrink() {
        return this.drink;
    }

    public int getSugar() {
        return this.sugar;
    }

}
