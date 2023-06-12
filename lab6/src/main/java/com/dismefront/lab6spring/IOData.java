package com.dismefront.lab6spring;


import com.dismefront.lab6spring.Charts.UploadChart;
import com.dismefront.lab6spring.Methods.EulerMethod;
import com.dismefront.lab6spring.Methods.RungeMethod;
import com.dismefront.lab6spring.Methods.AdamsMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IOData {

    @GetMapping("/")
    public String welcomePage(Model model) {
        model.addAttribute("form", new Form());
        return "index";
    }

    @GetMapping("/resultPage")
    public String outTable(@ModelAttribute Form form, Model model) {
        EulerMethod eulerMethod = new EulerMethod();
        RungeMethod rungeMethod = new RungeMethod();
        AdamsMethod adamsMethod = new AdamsMethod();
        int n = (int) (Math.abs(form.getB() - form.getA()) / form.getH()) + 1;
//        double[][] result = new double[n][4];
        if (form.getA() >= form.getB())
            return "error";
        if (form.getE() <= 0)
            return "error2";

        if (form.getMethod() == 1) {
            double[][] result = eulerMethod.method(form.getA(), form.getB(), form.getY0(), form.getH(),
                    form.getNumberOfFunction(), form.getH());
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    if (Double.isNaN(result[i][j]) || Double.isInfinite(result[i][j])) {
                        result[i][j] = result[i - 1][j];
                    }
                }
            }
            UploadChart.result = result;
            model.addAttribute("result", result);
        } else if (form.getMethod() == 2) {
            double[][] result = rungeMethod.method(form.getA(), form.getB(), form.getY0(), form.getH(),
                    form.getNumberOfFunction(), form.getE());
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    if (Double.isNaN(result[i][j]) || Double.isInfinite(result[i][j])) {
                        result[i][j] = result[i - 1][j];
                    }
                }
            }
            model.addAttribute("result", result);
            UploadChart.result = result;
        } else if (form.getMethod() == 3) {
            double[][] result = adamsMethod.method(form.getA(), form.getB(), form.getY0(), form.getH(),
                    form.getNumberOfFunction(), form.getE());
            UploadChart.result = result;
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    if (Double.isNaN(result[i][j]) || Double.isInfinite(result[i][j])) {
                        result[i][j] = result[i - 1][j];
                    }
                }
            }
            model.addAttribute("result", result);
        }


        UploadChart.numberOfFunction = form.getNumberOfFunction();


        return "result";
    }
}
