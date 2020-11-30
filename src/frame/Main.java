package frame;

import inversequadratic.InputFormat;
import inversequadratic.InverseqQuadratic;
import inversequadratic.ResultObj;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jdk.internal.util.xml.impl.Input;
import ryf.DichotomySceneReturn;
import ryf.NewtonSceneReturn;

import javax.script.ScriptException;
import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Main  extends Application {
    String input=null;
    ResultObj obj ;
    Stage primaryStage;
    MenuBar menuBar = new MenuBar();
    @Override
    public void start(Stage primaryStage) throws Exception {

        showMainScence(primaryStage);
    }

    private void showMainScence(Stage primaryStage){
        BorderPane borderPane = new BorderPane();
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(15);
        TextField textField  =new TextField();
        textField.setMaxWidth(200);
        textField.setPromptText("请输入函数：");
        Button button1 = new Button("二分法");
        button1.setPrefWidth(200);
        button1.setMinHeight(50);

        Button button2 = new Button("牛顿法");
        button2.setPrefWidth(200);
        button2.setMinHeight(50);

        Button button3 = new Button("割线法");
        button3.setPrefWidth(200);
        button3.setMinHeight(50);
        Button button4 = new Button("逆二次插值");
        button4.setPrefWidth(200);
        button4.setMinHeight(50);
        button4.setPadding(new Insets(10,10,10,10));

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( textField.getText().trim().equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("信息");
                    alert.headerTextProperty().set("请先输入函数");
                    alert.showAndWait();
                    showMainScence(primaryStage);
                }else{
                    input = textField.getText();
                    String format = InputFormat.format(input);
                    System.out.println(input);
                    HashMap<String, Double> map = DichotomySceneReturn.inputArg();
                    if (map.get("a") != null && map.get("b") != null && map.get("min") != null) {
                        Scene scene1 = null;
                        try {
                            scene1 = DichotomySceneReturn.returnScene(format, map.get("a"), map.get("b"), map.get("min"), map.get("max"));
                        } catch (ScriptException e) {
                            e.printStackTrace();
                        }

                        Stage stage = new Stage();
                        stage.setScene(scene1);
                        stage.show();

                    }else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.titleProperty().set("信息");
                        alert.headerTextProperty().set("请检查输入");
                        alert.showAndWait();
                        showMainScence(primaryStage);
                    }
                }
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( textField.getText().trim().equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("信息");
                    alert.headerTextProperty().set("请先输入函数");
                    alert.showAndWait();
                    showMainScence(primaryStage);
                }else{
                    input = textField.getText();
                    String format = InputFormat.format(input);
                    System.out.println(input);
                    HashMap<String, Double> map = NewtonSceneReturn.inputArg();
                    if (map.get("a") != null && map.get("b") != null && map.get("min") != null) {
                        Scene scene1 = null;
                        try {
                            scene1 = NewtonSceneReturn.returnScene(format, map.get("a"), map.get("b"), map.get("min"), map.get("max"));
                        } catch (ScriptException e) {
                            e.printStackTrace();
                        }

                        Stage stage = new Stage();
                        stage.setScene(scene1);
                        stage.show();

                    }else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.titleProperty().set("信息");
                        alert.headerTextProperty().set("请检查输入");
                        alert.showAndWait();
                        showMainScence(primaryStage);
                    }
                }
            }
        });


        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( textField.getText().trim().equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("信息");
                    alert.headerTextProperty().set("请先输入函数");
                    alert.showAndWait();
                    showMainScence(primaryStage);
                }else{
                    input = textField.getText();
                    System.out.println(input);
                    HashMap<String, Double> map = inputArg();
                    if(map.get("a") != null&&map.get("b")!=null&& map.get("c")!=null){
                        Scene scene1 = createNewScence(input, map.get("a"), map.get("b"), map.get("c"));
                        if(scene1==null){
                            showMainScence(primaryStage);
                        }
                        Stage stage = new Stage();
                        stage.setScene(scene1);
                        stage.show();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.titleProperty().set("信息");
                        alert.headerTextProperty().set("请检查输入");
                        alert.showAndWait();
                        showMainScence(primaryStage);
                    }
                }
            }
        });
        box.getChildren().addAll(textField,button1,button2,button3,button4);
        borderPane.setCenter(box);
        Scene scene = new Scene(borderPane,800,600);
//        borderPane.setTop(menuBar);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene createNewScence(String input,double a,double b,double c){
        initMenu();
        String formatInput ;
        Scene scene = null;
        InverseqQuadratic quadratic;
        try {
            formatInput = InputFormat.format(input);
            quadratic =new InverseqQuadratic(formatInput);
            ResultObj res = calculate(formatInput, a, b, c);
            obj = new ResultObj();
            obj.setResult(res.getResult());
            obj.setProcess(res.getProcess());

            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();

            final LineChart<Number,Number> lineChart =
                    new LineChart<Number,Number>(xAxis,yAxis);

            lineChart.setTitle(input);
            lineChart.setCreateSymbols(false);
            //defining a series
            XYChart.Series series = new XYChart.Series();
            series.setName("f(x)");
            int N  = (int) (res.getResult()+5);
            for (double x = -N; x < N; x+=0.1) {
                double f = quadratic.f(x);
                series.getData().add(new XYChart.Data(x, f));
            }

            XYChart.Series xSeries = new XYChart.Series();
            xSeries.setName("x");


            xSeries.getData().add(new XYChart.Data(0,0));

            XYChart.Series ySeries = new XYChart.Series();
            ySeries.setName("y");
            ySeries.getData().add(new XYChart.Data(-N,0));
            ySeries.getData().add(new XYChart.Data(0,0));
            lineChart.getData().add(series);


            final NumberAxis axis = (NumberAxis) lineChart.getXAxis();
            final double lowerX = axis.getLowerBound();
            final double upperX = axis.getUpperBound();
            lineChart.setOnScroll(new EventHandler<ScrollEvent>() {

                @Override
                public void handle(ScrollEvent event) {
                    final double minX = axis.getLowerBound();
                    final double maxX = axis.getUpperBound();
                    double threshold = minX + (maxX - minX) / 2d;
                    double x = event.getX();
                    double value = axis.getValueForDisplay(x).doubleValue();
                    double direction = event.getDeltaY();
                    if (direction > 0) {
                        if (maxX - minX <= 1) {
                            return;
                        }
                        if (value < threshold) {
                            axis.setLowerBound(minX + 1);
                        } else {
                            axis.setUpperBound(maxX - 1);
                        }
                    } else {
                        if (value < threshold) {
                            double nextBound = Math.max(lowerX, minX - 1);
                            axis.setLowerBound(nextBound);
                        } else {
                            double nextBound = Math.min(upperX, maxX + 1);
                            axis.setUpperBound(nextBound);
                        }
                    }

                }
            });


            BorderPane borderPane = new BorderPane();
            HBox box = new HBox();

            TextField aText = new TextField(String.valueOf(a));
            TextField bText = new TextField(String.valueOf(b));
            TextField cText= new TextField(String.valueOf(c));
            Button button = new Button("刷新");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });
            box.getChildren().addAll(new Text("a:"),aText,new Text("b:"),bText,new Text("c:"),cText,button);
            box.setPadding(new Insets(20));

            box.setSpacing(10);

            TextArea textArea = new TextArea();
            borderPane.setCenter(lineChart);
            borderPane.setBottom(textArea);

            borderPane.setTop(menuBar);
            showData(obj,textArea);
            textArea.setPadding(new Insets(10,10,10,10));
            textArea.setStyle("-fx-font-weight:bold");

            textArea.setStyle("-fx-font-family: 'Arial Rounded MT Bold'");
            textArea.setStyle("-fx-font-size: 32");
            textArea.setStyle("-fx-text-fill: #0080ff");
             scene = new Scene(borderPane, 800, 600);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("信息");
            alert.headerTextProperty().set("无法求根，请检查输入");
            alert.showAndWait();
            Stage stage = new Stage();
            showMainScence(stage);
            System.out.println("无法求根，请重新输入");
        }

            return scene;
    }

    public  ResultObj calculate(String input,double a ,double b, double c){
        ResultObj object = new ResultObj();
        InverseqQuadratic quadratic = new InverseqQuadratic(input);
        try {
            ArrayList<HashMap> cal = quadratic.cal(a, b, c);
            double result = (double) cal.get(cal.size() - 1).get("x");
            object.setProcess(cal);
            object.setResult(result);
        }catch (Exception e){

            showMainScence(primaryStage);
            e.printStackTrace();
        }
        return object;
    }
    public void  showData(ResultObj obj,TextArea textArea){

        double result1 = obj.getResult();
        ArrayList<HashMap> process = obj.getProcess();
        for (HashMap i : process) {
            textArea.appendText("第" + i.get("k") + "次迭代 : " + "" + "a=" + i.get("a") + ",b=" + i.get("b") + ",c=" + i.get("c") + ",x=" + i.get("x")+"\n");
        }
        textArea.appendText("结果为"+result1+"\n");



    }
    private HashMap<String, Double> inputArg ()
    {
        // 对话框内容
        VBox content = new VBox();
        TextField a = new TextField();
        TextField b = new TextField();
        TextField c = new TextField();
        a.setPromptText("a");
        b.setPromptText("b");
        c.setPromptText("c");
        content.setSpacing(10);
        content.getChildren().addAll(a, b, c);

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
            res.put("a",aVal);
            res.put("b",bVal);
            res.put("c",cVal);

        }
        return res;

    }
    private void initMenu()
    {
        Menu menuFile = new Menu("设置");
        Menu menuHelp = new Menu("帮助");
        if(menuBar.getMenus().size()<2){
            menuBar.getMenus().addAll(menuFile, menuHelp);

    }



        MenuItem menuItemOpen = new MenuItem("主界面");

        MenuItem menuItemSave = new MenuItem("刷新");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem menuItemExit = new MenuItem("退出程序");

        menuFile.getItems().addAll(menuItemOpen, menuItemSave, separator, menuItemExit);



        menuItemOpen.setOnAction((ActionEvent e)->{

            Stage stage = new Stage();
          showMainScence(stage);



        });

        menuItemSave.setOnAction((ActionEvent e)->{


        });

        menuItemExit.setOnAction((ActionEvent e)->{
            Platform.exit();
        });
    }

}
