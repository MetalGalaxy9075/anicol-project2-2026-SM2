package au.edu.unimelb.swen.oop.mokepon;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Main extends Application {

    static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Properties gameData = loadPropertiesFromArgument();
        ScreenManager.init(gameData);
    }

    private Properties loadPropertiesFromArgument() throws IOException {
        String argument = getParameters().getRaw().getFirst();
        if (!argument.startsWith("data=")) {
            throw new IllegalArgumentException("Missing expected argument in the form data=relativeFilePath");
        }

        Properties properties = new Properties();
        try (var input = Files.newInputStream(Path.of(argument.substring("data=".length())))) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error loading " + argument.substring("data=".length()));
        }
        return properties;
    }

}
