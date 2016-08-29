package org.vsirash.lights.repositary;

import org.vsirash.lights.model.Status;

import java.util.Date;
import java.util.List;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public interface StatusRepositary {

    void storeStatus(Status status);
    List<Status> getStatusesByDateRange(Date startDate, Date endDate);
}
