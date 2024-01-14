package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Borrow;
import mft.model.repository.BorrowRepository;
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

    public  Borrow remove(int id) throws Exception {
        try (BorrowRepository borrowRepository = new BorrowRepository()) {
            Borrow borrow= borrowRepository.findById(id);
            if (borrow != null){
                borrowRepository.remove(id);
                log.info("remove");
                return borrow;
            }
            else {
                return null;
            }
        }
    }

    public List<Borrow> findAll() throws Exception {
        try (BorrowRepository borrowRepository = new BorrowRepository()) {
            log.info("findAll");
            return borrowRepository.findAll();
        }
    }


    public Borrow findById(int id) throws Exception {
        try (BorrowRepository borrowRepository = new BorrowRepository()) {
            log.info("findById");
            return borrowRepository.findById(id);
        }
    }}
