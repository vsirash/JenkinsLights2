package org.vsirash.lights.common;

import org.vsirash.lights.notification.NotificationPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Volodymyr Sirash on 8/16/16.
 */
public class Registry {
    private Registry(){}

    private List<Class<?>> availablePlugins = new ArrayList<>();

    private static Registry instance = new Registry();

    public static Registry getInstance() {
        return instance;
    }

    public void addNotificationPluginClass(Class<?> clazz){
        if(!availablePlugins.contains(clazz)){
            availablePlugins.add(clazz);
        } else {
            System.out.println("Plugin '"+clazz.getSimpleName()+"' already added to the registry");
        }
    }

    public List<Class<?>> getAvailablePlugins() {
        List<Class<?>> resultList = new ArrayList<>();
        Collections.copy(resultList,availablePlugins);
        return resultList;
    }
}
