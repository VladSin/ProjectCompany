package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.EmployeeUtilApi;
import com.example.projectCompany.controller.config.EmployeeApiConfig;
import com.example.projectCompany.entity.Employee;
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
@FxmlView("infoEmployee.fxml")
public class ExpansionEmployeeFxmlController {

    private final EmployeeUtilApi api = EmployeeApiConfig.getApi();

    public ExpansionEmployeeFxmlController() {
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
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMarried;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfDepartment;

    @FXML
    private TableView<Employee> tvEmployees;
    @FXML
    private TableColumn<Employee, Long> colId;
    @FXML
    private TableColumn<Employee, String> colUsername;
    @FXML
    private TableColumn<Employee, String> colEmail;
    @FXML
    private TableColumn<Employee, Boolean> colMarried;
    @FXML
    private TableColumn<Employee, Double> colSalary;
    @FXML
    private TableColumn<Employee, String> colDepartment;

    @FXML
    public void menuHandleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == miHome) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miReport) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miBack) {
            redirectToAnotherWindow(event, "/fxml/mainEmployee.fxml");
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
