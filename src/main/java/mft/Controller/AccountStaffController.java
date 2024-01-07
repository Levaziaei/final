package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.AccountStaff;
import mft.model.service.AccountStaffService;

import java.util.List;
@Log4j
public class AccountStaffController {
    private static AccountStaffController controller = new AccountStaffController();

    private AccountStaffController() {
    }

    public static AccountStaffController getController() {
        return controller;
    }

    public AccountStaff save(String nameBook, String nameAndFamily,String addSuggestion,String yourSuggestion) {
        try {
AccountStaff accountStaff =
AccountStaff
.builder()
.nameBook(nameBook)
.yourSuggestion(yourSuggestion)
.addSuggestion(addSuggestion)
.nameAndFamily(nameAndFamily)
.build();
            System.out.println(accountStaff);
            AccountStaffService.getService().save(accountStaff);
            log.info("Save");
            return accountStaff;
        } catch (Exception e) {
            log.error(" Error Save");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

public AccountStaff remove( int id) throws Exception {
    AccountStaff accountStaffService = AccountStaffService.getService().findById(id);
    AccountStaffService.getService().remove(id);
    log.info("remove");
    return accountStaffService;
}
    public AccountStaff edit(Integer id, String nameBook, String yourSuggestion, String nameAndFamily,String addSuggestion) {
        try {
            AccountStaff accountStaff =
                    AccountStaff
                            .builder()
                            .id(id)
.nameBook(nameBook)
.addSuggestion(addSuggestion)
.yourSuggestion(yourSuggestion)
.nameAndFamily(nameAndFamily)
.build();
AccountStaffService.getService().edit(accountStaff);
log.info("edit");
return accountStaff;
        } catch (Exception e) {
            log.error(" Error edit");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
    public List<AccountStaff> findAll() {
        try {
            log.info("findAll");
            return AccountStaffService.getService().findAll();
        } catch (Exception e) {
            log.error(" Error findAll");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
}