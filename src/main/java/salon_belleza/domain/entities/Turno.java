package salon_belleza.domain.entities;

import java.time.LocalDateTime;
import java.util.Set;

public class Turno {
    private final Integer turnoID;
    private final LocalDateTime fechaHora;
    private final Cliente clienteID;
    private final Empleado empleadoID;
    private final Set<DetalleTurno>serviciosHechos;


    public Turno(LocalDateTime fechaHora, Empleado empleadoID,Cliente clienteID, Set<DetalleTurno>serviciosHechos){
        this.turnoID = null;
        this.fechaHora = fechaHora;
        this.clienteID = clienteID;
        this.empleadoID = empleadoID;
        this.serviciosHechos= serviciosHechos;
    }

    public Set<DetalleTurno> getServiciosHechos() {
        return serviciosHechos;
    }

    public Turno(Integer turno_id, LocalDateTime fechaHora, Empleado empleadoID, Cliente clienteID, Set<DetalleTurno> serviciosHechos){
        this.turnoID = turno_id;
        this.fechaHora = fechaHora;
        this.clienteID = clienteID;
        this.empleadoID = empleadoID;
        this.serviciosHechos = serviciosHechos;
    }

    public Integer getTurnoID() {
        return turnoID;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Cliente getClienteID() {
        return clienteID;
    }

    public Empleado getEmpleadoID() {
        return empleadoID;
    }

}
