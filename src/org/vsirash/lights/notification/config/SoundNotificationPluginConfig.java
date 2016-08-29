package org.vsirash.lights.notification.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */

@XmlRootElement
public class SoundNotificationPluginConfig {

    private String failureFileName;
    private String successFileName;
    private String warningFileName;
    private int repeatsCount;

    private SoundNotificationPluginConfig(){}
    private static SoundNotificationPluginConfig instance;


    public static SoundNotificationPluginConfig getInstance() {
        return instance;
    }

    public static void readConfig(File configFile){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SoundNotificationPluginConfig.class);
            Unmarshaller jaxbUnmarshaller  = jaxbContext.createUnmarshaller();
            instance = (SoundNotificationPluginConfig) jaxbUnmarshaller.unmarshal(configFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void writeConfig(File configFile){
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(SoundNotificationPluginConfig.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, configFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }



    }
    @XmlElement
    public String getFailureFileName() {
        return failureFileName;
    }

    public void setFailureFileName(String failureFileName) {
        this.failureFileName = failureFileName;
    }
    @XmlElement
    public String getSuccessFileName() {
        return successFileName;
    }

    public void setSuccessFileName(String successFileName) {
        this.successFileName = successFileName;
    }
    @XmlElement
    public String getWarningFileName() {
        return warningFileName;
    }

    public void setWarningFileName(String warningFileName) {
        this.warningFileName = warningFileName;
    }
    @XmlElement
    public int getRepeatsCount() {
        return repeatsCount;
    }

    public void setRepeatsCount(int repeatsCount) {
        this.repeatsCount = repeatsCount;
    }
}
