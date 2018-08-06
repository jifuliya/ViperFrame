package com.example.portalrutor;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

public class PortalRoutor {

    private static final String TAG = "PortalRouter";

    private static volatile PortalRoutor router;
    private HashMap<String, AbsAct> actions;
    private HashMap<String, Class> classHashMap;

    private PortalRoutor() {
        actions = new HashMap<>();
        classHashMap = new HashMap<>();
    }

    public static PortalRoutor initialize() {
        if (router == null) {
            synchronized (PortalRoutor.class) {
                if (router == null) {
                    router = new PortalRoutor();
                }
            }
        }
        return router;
    }

    public static PortalRoutor get() {
        if (router != null) {
            return router;
        } else {
            Log.i(TAG, "Router has not been initialized");
            return null;
        }
    }

    public PortalRoutor actionRegist(String action, AbsAct absAct) {
        if (router != null) {
            if (actions.containsKey(action)) {
                Log.i(TAG, "action has been registed");
            } else {
                actions.put(action, absAct);
            }
        }
        return router;
    }

    public PortalRouterResponse sendMessage(Context context, PortalRouterRequest request) {
        if (router != null) {
            PortalRouterResponse response = new PortalRouterResponse();
            AbsAct absAct = findRequest(request);
            if (absAct != null) {
                Object mObject = absAct.portal(context, request, classHashMap);
                response.setStatus(PortalRouterResponse.SUCCESS_CODE
                        , PortalRouterResponse.SUCCESS_DESC
                        , mObject);
            } else {
                response.setStatus(response.Fail_CODE
                        , response.Fail_DESC
                        , "can not find this action,check if you have been registered!");
            }
            return response;
        } else {
            Log.i(TAG, "Router has not been initialized");
            return null;
        }
    }

    private AbsAct findRequest(PortalRouterRequest request) {
        String action = request.getAction();
        if (actions.containsKey(action)) {
            return actions.get(action);
        }
        return null;
    }
}
