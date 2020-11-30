//package ryf;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//
//import javax.script.ScriptException;
//import java.util.HashMap;
//
///**
// * @Description
// * @ClassName draw
// * @Author L-Cohen
// * @date 2020.11.27 22:00
// */
//public class Draw extends Application {
//
//    public static void main(String[] args) {
//
//        launch(args);
//    }
//
////    @Override
////    public void start(Stage primaryStage) throws Exception {
////        BorderPane borderPane = new BorderPane();
////        Button button4 = new Button("确定");
////        button4.setOnAction(new EventHandler<ActionEvent>() {
////            @Override
////            public void handle(ActionEvent event) {
////                HashMap<String, Double> map = DichotomySceneReturn.inputArg();
////                if (map.get("a") != null && map.get("b") != null && map.get("min") != null) {
////                    Scene scene1 = null;
////                    try {
////                        scene1 = DichotomySceneReturn.returnScene("8*x*x-42*x+42", map.get("a"), map.get("b"), map.get("min"), map.get("max"));
////                    } catch (ScriptException e) {
////                        e.printStackTrace();
////                    }
////
////                    Stage stage = new Stage();
////                    stage.setScene(scene1);
////                    stage.show();
////
////                }
////
////            }
////        });
////        //Scene scene = NewtonSceneReturn.returnScene("8*x^2-42*x+42",1, 2, 0.0001, 100);
////        Scene scene = new Scene(button4);
////        primaryStage.setTitle("8*x*x-42*x+42");
////        primaryStage.setScene(scene);
////        primaryStage.setHeight(600);
////        primaryStage.setWidth(600);
////        primaryStage.show();
//    }
//}
