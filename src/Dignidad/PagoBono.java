package Dignidad;

import java.util.ArrayList;
import java.util.Scanner;

public class PagoBono {
    private String fechaPago;
    private double monto;
    private SolicitudBono solicitud;
    private Scanner lec;

    public PagoBono(String fechaPago, double monto, SolicitudBono solicitud, Scanner lec) {
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.solicitud = solicitud;
        this.lec = lec;
    }

    public PagoBono() {
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

    public void leer() {
        System.out.print("Fecha de Pago (DD/MM/AAAA): ");
        this.fechaPago = lec.nextLine();
        System.out.print("Monto del Pago: ");
        this.monto = Double.parseDouble(lec.nextLine());
    }

    public void mostrar() {
        System.out.println("  Fecha de Pago: " + fechaPago);
        System.out.println("  Monto: " + monto);
        if (solicitud != null) {
            System.out.println("  Solicitud asociada - Fecha: " + solicitud.getFechaSolicitud());
        }
    }

    public String toCSV() {
        String solicitudFecha = (solicitud != null) ? solicitud.getFechaSolicitud() : "";
        return String.join(",",
                escapeCSV(fechaPago),
                String.valueOf(monto),
                escapeCSV(solicitudFecha));
    }

    public static PagoBono fromCSV(String csv, ArrayList<SolicitudBono> solicitudes) {
        String[] parts = csv.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (parts.length < 3) return null;

        try {
            String fechaPago = unescapeCSV(parts[0]);
            double monto = Double.parseDouble(parts[1]);
            String solicitudFecha = unescapeCSV(parts[2]);

            SolicitudBono solicitud = null;
            if (!solicitudFecha.isEmpty()) {
                for (SolicitudBono s : solicitudes) {
                    if (s.getFechaSolicitud().equals(solicitudFecha)) {
                        solicitud = s;
                        break;
                    }
                }
            }

            PagoBono pago = new PagoBono(fechaPago, monto, solicitud, null);
            return pago;
        } catch (NumberFormatException e) {
            return null;
        }
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