package com.example.projectCompany.controller.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@FxmlView("/fxml/index.fxml")
public class MainFxmlController {

    @FXML
    private MenuItem miLogout;
    @FXML
    private MenuItem miCompany;
    @FXML
    private MenuItem miDepartment;
    @FXML
    private MenuItem miEmployee;

    @FXML
    private static StackPane stackPaneHolder;

    public static void setPane(Node node) {
        if (!stackPaneHolder.getChildren().isEmpty()) {
            //if stackPaneHolder is empty
            stackPaneHolder.getChildren().add(node);


        } else {
            if (stackPaneHolder.getClip() != node) {
                //if stackPaneHolder is not empty then remove existing layer and add new layer
                stackPaneHolder.getChildren().remove(0);
                stackPaneHolder.getChildren().add(0, node);
            }
        }
    }

    public void menuHandleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == miLogout) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miCompany) {
            redirectToAnotherWindow(event, "/fxml/company.fxml");
        } else if (event.getSource() == miDepartment) {
            redirectToAnotherWindow(event, "/fxml/department.fxml");
        } else if (event.getSource() == miEmployee) {
            redirectToAnotherWindow(event, "/fxml/employee.fxml");
        }

    }

    public void redirectToAnotherWindow(ActionEvent event, String url) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(url));

        InputStream iconStream = getClass().getResourceAsStream("/icon/bird.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);

        stage.setTitle("VladSin Application");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
