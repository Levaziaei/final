package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Librarian;
import mft.model.service.LibrarianService;

import java.util.List;
import java.util.regex.Pattern;
@Log4j
public class LibrarianController {
    private static LibrarianController controller = new LibrarianController();
    private LibrarianController() {
    }
    public static LibrarianController getController() {
        return controller;
    }

    public Librarian save(String nameBook, String nameAndFamily, String search,String password) throws Exception {
        if (Pattern.matches("^[a-zA-Z]$", nameAndFamily) &&
                (Pattern.matches("^[a-zA-Z]+$", search) &
                        (Pattern.matches("^[\\w\\S]{5,30}$", password)))) {
            Librarian librarian =
                    Librarian
                            .builder()
                            .search(search)
                            .nameAndFamily(nameAndFamily)
                            .password(password)
                            .build();
            LibrarianService.getService().save(librarian);
            log.info("Save");
            return librarian;
        } else {
            log.error("Error save");
            throw new Exception("Invalid Data");
        }
    }
    public Librarian edit(Integer id, String nameBook,  String nameAndFamily , String password) {
        try {
            Librarian librarian =
                    Librarian
                            .builder()
                            .id(id)
                            .nameBook(nameBook)
                            .nameAndFamily(nameAndFamily)
                            .password(password)
                            .build();
            LibrarianService.getService().edit(librarian);
            log.info("edit");
            return librarian;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            log.error("Error edit");

            return null;
        }
    }
    public List<Librarian> findAll() {
        try {
            log.info("findAll");
            return LibrarianService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            log.error("Error findAll");
            return null;
        }
    }
    public Librarian findByBook(String nameBook){ try {
    log.info("findByBook");
        return LibrarianService.getService().findByBook(nameBook);
    } catch (Exception e) {
        System.out.println("Error : " + e.getMessage());
        log.error("Error findByBook");
        return null;
    }
    }
}


