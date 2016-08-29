/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
package org.vsirash.lights.servlets;

import org.vsirash.lights.NotificationWorker;
import org.vsirash.lights.model.Status;
import org.vsirash.lights.repositary.FileStatusRepositaryImpl;
import org.vsirash.lights.utils.StatusParserImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReceiverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("asda");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Status status = new StatusParserImpl().parseStatus(req);
            if(status!=null){
                Status.setLastStatus(status);
                new FileStatusRepositaryImpl().storeStatus(status);
                new Thread(new NotificationWorker(status)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
