package consulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class moneda {
    private String monedaInicial;
    private String monedaFinal;
    private double montoFinal;
    private LocalDate fechaRegistro;
    private LocalTime horaRegistro;

    public moneda(String monedaInicial, String monedaFinal, double montoFinal, LocalDate fechaRegistro, LocalTime horaRegistro) {
        this.monedaInicial = monedaInicial;
        this.monedaFinal = monedaFinal;
        this.montoFinal = montoFinal;
        this.fechaRegistro = fechaRegistro;
        this.horaRegistro = horaRegistro;
    }

    public moneda(monedaAPI monedaAPI, LocalDate fechaRegistro, LocalTime horaRegistro) {
        this.monedaInicial = monedaAPI.base_code();
        this.monedaFinal = monedaAPI.target_code();
        this.montoFinal = Double.valueOf( monedaAPI.conversion_result());
        this.fechaRegistro = fechaRegistro;
        this.horaRegistro = horaRegistro;
    }

    public String getMonedaInicial() {
        return monedaInicial;
    }

    public String getMonedaFinal() {
        return monedaFinal;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public LocalTime getHoraRegistro() {
        return horaRegistro;
    }

    @Override
    public String toString() {
        return "(moneda{)" +
                "monedaInicial=" + monedaInicial  +
                ", monedaFinal=" + monedaFinal +
                ", montoFinal=" + montoFinal +
                ", fechaRegistro=" + fechaRegistro.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ", horaRegistro=" + horaRegistro.format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
                ")";
    }
}
