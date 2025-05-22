package Dignidad;

public class Persistencia {
    public static void cargarDatos(BonoDignidad[] bonos, int[] numBonos, Beneficiario[] beneficiarios, int[] numBeneficiarios, Administrador[] administradores, int[] numAdministradores) {
        // Simulación de carga de datos
        bonos[0] = new BonoDignidad("Bono Anual", "01/01/2025", "31/12/2025", 1000.0);
        numBonos[0] = 1;

        beneficiarios[0] = new Beneficiario("Juan Perez", "123456", 65, "Calle 1", "15/05/1960", "Física", "Moderada");
        numBeneficiarios[0] = 1;

        administradores[0] = new Administrador("A001", "Gestor", "juan@correo.com", "01/01/2020", "Juan Admin", "654321");
        numAdministradores[0] = 1;
    }

    public static void guardarDatos(BonoDignidad[] bonos, int[] numBonos, Beneficiario[] beneficiarios, int[] numBeneficiarios, Administrador[] administradores, int[] numAdministradores) {
        // Simulación de guardado (no hace nada en este caso)
    }
}