package com.dismefront.lab6spring.Charts;

import com.dismefront.lab6spring.Function;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart {
    public JFreeChart drawChart(double[][] result, int numberOfFunction) {
        Function function = new Function();
        XYSeries series = new XYSeries("Вычисленное");
        XYSeries series1 = new XYSeries("Точное");

        for (int i = 0; i < result.length; i++) {
            //if (Math.abs(result[i][0]) < 100 && Math.abs(result[i][1]) < 100)
            series.add(result[i][0], result[i][1]);
        }

        for (int i = 0; i < result.length; i++) {
            //if (Double.isFinite(function.f1(result[i][0], result[i][1], numberOfFunction)) &&
            //        Math.abs(function.f1(result[i][0], result[i][1], numberOfFunction)) < 100)

            series1.add(result[i][0], function.f1(result[i][0], result[i][1], numberOfFunction));
        }


        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series);
        dataset.addSeries(series1);

        JFreeChart lineChart = ChartFactory.createXYLineChart("Fi(x)", "x",
                "Y", dataset, PlotOrientation.VERTICAL,
                true, true, false);

        XYPlot plot = lineChart.getXYPlot();
        XYSplineRenderer renderer = new XYSplineRenderer();
        renderer.setPrecision(8);
        plot.setRenderer(renderer);


        plot.setDataset(dataset);


        renderer.setSeriesShapesVisible(0, false); // 0 - индекс серии данных

        return lineChart;
    }
}
