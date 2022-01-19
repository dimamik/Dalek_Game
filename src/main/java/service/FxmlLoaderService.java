package service;

import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;

import java.net.URL;
import java.util.ResourceBundle;

public record FxmlLoaderService(Injector injector) {
    @Inject
    public FxmlLoaderService {
    }

    public FXMLLoader getLoader(final URL location) {
        return getLoader(location, null);
    }

    public FXMLLoader getLoader(final URL location, ResourceBundle resources) {
        return new FXMLLoader(location,
                resources,
                new JavaFXBuilderFactory(),
                injector::getInstance);
    }
}