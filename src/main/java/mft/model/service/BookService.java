package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Book;
import mft.model.repository.BookRepository;

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

    public Book remove(int id) throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
            Book book = bookRepository.findById(id);
            if (book != null) {
                bookRepository.remove(id);
                log.info("remove");
                return book;
            } else {
                log.error("Error remove");
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

    public List<Book> findAll()throws Exception {
        try (BookRepository bookRepository = new BookRepository()) {
            log.info("findAll");
            return bookRepository.findAll();
        }
    }}

