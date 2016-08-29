/**
 * Created by Volodymyr Sirash on 8/17/16.
 */
public class H3gpIOWrapper {
    static {
        System.loadLibrary("wiringPi");

        digitalWrite(0,0);
    }

    public static native void digitalWrite(int port, int value);
    public static native void pwmWrite(int port, int value);
    public native int digitalRead(int port);

}
