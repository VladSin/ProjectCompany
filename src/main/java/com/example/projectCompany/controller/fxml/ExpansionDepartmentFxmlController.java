package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.DepartmentUtilApi;
import com.example.projectCompany.controller.config.DepartmentApiConfig;
import com.example.projectCompany.entity.Department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@FxmlView("/fxml/infoDepartment.fxml")
public class ExpansionDepartmentFxmlController {

    private final DepartmentUtilApi api = DepartmentApiConfig.getApi();

    public ExpansionDepartmentFxmlController() {
    }

    @FXML
    private MenuItem miHome;
    @FXML
    private MenuItem miReport;
    @FXML
    private MenuItem miBack;

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLocation;
    @FXML
    private TextField tfWebSite;
    @FXML
    private TextField tfCompany;

    @FXML
    private TableView<Department> tvDepartment;
    @FXML
    private TableColumn<Department, Long> colId;
    @FXML
    private TableColumn<Department, String> colName;
    @FXML
    private TableColumn<Department, String> colLocation;
    @FXML
    private TableColumn<Department, String> colWebSite;
    @FXML
    private TableColumn<Department, String> colCompany;

    @FXML
    public void menuHandleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == miHome) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miReport) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miBack) {
            redirectToAnotherWindow(event, "/fxml/mainDepartment.fxml");
        }

    }

    public void redirectToAnotherWindow(ActionEvent event, String url) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(url));

        InputStream iconStream = getClass().getResourceAsStream("/icon/bird.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);

        stage.setTitle("VladSin Company");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
