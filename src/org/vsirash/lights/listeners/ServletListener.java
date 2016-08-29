package org.vsirash.lights.listeners;


import org.vsirash.lights.NotificationWorker;
import org.vsirash.lights.common.Config;
import org.vsirash.lights.common.Registry;
import org.vsirash.lights.notification.NotificationPlugin;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public class ServletListener implements ServletContextListener {

    private void createDefaultConfigs(File configsDir){
        configsDir.mkdirs();


       /* Annotation[] an =  ps.getAnnotations();
        for(Annotation annotation:an){
            System.out.println("");
        }*/
    }


    private void setupNotificationPlugin(String className){
        className = className.replaceAll("Config","");//Getting plugin class name;
        try {
            Registry.getInstance().addNotificationPluginClass(Class.forName(className));
            NotificationWorker.addPlugin((NotificationPlugin) Class.forName(className).newInstance());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void readConfigs(File configsDir){
        File[] configFiles = configsDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".xml");//accepting only xml files
            }
        });
        for(File configFile:configFiles){
            String className = configFile.getName().replaceAll("\\.xml","");
            try {

                Class.forName("org.vsirash.lights.notification.config."+className).getMethod("readConfig",File.class).invoke(null,configFile);

                setupNotificationPlugin("org.vsirash.lights.notification."+className);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void readPluginConfigs(ServletContext context){
        File configsDir = new File(context.getRealPath("/")+"/config");
        if(!configsDir.exists()){
            createDefaultConfigs(configsDir);
        } else {
            readConfigs(configsDir);
        }


    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //new SoundNotificationPluginConfig().writeConfig(new File("/home/volodymyr/configg.xml"));
        Config.logsLocation = new File(servletContextEvent.getServletContext().getRealPath("/")+"/logs");
        if(!Config.logsLocation.exists()){
            Config.logsLocation.mkdirs();
        }
        Config.soundsLocation = servletContextEvent.getServletContext().getRealPath("/")+"/sounds";

        Config.nativeLibsLocation = servletContextEvent.getServletContext().getRealPath("/")+"/WEB-INF/classes";
        System.setProperty("java.library.path",Config.nativeLibsLocation);

        /*H3gpIOWrapper.digitalWrite(0,0);
        new H3gpIOWrapper().digitalRead(0);//TODO: remove*/

        readPluginConfigs(servletContextEvent.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
