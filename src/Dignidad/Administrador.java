package Dignidad;

import java.util.Scanner;

public class Administrador extends Persona {
    private String id;
    private String cargo;
    private double contacto;
    private String fecCreCuenta;
    private Scanner lec;

    public Administrador(String id, String cargo, double contacto, String fecCreCuenta, String nombre, String ci) {
        super(nombre, ci);
        this.id = id;
        this.cargo = cargo;
        this.contacto = contacto;
        this.fecCreCuenta = fecCreCuenta;
    }

    public Administrador() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getContacto() {
        return contacto;
    }

    public void setContacto(double contacto) {
        this.contacto = contacto;
    }

    public String getFecCreCuenta() {
        return fecCreCuenta;
    }

    public void setFecCreCuenta(String fecCreCuenta) {
        this.fecCreCuenta = fecCreCuenta;
    }

    @Override
    public void leer() {
        lec = new Scanner(System.in);
        System.out.print("ID: ");
        this.id = lec.nextLine();
        System.out.print("Cargo: ");
        this.cargo = lec.nextLine();
        System.out.print("Contacto: ");
        this.contacto = Double.parseDouble(lec.nextLine());
        System.out.print("Fecha de Creación de Cuenta (DD/MM/AAAA): ");
        this.fecCreCuenta = lec.nextLine();
        System.out.print("Nombre: ");
        this.nombre = lec.nextLine();
        System.out.print("CI: ");
        this.ci = lec.nextLine();
    }

    @Override
    public void mostrar() {
        System.out.println("ID: " + id);
        System.out.println("Cargo: " + cargo);
        System.out.println("Contacto: " + contacto);
        System.out.println("Fecha de Creación: " + fecCreCuenta);
        System.out.println("Nombre: " + nombre);
        System.out.println("CI: " + ci);
    }

    public String toCSV() {
        return String.join(",",
                escapeCSV(id),
                escapeCSV(cargo),
                String.valueOf(contacto),
                escapeCSV(fecCreCuenta),
                escapeCSV(nombre),
                escapeCSV(ci));
    }

    public static Administrador fromCSV(String csv) {
        String[] parts = csv.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (parts.length < 6) return null;

        try {
            String id = unescapeCSV(parts[0]);
            String cargo = unescapeCSV(parts[1]);
            double contacto = Double.parseDouble(parts[2]);
            String fecCreCuenta = unescapeCSV(parts[3]);
            String nombre = unescapeCSV(parts[4]);
            String ci = unescapeCSV(parts[5]);

            return new Administrador(id, cargo, contacto, fecCreCuenta, nombre, ci);
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