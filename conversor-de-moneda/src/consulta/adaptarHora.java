package consulta;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class adaptarHora extends TypeAdapter<LocalTime> {

    @Override
    public void write(JsonWriter jsonWriter, LocalTime localTime) throws IOException {
        jsonWriter.value(localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Override
    public LocalTime read(JsonReader jsonReader) throws IOException {
        return LocalTime.parse(jsonReader.nextString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
