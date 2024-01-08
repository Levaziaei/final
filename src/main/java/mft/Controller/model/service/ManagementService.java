package mft.Controller.model.service;

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

        public Management remove(String username,String password) throws Exception {
            try (ManagementRepository managementRepository = new ManagementRepository()) {
                Management management = managementRepository.findByUsername(username);
                if (management != null) {
                    managementRepository.remove(username,password);
                  log.info("Save");
                    return management;
                }
                else {
log.error("Error");
                    return null;
                }
            }
        }

    public Management findByUsernameAndPassword(String username, String password) {
            try (ManagementRepository managementRepository = new ManagementRepository()) {
                log.info("Save");
                return managementRepository.findByUsernameAndPassword(username,password);
            } catch (Exception e) {
                log.error("Error");
                throw new RuntimeException(e);
            }
    }

        public Management save(Management management) throws Exception {
            try (ManagementRepository managementRepository = new ManagementRepository()) {
                log.info("save");
               return managementRepository.save(management);
            }
        }
    }
