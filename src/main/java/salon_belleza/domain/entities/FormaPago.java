package salon_belleza.domain.entities;

import salon_belleza.domain.constants.TipoPago;

public class FormaPago {
    private final Integer IDformaPago;
    private final TipoPago tipoPago;


    public FormaPago(TipoPago tipoPago) {
        this.IDformaPago = null;
        this.tipoPago = tipoPago;
    }

    public FormaPago(Integer IDformaPago, TipoPago tipoPago){
        this.IDformaPago = IDformaPago;
        this.tipoPago = tipoPago;
    }

    public Integer getIDformaPago() {
        return IDformaPago;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }
}
