package Dignidad;

import java.util.ArrayList;
import java.util.Scanner;

public class SolicitudBono {
    private String fechaSolicitud;
    private String estado;
    private boolean pagada;
    private Beneficiario beneficiario;
    private Scanner lec;

    public SolicitudBono(String fechaSolicitud, String estado, boolean pagada, Beneficiario beneficiario, Scanner lec) {
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.pagada = pagada;
        this.beneficiario = beneficiario;
        this.lec = lec;
    }

    public SolicitudBono() {
        this.pagada = false;
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

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public void leer() {
        System.out.print("Fecha de Solicitud (DD/MM/AAAA): ");
        this.fechaSolicitud = lec.nextLine();
        System.out.print("Estado de la solicitud: ");
        this.estado = lec.nextLine();
    }

    public void mostrar() {
        System.out.println("  Fecha de Solicitud: " + fechaSolicitud);
        System.out.println("  Estado: " + estado);
        System.out.println("  Pagada: " + (pagada ? "Sí" : "No"));
        if (beneficiario != null) {
            System.out.println("  Beneficiario: " + beneficiario.getNombre() + " (CI: " + beneficiario.getCi() + ")");
        }
    }

    public String toCSV() {
        String ciBeneficiario = (beneficiario != null) ? beneficiario.getCi() : "";
        return String.join(",",
                escapeCSV(fechaSolicitud),
                escapeCSV(estado),
                String.valueOf(pagada),
                escapeCSV(ciBeneficiario));
    }

    public static SolicitudBono fromCSV(String csv, ArrayList<Beneficiario> beneficiarios) {
        String[] parts = csv.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (parts.length < 4) return null;

        String fechaSolicitud = unescapeCSV(parts[0]);
        String estado = unescapeCSV(parts[1]);
        boolean pagada = Boolean.parseBoolean(parts[2]);
        String ciBeneficiario = unescapeCSV(parts[3]);

        Beneficiario beneficiario = null;
        if (!ciBeneficiario.isEmpty()) {
            for (Beneficiario b : beneficiarios) {
                if (b.getCi().equals(ciBeneficiario)) {
                    beneficiario = b;
                    break;
                }
            }
        }

        SolicitudBono solicitud = new SolicitudBono(fechaSolicitud, estado, pagada, beneficiario, null);
        if (beneficiario != null) {
            beneficiario.getSolicitudes().add(solicitud);
        }
        return solicitud;
    }

    private static String escapeCSV(String field) {
        if (field == null) return "";
        if (field.contains(",") || field.contains("\"")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }

    private static String unescapeCSV(String field) {
        if (field == null) return "";
        if (field.startsWith("\"") && field.endsWith("\"")) {
            return field.substring(1, field.length() - 1).replace("\"\"", "\"");
        }
        return field;
    }
}