package com.example.projectCompany.controller;

import com.example.projectCompany.controller.fxml.MainFxmlController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class Navigator {

    private final String index;
    private final String mainCompany;
    private final String mainDepartment;
    private final String mainEmployee;
    private final String infoCompany;
    private final String infoDepartment;
    private final String infoEmployee;
    private final String login;
    private final String register;

    public String getIndex() {
        return index;
    }

    public String getMainCompany() {
        return mainCompany;
    }

    public String getMainDepartment() {
        return mainDepartment;
    }

    public String getMainEmployee() {
        return mainEmployee;
    }

    public String getInfoCompany() {
        return infoCompany;
    }

    public String getInfoDepartment() {
        return infoDepartment;
    }

    public String getInfoEmployee() {
        return infoEmployee;
    }

    public String getLogin() {
        return login;
    }

    public String getRegister() {
        return register;
    }

    private static MainFxmlController controller;

    public static void loadPane(String fxml) {

        try {
            MainFxmlController.setPane((Node) FXMLLoader.load(Navigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Navigator() throws IOException {
        this.index = "/fxml/index.fxml";
        this.mainCompany = "/fxml/mainCompany.fxml";
        this.mainDepartment = "/fxml/mainDepartment.fxml";
        this.mainEmployee = "/fxml/mainEmployee.fxml";
        this.infoCompany = "/fxml/infoCompany.fxml";
        this.infoDepartment = "/fxml/infoDepartment.fxml";
        this.infoEmployee = "/fxml/infoEmployee.fxml";
        this.login = "/fxml/login.fxml";
        this.register = "/fxml/register.fxml";
    }
}
