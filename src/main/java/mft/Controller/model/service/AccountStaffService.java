package mft.Controller.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.AccountStaff;
import mft.model.repository.AccountStaffRepository;

import java.util.List;
@Log4j
public class AccountStaffService {
    private static AccountStaffService service = new AccountStaffService();

    private AccountStaffService() {
    }

    public static AccountStaffService getService() {
        return service;
    }

    public AccountStaff save(AccountStaff accountStaff) throws Exception {
        try (AccountStaffRepository accountStaffRepository = new AccountStaffRepository()) {
           log.info("save");
            return accountStaffRepository.save(accountStaff);
        }
    }

    public AccountStaff edit(AccountStaff accountStaff) throws Exception {
        try (AccountStaffRepository accountStaffRepository = new AccountStaffRepository()) {
          log.info("edit");
            return accountStaffRepository.edit(accountStaff);
        }
    }

    public AccountStaff remove(int id) throws Exception {
        try (AccountStaffRepository accountStaffRepository = new AccountStaffRepository()) {
            AccountStaff accountStaff = accountStaffRepository.findById(id);
            if (accountStaff != null) {
                accountStaffRepository.remove(id);
                log.info("remove");
                return accountStaff;
            } else {
log.error("Error remove");
                return null;
            }
        }
    }

    public List<AccountStaff> findAll() throws Exception {
        try (AccountStaffRepository accountStaffRepository = new AccountStaffRepository()) {
           log.info("findAll");
            return accountStaffRepository.findAll();
        }
    }

    public AccountStaff findById(int id)throws Exception  {
        try (AccountStaffRepository accountStaffRepository = new AccountStaffRepository()) {
            log.info("findById");
            return accountStaffRepository.findById(id);
        }
    }
    }
