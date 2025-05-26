package Dignidad;

public class Persistencia {
    // Almacenamiento en memoria (simula archivo)
    private static String datosGuardados = "";
    private static final String SEPARADOR_REGISTRO = "%%%";
    private static final String SEPARADOR_CAMPO = "###";

    public static void cargarDatos(BonoDignidad[] bonos, int[] numBonos, Beneficiario[] beneficiarios, 
                                 int[] numBeneficiarios, Administrador[] administradores, int[] numAdministradores) {
        if (datosGuardados.isEmpty()) {
            inicializarDatosPorDefecto(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
            return;
        }

        // Dividir los datos guardados en registros
        String[] registros = dividir(datosGuardados, SEPARADOR_REGISTRO);
        
        // El primer registro contiene los contadores
        String[] contadores = dividir(registros[0], SEPARADOR_CAMPO);
        numBonos[0] = Integer.parseInt(contadores[0]);
        numBeneficiarios[0] = Integer.parseInt(contadores[1]);
        numAdministradores[0] = Integer.parseInt(contadores[2]);

        // Procesar bonos
        int indice = 1;
        for (int i = 0; i < numBonos[0]; i++) {
            bonos[i] = parseBono(dividir(registros[indice++], SEPARADOR_CAMPO));
        }

        // Procesar beneficiarios
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            beneficiarios[i] = parseBeneficiario(dividir(registros[indice++], SEPARADOR_CAMPO));
        }

        // Procesar administradores
        for (int i = 0; i < numAdministradores[0]; i++) {
            administradores[i] = parseAdministrador(dividir(registros[indice++], SEPARADOR_CAMPO));
        }
    }

    public static void guardarDatos(BonoDignidad[] bonos, int[] numBonos, Beneficiario[] beneficiarios, 
                                  int[] numBeneficiarios, Administrador[] administradores, int[] numAdministradores) {
        StringBuilder sb = new StringBuilder();

        // Guardar contadores
        sb.append(numBonos[0]).append(SEPARADOR_CAMPO)
          .append(numBeneficiarios[0]).append(SEPARADOR_CAMPO)
          .append(numAdministradores[0]).append(SEPARADOR_REGISTRO);

        // Guardar bonos
        for (int i = 0; i < numBonos[0]; i++) {
            sb.append(serializarBono(bonos[i])).append(SEPARADOR_REGISTRO);
        }

        // Guardar beneficiarios
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            sb.append(serializarBeneficiario(beneficiarios[i])).append(SEPARADOR_REGISTRO);
        }

        // Guardar administradores
        for (int i = 0; i < numAdministradores[0]; i++) {
            sb.append(serializarAdministrador(administradores[i])).append(SEPARADOR_REGISTRO);
        }

        datosGuardados = sb.toString();
    }

    // Métodos auxiliares para serialización
    private static String serializarBono(BonoDignidad bono) {
        return bono.getNombretipo() + SEPARADOR_CAMPO + bono.getFechaIni() + SEPARADOR_CAMPO + 
               bono.getFechaFin() + SEPARADOR_CAMPO + bono.getMonto();
    }

    private static String serializarBeneficiario(Beneficiario beneficiario) {
        return beneficiario.getNombre() + SEPARADOR_CAMPO + beneficiario.getCi() + SEPARADOR_CAMPO + 
               beneficiario.getEdad() + SEPARADOR_CAMPO + beneficiario.getDireccion() + SEPARADOR_CAMPO + 
               beneficiario.getFecha_nacimiento() + SEPARADOR_CAMPO + beneficiario.getTipodiscapacidad() + SEPARADOR_CAMPO + 
               beneficiario.getGradodiscapacidad();
    }

    private static String serializarAdministrador(Administrador admin) {
        return admin.getId() + SEPARADOR_CAMPO + admin.getCargo() + SEPARADOR_CAMPO + 
               admin.getContacto() + SEPARADOR_CAMPO + admin.getFecCreCuenta() + SEPARADOR_CAMPO + 
               admin.getNombre() + SEPARADOR_CAMPO + admin.getCi();
    }

    // Métodos auxiliares para deserialización
    private static BonoDignidad parseBono(String[] campos) {
        return new BonoDignidad(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]));
    }

    private static Beneficiario parseBeneficiario(String[] campos) {
        return new Beneficiario(campos[0], campos[1], Integer.parseInt(campos[2]), campos[3], 
                              campos[4], campos[5], campos[6]);
    }

    private static Administrador parseAdministrador(String[] campos) {
        return new Administrador(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5]);
    }

    // Método para dividir cadenas manualmente
    private static String[] dividir(String cadena, String separador) {
        int count = 1;
        for (int i = 0; i < cadena.length() - separador.length() + 1; i++) {
            if (cadena.substring(i, i + separador.length()).equals(separador)) {
                count++;
                i += separador.length() - 1;
            }
        }

        String[] partes = new String[count];
        int index = 0;
        int start = 0;

        for (int i = 0; i < cadena.length() - separador.length() + 1; i++) {
            if (cadena.substring(i, i + separador.length()).equals(separador)) {
                partes[index++] = cadena.substring(start, i);
                start = i + separador.length();
                i += separador.length() - 1;
            }
        }
        partes[index] = cadena.substring(start);

        return partes;
    }

    private static void inicializarDatosPorDefecto(BonoDignidad[] bonos, int[] numBonos, 
                                                 Beneficiario[] beneficiarios, int[] numBeneficiarios,
                                                 Administrador[] administradores, int[] numAdministradores) {
        // Limpiar arrays
        for (int i = 0; i < 100; i++) {
            bonos[i] = null;
            beneficiarios[i] = null;
            administradores[i] = null;
        }
        
        // Inicializar datos por defecto
        bonos[0] = new BonoDignidad("Bono Anual", "01/01/2025", "31/12/2025", 1000.0);
        numBonos[0] = 1;
        
        beneficiarios[0] = new Beneficiario("Juan Perez", "123456", 65, "Calle 1", "15/05/1960", "Física", "Moderada");
        numBeneficiarios[0] = 1;
        
        administradores[0] = new Administrador("A001", "Gestor", "juan@correo.com", "01/01/2020", "Juan Admin", "654321");
        numAdministradores[0] = 1;
    }
}