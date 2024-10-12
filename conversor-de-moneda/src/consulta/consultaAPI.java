package consulta;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class consultaAPI {

    public monedaAPI buscaMoneda(String monedaBuscada, String monedaResultado, double monto) {
        try {
            String direccion = "https://v6.exchangerate-api.com/v6/a1d090fbc60b368c599727eb/pair/" +
                    monedaBuscada.toUpperCase() + "/" +
                    monedaResultado.toUpperCase() + "/" +
                    monto;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), monedaAPI.class);

        } catch (Exception e) {
            throw new RuntimeException("No fue posible realizar un cambio desde " +
                    "la moneda: " + monedaBuscada +
                    " a moneda: " + monedaResultado +
                    " debido a: " + e.getMessage());
        }
    }

    public Map<String, String> obtenerMonedasDisponibles() {
        try {
            String direccion = "https://v6.exchangerate-api.com/v6/a1d090fbc60b368c599727eb/codes";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            JsonArray codigos = jsonResponse.getAsJsonArray("supported_codes");

            Map<String, String> monedasMap = new HashMap<>();

            for (int i = 0; i < codigos.size(); i++) {
                JsonArray parMoneda = codigos.get(i).getAsJsonArray();
                String codigo = parMoneda.get(0).getAsString();
                String nombre = parMoneda.get(1).getAsString();
                monedasMap.put(codigo, nombre);
            }

            return monedasMap;

        } catch (Exception e) {
            throw new RuntimeException("No fue posible obtener las monedas disponibles debido a: " + e.getMessage());
        }
    }
}
