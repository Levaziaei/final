package mft.model.entity;
import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j;

@Getter
@Setter
@SuperBuilder(toBuilder = true)

@Log4j
public class Book  {
   public int id;
   public String nameBook;
   public String authorBook;


   public Book() {
      log.info("Save created");
   }
   @Override
   public String toString() {
      return new Gson().toJson(this);
   }


}




