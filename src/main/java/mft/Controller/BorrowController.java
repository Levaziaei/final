package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Management;
import mft.model.service.BorrowService;

import java.util.List;


@Log4j

public class BorrowController {
    private static BorrowController controller = new BorrowController();

    private BorrowController() {
    }

    public static BorrowController getController() {
        return controller;
    }


     public Borrow save(String username, String nameBook, String authorBook) {
    try {
      Management management = ManagementController.getController().findByUsername(
      username);
      Book book=BookController.getController().findByNameBookAndAuthorBook(
      nameBook,authorBook);

       if  (book  != null && management!= null) {
      Borrow borrow = Borrow
      .builder()
      .username(username)
      .nameBook(nameBook)
      .authorBook(authorBook)
      .build();
      System.out.println(borrow);
      BorrowService.getService().save(borrow);
     log.info("Save");
      return borrow;
      } } catch (Exception ex) {
      throw new RuntimeException(ex);
      }
      return null;
      }
public List<Borrow> findAll () throws Exception {
            log.info("findAll");
            return BorrowService.getService().findAll();
        }

 public Borrow remove(int id) throws Exception {
        Borrow borrow = BorrowService.getService().findById(id);
        BorrowService.getService().remove(id);
        log.info("remove");
        return borrow;
    }

    }
