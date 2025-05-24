package Dignidad;

import java.util.Scanner;

public class Main {
    private static final int MAX_REGISTROS = 100;
    private static BonoDignidad[] bonos;
    private static Beneficiario[] beneficiarios;
    private static Administrador[] administradores;
    private static int[] numBonos;
    private static int[] numBeneficiarios;
    private static int[] numAdministradores;
    private static Scanner lec = new Scanner(System.in);

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        bonos = new BonoDignidad[MAX_REGISTROS];
        beneficiarios = new Beneficiario[MAX_REGISTROS];
        administradores = new Administrador[MAX_REGISTROS];
        numBonos = new int[1];
        numBeneficiarios = new int[1];
        numAdministradores = new int[1];
        numBonos[0] = 0;
        numBeneficiarios[0] = 0;
        numAdministradores[0] = 0;

        Persistencia.cargarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);

        System.out.println(ANSI_CYAN + "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┃     BIENVENIDO AL SISTEMA BONO DIGNIDAD       ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Seleccione el modo de ejecucion:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1. Nodo 1 - Consola" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2. Nodo 2 - Interfaz Grafica" + ANSI_RESET);
        System.out.print(ANSI_BLUE + "Ingrese su opcion (1 o 2): " + ANSI_RESET);

        int modo = obtenerOpcionModo();

        if (modo == 1) {
            ejecutarModoConsola();
        } else if (modo == 2) {
            ejecutarModoGrafico();
        } else {
            System.out.println(ANSI_YELLOW + "Opción no válida. El programa se cerrará." + ANSI_RESET);
        }

        Persistencia.guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        lec.close();
    }

    private static int obtenerOpcionModo() {
        try {
            return Integer.parseInt(lec.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void ejecutarModoConsola() {
        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion();
            switch (opcion) {
                case 1:
                    registrarBono();
                    break;
                case 2:
                    registrarBeneficiario();
                    break;
                case 3:
                    registrarAdministrador();
                    break;
                case 4:
                    mostrarBonos();
                    break;
                case 5:
                    mostrarBeneficiarios();
                    break;
                case 6:
                    mostrarAdministradores();
                    break;
                case 7:
                    mostrarBeneficiariosPorTipoDiscapacidad();
                    break;
                case 8:
                    calcularMontoTotalPorPeriodo();
                    break;
                case 9:
                    verificarSolicitudesProcesadas();
                    break;
                case 10:
                    mostrarFechasBonoBeneficiario();
                    break;
                case 11:
                    listarBeneficiariosConPagos();
                    break;
                case 12:
                    System.out.println(ANSI_YELLOW + "Saliendo del sistema..." + ANSI_RESET);
                    break;
                case 13:
                    borrarRegistro();
                    break;
                default:
                    System.out.println(ANSI_YELLOW + "Opción no válida. Intente de nuevo." + ANSI_RESET);
            }
        } while (opcion != 12);
    }

    private static void ejecutarModoGrafico() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BonoDignidadGUI(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores).setVisible(true);
            }
        });
    }

    private static void mostrarMenu() {
        System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┃        SISTEMA BONO DIGNIDAD        ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 1. Registrar Bono                  ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 2. Registrar Beneficiario          ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 3. Registrar Administrador         ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 4. Mostrar Bonos                   ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 5. Mostrar Beneficiarios           ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 6. Mostrar Administradores         ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 7. Beneficiarios por Discapacidad  ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 8. Monto Total Pagado por Período  ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 9. Verificar Solicitudes Pagadas   ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 10. Fechas de Bono por Beneficiario┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 11. Beneficiarios con Pagos        ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 12. Salir                          ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 13. Borrar Registro                ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
        System.out.print(ANSI_BLUE + "Seleccione una opción: " + ANSI_RESET);
    }

    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(lec.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void registrarBono() {
        if (numBonos[0] >= MAX_REGISTROS) {
            System.out.println(ANSI_YELLOW + "No se pueden agregar más bonos, límite alcanzado." + ANSI_RESET);
            return;
        }
        BonoDignidad bono = new BonoDignidad();
        bono.leer(beneficiarios, numBeneficiarios[0], lec);
        bonos[numBonos[0]] = bono;
        numBonos[0]++;
        System.out.println(ANSI_GREEN + "Bono registrado exitosamente." + ANSI_RESET);
    }

    private static void registrarBeneficiario() {
        System.out.println(ANSI_YELLOW + "\n--- Registro de Beneficiario ---" + ANSI_RESET);
        if (numBeneficiarios[0] >= MAX_REGISTROS) {
            System.out.println(ANSI_YELLOW + "No se pueden agregar más beneficiarios, límite alcanzado." + ANSI_RESET);
            return;
        }
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.leer(lec);
        beneficiarios[numBeneficiarios[0]] = beneficiario;
        numBeneficiarios[0]++;
        System.out.println(ANSI_GREEN + "Beneficiario registrado exitosamente." + ANSI_RESET);
    }

    private static void registrarAdministrador() {
        System.out.println(ANSI_YELLOW + "\n--- Registro de Administrador ---" + ANSI_RESET);
        if (numAdministradores[0] >= MAX_REGISTROS) {
            System.out.println(ANSI_YELLOW + "No se pueden agregar más administradores, límite alcanzado." + ANSI_RESET);
            return;
        }
        Administrador administrador = new Administrador();
        administrador.leer(lec);
        administradores[numAdministradores[0]] = administrador;
        numAdministradores[0]++;
        System.out.println(ANSI_GREEN + "Administrador registrado exitosamente." + ANSI_RESET);
    }

    private static void mostrarBonos() {
        if (numBonos[0] == 0) {
            System.out.println(ANSI_YELLOW + "\nNo hay bonos registrados." + ANSI_RESET);
            return;
        }
        for (int i = 0; i < numBonos[0]; i++) {
            System.out.println(ANSI_CYAN + "\nBono #" + (i + 1) + ANSI_RESET);
            bonos[i].mostrar();
        }
    }

    private static void mostrarBeneficiarios() {
        if (numBeneficiarios[0] == 0) {
            System.out.println(ANSI_YELLOW + "No hay beneficiarios registrados." + ANSI_RESET);
            return;
        }
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            System.out.println(ANSI_CYAN + "Beneficiario #" + (i + 1) + ANSI_RESET);
            beneficiarios[i].mostrar();
        }
    }

    private static void mostrarAdministradores() {
        if (numAdministradores[0] == 0) {
            System.out.println(ANSI_YELLOW + "\nNo hay administradores registrados." + ANSI_RESET);
            return;
        }
        for (int i = 0; i < numAdministradores[0]; i++) {
            System.out.println(ANSI_CYAN + "\nAdministrador #" + (i + 1) + ANSI_RESET);
            administradores[i].mostrar();
        }
    }

    private static void mostrarBeneficiariosPorTipoDiscapacidad() {
        if (numBeneficiarios[0] == 0) {
            System.out.println(ANSI_YELLOW + "\nNo hay beneficiarios registrados." + ANSI_RESET);
            return;
        }

        int[] conteoDiscapacidades = new int[10]; // Asumimos un límite de 10 tipos de discapacidad diferentes
        String[] tipos = new String[10];
        int tiposCount = 0;

        for (int i = 0; i < numBeneficiarios[0]; i++) {
            String tipo = beneficiarios[i].getTipodiscapacidad();
            boolean encontrado = false;
            for (int j = 0; j < tiposCount; j++) {
                if (tipos[j].equals(tipo)) {
                    conteoDiscapacidades[j]++;
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado && tiposCount < 10) {
                tipos[tiposCount] = tipo;
                conteoDiscapacidades[tiposCount]++;
                tiposCount++;
            }
        }

        System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┃  BENEFICIARIOS POR TIPO DISCAPACIDAD ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
        for (int i = 0; i < tiposCount; i++) {
            System.out.println("┃ " + tipos[i] + ": " + conteoDiscapacidades[i] + " beneficiarios");
        }
        System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
    }

    private static void calcularMontoTotalPorPeriodo() {
        System.out.print(ANSI_BLUE + "Ingrese la fecha de inicio del período (DD/MM/AAAA): " + ANSI_RESET);
        String fechaInicioStr = lec.nextLine().trim();
        System.out.print(ANSI_BLUE + "Ingrese la fecha de fin del período (DD/MM/AAAA): " + ANSI_RESET);
        String fechaFinStr = lec.nextLine().trim();

        // Validar formato básico de las fechas (dd/MM/yyyy)
        if (!validarFormatoFecha(fechaInicioStr) || !validarFormatoFecha(fechaFinStr)) {
            System.out.println(ANSI_YELLOW + "Formato de fecha inválido. Use DD/MM/AAAA." + ANSI_RESET);
            return;
        }

        double montoTotal = 0.0;
        for (int i = 0; i < numBonos[0]; i++) {
            for (int j = 0; j < bonos[i].getNumPagos(); j++) {
                String fechaPago = bonos[i].getRegisPago()[j].getFechaPago();
                if (!validarFormatoFecha(fechaPago)) {
                    System.out.println(ANSI_YELLOW + "Fecha de pago inválida: " + fechaPago + ANSI_RESET);
                    continue;
                }
                // Comparar fechas como cadenas
                if (fechaEsDentroDelRango(fechaPago, fechaInicioStr, fechaFinStr)) {
                    montoTotal += bonos[i].getRegisPago()[j].getMonto();
                }
            }
        }

        System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┃       MONTO TOTAL PAGADO            ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
        System.out.println("┃ Período: " + fechaInicioStr + " - " + fechaFinStr);
        System.out.println("┃ Monto Total: " + montoTotal);
        System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
    }

    // Método para validar el formato básico de la fecha (dd/MM/yyyy)
    private static boolean validarFormatoFecha(String fecha) {
        if (fecha == null || !fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }
        String[] partes = fecha.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);
        return dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && anio >= 1900 && anio <= 9999;
    }

    // Método para comparar fechas como cadenas
    private static boolean fechaEsDentroDelRango(String fechaPago, String fechaInicio, String fechaFin) {
        // Convertir fechas a un valor comparable (por ejemplo, AAAAMMDD)
        long valorFechaPago = convertirFechaAValor(fechaPago);
        long valorFechaInicio = convertirFechaAValor(fechaInicio);
        long valorFechaFin = convertirFechaAValor(fechaFin);

        // Verificar si fechaPago está dentro del rango [fechaInicio, fechaFin]
        return valorFechaPago >= valorFechaInicio && valorFechaPago <= valorFechaFin;
    }

    // Método para convertir una fecha en formato dd/MM/yyyy a un valor numérico comparable
    private static long convertirFechaAValor(String fecha) {
        String[] partes = fecha.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);
        return anio * 10000L + mes * 100L + dia; // Formato AAAAMMDD
    }

    private static void verificarSolicitudesProcesadas() {
        if (numBonos[0] == 0) {
            System.out.println(ANSI_YELLOW + "\nNo hay bonos registrados." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┃     VERIFICACIÓN DE SOLICITUDES     ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
        for (int i = 0; i < numBonos[0]; i++) {
            System.out.println("┃ Bono: " + bonos[i].getNombretipo());
            for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                SolicitudBono solicitud = bonos[i].getRegisSoli()[j];
                System.out.println("┃ - Fecha: " + solicitud.getFechaSolicitud());
                System.out.println("┃   Beneficiario: " + (solicitud.getBeneficiario() != null ? solicitud.getBeneficiario().getNombre() : "No asignado"));
                System.out.println("┃   Estado: " + solicitud.getEstado());
                System.out.println("┃   Pagada: " + (solicitud.isPagada() ? "Sí" : "No"));
            }
        }
        System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
    }

    private static void mostrarFechasBonoBeneficiario() {
        if (numBeneficiarios[0] == 0) {
            System.out.println(ANSI_YELLOW + "\nNo hay beneficiarios registrados." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_YELLOW + "\n--- Seleccione un Beneficiario ---" + ANSI_RESET);
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            System.out.println((i + 1) + ". " + beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
        }
        System.out.print(ANSI_BLUE + "Ingrese el número del beneficiario: " + ANSI_RESET);
        try {
            int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
            if (indice >= 0 && indice < numBeneficiarios[0]) {
                Beneficiario beneficiario = beneficiarios[indice];
                System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "┃       FECHAS DE BONOS               ┃" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
                System.out.println("┃ Beneficiario: " + beneficiario.getNombre());
                boolean tieneBono = false;
                for (int i = 0; i < numBonos[0]; i++) {
                    for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                        if (bonos[i].getRegisSoli()[j].getBeneficiario() == beneficiario) {
                            System.out.println("┃ - Bono: " + bonos[i].getNombretipo());
                            System.out.println("┃   Inicio: " + bonos[i].getFechaIni());
                            System.out.println("┃   Fin: " + bonos[i].getFechaFin());
                            tieneBono = true;
                        }
                    }
                }
                if (!tieneBono) {
                    System.out.println("┃ No tiene bonos asignados.");
                }
                System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
            } else {
                System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
        }
    }

    private static void listarBeneficiariosConPagos() {
        System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┃    BENEFICIARIOS CON PAGOS          ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
        boolean hayPagos = false;
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            for (int j = 0; j < numBonos[0]; j++) {
                for (int k = 0; k < bonos[j].getNumPagos(); k++) {
                    if (bonos[j].getRegisPago()[k].getSolicitud() != null && 
                        bonos[j].getRegisPago()[k].getSolicitud().getBeneficiario() == beneficiarios[i]) {
                        System.out.println("┃ - " + beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
                        hayPagos = true;
                        break;
                    }
                }
            }
        }
        if (!hayPagos) {
            System.out.println("┃ No hay beneficiarios con pagos.");
        }
        System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
    }

    private static void borrarRegistro() {
        System.out.println(ANSI_YELLOW + "\n--- Borrar Registro ---" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1. Borrar Bono" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2. Borrar Beneficiario" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3. Borrar Administrador" + ANSI_RESET);
        System.out.print(ANSI_BLUE + "Seleccione el tipo de registro a borrar (1-3): " + ANSI_RESET);

        int tipo = obtenerOpcion();
        switch (tipo) {
            case 1:
                if (numBonos[0] == 0) {
                    System.out.println(ANSI_YELLOW + "No hay bonos registrados para borrar." + ANSI_RESET);
                    return;
                }
                mostrarBonos();
                System.out.print(ANSI_BLUE + "Ingrese el número del bono a borrar: " + ANSI_RESET);
                try {
                    int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
                    if (indice >= 0 && indice < numBonos[0]) {
                        System.out.print(ANSI_YELLOW + "¿Está seguro de borrar este bono? (S/N): " + ANSI_RESET);
                        String confirmacion = lec.nextLine().trim().toUpperCase();
                        if (confirmacion.startsWith("S")) {
                            for (int i = indice; i < numBonos[0] - 1; i++) {
                                bonos[i] = bonos[i + 1];
                            }
                            bonos[numBonos[0] - 1] = null;
                            numBonos[0]--;
                            System.out.println(ANSI_GREEN + "Bono y datos asociados eliminados exitosamente." + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_YELLOW + "Operación de borrado cancelada." + ANSI_RESET);
                        }
                    } else {
                        System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
                }
                break;
            case 2:
                if (numBeneficiarios[0] == 0) {
                    System.out.println(ANSI_YELLOW + "No hay beneficiarios registrados para borrar." + ANSI_RESET);
                    return;
                }
                mostrarBeneficiarios();
                System.out.print(ANSI_BLUE + "Ingrese el número del beneficiario a borrar: " + ANSI_RESET);
                try {
                    int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
                    if (indice >= 0 && indice < numBeneficiarios[0]) {
                        System.out.print(ANSI_YELLOW + "¿Está seguro de borrar este beneficiario? (S/N): " + ANSI_RESET);
                        String confirmacion = lec.nextLine().trim().toUpperCase();
                        if (confirmacion.startsWith("S")) {
                            for (int i = 0; i < numBonos[0]; i++) {
                                for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                                    if (bonos[i].getRegisSoli()[j].getBeneficiario() == beneficiarios[indice]) {
                                        for (int k = j; k < bonos[i].getNumSolicitudes() - 1; k++) {
                                            bonos[i].getRegisSoli()[k] = bonos[i].getRegisSoli()[k + 1];
                                        }
                                        bonos[i].getRegisSoli()[bonos[i].getNumSolicitudes() - 1] = null;
                                        bonos[i].setNumSolicitudes(bonos[i].getNumSolicitudes() - 1);
                                    }
                                }
                            }
                            for (int i = indice; i < numBeneficiarios[0] - 1; i++) {
                                beneficiarios[i] = beneficiarios[i + 1];
                            }
                            beneficiarios[numBeneficiarios[0] - 1] = null;
                            numBeneficiarios[0]--;
                            System.out.println(ANSI_GREEN + "Beneficiario y solicitudes asociadas eliminados exitosamente." + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_YELLOW + "Operación de borrado cancelada." + ANSI_RESET);
                        }
                    } else {
                        System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
                }
                break;
            case 3:
                if (numAdministradores[0] == 0) {
                    System.out.println(ANSI_YELLOW + "No hay administradores registrados para borrar." + ANSI_RESET);
                    return;
                }
                mostrarAdministradores();
                System.out.print(ANSI_BLUE + "Ingrese el número del administrador a borrar: " + ANSI_RESET);
                try {
                    int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
                    if (indice >= 0 && indice < numAdministradores[0]) {
                        System.out.print(ANSI_YELLOW + "¿Está seguro de borrar este administrador? (S/N): " + ANSI_RESET);
                        String confirmacion = lec.nextLine().trim().toUpperCase();
                        if (confirmacion.startsWith("S")) {
                            for (int i = indice; i < numAdministradores[0] - 1; i++) {
                                administradores[i] = administradores[i + 1];
                            }
                            administradores[numAdministradores[0] - 1] = null;
                            numAdministradores[0]--;
                            System.out.println(ANSI_GREEN + "Administrador eliminado exitosamente." + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_YELLOW + "Operación de borrado cancelada." + ANSI_RESET);
                        }
                    } else {
                        System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
                }
                break;
            default:
                System.out.println(ANSI_YELLOW + "Opción no válida. Intente de nuevo." + ANSI_RESET);
        }
    }
}