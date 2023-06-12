package com.dismefront.lab6spring.Charts;

import com.dismefront.lab6spring.Form;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.OutputStream;

import static org.jfree.chart.ChartUtils.writeChartAsJPEG;

@Controller
public class UploadChart {


    public static double[][] result;
    public static int numberOfFunction;

    @GetMapping("/chart")
    public void handleChart(HttpServletResponse response, Form form, Model model) throws IOException {
        response.setContentType("image/jpeg");
        model.addAttribute("form", form);
        OutputStream out = response.getOutputStream();
        Chart drawChart = new Chart();

        writeChartAsJPEG(out, drawChart.drawChart(result, numberOfFunction), 600, 600);


    }
}
