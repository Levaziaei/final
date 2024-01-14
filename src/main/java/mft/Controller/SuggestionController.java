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

    public Suggestion save(String suggest)  {
        try {
            if (Pattern.matches("^[ a-zA-Z\\d\\S]{3,30}+$", suggest)) {
                Suggestion suggestion =
                        Suggestion
                                .builder()
                                .suggest(suggest)
                                .build();
                SuggestionService.getService().save(suggestion);
                log.info("Save");
                return suggestion;
            } else {
                log.error("Error Save");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public List<Suggestion> findAll() throws Exception {
        log.info("findAll");
        return SuggestionService.getService().findAll();
    }
    public Suggestion remove(Integer id) throws Exception {
        Suggestion suggestion = SuggestionService.getService().findById(id);
        SuggestionService.getService().remove(id);
        log.info("remove");
        return suggestion;
    }


}
