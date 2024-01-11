package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Borrow;
import mft.model.entity.Management;
import mft.model.repository.BorrowRepository;
import mft.model.repository.ManagementRepository;

import java.util.List;


@Log4j
public class BorrowService {
    private static BorrowService service = new BorrowService();

    private BorrowService() {
    }

    public static BorrowService getService() {
        return service;
    }

    public Borrow save(Borrow borrow) throws Exception {
        try (BorrowRepository borrowRepository = new BorrowRepository()) {
            log.info("save");
            return borrowRepository.save(borrow);
        }
    }

    public Borrow edit(Borrow borrow) throws Exception {
        try (BorrowRepository borrowRepository = new BorrowRepository()) {
            log.info("edit");
            return borrowRepository.edit(borrow);
        }
    }

    public Borrow remove(String username,String nameBook, String authorBook) throws Exception {
        try (BorrowRepository borrowRepository = new BorrowRepository()) {
            Borrow borrow = borrowRepository.findByUsernameNameBookAndAuthorBook(username,nameBook,authorBook);
            if (borrow != null) {
                borrowRepository.remove(username,nameBook,authorBook);
                log.info("Save");
                return borrow;
            } else {
                log.error("Error");
                return null;
            }
        }
    }
    public Borrow findByUsernameAndNameBookAndAuthorBook(String username,String nameBook, String authorBook) throws Exception {
        try (BorrowRepository borrowRepository = new BorrowRepository()) {
            return borrowRepository.findByUsernameNameBookAndAuthorBook(username, nameBook,  authorBook);
        }
    }

    public List<Borrow> findAll() throws Exception {
        try (BorrowRepository borrowRepository = new BorrowRepository()) {
            log.info("save");
            return borrowRepository.findAll();
        }
    }


}
