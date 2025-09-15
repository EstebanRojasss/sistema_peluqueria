package salon_belleza.domain.entities;

import java.math.BigDecimal;

public class DetalleTurno {

    private final Integer ID_detalleTurno;
    private final Turno turnoID;
    private final Servicio ServicioID;
    private final BigDecimal montoDelMomento;
    private final Integer cantidad;
    private final BigDecimal comisionEmpleado;
    private final BigDecimal subototal;

    public DetalleTurno(Turno turnoID, Servicio servicioID, BigDecimal montoDelMomento, Integer cantidad, BigDecimal comisionEmpleado, BigDecimal subototal) {
        this.ID_detalleTurno = null;
        this.turnoID = turnoID;
        this.ServicioID = servicioID;
        this.montoDelMomento = montoDelMomento;
        this.cantidad = cantidad;
        this.comisionEmpleado = comisionEmpleado;
        this.subototal = subototal;
    }

    public DetalleTurno(Integer detalleTurnoID,Turno turnoID, Servicio servicioID, BigDecimal montoDelMomento, Integer cantidad, BigDecimal comisionEmpleado, BigDecimal subototal) {
        this.ID_detalleTurno = detalleTurnoID;
        this.turnoID = turnoID;
        this.ServicioID = servicioID;
        this.montoDelMomento = montoDelMomento;
        this.cantidad = cantidad;
        this.comisionEmpleado = comisionEmpleado;
        this.subototal = subototal;
    }
}
