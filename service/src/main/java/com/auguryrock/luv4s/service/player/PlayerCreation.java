package com.auguryrock.luv4s.service.player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
public class PlayerCreation {
    private Status status = Status.OK;
    private Map<String, FieldStatus> fields = new HashMap<>();

    public enum Status {
        OK, KO;
    }

    public enum FieldStatus {
        alreadyExits, cantBeNull
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<String, FieldStatus> getFields() {
        return fields;
    }

    public void setFields(Map<String, FieldStatus> fields) {
        this.fields = fields;
    }
}
