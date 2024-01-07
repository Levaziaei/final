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
public class Book extends Management{
   public int id;
   public String nameBook;
   public String authorBook;
   public Management getNameAndFamily;
   public String yourSuggestion;

   public Book() {
      log.info("Save created");
   }
   @Override
   public String toString() {
      return new Gson().toJson(this);
   }

}


