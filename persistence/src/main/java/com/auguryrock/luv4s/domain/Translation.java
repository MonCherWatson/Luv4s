package com.auguryrock.luv4s.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by MonCherWatson on 22/05/2015.
 */
@Entity(name = "Translations")
@IdClass(Translation.TranslationId.class)
@JsonSerialize(using = TranslationSerializer.class)
public class Translation {
    @Id
    private String nameKey;
    @Id
    private Language language;
    private String value;


    public Translation() {
    }

    public Translation(String nameKey, Language language, String value) {
        this.nameKey = nameKey;
        this.value = value;
        this.language = language;
    }

    public String getNameKey() {
        return nameKey;
    }

    public String getValue() {
        return value;
    }

    public Language getLanguage() {
        return language;
    }

    public static class TranslationId implements Serializable{
        public String nameKey;
        public Language language;

    }
}
