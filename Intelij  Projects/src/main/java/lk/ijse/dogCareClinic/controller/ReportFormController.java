package lk.ijse.dogCareClinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dogCareClinic.db.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class ReportFormController {



        @FXML
        private JFXButton btnBackButton;

        @FXML
        private JFXButton btnEmployee;

        @FXML
        private JFXButton btnInventory;

        @FXML
        private JFXButton btnOwnerDetail;

        @FXML
        private AnchorPane MainReportPane;

        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
            MainReportPane.getChildren().clear();
            MainReportPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashboard-form.fxml"))));

        }



        @FXML
        void btnInventoryOnAction(ActionEvent event) throws JRException, SQLException {
            ReportbtnOnActhion();

            /*InputStream resource = this.getClass().getResourceAsStream("/reports/inventory_details.jrxml");
            try {
                JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
                JasperViewer.viewReport(jasperPrint, false);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }


    void ReportbtnOnActhion() throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/Inventory_details.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT * FROM inventory_details");
        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
}

        @FXML
        void btnOwnerOnAction(ActionEvent event) {
            InputStream resource = this.getClass().getResourceAsStream("/reports/OwnerReport.jrxml");
            try {
                JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
                JasperViewer.viewReport(jasperPrint, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @FXML
        void btnEmployeeOnAction(ActionEvent event) {

                InputStream resource = this.getClass().getResourceAsStream("/reports/Employee.jrxml");
                try {
                    JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
                    JasperViewer.viewReport(jasperPrint, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


