package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j;

@Getter
@Setter
@SuperBuilder(toBuilder = true)

@Log4j

public class Librarian {
    public  int id;
    public String nameAndFamily;
    public String password;
    public String nameBook;
    public  String search;
    public Librarian() {
        log.info("Save created");
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
