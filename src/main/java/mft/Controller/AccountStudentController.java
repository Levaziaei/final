package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.AccountStudent;
import mft.model.service.AccountStudentService;

import java.util.regex.Pattern;

@Log4j
public class AccountStudentController {
    private static AccountStudentController controller = new AccountStudentController();

    private AccountStudentController() {
    }

    public static AccountStudentController getController() {
        return controller;
    }

    public AccountStudent save(Integer id ,String nameAndFamily,String yourSuggestion) throws Exception {
        try {
                  if  (Pattern.matches("^[a-zA-Z]+$", nameAndFamily) &
                    (Pattern.matches("^[a-zA-Z]+$", yourSuggestion))) {

                AccountStudent accountStudent =
                        AccountStudent
                                .builder()
                                .id(id)
                                .nameAndFamily(nameAndFamily)
                                .yourSuggestion(yourSuggestion)
                                .build();
                AccountStudentService.getService().save(accountStudent);
                log.info("Save");
                return accountStudent;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
          log.error("Error Save");
        return null;
    }

    public AccountStudent edit(Integer id, String yourSuggestion, String nameAndFamily) {
        try {
            AccountStudent accountStudent =
                    AccountStudent
                            .builder()
                            .id(id)
                            .yourSuggestion(yourSuggestion)
                            .nameAndFamily(nameAndFamily)
                            .build();
            AccountStudentService.getService().edit(accountStudent);
            log.info("edit");
            return accountStudent;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            log.error("Error edit");
            return null;
        }
    }

    public AccountStudent findAllByNameAndFamily(String nameBook){ try {
        log.info("findAllByNameAndFamily");
        return AccountStudentService.getService().findAllByNameAndFamily(nameBook);
    } catch (Exception e) {
        System.out.println("Error : " + e.getMessage());
        log.error("Error findAllByNameAndFamily");
        return null;
    }
    }

}
