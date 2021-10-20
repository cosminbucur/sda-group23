package com.sda.spring.core.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextEditor {

    // field injection
    @Autowired
    private ImageConverter imageConverter;

    private TextFormatter textFormatter;

    private SpellChecker spellChecker;

    // constructor injection
    @Autowired
    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    // delegation
    public void spellCheck() {
        spellChecker.checkSpelling();
    }

    public void format() {
        textFormatter.format();
    }

    public void convertImage() {
        imageConverter.convert();
    }

    // setter injection
    @Autowired
    public void setTextFormatter(TextFormatter newTextFormatter) {
        this.textFormatter = newTextFormatter;
    }
}
