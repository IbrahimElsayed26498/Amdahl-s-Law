package amdalslaw;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class AmdalsLaw {
    static XYChart chart;
    static double[] xData;
    static int r;
    static double qr;
    static double s;
    final static int ARRAY_SIZE = 16;
    public static void main(String[] args) {
        xData = new double[ARRAY_SIZE];
        double[] yData = new double[ARRAY_SIZE];
        
        int n = 16; 
        double f = 0.975;
        
        
        for (int i = 1; i <= ARRAY_SIZE; i++) {
            r = i;
            qr = Math.sqrt(r);
            xData[i-1] = r;
            if ((n/r)-Math.floor(n/r) != 0) {
                continue;
            }
            s = 1 / (((1-f)/qr) + ((f*r) / (qr*n)));
            yData[i-1] = s;
        }
        

        // Create Chart
        chart = QuickChart.getChart("n = " + n, "r BCEs", 
                "Speedup (symmetric)", "f = " + f, xData, yData);
        
        // add add curves
        f = 0.9;
        addChart("f = " + f , n, f);
        
        f = 0.5;
        addChart("f = " + f , n, f);
        
        // Show it
        new SwingWrapper(chart).displayChart();
    }
    
    public static void addChart(String seriesName, int n, double f){
        double[] yData = new double[ARRAY_SIZE];
        for (int i = 1; i <= ARRAY_SIZE; i++) {
            r = i;
            qr = Math.sqrt(r);
            s = 1 / (((1-f)/qr) + (f*r) / (qr*n));
            yData[i-1] = s;
        }
        chart.addSeries(seriesName, xData, yData);
    }
}
