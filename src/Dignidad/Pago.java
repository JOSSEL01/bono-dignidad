package Dignidad;

public class Pago {
    private String fechaPago;
    private double monto;
    private SolicitudBono solicitud;

    public Pago(String fechaPago, double monto, SolicitudBono solicitud) {
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.solicitud = solicitud;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public SolicitudBono getSolicitud() {
        return solicitud;
    }
}