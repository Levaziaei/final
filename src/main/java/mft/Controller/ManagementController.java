package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Management;
import mft.model.entity.UserType;
import mft.model.service.ManagementService;

import java.util.regex.Pattern;
@Log4j
public class ManagementController {
    private static ManagementController controller = new ManagementController();

    private ManagementController() {
    }

    public static ManagementController getController() {
        return controller;
    }


    public Management save(String username, String nameAndFamily, String password, String value, UserType userType) throws Exception {
        if (Pattern.matches("^[a-z\\d\\S\\._]{3,30}$", username) &&
                (Pattern.matches("^[a-zA-Z]+$", nameAndFamily) &
                        (Pattern.matches("^[\\w\\S]{5,30}$", password)))) {

            Management management =
                    Management
                            .builder()
                            .username(username)
                            .nameAndFamily(nameAndFamily)
                            .userType(userType)
                            .password(password)
                            .build();
            ManagementService.getService().save(management);
            log.info("Save");
            return management;
        } else {
            log.error("Error Save");
            throw new Exception("Invalid Data");
        }

    }

    public Management remove(String username, String password) throws Exception {
        Management management = ManagementService.getService().findByUsernameAndPassword(username, password);
        log.info("remove");
        ManagementService.getService().remove(username, password);
        return management;
    }

    public Management findByUsernameAndPassword(String username, String password) throws Exception {
        Management management = ManagementService.getService().findByUsernameAndPassword(username, password);
        if (management != null) {
            return management;
        }

        return management;
    }

}