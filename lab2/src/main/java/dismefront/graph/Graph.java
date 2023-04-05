package dismefront.graph;

import dismefront.functions.Function;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Graph {
    public static void drawGraph(Function[] functions, double xMin, double xMax, double yMin, double yMax) {
        // Create a dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (Function f : functions) {
            XYSeries series = new XYSeries(f.toString());
            for (double x = xMin; x <= xMax; x += 0.1) {
                series.add(x, f.f(x));
            }
            dataset.addSeries(series);
        }

        // Create a chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph",
                "x",
                "y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Customize the plot
        XYPlot plot = chart.getXYPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        plot.getDomainAxis().setRange(xMin, xMax);
        plot.getRangeAxis().setRange(yMin, yMax);

        // Create a panel to display the chart
        ChartPanel chartPanel = new ChartPanel(chart);

        // Display the panel
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}