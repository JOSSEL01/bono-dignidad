package Dignidad;

public class Administrador {
    private String id;
    private String cargo;
    private String contacto;
    private String fecCreCuenta;
    private String nombre;
    private String ci;

    public Administrador() {}

    public Administrador(String id, String cargo, String contacto, String fecCreCuenta, String nombre, String ci) {
        this.id = id;
        this.cargo = cargo;
        this.contacto = contacto;
        this.fecCreCuenta = fecCreCuenta;
        this.nombre = nombre;
        this.ci = ci;
    }

    public void leer(java.util.Scanner scanner) {
        // Este método se usa solo en modo consola
        System.out.print("Ingrese el ID: ");
        this.id = scanner.nextLine().trim();
        System.out.print("Ingrese el cargo: ");
        this.cargo = scanner.nextLine().trim();
        System.out.print("Ingrese el contacto: ");
        this.contacto = scanner.nextLine().trim();
        System.out.print("Ingrese la fecha de creación (dd/mm/yyyy): ");
        this.fecCreCuenta = scanner.nextLine().trim();
        System.out.print("Ingrese el nombre: ");
        this.nombre = scanner.nextLine().trim();
        System.out.print("Ingrese la CI: ");
        this.ci = scanner.nextLine().trim();
    }

    public void mostrar() {
        System.out.println("ID: " + id);
        System.out.println("Cargo: " + cargo);
        System.out.println("Contacto: " + contacto);
        System.out.println("Fecha Creación: " + fecCreCuenta);
        System.out.println("Nombre: " + nombre);
        System.out.println("CI: " + ci);
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public String getFecCreCuenta() { return fecCreCuenta; }
    public void setFecCreCuenta(String fecCreCuenta) { this.fecCreCuenta = fecCreCuenta; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }
}