package mft.Controller.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Librarian;
import mft.model.repository.LibrarianRepository;

import java.util.List;
@Log4j
public class LibrarianService {
    private static LibrarianRepository service = new LibrarianRepository();

    private LibrarianService() {
    }

    public static LibrarianRepository getService() {
        return service;
    }

    public Librarian save(Librarian librarian) throws Exception {
        try (LibrarianRepository librarianRepository = new LibrarianRepository()) {
        log.info("save");
            return librarianRepository.save(librarian);
        }
    }

    public Librarian edit(Librarian librarian) throws Exception {
        try (LibrarianRepository librarianRepository = new LibrarianRepository()) {
            log.info("edit");
            return librarianRepository.edit(librarian);
        }
    }

    public Librarian findByBook(String nameBook) throws Exception {
        try (LibrarianRepository librarianRepository = new LibrarianRepository()) {
            log.info("findByBook");
            return librarianRepository.findByBook(nameBook);
        }
    }

    public List<Librarian> findAll()throws Exception {
        try (LibrarianRepository librarianRepository = new LibrarianRepository()) {
            log.info("findAll");
            return librarianRepository.findAll();
        }
    }}


