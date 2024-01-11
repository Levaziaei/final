package mft;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import lombok.extern.log4j.Log4j;
import mft.Controller.BookController;
import mft.Controller.BorrowController;
import mft.Controller.ManagementController;
import mft.model.entity.Management;

@Log4j
public class Main {
    public static void main(String[] args) throws Exception {

      //  System.out.println("Hello world!");
    //  Management management=new Management();
     //  ManagementController.getController().findByUsernameAndPassword("Levaziaee","Levaziaee123");
        BorrowController.getController().save("Levaziaee","Xcsd","Mos");
 // ManagementController.getController().save("Levaziaei","Levaziaee","123lerbyfsqq");
//BookController.getController().save(1,"Ghanoon","Albert");
   //  System.out.println(BookController.getController().findAll());
       //  AccountStudentController.getController().save(1,"Levaziaee","9Ghanoon");
       // System.out.println(BookController.getController().findByNameBook("Ghanoon"));
       // System.out.println(BookController.getController().findByNameBook("Xcsd"));
//BookController.getController().save(2,"Xcsd","Mos");
//        System.out.println(BookController.getController().findByAuthor("Mos"));

    }
}