package dismefront.gui;

import java.util.ArrayList;
import java.util.List;

import dismefront.logic.*;
import dismefront.methods.Exponential;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {

    LinearEquation linearEquation;
    Polynomial2DEquation polynomial2DEquation;
    Polynomial3DEquation polynomial3DEquation;
    ExponentialEquation exponentialEquation;
    LogarithmicEquation logarithmicEquation;
    PowerEquation powerEquation;

    public Graph(LinearEquation linearEquation,
                 Polynomial2DEquation polynomial2DEquation,
                 Polynomial3DEquation polynomial3DEquation,
                 ExponentialEquation exponentialEquation,
                 LogarithmicEquation logarithmicEquation,
                 PowerEquation powerEquation) {
        this.linearEquation = linearEquation;
        this.polynomial2DEquation = polynomial2DEquation;
        this.polynomial3DEquation = polynomial3DEquation;
        this.exponentialEquation = exponentialEquation;
        this.logarithmicEquation = logarithmicEquation;
        this.powerEquation = powerEquation;
    }

    public void main() {

        XYSeries linSeries = new XYSeries(linearEquation.what());
        for (double x = -3; x <= 3; x += 0.1) {
            linSeries.add(x, linearEquation.apply(x));
        }

        XYSeries poly2DSeries = new XYSeries(polynomial2DEquation.what());
        for (double x = -2; x <= 2; x += 0.1) {
            poly2DSeries.add(x, polynomial2DEquation.apply(x));
        }

        XYSeries poly3DSeries = new XYSeries(polynomial3DEquation.what());
        for (double x = -1; x <= 2; x += 0.1) {
            poly3DSeries.add(x, polynomial3DEquation.apply(x));
        }
//
        XYSeries exponentialSeries = new XYSeries(exponentialEquation.what());
        for (double x = -3; x <= 2; x += 0.1) {
            exponentialSeries.add(x, exponentialEquation.apply(x));
        }

        XYSeries logSeries = new XYSeries(logarithmicEquation.what());
        for (double x = 1.0001; x <= 3; x += 0.1) {
            logSeries.add(x, logarithmicEquation.apply(x));
        }

        XYSeries pwSeries = new XYSeries(powerEquation.what());
        for (double x = -3; x <= 3; x += 0.1) {
            pwSeries.add(x, powerEquation.apply(x));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        if (!Double.isNaN(linearEquation.apply(1.0)))
            dataset.addSeries(linSeries);
        if (!Double.isNaN(polynomial2DEquation.apply(1.0)))
            dataset.addSeries(poly2DSeries);
        if (!Double.isNaN(polynomial3DEquation.apply(1.0)))
            dataset.addSeries(poly3DSeries);
        if (!Double.isNaN(exponentialEquation.apply(1.0)))
            dataset.addSeries(exponentialSeries);
        if (!Double.isNaN(logarithmicEquation.apply(1.0)))
            dataset.addSeries(logSeries);
        if (!Double.isNaN(powerEquation.apply(1.0)))
            dataset.addSeries(pwSeries);

        ChartFrame frame = new ChartFrame("Graph", ChartFactory.createXYLineChart(
                "Approximation", "X Axis", "Y Axis", dataset, PlotOrientation.VERTICAL, true, true, false));
        frame.pack();
        frame.setVisible(true);
    }

}