package Dignidad;

public class Beneficiario {
    private String nombre;
    private String ci;
    private int edad;
    private String direccion;
    private String fecha_nacimiento;
    private String tipodiscapacidad;
    private String gradodiscapacidad;

    public Beneficiario() {}

    public Beneficiario(String nombre, String ci, int edad, String direccion, String fecha_nacimiento, String tipodiscapacidad, String gradodiscapacidad) {
        this.nombre = nombre;
        this.ci = ci;
        this.edad = edad;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tipodiscapacidad = tipodiscapacidad;
        this.gradodiscapacidad = gradodiscapacidad;
    }

    public void leer(java.util.Scanner scanner) {
        // Este método se usa solo en modo consola
        System.out.print("Ingrese el nombre: ");
        this.nombre = scanner.nextLine().trim();
        System.out.print("Ingrese la CI: ");
        this.ci = scanner.nextLine().trim();
        System.out.print("Ingrese la edad: ");
        this.edad = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Ingrese la dirección: ");
        this.direccion = scanner.nextLine().trim();
        System.out.print("Ingrese la fecha de nacimiento (dd/mm/yyyy): ");
        this.fecha_nacimiento = scanner.nextLine().trim();
        System.out.print("Ingrese el tipo de discapacidad: ");
        this.tipodiscapacidad = scanner.nextLine().trim();
        System.out.print("Ingrese el grado de discapacidad: ");
        this.gradodiscapacidad = scanner.nextLine().trim();
    }

    public void mostrar() {
        System.out.println("Nombre: " + nombre);
        System.out.println("CI: " + ci);
        System.out.println("Edad: " + edad);
        System.out.println("Dirección: " + direccion);
        System.out.println("Fecha Nacimiento: " + fecha_nacimiento);
        System.out.println("Tipo Discapacidad: " + tipodiscapacidad);
        System.out.println("Grado Discapacidad: " + gradodiscapacidad);
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getFecha_nacimiento() { return fecha_nacimiento; }
    public void setFecha_nacimiento(String fecha_nacimiento) { this.fecha_nacimiento = fecha_nacimiento; }
    public String getTipodiscapacidad() { return tipodiscapacidad; }
    public void setTipodiscapacidad(String tipodiscapacidad) { this.tipodiscapacidad = tipodiscapacidad; }
    public String getGradodiscapacidad() { return gradodiscapacidad; }
    public void setGradodiscapacidad(String gradodiscapacidad) { this.gradodiscapacidad = gradodiscapacidad; }
}