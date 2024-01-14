package mft.Controller;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Suggestion;
import mft.model.service.SuggestionService;

import java.util.List;
import java.util.regex.Pattern;

@Log4j
public class SuggestionController {

    private static SuggestionController controller = new SuggestionController();

    private SuggestionController() {
    }

    public static SuggestionController getController() {
        return controller;
    }

    public Suggestion save(String suggestion) throws Exception {
        if (Pattern.matches("^[a-zA-Z1-9]+$", suggestion)) {
            Suggestion admin =
                    Suggestion
                            .builder()
                            .suggestion(suggestion)
                            .build();
            System.out.println(admin);
            SuggestionService.getService().save(admin);
            log.info("Save");
            return admin;
        } else {
            log.error("Error Save");
            throw new Exception("Invalid Data");
        }
    }
    public List<Suggestion> findAll() throws Exception {
        log.info("Save");
        return SuggestionService.getService().findAll();
    }
    public Suggestion remove(Integer id) throws Exception {
        Suggestion suggestion = SuggestionService.getService().findById(id);
        SuggestionService.getService().remove(id);
        return suggestion;
    }


}
