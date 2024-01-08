package mft;

import lombok.extern.log4j.Log4j;
import mft.Controller.AccountStudentController;
import mft.Controller.BookController;
import mft.Controller.ManagementController;
import mft.model.entity.Management;
import mft.model.entity.UserType;

@Log4j
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        Management management=new Management();

      //  ManagementController.getController().save("Levaziaei","Levaziaee","123lerbyfsqq", userTypeCmb.getValue(), UserType.valueOf("Student"));
      //  AccountStudentController.getController().save(1,"Levaziaee","9Ghanoon");
        BookController.getController().save(2,"Ghanoon","Tozih");
    }
}