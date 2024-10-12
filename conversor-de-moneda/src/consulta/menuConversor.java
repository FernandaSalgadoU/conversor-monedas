package consulta;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

public class menuConversor {

    private final List<Map.Entry<String, String>> monedasOrdenadas;

    public menuConversor(Map<String, String> monedasDisponibles) {
        monedasOrdenadas = new ArrayList<>(monedasDisponibles.entrySet());
        monedasOrdenadas.sort(Entry.comparingByKey());
    }

    public String mostrarMenuMonedaInicial() {
        String[] opciones = new String[monedasOrdenadas.size()];
        for (int i = 0; i < monedasOrdenadas.size(); i++) {
            Entry<String, String> moneda = monedasOrdenadas.get(i);
            opciones[i] = moneda.getKey() + " - " + moneda.getValue();
        }

        String seleccion = (String) JOptionPane.showInputDialog(null,
                "Seleccione la moneda inicial:",
                "Conversor de monedas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (seleccion == null) {
            System.out.println("El usuario canceló la operación.");
            System.exit(0);
        }

        return seleccion.split(" - ")[0];
    }

    public String mostrarMenuMonedaFinal(String monedaInicial) {
        List<String> opcionesFiltradas = new ArrayList<>();
        for (Entry<String, String> moneda : monedasOrdenadas) {
            if (!moneda.getKey().equals(monedaInicial)) {
                opcionesFiltradas.add(moneda.getKey() + " - " + moneda.getValue());
            }
        }

        String[] opciones = opcionesFiltradas.toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(null,
                "Seleccione la moneda final:",
                "Menú de Monedas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (seleccion == null) {
            System.out.println("El usuario canceló la operación.");
            System.exit(0);
        }

        return seleccion.split(" - ")[0];
    }
}
