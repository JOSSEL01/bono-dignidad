package Dignidad;

import java.util.Scanner;

public class BonoDignidad {
    private String nombretipo;
    private String fechaIni;
    private String fechaFin;
    private double monto;
    private SolicitudBono[] regisSoli;
    private PagoBono[] regisPago;
    private int numSolicitudes;
    private int numPagos;
    private static final int MAX_REGISTROS = 100;

    public BonoDignidad() {
        this.regisSoli = new SolicitudBono[MAX_REGISTROS];
        this.regisPago = new PagoBono[MAX_REGISTROS];
        this.numSolicitudes = 0;
        this.numPagos = 0;
    }

    public BonoDignidad(String nombretipo, String fechaIni, String fechaFin, double monto) {
        this.nombretipo = nombretipo;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.monto = monto;
        this.regisSoli = new SolicitudBono[MAX_REGISTROS];
        this.regisPago = new PagoBono[MAX_REGISTROS];
        this.numSolicitudes = 0;
        this.numPagos = 0;
    }

    public void leer(Beneficiario[] beneficiarios, int numBeneficiarios, Scanner scanner) {
        System.out.print("Ingrese el tipo de bono: ");
        this.nombretipo = scanner.nextLine().trim();
        System.out.print("Ingrese la fecha de inicio (dd/mm/yyyy): ");
        this.fechaIni = scanner.nextLine().trim();
        System.out.print("Ingrese la fecha de fin (dd/mm/yyyy): ");
        this.fechaFin = scanner.nextLine().trim();
        System.out.print("Ingrese el monto: ");
        this.monto = Double.parseDouble(scanner.nextLine().trim());

        // Si hay beneficiarios, permitir asociar uno al bono
        if (numBeneficiarios > 0) {
            System.out.println("Lista de Beneficiarios:");
            for (int i = 0; i < numBeneficiarios; i++) {
                System.out.println((i + 1) + ". " + beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
            }
            System.out.print("Seleccione un beneficiario para asociar al bono (0 para no asociar): ");
            int indice = Integer.parseInt(scanner.nextLine().trim());
            if (indice > 0 && indice <= numBeneficiarios) {
                SolicitudBono solicitud = new SolicitudBono(this.fechaIni, "Aprobada", beneficiarios[indice - 1]);
                agregarSolicitud(solicitud);
            }
        }
    }

    public void mostrar() {
        System.out.println("Tipo: " + nombretipo);
        System.out.println("Fecha Inicio: " + fechaIni);
        System.out.println("Fecha Fin: " + fechaFin);
        System.out.println("Monto: " + monto);
    }

    // Getters y setters
    public String getNombretipo() { return nombretipo; }
    public void setNombretipo(String nombretipo) { this.nombretipo = nombretipo; }
    public String getFechaIni() { return fechaIni; }
    public void setFechaIni(String fechaIni) { this.fechaIni = fechaIni; }
    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public SolicitudBono[] getRegisSoli() { return regisSoli; }
    public int getNumSolicitudes() { return numSolicitudes; }
    public void setNumSolicitudes(int numSolicitudes) { this.numSolicitudes = numSolicitudes; }
    public PagoBono[] getRegisPago() { return regisPago; }
    public int getNumPagos() { return numPagos; }
    public void setNumPagos(int numPagos) { this.numPagos = numPagos; }
    public void agregarSolicitud(SolicitudBono solicitud) {
        if (numSolicitudes < MAX_REGISTROS) {
            regisSoli[numSolicitudes] = solicitud;
            numSolicitudes++;
        }
    }
    public void agregarPago(PagoBono pago) {
        if (numPagos < MAX_REGISTROS) {
            regisPago[numPagos] = pago;
            numPagos++;
        }
    }
}