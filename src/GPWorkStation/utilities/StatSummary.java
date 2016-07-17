package GPWorkStation.utilities;

public class StatSummary {

    public String name; // defaults to ""
    private double sum;
    private double sumsq;
    private double min;
    private double max;
    private double mazes;
    private double ticks;
    private double tickssq;
    private double minTicks;
    private double maxTicks;

    private double mean;
    private double sd;
    private double meanTicks;
    private double sdTicks;

    // trick class loader into loading this now
    // private static StatisticalTests dummy = new StatisticalTests();
    int n;
    boolean valid;

    public StatSummary() {
        this("");
        // System.out.println("Exited default...");
    }

    public StatSummary(String name) {
        // System.out.println("Creating SS");
        this.name = name;
        n = 0;
        sum = 0;
        sumsq = 0;
        // ensure that the first number to be
        // added will fix up min and max to
        // be that number
        min = Double.POSITIVE_INFINITY;
        max = Double.NEGATIVE_INFINITY;
        minTicks = Double.POSITIVE_INFINITY;
        maxTicks = Double.NEGATIVE_INFINITY;
        mazes = 0;
        ticks = 0;
        tickssq = 0;
        // System.out.println("Finished Creating SS");
        valid = false;
    }

    public final void reset() {
        n = 0;
        sum = 0;
        sumsq = 0;
        // ensure that the first number to be
        // added will fix up min and max to
        // be that number
        min = Double.POSITIVE_INFINITY;
        max = Double.NEGATIVE_INFINITY;
        minTicks = Double.POSITIVE_INFINITY;
        maxTicks = Double.NEGATIVE_INFINITY;

        mazes = 0;
        ticks = 0;
        tickssq = 0;
    }

    public double max() {
        return max;
    }

    public double min() {
        return min;
    }

    public double mean() {
        if (!valid) {
            computeStats();
        }
        return mean;
    }

    // returns the sum of the squares of the differences
    //  between the mean and the ith values
    public double sumSquareDiff() {
        return sumsq - n * mean() * mean();
    }

    private void computeStats() {
        if (!valid) {
            mean = sum / n;
            meanTicks = ticks / n;
            double num = sumsq - (n * mean * mean);
            if (num < 0) {
                // avoids tiny negative numbers possible through imprecision
                num = 0;
            }
             System.out.println("Num = " + num);
            sd = Math.sqrt(num / (n - 1));

            num = tickssq - (n * meanTicks * meanTicks);
            if (num < 0) {
                // avoids tiny negative numbers possible through imprecision
                num = 0;
            }
            // System.out.println("Num = " + num);
            sdTicks = Math.sqrt(num / (n - 1));
            // System.out.println(" Test: sd = " + sd);
            // System.out.println(" Test: n = " + n);
            valid = true;
        }
    }

    public double sd() {
        if (!valid) {
            computeStats();
        }
        return sd;
    }

    public double sdTicks() {
        if (!valid) {
            computeStats();
        }
        return sdTicks;
    }

    public int n() {
        return n;
    }

    public double stdErr() {
        return sd() / Math.sqrt(n);
    }

    public double stdErrTicks() {
        return sdTicks() / Math.sqrt(n);
    }

    public void add(StatSummary ss) {
        // implications for Watch?
        n += ss.n;
        sum += ss.sum;
        sumsq += ss.sumsq;
        max = Math.max(max, ss.max);
        min = Math.min(min, ss.min);
        ticks = ss.ticks;
        tickssq = ss.tickssq;
        meanTicks = ss.meanTicks;
        maxTicks = ss.maxTicks;
        minTicks = ss.minTicks;
        sdTicks = ss.sdTicks;
        valid = false;
    }

    public void add(double d) {
        n++;
        sum += d;
        sumsq += d * d;
        min = Math.min(min, d);
        max = Math.max(max, d);
        valid = false;
    }

    public void add(Number n) {
        add(n.doubleValue());
    }

//    public void add(double[] d) {
//        for (int i = 0; i < d.length; i++) {
//            add(d[i]);
//        }
//    }
//
    public void add(double... xa) {
        for (double x : xa) {
            add(x);
        }
    }

    public double mazes() {
        return mazes / n;
    }

    public double ticks() {
        return ticks / n;
    }

    public double minTicks() {
        return minTicks;
    }

    public double maxTicks() {
        return maxTicks;
    }

    public void addMazes(int noOfMazes) {
        this.mazes += noOfMazes;
    }

    public void addTicks(int ticks) {
        this.ticks += ticks;
        minTicks = Math.min(minTicks, ticks);
        maxTicks = Math.max(maxTicks, ticks);
        tickssq += ticks * ticks;
        valid = false;
    }

    @Override
    public String toString() {
        String s = (name == null) ? "" : name + "\n";
        s += " n     = " + n + "\n"
                + " Score : " + "\n"
                + " min   = " + min() + "\n"
                + " max   = " + max() + "\n"
                + " ave   = " + mean() + "\n"
                + " sd    = " + sd() + "\n"
                + " se    = " + stdErr() + "\n"
                + //                " sum   = " + sum           + "\n" +
                //                " sumsq = " + sumsq         + "\n" +
                " levels= " + mazes() + "\n"
                + " Ticks : " + "\n"
                + " mean  = " + ticks() + "\n"
                + " min   = " + minTicks() + "\n"
                + " max   = " + maxTicks() + "\n"
                + " sd    = " + sdTicks() + "\n"
                + " se    = " + stdErrTicks() + "\n"
                + mean() + "\t" + min() + "\t" + max() + "\t" + sd() + "\t" + ticks() + "\t" + minTicks() + "\t" + maxTicks() + "\t" + sdTicks() + "\t" + mazes();

        return s;
    }
}
