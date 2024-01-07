package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Management;
import mft.model.service.ManagementService;

import java.util.regex.Pattern;
@Log4j
public class UserStudentController {
    private static UserStudentController controller;

    private UserStudentController() throws Exception {
    }

    public static UserStudentController getController() {
        return controller;
    }

    public Management save(String nameAndFamily, String username,
                           String password) throws Exception {
        if (Pattern.matches("^[a-z\\d\\S\\._]{3,30}+$", username) &&
                (Pattern.matches("^[a-zA-Z]+$", nameAndFamily) &
                        (Pattern.matches("^[\\w\\S]{5,30}$", password)))) {
            Management management =
                    Management
                            .builder()
                            .username(username)
                            .nameAndFamily(nameAndFamily)
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

}