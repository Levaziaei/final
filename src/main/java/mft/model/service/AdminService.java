package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Admin;
import mft.model.entity.Borrow;
import mft.model.repository.AdminRepository;
import mft.model.repository.BorrowRepository;

import java.util.List;

@Log4j
public class AdminService {
    private static AdminService service = new AdminService();

    private AdminService() {
    }

    public static AdminService getService() {
        return service;
    }
    public Admin save(Admin admin) throws Exception {
        try (AdminRepository adminRepository = new AdminRepository()) {
            log.info("save");
            return adminRepository.save(admin);
        }
    }
    public List<Admin> findAll() throws Exception {
        try (AdminRepository adminRepository = new AdminRepository()) {
            log.info("save");
            return adminRepository.findAll();
        }
    }


}