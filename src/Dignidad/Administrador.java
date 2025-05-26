package Dignidad;

import java.util.Scanner;
import java.io.Serializable;

public class Administrador extends Persona implements Serializable {
    private String id;
    private String cargo;
    private String contacto;
    private String fecCreCuenta;

    public Administrador() {
        this.id = "";
        this.cargo = "";
        this.contacto = "";
        this.fecCreCuenta = "";
        this.nombre = "";
        this.ci = "";
    }

    public Administrador(String id, String cargo, String contacto, String fecCreCuenta, String nombre, String ci) {
        super(nombre, ci);
        this.id = id;
        this.cargo = cargo;
        this.contacto = contacto;
        this.fecCreCuenta = fecCreCuenta;
    }

    public String getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public String getContacto() {
        return contacto;
    }

    public String getFecCreCuenta() {
        return fecCreCuenta;
    }

    @Override
    public void leer(Scanner lec) {
        System.out.println("Ingrese el ID:");
        this.id = lec.nextLine();
        System.out.println("Ingrese el cargo:");
        this.cargo = lec.nextLine();
        System.out.println("Ingrese el contacto:");
        this.contacto = lec.nextLine();
        System.out.println("Ingrese la fecha de creación (dd/mm/yyyy):");
        this.fecCreCuenta = lec.nextLine();
        System.out.println("Ingrese el nombre:");
        this.nombre = lec.nextLine();
        System.out.println("Ingrese el CI:");
        this.ci = lec.nextLine();
    }

    @Override
    public void mostrar() {
        System.out.println("Administrador: " + nombre);
        System.out.println("ID: " + id);
        System.out.println("Cargo: " + cargo);
        System.out.println("Contacto: " + contacto);
        System.out.println("Fecha de Creación: " + fecCreCuenta);
        System.out.println("CI: " + ci);
        System.out.println("-------------------");
    }
}