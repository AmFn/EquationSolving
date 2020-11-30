package ryf;

import inversequadratic.Fun;
import inversequadratic.ResultObj;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * @Description
 * @ClassName Sence
 * @Author L-Cohen
 * @date 2020.11.27 20:12
 */
public class NewtonSceneReturn {


    public static HashMap<String, Double> inputArg ()
    {
        // 对话框内容
        VBox content = new VBox();
        TextField a = new TextField();
        TextField b = new TextField();
        TextField c = new TextField();
        TextField d = new TextField();
        a.setPromptText("a");
        b.setPromptText("b");
        c.setPromptText("min");
        d.setPromptText("max");
        content.setSpacing(10);
        content.getChildren().addAll(a, b, c,d);

        // Dialog -> DialogPane -> Root Node
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(content);

        // 添加按钮
        ButtonType ok = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(ok);

        // 创建对话框
        Dialog<ButtonType> dlg = new Dialog<ButtonType>();
        dlg.setDialogPane(dialogPane);
        dlg.setTitle("输入参数");

        // 显示对话框, 并接收返回结果
        Optional<ButtonType> result =  dlg.showAndWait();
        HashMap<String,Double> res = new HashMap<>();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)
        {
            double aVal = Double.valueOf( a.getText() );
            double bVal = Double.valueOf( b.getText() );;
            double cVal = Double.valueOf( c.getText() );;
            double dVal = Double.valueOf( d.getText() );
            res.put("a",aVal);
            res.put("b",bVal);
            res.put("min",cVal);
            res.put("max",dVal);

        }
        return res;

    }

    public NewtonSceneReturn() {
    }

    public static Scene returnScene(String str, double a , double b, double min, double max) throws ScriptException {
        double x = -10;
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("f(x)");

        while (x < 10) {
            dataSeries1.getData().add(new XYChart.Data(x, Fun.fun(str, x)));
            x += 0.1;
        }

        lineChart.getData().add(dataSeries1);
        lineChart.setCreateSymbols(false);

        TextArea textArea = new TextArea();
        Newton newton = new Newton(str);

        ArrayList<HashMap> process = newton.calculate(a, min , (int) max);
        double result = (double) process.get(process.size()-1).get("x");
        ResultObj resultObj = new ResultObj(process, result);
        for (int i = 0; i < process.size(); i++){
            textArea.appendText(process.get(i).toString() + "\n");

        }
        textArea.appendText("结果" + String.valueOf(result));

        VBox vbox = new VBox(lineChart,textArea);

        Scene scene = new Scene(vbox, 800, 600);
        return scene;
    }
}

