package org.vsirash.lights.notification;


import org.vsirash.lights.model.Status;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public interface NotificationPlugin {
    void notify(Status buildStatus);
}
