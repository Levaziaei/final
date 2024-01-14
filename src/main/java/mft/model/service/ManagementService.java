package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Management;
import mft.model.repository.ManagementRepository;

@Log4j
public class ManagementService {
    private static ManagementService service = new ManagementService();

    private ManagementService() {
    }

    public static ManagementService getService() {
        return service;
    }

    public Management remove(String username,String password) throws Exception {
        try (ManagementRepository managementRepository = new ManagementRepository()) {
            Management management = managementRepository.findByUsernameAndPassword(username,password);
            if (management != null) {
                managementRepository.remove(username,password);
                log.info("Save");
                return management;
            } else {
                log.error("Error");
                return null;
            }
        }
    }

    public Management findByUsername(String username) throws Exception {
        try (ManagementRepository managementRepository = new ManagementRepository()) {
         log.info("findByUsername");
            return managementRepository.findByUsername(username);
        }
    }

    public Management findByUsernameAndPassword(String username,String password) throws Exception {
        try (ManagementRepository managementRepository = new ManagementRepository()) {
            log.info("findByUsernameAndPassword");
            return managementRepository.findByUsernameAndPassword(username,password);
        }
    }

    public Management save(Management management) throws Exception {
        try (ManagementRepository managementRepository = new ManagementRepository()) {
            managementRepository.save(management);
            log.info("save");
            return management;
        }
    }
}
