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
public int id;
public String username;
public String nameBook;
public String authorBook;
public Management management;
  public Borrow() {
 log.info("Save created");
  }
 @Override
 public String toString() {
            return new Gson().toJson(this);
        }

    }
