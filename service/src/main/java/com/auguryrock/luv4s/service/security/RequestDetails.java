package com.auguryrock.luv4s.service.security;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
public class RequestDetails {
    private String ipAddress;
    private String scoutingKey;

    public RequestDetails(String ipAddress, String scoutingKey) {
        this.ipAddress = ipAddress;
        this.scoutingKey = scoutingKey;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getScoutingKey() {
        return scoutingKey;
    }
}
