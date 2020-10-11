package simcap.coffeemachine.lacombe.adapter;

import simcap.coffeemachine.lacombe.Message;

public class MessageAdapter {

    public String formatMessage(Message message) {
        return "M:" + message.getMessage();
    }

}
