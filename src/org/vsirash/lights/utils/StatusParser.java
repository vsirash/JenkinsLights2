package org.vsirash.lights.utils;

import org.vsirash.lights.model.Status;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public interface StatusParser {

    Status parseStatus(HttpServletRequest request) throws  Exception;
}
