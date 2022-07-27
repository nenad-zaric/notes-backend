package com.anunnakisoftware.notes.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/texts")
public class TextController {
    private final TextService textService;

    @Autowired
    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping
    public List<Text> getTexts(){
        return textService.getTexts();
    }

    @GetMapping(path = "{noteId}")
    public List<Text> getTextsByNoteId(@PathVariable Long noteId){
        return textService.getTextsByNoteId(noteId);
    }

    @PostMapping
    public void createText(@RequestBody Text text){
        textService.createText(text);
    }
}
