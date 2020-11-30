//package inversequadratic;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartFrame;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.labels.StandardXYItemLabelGenerator;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
//
//import javax.swing.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class Main {
//    public static void main(String[] args) {
//
//        String input = "x*x*x-2";
//        double a = 1;
//        double b = 2;
//        double c = 3;
//        double result;
//
//        InverseqQuadratic quadratic = new InverseqQuadratic(input);
//        try {
//            ArrayList<HashMap> cal = quadratic.cal(a, b, c);
//            result = (double) cal.get(cal.size() - 1).get("x");
//            for (HashMap i : cal) {
//                System.out.println("第" + i.get("k") + "次迭代 : " + "" + "a=" + i.get("a") + ",b=" + i.get("b") + ",c=" + i.get("c") + ",x=" + i.get("x"));
//            }
//            System.out.println("结果为:" + result);
//            DrawUtil.drawChart(quadratic,result);
//
//        } catch (Exception e) {
//            System.out.println("无法求根，请重新输入");
//        }
//    }
//}
