package Dignidad;

import java.util.ArrayList;
import java.util.Scanner;

public class BonoDignidad {
    private String nombretipo;
    private String fechaIni;
    private String fechaFin;
    private ArrayList<SolicitudBono> regisSoli;
    private ArrayList<PagoBono> regisPago;
    private Scanner lec;

    public BonoDignidad(String nombretipo, String fechaIni, String fechaFin, Scanner lec) {
        this.nombretipo = nombretipo;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.regisSoli = new ArrayList<>();
        this.regisPago = new ArrayList<>();
        this.lec = lec;
    }

    public BonoDignidad() {
        this.regisSoli = new ArrayList<>();
        this.regisPago = new ArrayList<>();
    }

    public String getNombretipo() {
        return nombretipo;
    }

    public void setNombretipo(String nombretipo) {
        this.nombretipo = nombretipo;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<SolicitudBono> getRegisSoli() {
        return regisSoli;
    }

    public void setRegisSoli(ArrayList<SolicitudBono> regisSoli) {
        this.regisSoli = regisSoli;
    }

    public ArrayList<PagoBono> getRegisPago() {
        return regisPago;
    }

    public void setRegisPago(ArrayList<PagoBono> regisPago) {
        this.regisPago = regisPago;
    }

    public void leer(ArrayList<Beneficiario> beneficiarios) {
        System.out.println("\n--- Registro de Bono ---");
        System.out.print("Nombre del tipo de bono: ");
        this.nombretipo = lec.nextLine();
        System.out.print("Fecha de Inicio (DD/MM/AAAA): ");
        this.fechaIni = lec.nextLine();
        System.out.print("Fecha de Fin (DD/MM/AAAA): ");
        this.fechaFin = lec.nextLine();

        System.out.print("¿Desea registrar una solicitud para este bono? (S/N): ");
        if (lec.nextLine().trim().toUpperCase().startsWith("S")) {
            if (beneficiarios.isEmpty()) {
                System.out.println("No hay beneficiarios registrados para asignar una solicitud.");
                return;
            }
            System.out.println("Lista de Beneficiarios:");
            for (int i = 0; i < beneficiarios.size(); i++) {
                System.out.println((i + 1) + ". " + beneficiarios.get(i).getNombre() + " (CI: " + beneficiarios.get(i).getCi() + ")");
            }
            System.out.print("Seleccione el número del beneficiario: ");
            try {
                int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
                if (indice >= 0 && indice < beneficiarios.size()) {
                    Beneficiario beneficiario = beneficiarios.get(indice);
                    SolicitudBono solicitud = new SolicitudBono();
                    solicitud.leer();
                    solicitud.setBeneficiario(beneficiario);
                    regisSoli.add(solicitud);
                    beneficiario.getSolicitudes().add(solicitud);

                    System.out.print("¿Desea registrar un pago para esta solicitud? (S/N): ");
                    if (lec.nextLine().trim().toUpperCase().startsWith("S")) {
                        PagoBono pago = new PagoBono();
                        pago.leer();
                        pago.setSolicitud(solicitud);
                        regisPago.add(pago);
                        solicitud.setPagada(true);
                    }
                } else {
                    System.out.println("Índice inválido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
    }

    public void mostrar() {
        System.out.println("Tipo de Bono: " + nombretipo);
        System.out.println("Fecha de Inicio: " + fechaIni);
        System.out.println("Fecha de Fin: " + fechaFin);
        if (!regisSoli.isEmpty()) {
            System.out.println("Solicitudes:");
            for (SolicitudBono solicitud : regisSoli) {
                solicitud.mostrar();
            }
        }
        if (!regisPago.isEmpty()) {
            System.out.println("Pagos:");
            for (PagoBono pago : regisPago) {
                pago.mostrar();
            }
        }
    }

    public String toCSV() {
        return String.join(",",
                escapeCSV(nombretipo),
                escapeCSV(fechaIni),
                escapeCSV(fechaFin));
    }

    public static BonoDignidad fromCSV(String csv, ArrayList<Beneficiario> beneficiarios) {
        String[] parts = csv.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (parts.length < 3) return null;

        String nombretipo = unescapeCSV(parts[0]);
        String fechaIni = unescapeCSV(parts[1]);
        String fechaFin = unescapeCSV(parts[2]);

        return new BonoDignidad(nombretipo, fechaIni, fechaFin, null);
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