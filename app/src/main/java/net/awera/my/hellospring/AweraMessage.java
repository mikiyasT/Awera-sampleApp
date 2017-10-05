package net.awera.my.hellospring;

import java.io.Serializable;

/**
 * Created by miki on 9/10/17.
 */

public class AweraMessage implements Serializable {

    private String messageId;
    private String toNumber;
    private String cardNumber;
    private String shortMessage;


    public AweraMessage(String messageId,String toNumber, String cardNumber, String shortMessage) {
        this.toNumber = toNumber;
        this.cardNumber = cardNumber;
        this.shortMessage = shortMessage;
    }

    public String getId() {
        return toNumber;
    }

    public void setId(String id) {
        this.toNumber = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }
}
