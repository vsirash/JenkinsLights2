package org.vsirash.lights.notification;

import org.vsirash.lights.common.Config;
import org.vsirash.lights.model.Status;
import org.vsirash.lights.notification.config.SoundNotificationPluginConfig;
import org.vsirash.lights.utils.Sound;

import java.io.File;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public class SoundNotificationPlugin implements NotificationPlugin{

    private static SoundNotificationPluginConfig config = SoundNotificationPluginConfig.getInstance();

    @Override
    public void notify(Status buildStatus) {
        for(int index=0;index<config.getRepeatsCount();index++) {
            switch (buildStatus.getBuildStatus()) {
                case SUCCESS:
                    Sound.playSound(Config.soundsLocation + File.separator + config.getSuccessFileName());
                    break;
                case WARNING:
                    Sound.playSound(Config.soundsLocation + File.separator + config.getWarningFileName());
                    break;
                case FAILURE:
                    Sound.playSound(Config.soundsLocation + File.separator + config.getFailureFileName());
                    break;
                default:
                    System.out.println("UNKNOWN BUILD STATUS: " + buildStatus.getBuildStatus().name());
            }
        }
    }
}
