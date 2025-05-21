package Dignidad;

import java.util.ArrayList;
import java.util.Scanner;

public class Beneficiario extends Persona {
    private int edad;
    private String direccion;
    private String fecha_nacimiento;
    private String tipodiscapacidad;
    private String gradodiscapacidad;
    private ArrayList<SolicitudBono> solicitudes;
    private Scanner lec;

    public Beneficiario(int edad, String direccion, String fecha_nacimiento, String tipodiscapacidad, String gradodiscapacidad, ArrayList<SolicitudBono> solicitudes, String nombre, String ci, Scanner lec) {
        super(nombre, ci);
        this.edad = edad;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tipodiscapacidad = tipodiscapacidad;
        this.gradodiscapacidad = gradodiscapacidad;
        this.solicitudes = (solicitudes != null) ? solicitudes : new ArrayList<>();
        this.lec = lec;
    }

    public Beneficiario() {
        super();
        this.solicitudes = new ArrayList<>();
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTipodiscapacidad() {
        return tipodiscapacidad;
    }

    public void setTipodiscapacidad(String tipodiscapacidad) {
        this.tipodiscapacidad = tipodiscapacidad;
    }

    public String getGradodiscapacidad() {
        return gradodiscapacidad;
    }

    public void setGradodiscapacidad(String gradodiscapacidad) {
        this.gradodiscapacidad = gradodiscapacidad;
    }

    public ArrayList<SolicitudBono> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(ArrayList<SolicitudBono> solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public void leer() {
        System.out.print("Nombre: ");
        this.nombre = lec.nextLine();
        System.out.print("CI: ");
        this.ci = lec.nextLine();
        System.out.print("Edad: ");
        this.edad = Integer.parseInt(lec.nextLine());
        System.out.print("Dirección: ");
        this.direccion = lec.nextLine();
        System.out.print("Fecha de Nacimiento (DD/MM/AAAA): ");
        this.fecha_nacimiento = lec.nextLine();
        System.out.print("Tipo de Discapacidad: ");
        this.tipodiscapacidad = lec.nextLine();
        System.out.print("Grado de Discapacidad: ");
        this.gradodiscapacidad = lec.nextLine();
    }

    @Override
    public void mostrar() {
        System.out.println("Nombre: " + nombre);
        System.out.println("CI: " + ci);
        System.out.println("Edad: " + edad);
        System.out.println("Dirección: " + direccion);
        System.out.println("Fecha de Nacimiento: " + fecha_nacimiento);
        System.out.println("Tipo de Discapacidad: " + tipodiscapacidad);
        System.out.println("Grado de Discapacidad: " + gradodiscapacidad);
        if (!solicitudes.isEmpty()) {
            System.out.println("Solicitudes:");
            for (SolicitudBono solicitud : solicitudes) {
                solicitud.mostrar();
            }
        }
    }

    public String toCSV() {
        return String.join(",",
                escapeCSV(nombre),
                escapeCSV(ci),
                String.valueOf(edad),
                escapeCSV(direccion),
                escapeCSV(fecha_nacimiento),
                escapeCSV(tipodiscapacidad),
                escapeCSV(gradodiscapacidad));
    }

    public static Beneficiario fromCSV(String csv, ArrayList<SolicitudBono> solicitudes) {
        String[] parts = csv.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (parts.length < 7) return null;

        try {
            String nombre = unescapeCSV(parts[0]);
            String ci = unescapeCSV(parts[1]);
            int edad = Integer.parseInt(parts[2]);
            String direccion = unescapeCSV(parts[3]);
            String fechaNacimiento = unescapeCSV(parts[4]);
            String tipoDiscapacidad = unescapeCSV(parts[5]);
            String gradoDiscapacidad = unescapeCSV(parts[6]);

            return new Beneficiario(edad, direccion, fechaNacimiento, tipoDiscapacidad, gradoDiscapacidad, solicitudes, nombre, ci, null);
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