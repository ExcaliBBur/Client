package Realisation;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Collections;
import java.util.ResourceBundle;

public class ObservableResourceFactory {
    private Languages currentLanguage;
    private final ObjectProperty<ResourceBundle> resources = new SimpleObjectProperty<>();

    public ObjectProperty<ResourceBundle> resourcesProperty() {
        return resources;
    }

    public ResourceBundle getResources() {
        return resourcesProperty().get();
    }

    public void setResources(ResourceBundle resources) {
        resourcesProperty().set(resources);
    }

    public void setLanguage(Languages language) {
        this.currentLanguage = language;

        this.setResources(language.getResources());
    }

    public Languages getLanguage() {
        return this.currentLanguage;
    }

    public StringBinding getStringBinding(String key) {
        return new StringBinding() {
            {
                bind(resourcesProperty());
            }

            @Override
            public String computeValue() {
                return getResources().getString(key);
            }
        };
    }

    public String getLocale(String word, ResourceBundle resourceBundle) {
        for (String key : Collections.list(this.getResources().getKeys())) {
            if (this.getResources().getString(key).equals(word)) {
                return resourceBundle.getString(key);
            }
        }
        return null;
    }
}