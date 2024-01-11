package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Book;
import mft.model.entity.Management;
import mft.model.service.BookService;
import mft.model.service.ManagementService;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.regex.Pattern;

import static mft.model.entity.Book.*;

@Log4j
public class BookController {
    private static BookController controller = new BookController();

    private BookController() {
    }

    public static BookController getController() {
        return controller;
    }

    public Book save(Integer id, String nameBook, String authorBook) throws Exception {
        if (Pattern.matches("^[a-zA-Z1-9]+$", nameBook) &&
                (Pattern.matches("^[a-zA-Z]+$", authorBook))) {
            Book book =
                    Book
                            .builder()
                            .id(id)
                            .nameBook(nameBook)
                            .authorBook(authorBook)
                            .build();
            System.out.println(book);
            BookService.getService().save(book);
            log.info("Save");
            return book;
        } else {
            log.error("Error Save");
            throw new Exception("Invalid Data");
        }
    }

    public List<Book> findAll() throws Exception {
        log.info("Save");
        return BookService.getService().findAll();
    }

    public Book edit(Integer id, String nameBook, String authorBook) throws Exception {
        if (Pattern.matches("^[a-zA-Z1-9]+$", nameBook) &&
                (Pattern.matches("^[a-zA-Z]+$", authorBook))) {
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

        } else {
            throw new Exception("Invalid Data");
        }
    }

    public Book remove(Integer id) throws Exception {
        Book book = BookService.getService().findById(id);
        BookService.getService().remove(id);
        log.info("remove");
        return book;
    }

    public List<Book> findByNameBook(String nameBook) throws Exception {
        return BookService.getService().findByNameBook(nameBook);
    }

    public Book findByNameBookAndAuthorBook(String nameBook, String authorBook) throws Exception {
        Book book = BookService.getService().findByNameBookAndAuthorBook(nameBook, authorBook);
        if (book != null) {
            log.info("save");
            return book;
        }
        return book;
    }
}
