package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Log4j
public class Borrow {
private int id;
private String username;
private String nameBook;
private String authorBook;
private Management management;
private Book book;
  public Borrow() {
 log.info("Save created");
  }
 @Override
 public String toString() {
            return new Gson().toJson(this);
        }

    }
