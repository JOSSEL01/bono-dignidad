package Dignidad;

import java.util.Scanner;

public class BonoDignidad {
    private String nombretipo;
    private String fechaIni;
    private String fechaFin;
    private double monto;
    private SolicitudBono[] regisSoli;
    private int numSolicitudes;
    private Pago[] regisPago;
    private int numPagos;

    public BonoDignidad() {
        this.nombretipo = "";
        this.fechaIni = "";
        this.fechaFin = "";
        this.monto = 0.0;
        this.regisSoli = new SolicitudBono[100];
        this.numSolicitudes = 0;
        this.regisPago = new Pago[100];
        this.numPagos = 0;
    }

    public BonoDignidad(String nombretipo, String fechaIni, String fechaFin, double monto) {
        this.nombretipo = nombretipo;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.monto = monto;
        this.regisSoli = new SolicitudBono[100];
        this.numSolicitudes = 0;
        this.regisPago = new Pago[100];
        this.numPagos = 0;
    }

    public String getNombretipo() {
        return nombretipo;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public double getMonto() {
        return monto;
    }

    public void agregarSolicitud(SolicitudBono solicitud) {
        regisSoli[numSolicitudes] = solicitud;
        numSolicitudes++;
    }

    public SolicitudBono[] getRegisSoli() {
        return regisSoli;
    }

    public int getNumSolicitudes() {
        return numSolicitudes;
    }

    public void setNumSolicitudes(int numSolicitudes) {
        this.numSolicitudes = numSolicitudes;
    }

    public void agregarPago(Pago pago) {
        regisPago[numPagos] = pago;
        numPagos++;
    }

    public Pago[] getRegisPago() {
        return regisPago;
    }

    public int getNumPagos() {
        return numPagos;
    }

    public void leer(Beneficiario[] beneficiarios, int numBeneficiarios, Scanner lec) {
        System.out.println("Ingrese el tipo de bono:");
        this.nombretipo = lec.nextLine();
        System.out.println("Ingrese la fecha de inicio (dd/mm/yyyy):");
        this.fechaIni = lec.nextLine();
        System.out.println("Ingrese la fecha de fin (dd/mm/yyyy):");
        this.fechaFin = lec.nextLine();
        System.out.println("Ingrese el monto:");
        this.monto = Double.parseDouble(lec.nextLine());

        if (numBeneficiarios > 0) {
            System.out.println("Beneficiarios disponibles:");
            for (int i = 0; i < numBeneficiarios; i++) {
                System.out.println((i + 1) + ". " + beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
            }
            System.out.println("Seleccione un beneficiario (1-" + numBeneficiarios + ") o 0 para no asignar:");
            int opcion = Integer.parseInt(lec.nextLine());
            if (opcion > 0 && opcion <= numBeneficiarios) {
                SolicitudBono solicitud = new SolicitudBono(this.fechaIni, "Aprobada", beneficiarios[opcion - 1]);
                agregarSolicitud(solicitud);
            }
        }
    }

    public void mostrar() {
        System.out.println("Bono: " + nombretipo);
        System.out.println("Fecha Inicio: " + fechaIni);
        System.out.println("Fecha Fin: " + fechaFin);
        System.out.println("Monto: " + monto);
        System.out.println("Solicitudes:");
        for (int i = 0; i < numSolicitudes; i++) {
            System.out.println("  - Fecha Solicitud: " + regisSoli[i].getFechaSolicitud());
            System.out.println("    Estado: " + regisSoli[i].getEstado());
            System.out.println("    Pagada: " + (regisSoli[i].isPagada() ? "Sí" : "No"));
            if (regisSoli[i].getBeneficiario() != null) {
                System.out.println("    Beneficiario: " + regisSoli[i].getBeneficiario().getNombre());
            }
        }
        System.out.println("Pagos:");
        for (int i = 0; i < numPagos; i++) {
            System.out.println("  - Fecha Pago: " + regisPago[i].getFechaPago());
            System.out.println("    Monto: " + regisPago[i].getMonto());
        }
        System.out.println("-------------------");
    }
}