package Realisation;

import Resource.ResourceHungarian;
import Resource.ResourcePortuguese;
import Resource.ResourceRussian;
import Resource.ResourceSpanish;

import java.io.Serializable;
import java.util.ListResourceBundle;
import java.util.Locale;

public enum Languages implements Serializable {
    RUSSIAN("Русский") {
        @Override
        public ListResourceBundle getResources() {
            return new ResourceRussian();
        }

        @Override
        public Locale getLocale() {
            return new Locale("ru");
        }
    },
    SPANISH("Español") {
        @Override
        public ListResourceBundle getResources() {
            return new ResourceSpanish();
        }

        @Override
        public Locale getLocale() {
            return new Locale("es", "EC");
        }
    },
    HUNGARIAN("Magyar") {
        @Override
        public ListResourceBundle getResources() {
            return new ResourceHungarian();
        }

        @Override
        public Locale getLocale() {
            return new Locale("hu");
        }
    },
    PORTUGUESE("Português") {
        @Override
        public ListResourceBundle getResources() {
            return new ResourcePortuguese();
        }

        @Override
        public Locale getLocale() {
            return new Locale("pt");
        }
    };

    public static final long serialVersionUID = 42L;

    private final String name;

    Languages(String name) {
        this.name = name;
    }

    public abstract ListResourceBundle getResources();

    public abstract Locale getLocale();

    public String getName() {
        return name;
    }

    public static Languages getEnum(String value) {
        for (Languages language : values()) {
            if (language.getName().equals(value)) return language;
        }
        return null;
    }
}