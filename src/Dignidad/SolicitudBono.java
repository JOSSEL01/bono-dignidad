package Dignidad;

import java.io.Serializable;

public class SolicitudBono implements Serializable {
    private String fechaSolicitud;
    private String estado;
    private Beneficiario beneficiario;
    private boolean pagada;

    public SolicitudBono(String fechaSolicitud, String estado, Beneficiario beneficiario) {
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.beneficiario = beneficiario;
        this.pagada = false;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
}