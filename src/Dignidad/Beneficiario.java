package Dignidad;

import java.util.Scanner;

public class Beneficiario {
    private String nombre;
    private String ci;
    private int edad;
    private String direccion;
    private String fecha_nacimiento;
    private String tipodiscapacidad;
    private String gradodiscapacidad;

    public Beneficiario() {
        this.nombre = "";
        this.ci = "";
        this.edad = 0;
        this.direccion = "";
        this.fecha_nacimiento = "";
        this.tipodiscapacidad = "";
        this.gradodiscapacidad = "";
    }

    public Beneficiario(String nombre, String ci, int edad, String direccion, String fecha_nacimiento, String tipodiscapacidad, String gradodiscapacidad) {
        this.nombre = nombre;
        this.ci = ci;
        this.edad = edad;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tipodiscapacidad = tipodiscapacidad;
        this.gradodiscapacidad = gradodiscapacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCi() {
        return ci;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getTipodiscapacidad() {
        return tipodiscapacidad;
    }

    public String getGradodiscapacidad() {
        return gradodiscapacidad;
    }

    public void leer(Scanner lec) {
        System.out.println("Ingrese el nombre:");
        this.nombre = lec.nextLine();
        System.out.println("Ingrese el CI:");
        this.ci = lec.nextLine();
        System.out.println("Ingrese la edad:");
        this.edad = Integer.parseInt(lec.nextLine());
        System.out.println("Ingrese la dirección:");
        this.direccion = lec.nextLine();
        System.out.println("Ingrese la fecha de nacimiento (dd/mm/yyyy):");
        this.fecha_nacimiento = lec.nextLine();
        System.out.println("Ingrese el tipo de discapacidad:");
        this.tipodiscapacidad = lec.nextLine();
        System.out.println("Ingrese el grado de discapacidad:");
        this.gradodiscapacidad = lec.nextLine();
    }

    public void mostrar() {
        System.out.println("Beneficiario: " + nombre);
        System.out.println("CI: " + ci);
        System.out.println("Edad: " + edad);
        System.out.println("Dirección: " + direccion);
        System.out.println("Fecha de Nacimiento: " + fecha_nacimiento);
        System.out.println("Tipo de Discapacidad: " + tipodiscapacidad);
        System.out.println("Grado de Discapacidad: " + gradodiscapacidad);
        System.out.println("-------------------");
    }
}