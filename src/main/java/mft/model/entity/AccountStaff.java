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


public class AccountStaff extends Book{
    public int id;
    public Book getNameBook;
    public Book getNameAndFamily;
    public UserStaff admin;
    public Book getYourSuggestion;
    public String addSuggestion;
    public UserStaff getPassword;
 public AccountStaff(){
         log.info("Save created");
     }
     @Override
     public String toString() {
         return new Gson().toJson(this);
     }

 }



