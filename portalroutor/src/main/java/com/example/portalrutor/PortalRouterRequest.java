package com.example.portalrutor;

import java.util.HashMap;

public class PortalRouterRequest {

    private String action;
    private HashMap<String, Object> data;

    private PortalRouterRequest() {
        this.data = new HashMap<>();
    }

    public static PortalRouterRequest create(){
        return new PortalRouterRequest();
    }

    public PortalRouterRequest data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public PortalRouterRequest action() {
        this.action = action;
        return this;
    }

    public HashMap<String, Object> getData() {
        return this.data;
    }

    public String getAction() {
        return this.action;
    }

}