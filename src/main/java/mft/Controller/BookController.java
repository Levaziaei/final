package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Book;
import mft.model.service.BookService;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static mft.model.entity.Book.*;

@Log4j
public class BookController {
    private static BookController controller = new BookController();

    private BookController() {
    }

    public static BookController getController() {
        return controller;
    }

    public Book save(Integer id, String nameBook, String yourSuggestion) {

        try {
            Book book =
                    builder()
                            .id(id)
                            .nameBook(nameBook)
                            .yourSuggestion(yourSuggestion)
                            .build();
            System.out.println(book);
            BookService.getService().save(book);
            log.info("Save");
            return book;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            log.error("Error save");
            return null;
        }
    }




    public List<Book> findAll() {
        try {
            log.info("findAll");
            return BookService.getService().findAll();

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            log.error("Error findAll");
            return null;
        }
    }
@GET
@Produces(MediaType.APPLICATION_JSON)


  public Book edit(Integer id, String nameBook, String authorBook,String yourSuggestion, String nameAndFamily) {
    try {
        Book book =
                builder()
                        .id(id)
                        .nameBook(nameBook)
                        .yourSuggestion(yourSuggestion)
                        .build();
        BookService.getService().edit(book);
        log.info("edit");
        return book;
    } catch (Exception e) {
        System.out.println("Error : " + e.getMessage());
        log.error("Error findAll ");
        return null;
    }
}


}