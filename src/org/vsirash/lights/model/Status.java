package org.vsirash.lights.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public class Status implements Serializable{
    private String sourceIP;
    private BuildStatus buildStatus;
    private String buildNumber;
    private String jobName;
    private Date date;
    private String requestLog;


    private static Status lastStatus;

    public static void setLastStatus(Status status){
        Status.lastStatus = status;
    }

    public Status(String sourceIP, BuildStatus buildStatus, String buildNumber, String jobName, Date date, String requestLog) {
        this.sourceIP = sourceIP;
        this.buildStatus = buildStatus;
        this.buildNumber = buildNumber;
        this.jobName = jobName;
        this.date = date;
        this.requestLog = requestLog;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public BuildStatus getBuildStatus() {
        return buildStatus;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public Date getDate() {
        return date;
    }

    public String getRequestLog() {
        return requestLog;
    }

    public static Status getLastStatus() {
        return lastStatus;
    }
}
