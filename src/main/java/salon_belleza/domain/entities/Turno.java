package salon_belleza.domain.entities;

import java.time.LocalDateTime;

public class Turno {
    private final Integer turnoId;
    private LocalDateTime fechaHora;
    private String empleadoID;
    private String servicioID;


    public Turno(LocalDateTime fechaHora, String empleadoID, String servicioID){
        this.turnoId = null;
        this.fechaHora = fechaHora;
        this.empleadoID = empleadoID;
        this.servicioID = servicioID;
    }

    public Turno(Integer turno_id, LocalDateTime fechaHora, String empleadoID, String servicioID){
        this.turnoId = turno_id;
        this.fechaHora = fechaHora;
        this.empleadoID = empleadoID;
        this.servicioID = servicioID;
    }

    public Integer getTurnoId() {
        return turnoId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getEmpleadoID() {
        return empleadoID;
    }

    public String getServicioID() {
        return servicioID;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setEmpleadoID(String empleadoID) {
        this.empleadoID = empleadoID;
    }

    public void setServicioID(String servicioID) {
        this.servicioID = servicioID;
    }
}
