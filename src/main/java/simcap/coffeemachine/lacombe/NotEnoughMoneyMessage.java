package simcap.coffeemachine.lacombe;

public class NotEnoughMoneyMessage extends Message {

    int difference;

    public NotEnoughMoneyMessage(String message) {
        super(message);
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public String getMessage() {
        return super.getMessage() + difference;
    }
}
