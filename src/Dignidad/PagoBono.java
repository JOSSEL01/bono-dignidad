package Dignidad;

import java.io.Serializable;

public class PagoBono implements Serializable {
    private String fechaPago;
    private double monto;
    private SolicitudBono solicitud;

    public PagoBono(String fechaPago, double monto, SolicitudBono solicitud) {
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.solicitud = solicitud;
    }

    public PagoBono() {
        this.fechaPago = "";
        this.monto = 0.0;
        this.solicitud = null;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public SolicitudBono getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudBono solicitud) {
        this.solicitud = solicitud;
    }
}