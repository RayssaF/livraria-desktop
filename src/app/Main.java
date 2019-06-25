package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/tela_principal.fxml"));
        primaryStage.setScene(new Scene(root));
        root.setOnMousePressed(event -> {

        } );
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
