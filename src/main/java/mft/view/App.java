package mft.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ManagementFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Info");
        primaryStage.show();
    }}