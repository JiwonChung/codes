package com.company.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Message {
    private long index;
    private long sender_id;
    private long receiver_id;
    private Timestamp transmitted_time;
    private Timestamp read_time;
    private boolean readOrNot;
    private String text;

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getSender_id() {
        return sender_id;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }

    public long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public Timestamp getTransmitted_time() {
        return transmitted_time;
    }

    public void setTransmitted_time(Timestamp transmitted_time) {
        this.transmitted_time = transmitted_time;
    }

    public Timestamp getRead_time() {
        return read_time;
    }

    public void setRead_time(Timestamp read_time) {
        this.read_time = read_time;
    }

    public boolean isReadOrNot() {
        return readOrNot;
    }

    public void setReadOrNot(boolean readOrNot) {
        this.readOrNot = readOrNot;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
