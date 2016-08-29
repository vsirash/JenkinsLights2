package org.vsirash.lights.notification;

import org.vsirash.lights.model.Status;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public class LightsNotificationplugin implements NotificationPlugin {
    @Override
    public void notify(Status buildStatus) {
        System.out.println("Lights Not implemented");
    }
}
