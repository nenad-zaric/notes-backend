package com.anunnakisoftware.notes.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextService {
    private final TextRepository textRepository;

    @Autowired
    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public List<Text> getTextsByNoteId(Long noteId){
        return textRepository.getTextsByNoteId(noteId);
    }

    public void createText(Text text){
        textRepository.save(text);
    }

    public void deleteTextsByNoteId(Long noteId){
        textRepository.deleteTextsByNoteId(noteId);
    }

    public List<Text> getTexts() {
        return textRepository.getTexts();
    }
}
