package utilities;

/**
 * Created by IntelliJ IDEA.
 * User: amalhe
 * Date: 14-May-2009
 * Time: 12:39:38
 * To change this template use File | Settings | File Templates.
 */
public class ElapsedTimer {
    long oldTime;
    long elapsedTime;

    public ElapsedTimer() {
        oldTime = System.currentTimeMillis();
    }

    public long elapsed() {
        elapsedTime = System.currentTimeMillis() - oldTime;
        return elapsedTime;
    }

    public void reset() {
        oldTime = System.currentTimeMillis();
    }

    public String toString() {
        long x=elapsedTime;

        long h;
        long m;
        double s;
        long temp;

        h = x / (60*60*1000);
        temp = x % (60*60*1000);

        m = temp / (60*1000);
        temp = temp % (60*1000);

        s = (double)temp / 1000;
        return "The elapsed time is : "+h+":"+m+":"+s;
    }
}
