package mft;

import lombok.extern.log4j.Log4j;
import mft.Controller.ManagementController;
import mft.model.entity.Management;

@Log4j
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        Management management=new Management();

        ManagementController.getController().save("sddfsdfsd","QWERTY","highpoint");
    }
}