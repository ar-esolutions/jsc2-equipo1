package com.esolutions.trainings.jsc2.web;

public class WifiSsidResponse {

    private String ssid;

    public WifiSsidResponse(String ssid) {
        this.ssid = ssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
}
