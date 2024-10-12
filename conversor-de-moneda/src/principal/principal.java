package principal;

import consulta.consultaAPI;
import consulta.menuConversor;
import consulta.moneda;
import consulta.monedaAPI;
import consulta.generadorArchivo;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class principal {

    public static void main(String[] args) throws IOException {

        consultaAPI consulta = new consultaAPI();
        Map<String, String> monedasDisponibles = consulta.obtenerMonedasDisponibles();
        menuConversor menu = new menuConversor(monedasDisponibles);

        List<moneda> lista = new ArrayList<>();

        while (true) {
            String monedaInicial = menu.mostrarMenuMonedaInicial();
            String monedaFinal = menu.mostrarMenuMonedaFinal(monedaInicial);

            double montoInicial = Double.parseDouble(javax.swing.JOptionPane.showInputDialog(
                    "Por favor, ingrese el monto a cambiar:"));

            monedaAPI cambioAPI = consulta.buscaMoneda(monedaInicial, monedaFinal, montoInicial);

            LocalDate fechaActual = LocalDate.now();
            LocalTime horaActual = LocalTime.now();

            moneda cambio = new moneda(cambioAPI, fechaActual, horaActual);
            lista.add(cambio);

            System.out.println("\n" + cambio + "\n");

            int respuesta = JOptionPane.showConfirmDialog(null,
                    "¿Desea realizar otra conversión?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.NO_OPTION) {
                break;
            }
        }
        generadorArchivo generador = new generadorArchivo();
        generador.guardarJson(lista);
    }
}
