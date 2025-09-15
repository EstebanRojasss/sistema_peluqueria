package salon_belleza.domain.entities;

import salon_belleza.domain.constants.EstadoCaja;

import java.math.BigDecimal;

public class Caja {
    private final Integer IDcaja;
    private BigDecimal montoInicial;
    private BigDecimal montoFinal;
    private BigDecimal cierreCaja;
    private EstadoCaja estado;


    public Caja(Integer IDcaja, BigDecimal montoInicial, BigDecimal montoFinal, BigDecimal cierreCaja, EstadoCaja estado) {
        this.IDcaja = IDcaja;
        this.montoInicial = montoInicial;
        this.montoFinal = montoFinal;
        this.cierreCaja = cierreCaja;
        this.estado = estado;
    }

    public Caja(BigDecimal montoInicial, BigDecimal montoFinal, BigDecimal cierreCaja, EstadoCaja estado) {
        this.IDcaja = null;
        this.montoInicial = montoInicial;
        this.montoFinal = montoFinal;
        this.cierreCaja = cierreCaja;
        this.estado = estado;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public void setMontoFinal(BigDecimal montoFinal) {
        this.montoFinal = montoFinal;
    }

    public void setCierreCaja(BigDecimal cierreCaja) {
        this.cierreCaja = cierreCaja;
    }

    public void setEstado(EstadoCaja estado) {
        this.estado = estado;
    }

    public Integer getIDcaja() {
        return IDcaja;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public BigDecimal getMontoFinal() {
        return montoFinal;
    }

    public BigDecimal getCierreCaja() {
        return cierreCaja;
    }

    public EstadoCaja getEstado() {
        return estado;
    }
}
