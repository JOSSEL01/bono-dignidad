package Dignidad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static ArrayList<BonoDignidad> bonos;
    private static ArrayList<Beneficiario> beneficiarios;
    private static ArrayList<Administrador> administradores;
    private static Scanner lec = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        bonos = new ArrayList<>();
        beneficiarios = new ArrayList<>();
        administradores = new ArrayList<>();

        Persistencia.cargarDatos(bonos, beneficiarios, administradores);

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

        Persistencia.guardarDatos(bonos, beneficiarios, administradores);
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
                case 13:
                    borrarRegistro();
                    break;
                case 12:
                    System.out.println(ANSI_YELLOW + "Saliendo del sistema..." + ANSI_RESET);
                    break;
                default:
                    System.out.println(ANSI_YELLOW + "Opción no válida. Intente de nuevo." + ANSI_RESET);
            }
        } while (opcion != 12);
    }

    private static void ejecutarModoGrafico() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BonoDignidadGUI(bonos, beneficiarios, administradores).setVisible(true);
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
        System.out.println(ANSI_GREEN + "┃ 13. Borrar Registro                ┃" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "┃ 12. Salir                          ┃" + ANSI_RESET);
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
        BonoDignidad bono = new BonoDignidad();
        bono.leer(beneficiarios);
        bonos.add(bono);
        System.out.println(ANSI_GREEN + "Bono registrado exitosamente." + ANSI_RESET);
    }

    private static void registrarBeneficiario() {
        System.out.println(ANSI_YELLOW + "\n--- Registro de Beneficiario ---" + ANSI_RESET);
        Beneficiario beneficiario = new Beneficiario(0, "", "", "", "", null, "", "", lec);
        beneficiario.leer();
        beneficiarios.add(beneficiario);
        System.out.println(ANSI_GREEN + "Beneficiario registrado exitosamente." + ANSI_RESET);
    }

    private static void registrarAdministrador() {
        System.out.println(ANSI_YELLOW + "\n--- Registro de Administrador ---" + ANSI_RESET);
        Administrador administrador = new Administrador();
        administrador.leer();
        administradores.add(administrador);
        System.out.println(ANSI_GREEN + "Administrador registrado exitosamente." + ANSI_RESET);
    }

    private static void mostrarBonos() {
        if (bonos.isEmpty()) {
            System.out.println(ANSI_YELLOW + "\nNo hay bonos registrados." + ANSI_RESET);
            return;
        }
        for (int i = 0; i < bonos.size(); i++) {
            System.out.println(ANSI_CYAN + "\nBono #" + (i + 1) + ANSI_RESET);
            bonos.get(i).mostrar();
        }
    }

    private static void mostrarBeneficiarios() {
        if (beneficiarios.isEmpty()) {
            System.out.println(ANSI_YELLOW + "\nNo hay beneficiarios registrados." + ANSI_RESET);
            return;
        }
        for (int i = 0; i < beneficiarios.size(); i++) {
            System.out.println(ANSI_CYAN + "\nBeneficiario #" + (i + 1) + ANSI_RESET);
            beneficiarios.get(i).mostrar();
        }
    }

    private static void mostrarAdministradores() {
        if (administradores.isEmpty()) {
            System.out.println(ANSI_YELLOW + "\nNo hay administradores registrados." + ANSI_RESET);
            return;
        }
        for (int i = 0; i < administradores.size(); i++) {
            System.out.println(ANSI_CYAN + "\nAdministrador #" + (i + 1) + ANSI_RESET);
            administradores.get(i).mostrar();
        }
    }

    private static void mostrarBeneficiariosPorTipoDiscapacidad() {
        if (beneficiarios.isEmpty()) {
            System.out.println(ANSI_YELLOW + "\nNo hay beneficiarios registrados." + ANSI_RESET);
            return;
        }

        HashMap<String, Integer> conteoDiscapacidades = new HashMap<>();
        for (Beneficiario beneficiario : beneficiarios) {
            String tipo = beneficiario.getTipodiscapacidad();
            conteoDiscapacidades.put(tipo, conteoDiscapacidades.getOrDefault(tipo, 0) + 1);
        }

        System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┃  BENEFICIARIOS POR TIPO DISCAPACIDAD ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
        for (String tipo : conteoDiscapacidades.keySet()) {
            System.out.println("┃ " + tipo + ": " + conteoDiscapacidades.get(tipo) + " beneficiarios");
        }
        System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
    }

    private static void calcularMontoTotalPorPeriodo() {
        System.out.print(ANSI_BLUE + "Ingrese la fecha de inicio del período (DD/MM/AAAA): " + ANSI_RESET);
        String fechaInicioStr = lec.nextLine().trim();
        System.out.print(ANSI_BLUE + "Ingrese la fecha de fin del período (DD/MM/AAAA): " + ANSI_RESET);
        String fechaFinStr = lec.nextLine().trim();

        LocalDate fechaInicio, fechaFin;
        try {
            fechaInicio = LocalDate.parse(fechaInicioStr, FORMATO_FECHA);
            fechaFin = LocalDate.parse(fechaFinStr, FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            System.out.println(ANSI_YELLOW + "Formato de fecha inválido. Use DD/MM/AAAA." + ANSI_RESET);
            return;
        }

        double montoTotal = 0.0;
        for (BonoDignidad bono : bonos) {
            for (PagoBono pago : bono.getRegisPago()) {
                try {
                    LocalDate fechaPago = LocalDate.parse(pago.getFechaPago(), FORMATO_FECHA);
                    if ((fechaPago.isEqual(fechaInicio) || fechaPago.isAfter(fechaInicio)) &&
                        (fechaPago.isEqual(fechaFin) || fechaPago.isBefore(fechaFin))) {
                        montoTotal += pago.getMonto();
                    }
                } catch (DateTimeParseException e) {
                    System.out.println(ANSI_YELLOW + "Fecha de pago inválida: " + pago.getFechaPago() + ANSI_RESET);
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

    private static void verificarSolicitudesProcesadas() {
        if (bonos.isEmpty()) {
            System.out.println(ANSI_YELLOW + "\nNo hay bonos registrados." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┃     VERIFICACIÓN DE SOLICITUDES     ┃" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
        for (BonoDignidad bono : bonos) {
            System.out.println("┃ Bono: " + bono.getNombretipo());
            for (SolicitudBono solicitud : bono.getRegisSoli()) {
                System.out.println("┃ - Fecha: " + solicitud.getFechaSolicitud());
                System.out.println("┃   Beneficiario: " + (solicitud.getBeneficiario() != null ? solicitud.getBeneficiario().getNombre() : "No asignado"));
                System.out.println("┃   Estado: " + solicitud.getEstado());
                System.out.println("┃   Pagada: " + (solicitud.isPagada() ? "Sí" : "No"));
            }
        }
        System.out.println(ANSI_CYAN + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + ANSI_RESET);
    }

    private static void mostrarFechasBonoBeneficiario() {
        if (beneficiarios.isEmpty()) {
            System.out.println(ANSI_YELLOW + "\nNo hay beneficiarios registrados." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_YELLOW + "\n--- Seleccione un Beneficiario ---" + ANSI_RESET);
        for (int i = 0; i < beneficiarios.size(); i++) {
            System.out.println((i + 1) + ". " + beneficiarios.get(i).getNombre() + " (CI: " + beneficiarios.get(i).getCi() + ")");
        }
        System.out.print("Ingrese el número del beneficiario: ");
        try {
            int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
            if (indice >= 0 && indice < beneficiarios.size()) {
                Beneficiario beneficiario = beneficiarios.get(indice);
                System.out.println(ANSI_CYAN + "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "┃       FECHAS DE BONOS               ┃" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + ANSI_RESET);
                System.out.println("┃ Beneficiario: " + beneficiario.getNombre());
                boolean tieneBono = false;
                for (BonoDignidad bono : bonos) {
                    for (SolicitudBono solicitud : bono.getRegisSoli()) {
                        if (solicitud.getBeneficiario() == beneficiario) {
                            System.out.println("┃ - Bono: " + bono.getNombretipo());
                            System.out.println("┃   Inicio: " + bono.getFechaIni());
                            System.out.println("┃   Fin: " + bono.getFechaFin());
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
        for (Beneficiario beneficiario : beneficiarios) {
            for (SolicitudBono solicitud : beneficiario.getSolicitudes()) {
                if (solicitud.isPagada()) {
                    System.out.println("┃ - " + beneficiario.getNombre() + " (CI: " + beneficiario.getCi() + ")");
                    hayPagos = true;
                    break;
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
                if (bonos.isEmpty()) {
                    System.out.println(ANSI_YELLOW + "No hay bonos registrados para borrar." + ANSI_RESET);
                    return;
                }
                mostrarBonos();
                System.out.print(ANSI_BLUE + "Ingrese el número del bono a borrar: " + ANSI_RESET);
                try {
                    int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
                    if (indice >= 0 && indice < bonos.size()) {
                        System.out.print(ANSI_YELLOW + "¿Está seguro de borrar este bono? (S/N): " + ANSI_RESET);
                        String confirmacion = lec.nextLine().trim().toUpperCase();
                        if (confirmacion.startsWith("S")) {
                            BonoDignidad bono = bonos.get(indice);
                            
                            bonos.remove(indice);
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
                if (beneficiarios.isEmpty()) {
                    System.out.println(ANSI_YELLOW + "No hay beneficiarios registrados para borrar." + ANSI_RESET);
                    return;
                }
                mostrarBeneficiarios();
                System.out.print(ANSI_BLUE + "Ingrese el número del beneficiario a borrar: " + ANSI_RESET);
                try {
                    int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
                    if (indice >= 0 && indice < beneficiarios.size()) {
                        System.out.print(ANSI_YELLOW + "¿Está seguro de borrar este beneficiario? (S/N): " + ANSI_RESET);
                        String confirmacion = lec.nextLine().trim().toUpperCase();
                        if (confirmacion.startsWith("S")) {
                            Beneficiario beneficiario = beneficiarios.get(indice);
                            
                            for (BonoDignidad bono : bonos) {
                                bono.getRegisSoli().removeIf(s -> s.getBeneficiario() == beneficiario);
                            }
                            beneficiarios.remove(indice);
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
                if (administradores.isEmpty()) {
                    System.out.println(ANSI_YELLOW + "No hay administradores registrados para borrar." + ANSI_RESET);
                    return;
                }
                mostrarAdministradores();
                System.out.print(ANSI_BLUE + "Ingrese el número del administrador a borrar: " + ANSI_RESET);
                try {
                    int indice = Integer.parseInt(lec.nextLine().trim()) - 1;
                    if (indice >= 0 && indice < administradores.size()) {
                        System.out.print(ANSI_YELLOW + "¿Está seguro de borrar este administrador? (S/N): " + ANSI_RESET);
                        String confirmacion = lec.nextLine().trim().toUpperCase();
                        if (confirmacion.startsWith("S")) {
                            administradores.remove(indice);
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