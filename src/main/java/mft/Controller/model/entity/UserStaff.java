package mft.Controller.model.entity;

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

public class UserStaff {
String admin="admin";
String password="777admin777";
    public UserStaff() {
        log.info("Save created");
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
