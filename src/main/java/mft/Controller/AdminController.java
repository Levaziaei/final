package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Admin;
import mft.model.service.AdminService;
import java.util.List;
import java.util.regex.Pattern;

@Log4j
public class AdminController {

    private static AdminController controller = new AdminController();

    private AdminController() {
    }

    public static AdminController getController() {
        return controller;
    }

    public Admin save(Integer id, String suggestion) throws Exception {
        if (Pattern.matches("^[a-zA-Z1-9]+$", suggestion)) {
            Admin admin =
                    Admin
                            .builder()
                            .id(id)
                            .suggestion(suggestion)
                            .build();
            System.out.println(admin);
            AdminService.getService().save(admin);
            log.info("Save");
            return admin;
        } else {
            log.error("Error Save");
            throw new Exception("Invalid Data");
        }
    }
    public List<Admin> findAll() throws Exception {
        log.info("Save");
        return AdminService.getService().findAll();
    }
}
