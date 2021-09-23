package com.example.projectCompany;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class JavaFXApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(ProjectCompanyApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/fxml/index.fxml";

        InputStream iconStream = getClass().getResourceAsStream("/icon/bird.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);

        primaryStage.setTitle("VladSin Application");
        primaryStage.setScene(createScene(loadMainPane(fxmlFile)));
        primaryStage.show();
    }

    private Pane loadMainPane(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(path));
        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        return scene;
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
