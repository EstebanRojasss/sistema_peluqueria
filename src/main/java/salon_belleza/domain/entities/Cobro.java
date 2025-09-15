package salon_belleza.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class Cobro {
    private final Integer ID_cobro;
    private final LocalDateTime fecha;
    private final BigDecimal montoRecibido;
    private final BigDecimal total;
    private final BigDecimal vuelto;
    private final Set<DetalleCobro> cobros;
    private final Caja cajaID;

    public Cobro(LocalDateTime fecha, BigDecimal montoRecibido, BigDecimal total, BigDecimal vuelto, Set<DetalleCobro> cobros, Caja cajaID) {
        this.ID_cobro = null;
        this.fecha = fecha;
        this.montoRecibido = montoRecibido;
        this.total = total;
        this.vuelto = vuelto;
        this.cobros = cobros;
        this.cajaID = cajaID;
    }

    public Cobro(Integer ID_cobro, LocalDateTime fecha, BigDecimal montoRecibido, BigDecimal total, BigDecimal vuelto, Set<DetalleCobro> cobros, Caja cajaID) {
        this.ID_cobro = ID_cobro;
        this.fecha = fecha;
        this.montoRecibido = montoRecibido;
        this.total = total;
        this.vuelto = vuelto;
        this.cobros = cobros;
        this.cajaID = cajaID;
    }

    public Integer getID_cobro() {
        return ID_cobro;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public BigDecimal getMontoRecibido() {
        return montoRecibido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getVuelto() {
        return vuelto;
    }

    public Set<DetalleCobro> getCobros() {
        return cobros;
    }

    public Caja getCajaID() {
        return cajaID;
    }
}
