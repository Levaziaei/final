package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Management;
import mft.model.service.BorrowService;

import java.util.List;
import java.util.regex.Pattern;


@Log4j

public class BorrowController {
    private static BorrowController controller = new BorrowController();

    private BorrowController() {
    }

    public static BorrowController getController() {
        return controller;
    }

public Borrow save(String username, String nameBook, String authorBook)  {
  try {
Management management = ManagementController.getController().findByUsername(
        username);
      while (management != null){
          Book book=BookController.getController().findByNameBookAndAuthorBook(
                  nameBook,authorBook);
          while (book !=null) {
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

          }}

    return null;
} catch (Exception e) {
      throw new RuntimeException(e);
  }}
    public List<Borrow> findAll () throws Exception {
            log.info("Save");
            return BorrowService.getService().findAll();
        }

    public Borrow edit ( Integer id,String username, String nameBook, String authorBook) throws Exception {
        if (Pattern.matches("^[a-zA-Z1-9]+$", nameBook) &&
                (Pattern.matches("^[a-zA-Z1-9]+$", username)&&
                        (Pattern.matches("^[a-zA-Z]+$", authorBook)))) {
            Borrow borrow =
                    Borrow
                            .builder()
                            .id(id)
                            .authorBook(authorBook)
                            .nameBook(nameBook)
                            .authorBook(authorBook)
                            .build();
            BorrowService.getService().edit(borrow);
            log.info("edit");
            return borrow;

        } else {
            throw new Exception("Invalid Data");
        }
    }

    public Borrow remove(int id) throws Exception {
        Borrow borrow = BorrowService.getService().findById(id);
        log.info("remove");
        BorrowService.getService().remove(id);
        return borrow;
    }
}
