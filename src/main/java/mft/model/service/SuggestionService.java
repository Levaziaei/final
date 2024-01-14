package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Suggestion;
import mft.model.repository.SuggestionRepository;

import java.util.List;

@Log4j
public class SuggestionService {
    private static SuggestionService service = new SuggestionService();

    private SuggestionService() {
    }

    public static SuggestionService getService() {
        return service;
    }
    public Suggestion save(Suggestion suggestion) throws Exception {
        try (SuggestionRepository suggestionRepository = new SuggestionRepository()) {
            log.info("save");
            return suggestionRepository.save(suggestion);
        }
    }
    public List<Suggestion> findAll() throws Exception {
        try (SuggestionRepository suggestionRepository = new SuggestionRepository()) {
            log.info("findAll");
            return suggestionRepository.findAll();
        }
    }
    public Suggestion remove(int id) throws Exception {
        try (SuggestionRepository suggestionRepository = new SuggestionRepository()) {
            Suggestion suggestion = suggestionRepository.findById(id);
            if (suggestion != null){
                suggestionRepository.remove(id);
                log.info("remove");
                return suggestion;
            }
            else {
                log.error("Error remove");
                return null;
            }
        }
    }
    public Suggestion findById(int id) throws Exception {
        try (SuggestionRepository suggestionRepository = new SuggestionRepository()) {
            log.info("findById");
            return suggestionRepository.findById(id);
        }
    }




}
