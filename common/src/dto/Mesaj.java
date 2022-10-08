package dto;

import java.io.Serializable;
import java.time.Instant;

public class Mesaj implements Serializable {

    private String continut;

    private String expeditor;

    private Instant timeStamp;

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public String getExpeditor() {
        return expeditor;
    }

    public void setExpeditor(String expeditor) {
        this.expeditor = expeditor;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString(){
        return expeditor + ":" + continut + " (" +timeStamp + ")";

    }

    public static Mesaj of(String expeditor , String  continut){
        Mesaj mesaj = new Mesaj();
        mesaj.setExpeditor(expeditor);
        mesaj.setContinut(continut);
        mesaj.setTimeStamp(Instant.now());
        return mesaj;
    }
}
