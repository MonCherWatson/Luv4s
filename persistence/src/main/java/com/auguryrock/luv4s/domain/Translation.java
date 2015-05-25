package com.auguryrock.luv4s.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by MonCherWatson on 22/05/2015.
 */
@Entity(name = "Translations")
@IdClass(Translation.TranslationId.class)
public class Translation {
    @Id
    private String key;
    @Id
    private Language language;
    private String value;


    public Translation() {
    }

    public Translation(String key, Language language, String value) {
        this.key = key;
        this.value = value;
        this.language = language;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Language getLanguage() {
        return language;
    }

    public static class TranslationId implements Serializable{
        public String key;
        public Language language;

    }
}
