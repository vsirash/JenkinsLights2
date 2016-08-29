package org.vsirash.lights;

import org.vsirash.lights.model.BuildStatus;
import org.vsirash.lights.model.Status;
import org.vsirash.lights.notification.NotificationPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public class NotificationWorker implements Runnable{

    private Status status;

    public NotificationWorker(Status status){
        this.status = status;
    }

    @Override
    public void run() {
        for(NotificationPlugin plugin:notificationPlugins){
            plugin.notify(status);
        }
    }

    public static void addPlugin(NotificationPlugin plugin){
        notificationPlugins.add(plugin);
    }

    public static void removePlugin(Class<NotificationPlugin> clazz){
        for(NotificationPlugin plugin:notificationPlugins){
            if(plugin.getClass() == clazz){
                notificationPlugins.remove(plugin);
            }
        }
    }
    private static List<NotificationPlugin> notificationPlugins = new ArrayList<>();
}
