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
                case 14:
                    registrarPagoConsola();
                    break;
                case 15:
                    generarReporteGeneral();
                    break;
                case 16:
                    mostrarBeneficiariosPorTipoDiscapacidad();
                    break;
                case 17:
                    calcularMontoTotalPorPeriodo();
                    break;
                case 18:
                    verificarSolicitudesProcesadas();
                    break;
                case 19:
                    mostrarFechasBonoBeneficiario();
                    break;
                case 20:
                    mostrarBeneficiariosConMultiplesBonos();
                    break;
                case 21:
                    calcularTiempoTotalPago();
                    break;
                case 22:
                    editarBonoConsola();
                    break;
                case 23:
                    editarBeneficiarioConsola();
                    break;
                case 24:
                    editarAdministradorConsola();
                    break;
                case 25:
                    borrarBonoConsola();
                    break;
                case 26:
                    borrarBeneficiarioConsola();
                    break;
                case 27:
                    borrarAdministradorConsola();
                    break;
                default:
                    System.out.println(ANSI_YELLOW + "Opción no válida. Intente de nuevo." + ANSI_RESET);
            }
        } while (opcion != 12);
    }

    private static void ejecutarModoGrafico() {
        java.awt.EventQueue.invokeLater(() -> {
            new BonoDignidadGUI(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores).setVisible(true);
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
        System.out.println(ANSI_GREEN + "┃ 8. Monto Total Pagado por Periodo  ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 9. Verificar Solicitudes Pagadas   ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 10. Fechas de Bono por Beneficiario┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 11. Beneficiarios con Pagos        ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 12. Salir                          ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 13. Borrar Registro                ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 14. Registrar Pago                 ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 15. Generar Reporte General        ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 16. Beneficiarios por Discapacidad ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 17. Monto por Periodo              ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 18. Verificar Solicitudes          ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 19. Fechas por Beneficiario        ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 20. Multiples Bonos                ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 21. Tiempo Total Pago              ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 22. Editar Bono                    ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 23. Editar Beneficiario            ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 24. Editar Administrador           ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 25. Borrar Bono                    ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 26. Borrar Beneficiario            ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 27. Borrar Administrador           ┃" + ANSI_RESET);
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
        Persistencia.agregarBono(bonos, numBonos, bono, beneficiarios, numBeneficiarios, administradores, numAdministradores);
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
        Persistencia.agregarBeneficiario(beneficiarios, numBeneficiarios, beneficiario, bonos, numBonos, administradores, numAdministradores);
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
        Persistencia.agregarAdministrador(administradores, numAdministradores, administrador, bonos, numBonos, beneficiarios, numBeneficiarios);
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

        int[] conteoDiscapacidades = new int[10];
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

    private static boolean fechaEsDentroDelRango(String fechaPago, String fechaInicio, String fechaFin) {
        long valorFechaPago = convertirFechaAValor(fechaPago);
        long valorFechaInicio = convertirFechaAValor(fechaInicio);
        long valorFechaFin = convertirFechaAValor(fechaFin);
        return valorFechaPago >= valorFechaInicio && valorFechaPago <= valorFechaFin;
    }

    private static long convertirFechaAValor(String fecha) {
        String[] partes = fecha.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);
        return anio * 10000L + mes * 100L + dia;
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
                borrarBonoConsola();
                break;
            case 2:
                borrarBeneficiarioConsola();
                break;
            case 3:
                borrarAdministradorConsola();
                break;
            default:
                System.out.println(ANSI_YELLOW + "Opción no válida. Intente de nuevo." + ANSI_RESET);
        }
    }

    private static void registrarPagoConsola() {
        if (numBonos[0] == 0) {
            System.out.println(ANSI_YELLOW + "No hay bonos registrados para registrar un pago." + ANSI_RESET);
            return;
        }
        System.out.println(ANSI_YELLOW + "\n--- Seleccione un Bono ---" + ANSI_RESET);
        for (int i = 0; i < numBonos[0]; i++) {
            System.out.println((i + 1) + ". " + bonos[i].getNombretipo() + " (Inicio: " + bonos[i].getFechaIni() + ")");
        }
        System.out.print(ANSI_BLUE + "Ingrese el número del bono: " + ANSI_RESET);
        try {
            int bonoIndex = Integer.parseInt(lec.nextLine().trim()) - 1;
            if (bonoIndex >= 0 && bonoIndex < numBonos[0]) {
                BonoDignidad bono = bonos[bonoIndex];
                SolicitudBono solicitud = null;
                if (bono.getNumSolicitudes() > 0) {
                    System.out.println(ANSI_YELLOW + "\n--- Seleccione una Solicitud ---" + ANSI_RESET);
                    for (int i = 0; i < bono.getNumSolicitudes(); i++) {
                        SolicitudBono s = bono.getRegisSoli()[i];
                        String beneficiario = s.getBeneficiario() != null ? s.getBeneficiario().getNombre() : "Sin beneficiario";
                        System.out.println((i + 1) + ". Solicitud " + (i + 1) + " - Fecha: " + s.getFechaSolicitud() + " - Beneficiario: " + beneficiario);
                    }
                    System.out.println((bono.getNumSolicitudes() + 1) + ". Crear nueva solicitud");
                    System.out.print(ANSI_BLUE + "Ingrese el número de la solicitud: " + ANSI_RESET);
                    int solicitudIndex = Integer.parseInt(lec.nextLine().trim()) - 1;
                    if (solicitudIndex == bono.getNumSolicitudes()) {
                        solicitud = crearNuevaSolicitudConsola(bono);
                        if (solicitud == null) return;
                    } else if (solicitudIndex >= 0 && solicitudIndex < bono.getNumSolicitudes()) {
                        solicitud = bono.getRegisSoli()[solicitudIndex];
                    } else {
                        System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
                        return;
                    }
                } else {
                    solicitud = crearNuevaSolicitudConsola(bono);
                    if (solicitud == null) return;
                }
                System.out.print(ANSI_BLUE + "Ingrese la fecha de pago (DD/MM/AAAA): " + ANSI_RESET);
                String fechaPago = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Ingrese el monto: " + ANSI_RESET);
                double monto = Double.parseDouble(lec.nextLine().trim());
                if (!validarFormatoFecha(fechaPago)) {
                    System.out.println(ANSI_YELLOW + "Formato de fecha inválido. Use DD/MM/AAAA." + ANSI_RESET);
                    return;
                }
                PagoBono pago = new PagoBono(fechaPago, monto, solicitud);
                Persistencia.agregarPago(bono, pago, bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
                System.out.println(ANSI_GREEN + "Pago registrado exitosamente." + ANSI_RESET);
            } else {
                System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
        }
    }

    private static SolicitudBono crearNuevaSolicitudConsola(BonoDignidad bono) {
        if (numBeneficiarios[0] == 0) {
            System.out.println(ANSI_YELLOW + "No hay beneficiarios registrados para asociar una solicitud." + ANSI_RESET);
            return null;
        }
        System.out.println(ANSI_YELLOW + "\n--- Seleccione un Beneficiario ---" + ANSI_RESET);
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            System.out.println((i + 1) + ". " + beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
        }
        System.out.print(ANSI_BLUE + "Ingrese el número del beneficiario: " + ANSI_RESET);
        try {
            int beneficiarioIndex = Integer.parseInt(lec.nextLine().trim()) - 1;
            if (beneficiarioIndex >= 0 && beneficiarioIndex < numBeneficiarios[0]) {
                System.out.print(ANSI_BLUE + "Ingrese la fecha de solicitud (DD/MM/AAAA): " + ANSI_RESET);
                String fechaSolicitud = lec.nextLine().trim();
                if (!validarFormatoFecha(fechaSolicitud)) {
                    System.out.println(ANSI_YELLOW + "Formato de fecha inválido. Use DD/MM/AAAA." + ANSI_RESET);
                    return null;
                }
                SolicitudBono solicitud = new SolicitudBono(fechaSolicitud, "Aprobada", beneficiarios[beneficiarioIndex]);
                Persistencia.agregarSolicitud(bono, solicitud, bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
                return solicitud;
            } else {
                System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
            return null;
        }
    }

    private static void generarReporteGeneral() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte del Sistema Bono Dignidad\n\n");
        reporte.append("Bonos Registrados:\n");
        for (int i = 0; i < numBonos[0]; i++) {
            reporte.append("Bono #").append(i + 1).append("\n");
            reporte.append("Tipo: ").append(bonos[i].getNombretipo()).append("\n");
            reporte.append("Fecha Inicio: ").append(bonos[i].getFechaIni()).append("\n");
            reporte.append("Fecha Fin: ").append(bonos[i].getFechaFin()).append("\n");
            reporte.append("Monto: ").append(bonos[i].getMonto()).append("\n\n");
        }
        reporte.append("Beneficiarios Registrados:\n");
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            reporte.append("Beneficiario #").append(i + 1).append("\n");
            reporte.append("Nombre: ").append(beneficiarios[i].getNombre()).append("\n");
            reporte.append("CI: ").append(beneficiarios[i].getCi()).append("\n");
            reporte.append("Edad: ").append(beneficiarios[i].getEdad()).append("\n");
            reporte.append("Dirección: ").append(beneficiarios[i].getDireccion()).append("\n");
            reporte.append("Fecha Nacimiento: ").append(beneficiarios[i].getFecha_nacimiento()).append("\n");
            reporte.append("Tipo Discapacidad: ").append(beneficiarios[i].getTipodiscapacidad()).append("\n");
            reporte.append("Grado Discapacidad: ").append(beneficiarios[i].getGradodiscapacidad()).append("\n\n");
        }
        reporte.append("Administradores Registrados:\n");
        for (int i = 0; i < numAdministradores[0]; i++) {
            reporte.append("Administrador #").append(i + 1).append("\n");
            reporte.append("ID: ").append(administradores[i].getId()).append("\n");
            reporte.append("Cargo: ").append(administradores[i].getCargo()).append("\n");
            reporte.append("Contacto: ").append(administradores[i].getContacto()).append("\n");
            reporte.append("Fecha Creación: ").append(administradores[i].getFecCreCuenta()).append("\n");
            reporte.append("Nombre: ").append(administradores[i].getNombre()).append("\n");
            reporte.append("CI: ").append(administradores[i].getCi()).append("\n\n");
        }
        System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┃      REPORTE GENERAL                ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
        System.out.println(reporte.toString().replace("\n", "\n┃ "));
        System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
    }

    private static void mostrarBeneficiariosConMultiplesBonos() {
        if (numBeneficiarios[0] == 0) {
            System.out.println(ANSI_YELLOW + "No hay beneficiarios registrados." + ANSI_RESET);
            return;
        }
        System.out.println(ANSI_YELLOW + "\n--- Seleccione un Beneficiario ---" + ANSI_RESET);
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            System.out.println((i + 1) + ". " + beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
        }
        System.out.print(ANSI_BLUE + "Ingrese el número del beneficiario: " + ANSI_RESET);
        try {
            int beneficiarioIndex = Integer.parseInt(lec.nextLine().trim()) - 1;
            if (beneficiarioIndex >= 0 && beneficiarioIndex < numBeneficiarios[0]) {
                Beneficiario beneficiario = beneficiarios[beneficiarioIndex];
                StringBuilder reporte = new StringBuilder();
                reporte.append("REPORTE DETALLADO DE BONOS PARA BENEFICIARIO\n\n");
                reporte.append("Beneficiario: ").append(beneficiario.getNombre()).append("\n");
                reporte.append("CI: ").append(beneficiario.getCi()).append("\n");
                reporte.append("Edad: ").append(beneficiario.getEdad()).append("\n");
                reporte.append("Tipo Discapacidad: ").append(beneficiario.getTipodiscapacidad()).append("\n");
                reporte.append("Grado Discapacidad: ").append(beneficiario.getGradodiscapacidad()).append("\n\n");
                reporte.append("BONOS ASIGNADOS:\n");
                reporte.append("--------------------------------------------------\n");
                boolean tieneBonos = false;
                double montoTotal = 0.0;
                int totalBonos = 0;

                for (int i = 0; i < numBonos[0]; i++) {
                    boolean tieneBono = false;
                    int solicitudesParaEsteBono = 0;
                    double montoPagado = 0.0;
                    int pagosRealizados = 0;
                    
                    for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                        if (bonos[i].getRegisSoli()[j].getBeneficiario() == beneficiario) {
                            if (!tieneBono) {
                                reporte.append("Bono: ").append(bonos[i].getNombretipo()).append("\n");
                                reporte.append("Monto Base: ").append(bonos[i].getMonto()).append("\n");
                                reporte.append("Período: ").append(bonos[i].getFechaIni()).append(" - ").append(bonos[i].getFechaFin()).append("\n");
                                reporte.append("Solicitudes:\n");
                                tieneBono = true;
                                totalBonos++;
                            }
                            SolicitudBono solicitud = bonos[i].getRegisSoli()[j];
                            reporte.append("  - Fecha: ").append(solicitud.getFechaSolicitud()).append(", Estado: ").append(solicitud.getEstado()).append(", Pagada: ").append(solicitud.isPagada() ? "Sí" : "No").append("\n");
                            solicitudesParaEsteBono++;
                        }
                    }

                    if (tieneBono) {
                        reporte.append("Pagos:\n");
                        for (int j = 0; j < bonos[i].getNumPagos(); j++) {
                            PagoBono pago = bonos[i].getRegisPago()[j];
                            if (pago.getSolicitud() != null && pago.getSolicitud().getBeneficiario() == beneficiario) {
                                reporte.append("  - Fecha: ").append(pago.getFechaPago()).append(", Monto Pagado: ").append(pago.getMonto()).append("\n");
                                montoPagado += pago.getMonto();
                                pagosRealizados++;
                            }
                        }
                        if (pagosRealizados == 0) {
                            reporte.append("  No hay pagos registrados.\n");
                        }
                        montoTotal += montoPagado;
                        reporte.append("Total solicitudes: ").append(solicitudesParaEsteBono).append("\n");
                        reporte.append("Total pagado: ").append(montoPagado).append(" (").append(pagosRealizados).append(" pagos)\n");
                        reporte.append("--------------------------------------------------\n");
                        tieneBonos = true;
                    }
                }

                if (tieneBonos) {
                    reporte.append("\nRESUMEN FINAL\n");
                    reporte.append("--------------------------------------------------\n");
                    reporte.append("Total bonos asignados: ").append(totalBonos).append("\n");
                    reporte.append("Monto total pagado: ").append(montoTotal).append("\n");
                } else {
                    reporte.append("\nEl beneficiario no tiene bonos asignados.\n");
                }
                System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "┃      REPORTE DE BONOS               ┃" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
                System.out.println(reporte.toString().replace("\n", "\n┃ "));
                System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
            } else {
                System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
        }
    }

    private static void calcularTiempoTotalPago() {
        if (numBeneficiarios[0] == 0) {
            System.out.println(ANSI_YELLOW + "No hay beneficiarios registrados." + ANSI_RESET);
            return;
        }
        System.out.println(ANSI_YELLOW + "\n--- Seleccione un Beneficiario ---" + ANSI_RESET);
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            System.out.println((i + 1) + ". " + beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
        }
        System.out.print(ANSI_BLUE + "Ingrese el número del beneficiario: " + ANSI_RESET);
        try {
            int beneficiarioIndex = Integer.parseInt(lec.nextLine().trim()) - 1;
            if (beneficiarioIndex >= 0 && beneficiarioIndex < numBeneficiarios[0]) {
                Beneficiario beneficiario = beneficiarios[beneficiarioIndex];
                String earliestDate = null;
                String latestDate = null;
                long earliestValue = Long.MAX_VALUE;
                long latestValue = Long.MIN_VALUE;
                int paymentCount = 0;

                for (int i = 0; i < numBonos[0]; i++) {
                    for (int j = 0; j < bonos[i].getNumPagos(); j++) {
                        PagoBono pago = bonos[i].getRegisPago()[j];
                        if (pago.getSolicitud() != null && pago.getSolicitud().getBeneficiario() == beneficiario) {
                            String fechaPago = pago.getFechaPago();
                            if (!validarFormatoFecha(fechaPago)) continue;
                            long fechaValue = convertirFechaAValor(fechaPago);
                            if (fechaValue < earliestValue) {
                                earliestValue = fechaValue;
                                earliestDate = fechaPago;
                            }
                            if (fechaValue > latestValue) {
                                latestValue = fechaValue;
                                latestDate = fechaPago;
                            }
                            paymentCount++;
                        }
                    }
                }

                StringBuilder reporte = new StringBuilder();
                reporte.append("TIEMPO TOTAL DE PAGOS PARA BENEFICIARIO\n\n");
                reporte.append("Beneficiario: ").append(beneficiario.getNombre()).append(" (CI: ").append(beneficiario.getCi()).append(")\n\n");
                if (paymentCount == 0) {
                    reporte.append("No se encontraron pagos para este beneficiario.\n");
                } else {
                    long daysBetween = calcularDiasEntreFechas(earliestDate, latestDate);
                    reporte.append("Primer pago: ").append(earliestDate).append("\n");
                    reporte.append("Último pago: ").append(latestDate).append("\n");
                    reporte.append("Total de pagos: ").append(paymentCount).append("\n");
                    reporte.append("Tiempo total entre pagos: ").append(daysBetween).append(" días\n");
                }
                System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "┃      TIEMPO TOTAL DE PAGO           ┃" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
                System.out.println(reporte.toString().replace("\n", "\n┃ "));
                System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
            } else {
                System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
        }
    }

    private static long calcularDiasEntreFechas(String fechaInicio, String fechaFin) {
        try {
            String[] partesInicio = fechaInicio.split("/");
            String[] partesFin = fechaFin.split("/");
            java.time.LocalDate inicio = java.time.LocalDate.of(
                Integer.parseInt(partesInicio[2]),
                Integer.parseInt(partesInicio[1]),
                Integer.parseInt(partesInicio[0])
            );
            java.time.LocalDate fin = java.time.LocalDate.of(
                Integer.parseInt(partesFin[2]),
                Integer.parseInt(partesFin[1]),
                Integer.parseInt(partesFin[0])
            );
            return java.time.temporal.ChronoUnit.DAYS.between(inicio, fin);
        } catch (Exception e) {
            return 0;
        }
    }

    private static void editarBonoConsola() {
        if (numBonos[0] == 0) {
            System.out.println(ANSI_YELLOW + "No hay bonos registrados para editar." + ANSI_RESET);
            return;
        }
        mostrarBonos();
        System.out.print(ANSI_BLUE + "Ingrese el número del bono a editar: " + ANSI_RESET);
        try {
            int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
            if (indice >= 0 && indice < numBonos[0]) {
                System.out.print(ANSI_BLUE + "Nuevo tipo: " + ANSI_RESET);
                String tipo = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nueva fecha inicio (DD/MM/AAAA): " + ANSI_RESET);
                String fechaIni = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nueva fecha fin (DD/MM/AAAA): " + ANSI_RESET);
                String fechaFin = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nuevo monto: " + ANSI_RESET);
                double monto = Double.parseDouble(lec.nextLine().trim());
                if (!validarFormatoFecha(fechaIni) || !validarFormatoFecha(fechaFin)) {
                    System.out.println(ANSI_YELLOW + "Formato de fecha inválido. Use DD/MM/AAAA." + ANSI_RESET);
                    return;
                }
                BonoDignidad bono = new BonoDignidad(tipo, fechaIni, fechaFin, monto);
                if (numBeneficiarios[0] > 0) {
                    System.out.println(ANSI_YELLOW + "\n--- Seleccione un Beneficiario (0 para ninguno) ---" + ANSI_RESET);
                    for (int i = 0; i < numBeneficiarios[0]; i++) {
                        System.out.println((i + 1) + ". " + beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
                    }
                    System.out.print(ANSI_BLUE + "Ingrese el número del beneficiario: " + ANSI_RESET);
                    int beneficiarioIndex = Integer.parseInt(lec.nextLine().trim()) - 1;
                    if (beneficiarioIndex >= 0 && beneficiarioIndex < numBeneficiarios[0]) {
                        SolicitudBono solicitud = new SolicitudBono(fechaIni, "Aprobada", beneficiarios[beneficiarioIndex]);
                        Persistencia.agregarSolicitud(bono, solicitud, bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
                    }
                }
                Persistencia.editarBono(bonos, numBonos, indice, bono, beneficiarios, numBeneficiarios, administradores, numAdministradores);
                System.out.println(ANSI_GREEN + "Bono editado exitosamente." + ANSI_RESET);
            } else {
                System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
        }
    }

    private static void editarBeneficiarioConsola() {
        if (numBeneficiarios[0] == 0) {
            System.out.println(ANSI_YELLOW + "No hay beneficiarios registrados para editar." + ANSI_RESET);
            return;
        }
        mostrarBeneficiarios();
        System.out.print(ANSI_BLUE + "Ingrese el número del beneficiario a editar: " + ANSI_RESET);
        try {
            int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
            if (indice >= 0 && indice < numBeneficiarios[0]) {
                System.out.print(ANSI_BLUE + "Nuevo nombre: " + ANSI_RESET);
                String nombre = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nueva CI: " + ANSI_RESET);
                String ci = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nueva edad: " + ANSI_RESET);
                int edad = Integer.parseInt(lec.nextLine().trim());
                System.out.print(ANSI_BLUE + "Nueva dirección: " + ANSI_RESET);
                String direccion = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nueva fecha nacimiento (DD/MM/AAAA): " + ANSI_RESET);
                String fechaNac = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nuevo tipo discapacidad: " + ANSI_RESET);
                String tipoDisc = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nuevo grado discapacidad: " + ANSI_RESET);
                String gradoDisc = lec.nextLine().trim();
                if (!validarFormatoFecha(fechaNac)) {
                    System.out.println(ANSI_YELLOW + "Formato de fecha inválido. Use DD/MM/AAAA." + ANSI_RESET);
                    return;
                }
                Beneficiario beneficiario = new Beneficiario(nombre, ci, edad, direccion, fechaNac, tipoDisc, gradoDisc);
                Persistencia.editarBeneficiario(beneficiarios, numBeneficiarios, indice, beneficiario, bonos, numBonos, administradores, numAdministradores);
                System.out.println(ANSI_GREEN + "Beneficiario editado exitosamente." + ANSI_RESET);
            } else {
                System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
        }
    }

    private static void editarAdministradorConsola() {
        if (numAdministradores[0] == 0) {
            System.out.println(ANSI_YELLOW + "No hay administradores registrados para editar." + ANSI_RESET);
            return;
        }
        mostrarAdministradores();
        System.out.print(ANSI_BLUE + "Ingrese el número del administrador a editar: " + ANSI_RESET);
        try {
            int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
            if (indice >= 0 && indice < numAdministradores[0]) {
                System.out.print(ANSI_BLUE + "Nuevo ID: " + ANSI_RESET);
                String id = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nuevo cargo: " + ANSI_RESET);
                String cargo = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nuevo contacto: " + ANSI_RESET);
                String contacto = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nueva fecha creación (DD/MM/AAAA): " + ANSI_RESET);
                String fechaCreacion = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nuevo nombre: " + ANSI_RESET);
                String nombre = lec.nextLine().trim();
                System.out.print(ANSI_BLUE + "Nueva CI: " + ANSI_RESET);
                String ci = lec.nextLine().trim();
                if (!validarFormatoFecha(fechaCreacion)) {
                    System.out.println(ANSI_YELLOW + "Formato de fecha inválido. Use DD/MM/AAAA." + ANSI_RESET);
                    return;
                }
                Administrador administrador = new Administrador(id, cargo, contacto, fechaCreacion, nombre, ci);
                Persistencia.editarAdministrador(administradores, numAdministradores, indice, administrador, bonos, numBonos, beneficiarios, numBeneficiarios);
                System.out.println(ANSI_GREEN + "Administrador editado exitosamente." + ANSI_RESET);
            } else {
                System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
        }
    }

    private static void borrarBonoConsola() {
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
                    Persistencia.borrarBono(bonos, numBonos, indice, beneficiarios, numBeneficiarios, administradores, numAdministradores);
                    System.out.println(ANSI_GREEN + "Bono eliminado exitosamente." + ANSI_RESET);
                } else {
                    System.out.println(ANSI_YELLOW + "Operación de borrado cancelada." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_YELLOW + "Índice inválido." + ANSI_RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_YELLOW + "Entrada inválida." + ANSI_RESET);
        }
    }

    private static void borrarBeneficiarioConsola() {
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
                    Persistencia.borrarBeneficiario(beneficiarios, numBeneficiarios, indice, bonos, numBonos, administradores, numAdministradores);
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
    }

    private static void borrarAdministradorConsola() {
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
                    Persistencia.borrarAdministrador(administradores, numAdministradores, indice, bonos, numBonos, beneficiarios, numBeneficiarios);
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
    }
}