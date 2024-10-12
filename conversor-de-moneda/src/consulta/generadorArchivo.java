package consulta;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class generadorArchivo {
    public void guardarJson(List<moneda> lista) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new adaptarFecha())
                .registerTypeAdapter(LocalTime.class, new adaptarHora())
                .create();

        FileWriter escritura = null;

        escritura = new FileWriter("Cambios.json");
        escritura.write(gson.toJson(lista));
        escritura.close();
    }
}
