package Dignidad;

public class SolicitudBono {
    private String fechaSolicitud;
    private String estado;
    private Beneficiario beneficiario;

    public SolicitudBono(String fechaSolicitud, String estado, Beneficiario beneficiario) {
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.beneficiario = beneficiario;
    }

    public SolicitudBono() {
        this.fechaSolicitud = "";
        this.estado = "";
        this.beneficiario = null;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }
}