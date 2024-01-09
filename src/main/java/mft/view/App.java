package mft.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
  Scene scene = new Scene(FXMLLoader
.load(getClass().getResource("ManagementFrame.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Management Info");
        primaryStage.show();
      /**    Scene scene1 = new Scene(FXMLLoader
        .load(getClass().getResource("AccountStudentFrame.fxml")));
        primaryStage.setScene(scene1);
  primaryStage.setTitle("Login");
  primaryStage.show();
  Scene scene2 = new Scene(FXMLLoader
                .load(getClass().getResource("LibrarianFrame.fxml")));
        primaryStage.setScene(scene2);
        primaryStage.setTitle("Login");
        primaryStage.show();
        Scene scene3 = new Scene(FXMLLoader.load(getClass().getResource("AccountStaffFrame.fxml")));
        primaryStage.setScene(scene3);
        primaryStage.setTitle("Login");
     primaryStage.show();
*/
        }}
