package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Book;
import mft.model.entity.Management;
import mft.model.repository.BookRepository;
import mft.model.repository.ManagementRepository;

import java.util.List;
@Log4j
public class BookService {
    private static BookService service = new BookService();

    private BookService() {
    }

    public static BookService getService() {
        return service;
    }

    public Book save(Book book) throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
            log.info("save");
            return bookRepository.save(book);
        }
    }

    public Book edit(Book book) throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
            log.info("edit");
            return bookRepository.edit(book);
        }
    }

    public Book remove1(String nameBook,String authorBook) throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
            Book book = bookRepository.findByNameBookAndAuthorBook(nameBook,authorBook);
                bookRepository.remove1(nameBook,authorBook);
                log.info("Save");
                return book;

        }
    }
    public Book remove2(int id) throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
            Book book= bookRepository.findById(id);
            if (book != null){
                bookRepository.remove2(id);
                return book;
            }
            else {
                return null;
            }
        }
    }


    public Book findById(int id) throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
            log.info("findById");
            return bookRepository.findById(id);
        }
    }

    public List<Book> findAll() throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
          log.info("save");
            return bookRepository.findAll();
        }
    }
    public List<Book> findByNameBook(String nameBook) throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
            return bookRepository.findByNameBook(nameBook);
        }
    }
    public Book findByNameBookAndAuthorBook(String nameBook,String authorBook) throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
            log.info("Save");
            return bookRepository.findByNameBookAndAuthorBook(nameBook,authorBook);
        }
    }

}
