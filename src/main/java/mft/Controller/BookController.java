package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Book;
import mft.model.entity.Management;
import mft.model.service.BookService;

import java.util.List;

@Log4j
public class BookController {
    private static BookController controller = new BookController();

    private BookController() {
    }

    public static BookController getController() {
        return controller;
    }

    public Book save(String nameBook,String authorBook)throws Exception {
          Book book =
                      Book
                              .builder()
                              .nameBook(nameBook)
                              .authorBook(authorBook)
                              .build();
              System.out.println(book);
              BookService.getService().save(book);
              log.info("save");
              return book;

          }

    public List<Book> findAll() throws Exception {
        log.info("findAll");
        return BookService.getService().findAll();
    }

    public Book edit(Integer id, String nameBook, String authorBook) throws Exception {

            Book book =
                    Book
                            .builder()
                            .id(id)
                            .nameBook(nameBook)
                            .authorBook(authorBook)
                            .build();
            BookService.getService().edit(book);
            log.info("edit");
            return book;

        }

    public Book remove1(String username,String nameBook, String authorBook) {
        try {Management management = ManagementController.getController().findByUsername(
                    username);
            if (nameBook != null && authorBook != null && management!=null) {
                Book book = BookService.getService().findByNameBookAndAuthorBook(nameBook, authorBook);
                BookService.getService().remove1(nameBook, authorBook);
                log.info("remove1");
                return book;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

        public List<Book> searchForNameBook(String nameBook) throws Exception {
       log.info("searchForNameBook");
        return BookService.getService().searchForNameBook(nameBook);
    }


    public Book findByNameBookAndAuthorBook(String nameBook, String authorBook) throws Exception {
        Book book = BookService.getService().findByNameBookAndAuthorBook(nameBook, authorBook);
        if (book != null) {
            log.info("findByNameBookAndAuthorBook");
            return book;
        }
        return book;
    }
    public Book remove2(Integer id) throws Exception {
        Book book = BookService.getService().findById(id);
        BookService.getService().remove2(id);
        log.info("remove2");
        return book;
    }

}
