package org.vsirash.lights.repositary;

import org.vsirash.lights.common.Config;
import org.vsirash.lights.model.Status;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public class FileStatusRepositaryImpl implements StatusRepositary {
    @Override
    public void storeStatus(Status status) {
        FileOutputStream fos = null;
        try {
            File logFile = new File(Config.logsLocation+File.separator+String.format(Config.buildLogsFormat,new Date()));
            logFile.createNewFile();
            fos = new FileOutputStream(logFile);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(status);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Status getStatusByFileName(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Status status = (Status) ois.readObject();
            return status;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Status> getStatusesByDateRange(final Date startDate, final Date endDate) {

        File[] filesList = Config.logsLocation.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.lastModified()>startDate.getTime()&&pathname.lastModified()<endDate.getTime();
            }
        });

        List<Status> statusList = new ArrayList<>();
        for(File file:filesList){
            Status status = getStatusByFileName(file);
            if(status!=null){
                statusList.add(status);
            }
        }
        return statusList;
    }
}
