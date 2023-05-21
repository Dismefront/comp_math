package dismefront.gui;

import dismefront.methods.Lagrange;
import dismefront.methods.Newton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;

public class Graph {

    private ArrayList<Double> xp, yp;
    private double lb, rb;

    public void updatePoints(ArrayList<Double> xp, ArrayList<Double> yp, double lb, double rb) {
        this.xp = xp;
        this.yp = yp;
        this.lb = lb;
        this.rb = rb;
    }

    private ChartPanel chartPanel;

    public void showGraph(JPanel panel) {

        XYSeries lagrangeSeries = new XYSeries("Lagrange");
        Lagrange lagrangeInt = new Lagrange(xp, yp);
        for (double x = lb; x <= rb; x += 0.1) {
            lagrangeSeries.add(x, lagrangeInt.interpolate(x));
        }

        XYSeries newtonSeries = new XYSeries("Newton");
        Newton newton = new Newton(xp, yp);
        for (double x = lb; x <= rb; x += 0.1)
            newtonSeries.add(x, newton.interpolate(x));

        XYSeriesCollection dataset = new XYSeriesCollection();
        if (!Double.isNaN(lagrangeInt.interpolate(1.0)))
            dataset.addSeries(lagrangeSeries);
        if (!Double.isNaN(newton.interpolate(1.0)))
            dataset.addSeries(newtonSeries);

        JFreeChart chart = ChartFactory.createXYLineChart("Interpolation", "X Axis", "Y Axis", dataset, PlotOrientation.VERTICAL, true, true, false);
        if (chartPanel != null) {
            panel.remove(chartPanel);
        }
        if (chartPanel != null)
            panel.remove(chartPanel);
        chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
        panel.revalidate();
        panel.repaint();

    }

}