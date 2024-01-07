package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.AccountStudent;
import mft.model.repository.AccountStudentRepository;

@Log4j
public class  AccountStudentService {

    private static AccountStudentService service = new AccountStudentService();

    private AccountStudentService() {
    }

    public static AccountStudentService getService() {
        return service;
    }

    public AccountStudent save(AccountStudent accountStudent) throws Exception {
        try (AccountStudentRepository accountStudentRepository = new AccountStudentRepository()) {
           log.info("save");
            return accountStudentRepository.save(accountStudent);
        }
    }
    public AccountStudent edit(AccountStudent accountStudent) throws Exception {
        try (AccountStudentRepository accountStudentRepository = new AccountStudentRepository()) {
           log.info("edit");
            return accountStudentRepository.edit(accountStudent);
        }
    }
    public AccountStudent remove(int id) throws Exception {
        try (AccountStudentRepository accountStudentRepository = new AccountStudentRepository()) {
            AccountStudent accountStudent = accountStudentRepository.findById(id);
            if (accountStudent != null) {
                accountStudentRepository.remove(id);
               log.info("remove");
                return accountStudent;
            } else {
log.error("Error remove");
                return null;
            }
        }}
        public AccountStudent findAllByNameAndFamily(String nameAndFamily) throws Exception {
            try (AccountStudentRepository accountStudentRepository = new AccountStudentRepository()) {
              log.info("findAllByNameAndFamily");
                return accountStudentRepository.findAllByNameAndFamily(nameAndFamily);
            }
        }
    }