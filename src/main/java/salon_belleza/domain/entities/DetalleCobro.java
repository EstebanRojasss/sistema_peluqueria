package salon_belleza.domain.entities;

import java.math.BigDecimal;

public class DetalleCobro {
    private final Integer IDdetalleCobro;
    private final BigDecimal monto;
    private final Cobro cobroID;
    private final FormaPago formaPagoID;


    public DetalleCobro(Integer IDdetalleCobro, BigDecimal monto, Cobro cobroID, FormaPago formaPagoID) {
        this.IDdetalleCobro = IDdetalleCobro;
        this.monto = monto;
        this.cobroID = cobroID;
        this.formaPagoID = formaPagoID;
    }


    public DetalleCobro(BigDecimal monto, Cobro cobroID, FormaPago formaPagoID) {
        this.IDdetalleCobro = null;
        this.monto = monto;
        this.cobroID = cobroID;
        this.formaPagoID = formaPagoID;
    }

    public Integer getIDdetalleCobro() {
        return IDdetalleCobro;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public Cobro getCobroID() {
        return cobroID;
    }

    public FormaPago getFormaPagoID() {
        return formaPagoID;
    }
}
