package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Management;
import mft.model.repository.ManagementRepository;
import org.apache.log4j.BasicConfigurator;

@Log4j
public class ManagementService {
    private static ManagementService service = new ManagementService();

    private ManagementService() {
    }

    public static ManagementService getService() {
        return service;
    }

    public Management remove(String username) throws Exception {
        try (ManagementRepository managementRepository = new ManagementRepository()) {
            Management management = managementRepository.findByUsernameAndPassword(username);
            if (management != null) {
                managementRepository.remove(username);
                log.info("Save");
                return management;
            } else {
                log.error("Error");
                return null;
            }
        }
    }

    public Management findByUsernameAndPassword(String username, String password) {
        try (ManagementRepository managementRepository = new ManagementRepository()) {
            log.info("Save");
            return managementRepository.findByUsernameAndPassword(username);
        } catch (Exception e) {
            log.error("Error");
            throw new RuntimeException(e);
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
