package salon_belleza.infraestructure.factory.pagoFactory;

import salon_belleza.domain.constants.TipoPago;
import salon_belleza.domain.entities.FormaPago;

import java.util.HashMap;
import java.util.Map;

public class PagoFactory {

    public static Map<TipoPago, Pago>pagos = new HashMap<>(){
        {
            pagos.put(TipoPago.EFECTIVO, new PagoEfectivo());
            pagos.put(TipoPago.TRANSFERENCIA, new PagoTransferencia());
            pagos.put(TipoPago.CHEQUE, new PagoCheque());
        }
    };


    public Pago obtenerPago(FormaPago formaPago){
        return pagos.get(formaPago.getTipoPago());
    }
}
