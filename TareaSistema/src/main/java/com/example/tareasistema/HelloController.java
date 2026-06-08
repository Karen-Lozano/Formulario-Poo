package com.example.tareasistema;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEdad;

    @FXML
    private ComboBox<String> cmbCarrera;

    @FXML
    private ListView<String> listMaterias;

    @FXML
    private Label lblEncuesta;

    @FXML
    private TextField txtNumero1;

    @FXML
    private TextField txtNumero2;

    @FXML
    private ComboBox<String> cmbOperacion;

    @FXML
    private TextField txtResultado;

    @FXML
    private ListView<String> listHistorial;

    @FXML
    public void initialize() {

        cmbCarrera.setItems(
                FXCollections.observableArrayList(
                        "Ingeniería en Sistemas",
                        "Software",
                        "Industrial",
                        "Civil",
                        "Electrónica"
                )
        );

        cmbOperacion.setItems(
                FXCollections.observableArrayList(
                        "Suma",
                        "Resta",
                        "Multiplicación",
                        "División",
                        "Potencia",
                        "Promedio"
                )
        );

        listMaterias.setItems(
                FXCollections.observableArrayList(
                        "Programación",
                        "Base de Datos",
                        "Redes",
                        "Matemáticas",
                        "Física",
                        "Estadística"
                )
        );

        listMaterias.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        txtResultado.setEditable(false);
    }

    @FXML
    private void registrarEncuesta() {

        String nombre = txtNombre.getText().trim();
        String edad = txtEdad.getText().trim();
        String carrera = cmbCarrera.getValue();

        if(nombre.isEmpty() || edad.isEmpty() || carrera == null) {

            lblEncuesta.setText(
                    "Complete todos los campos."
            );
            return;
        }

        lblEncuesta.setText(
                "Registro guardado para: " + nombre
        );
    }

    @FXML
    private void limpiarEncuesta() {

        txtNombre.clear();
        txtEdad.clear();

        cmbCarrera.setValue(null);

        listMaterias.getSelectionModel()
                .clearSelection();

        lblEncuesta.setText("");
    }

    @FXML
    private void calcular() {

        try {

            double n1 =
                    Double.parseDouble(txtNumero1.getText());

            double n2 =
                    Double.parseDouble(txtNumero2.getText());

            String op = cmbOperacion.getValue();

            if(op == null) {

                txtResultado.setText(
                        "Seleccione una operación"
                );
                return;
            }

            double resultado = 0;

            String historial = "";

            switch (op) {

                case "Suma":
                    resultado = n1 + n2;
                    historial = n1 + " + " + n2 + " = " + resultado;
                    break;

                case "Resta":
                    resultado = n1 - n2;
                    historial = n1 + " - " + n2 + " = " + resultado;
                    break;

                case "Multiplicación":
                    resultado = n1 * n2;
                    historial = n1 + " × " + n2 + " = " + resultado;
                    break;

                case "División":

                    if(n2 == 0) {

                        txtResultado.setText(
                                "No dividir para cero"
                        );
                        return;
                    }

                    resultado = n1 / n2;
                    historial = n1 + " ÷ " + n2 + " = " + resultado;
                    break;

                case "Potencia":
                    resultado = Math.pow(n1, n2);
                    historial = n1 + "^" + n2 + " = " + resultado;
                    break;

                case "Promedio":
                    resultado = (n1 + n2) / 2;
                    historial = "Promedio = " + resultado;
                    break;
            }

            txtResultado.setText(
                    String.format("%.2f", resultado)
            );

            listHistorial.getItems().add(0, historial);

        }
        catch (Exception e) {

            txtResultado.setText(
                    "Ingrese números válidos"
            );
        }
    }

    @FXML
    private void limpiarCalculo() {

        txtNumero1.clear();
        txtNumero2.clear();
        txtResultado.clear();

        cmbOperacion.setValue(null);
    }

    @FXML
    private void borrarHistorial() {

        listHistorial.getItems().clear();
    }
}