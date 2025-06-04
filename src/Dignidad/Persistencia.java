package Dignidad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Persistencia {
    private static final String ARCHIVO_BONOS = "bonos.csv";
    private static final String ARCHIVO_BENEFICIARIOS = "beneficiarios.csv";
    private static final String ARCHIVO_ADMINISTRADORES = "administradores.csv";
    private static final String ARCHIVO_SOLICITUDES = "solicitudes.csv";
    private static final String ARCHIVO_PAGOS = "pagos.csv";

    public static void cargarDatos(BonoDignidad[] bonos, int[] numBonos, Beneficiario[] beneficiarios, 
                                 int[] numBeneficiarios, Administrador[] administradores, int[] numAdministradores) {
        if (!archivosExistenConDatos()) {
            inicializarDatosPorDefecto(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
            return;
        }

        numBeneficiarios[0] = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_BENEFICIARIOS))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] campos = linea.split(",");
                System.out.println("Leyendo linea beneficiarios: " + linea);
                if (campos.length == 7) {
                    beneficiarios[numBeneficiarios[0]++] = parseBeneficiario(campos);
                }
            }
            System.out.println("Cargados " + numBeneficiarios[0] + " beneficiarios");
        } catch (IOException e) {
            System.err.println("Error al cargar beneficiarios: " + e.getMessage());
            e.printStackTrace();
        }

        numBonos[0] = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_BONOS))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] campos = linea.split(",");
                System.out.println("Leyendo linea bonos: " + linea);
                if (campos.length == 4) {
                    bonos[numBonos[0]++] = parseBono(campos);
                }
            }
            System.out.println("Cargados " + numBonos[0] + " bonos");
        } catch (IOException e) {
            System.err.println("Error al cargar bonos: " + e.getMessage());
            e.printStackTrace();
        }

        numAdministradores[0] = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ADMINISTRADORES))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] campos = linea.split(",");
                System.out.println("Leyendo linea administradores: " + linea);
                if (campos.length == 6) {
                    administradores[numAdministradores[0]++] = parseAdministrador(campos);
                }
            }
            System.out.println("Cargados " + numAdministradores[0] + " administradores");
        } catch (IOException e) {
            System.err.println("Error al cargar administradores: " + e.getMessage());
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_SOLICITUDES))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] campos = linea.split(",");
                System.out.println("Leyendo linea solicitudes: " + linea);
                if (campos.length == 5) {
                    int bonoIndex = Integer.parseInt(campos[0].trim());
                    if (bonoIndex >= 0 && bonoIndex < numBonos[0]) {
                        SolicitudBono solicitud = parseSolicitud(campos, beneficiarios, numBeneficiarios);
                        bonos[bonoIndex].agregarSolicitud(solicitud);
                    }
                }
            }
            System.out.println("Solicitudes cargadas");
        } catch (IOException e) {
            System.err.println("Error al cargar solicitudes: " + e.getMessage());
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PAGOS))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] campos = linea.split(",");
                System.out.println("Leyendo linea pagos: " + linea);
                if (campos.length == 5) {
                    int bonoIndex = Integer.parseInt(campos[0].trim());
                    int solicitudIndex = Integer.parseInt(campos[1].trim());
                    if (bonoIndex >= 0 && bonoIndex < numBonos[0] && solicitudIndex >= 0 && solicitudIndex < bonos[bonoIndex].getNumSolicitudes()) {
                        PagoBono pago = parsePago(campos, bonos[bonoIndex].getRegisSoli()[solicitudIndex]);
                        bonos[bonoIndex].agregarPago(pago);
                    }
                }
            }
            System.out.println("Pagos cargados");
        } catch (IOException e) {
            System.err.println("Error al cargar pagos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void guardarDatos(BonoDignidad[] bonos, int[] numBonos, Beneficiario[] beneficiarios, 
                                  int[] numBeneficiarios, Administrador[] administradores, int[] numAdministradores) {
        if (bonos != null && numBonos != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_BONOS))) {
                bw.write("Tipo,FechaInicio,FechaFin,Monto");
                bw.newLine();
                for (int i = 0; i < numBonos[0]; i++) {
                    bw.write(serializarBono(bonos[i]));
                    bw.newLine();
                }
                System.out.println("Bonos guardados exitosamente en " + ARCHIVO_BONOS);
            } catch (IOException e) {
                System.err.println("Error al guardar bonos: " + e.getMessage());
                throw new RuntimeException("No se pudo guardar los bonos", e);
            }
        }

        if (beneficiarios != null && numBeneficiarios != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_BENEFICIARIOS))) {
                bw.write("Nombre,CI,Edad,Direccion,FechaNacimiento,TipoDiscapacidad,GradoDiscapacidad");
                bw.newLine();
                for (int i = 0; i < numBeneficiarios[0]; i++) {
                    bw.write(serializarBeneficiario(beneficiarios[i]));
                    bw.newLine();
                }
                System.out.println("Beneficiarios guardados exitosamente en " + ARCHIVO_BENEFICIARIOS);
            } catch (IOException e) {
                System.err.println("Error al guardar beneficiarios: " + e.getMessage());
                throw new RuntimeException("No se pudo guardar los beneficiarios", e);
            }
        }

        if (administradores != null && numAdministradores != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ADMINISTRADORES))) {
                bw.write("ID,Cargo,Contacto,FechaCreacion,Nombre,CI");
                bw.newLine();
                for (int i = 0; i < numAdministradores[0]; i++) {
                    bw.write(serializarAdministrador(administradores[i]));
                    bw.newLine();
                }
                System.out.println("Administradores guardados exitosamente en " + ARCHIVO_ADMINISTRADORES);
            } catch (IOException e) {
                System.err.println("Error al guardar administradores: " + e.getMessage());
                throw new RuntimeException("No se pudo guardar los administradores", e);
            }
        }

        if (bonos != null && numBonos != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_SOLICITUDES))) {
                bw.write("BonoIndex,FechaSolicitud,Estado,BeneficiarioCI,Pagada");
                bw.newLine();
                for (int i = 0; i < numBonos[0]; i++) {
                    for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                        SolicitudBono solicitud = bonos[i].getRegisSoli()[j];
                        bw.write(serializarSolicitud(i, solicitud));
                        bw.newLine();
                    }
                }
                System.out.println("Solicitudes guardadas exitosamente en " + ARCHIVO_SOLICITUDES);
            } catch (IOException e) {
                System.err.println("Error al guardar solicitudes: " + e.getMessage());
                throw new RuntimeException("No se pudo guardar las solicitudes", e);
            }
        }

        if (bonos != null && numBonos != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PAGOS))) {
                bw.write("BonoIndex,SolicitudIndex,FechaPago,Monto,BeneficiarioCI");
                bw.newLine();
                for (int i = 0; i < numBonos[0]; i++) {
                    for (int j = 0; j < bonos[i].getNumPagos(); j++) {
                        PagoBono pago = bonos[i].getRegisPago()[j];
                        int solicitudIndex = buscarIndiceSolicitud(bonos[i], pago.getSolicitud());
                        if (solicitudIndex != -1) {
                            bw.write(serializarPago(i, solicitudIndex, pago));
                            bw.newLine();
                        }
                    }
                }
                System.out.println("Pagos guardados exitosamente en " + ARCHIVO_PAGOS);
            } catch (IOException e) {
                System.err.println("Error al guardar pagos: " + e.getMessage());
                throw new RuntimeException("No se pudo guardar los pagos", e);
            }
        }
    }

    public static void agregarBono(BonoDignidad[] bonos, int[] numBonos, BonoDignidad bono, 
                                 Beneficiario[] beneficiarios, int[] numBeneficiarios, 
                                 Administrador[] administradores, int[] numAdministradores) {
        if (numBonos[0] < bonos.length) {
            bonos[numBonos[0]++] = bono;
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        }
    }

    public static void agregarBeneficiario(Beneficiario[] beneficiarios, int[] numBeneficiarios, Beneficiario beneficiario,
                                         BonoDignidad[] bonos, int[] numBonos, 
                                         Administrador[] administradores, int[] numAdministradores) {
        if (numBeneficiarios[0] < beneficiarios.length) {
            beneficiarios[numBeneficiarios[0]++] = beneficiario;
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        }
    }

    public static void agregarAdministrador(Administrador[] administradores, int[] numAdministradores, Administrador admin,
                                          BonoDignidad[] bonos, int[] numBonos, 
                                          Beneficiario[] beneficiarios, int[] numBeneficiarios) {
        if (numAdministradores[0] < administradores.length) {
            administradores[numAdministradores[0]++] = admin;
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        }
    }

    public static void agregarSolicitud(BonoDignidad bono, SolicitudBono solicitud,
                                      BonoDignidad[] bonos, int[] numBonos, 
                                      Beneficiario[] beneficiarios, int[] numBeneficiarios, 
                                      Administrador[] administradores, int[] numAdministradores) {
        bono.agregarSolicitud(solicitud);
        guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
    }

    public static void agregarPago(BonoDignidad bono, PagoBono pago,
                                 BonoDignidad[] bonos, int[] numBonos, 
                                 Beneficiario[] beneficiarios, int[] numBeneficiarios, 
                                 Administrador[] administradores, int[] numAdministradores) {
        bono.agregarPago(pago);
        pago.getSolicitud().setPagada(true);
        guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
    }

    public static void editarBono(BonoDignidad[] bonos, int[] numBonos, int indice, BonoDignidad nuevoBono,
                            Beneficiario[] beneficiarios, int[] numBeneficiarios, 
                            Administrador[] administradores, int[] numAdministradores) {
        if (indice >= 0 && indice < numBonos[0]) {
            BonoDignidad bonoOriginal = bonos[indice];
            nuevoBono.setRegisSoli(bonoOriginal.getRegisSoli());
            nuevoBono.setNumSolicitudes(bonoOriginal.getNumSolicitudes());
            nuevoBono.setRegisPago(bonoOriginal.getRegisPago());
            nuevoBono.setNumPagos(bonoOriginal.getNumPagos());
            bonos[indice] = nuevoBono;
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        }
    }

    public static void editarBeneficiario(Beneficiario[] beneficiarios, int[] numBeneficiarios, int indice, Beneficiario nuevoBeneficiario,
                                        BonoDignidad[] bonos, int[] numBonos, 
                                        Administrador[] administradores, int[] numAdministradores) {
        if (indice >= 0 && indice < numBeneficiarios[0]) {
            beneficiarios[indice] = nuevoBeneficiario;
            for (int i = 0; i < numBonos[0]; i++) {
                for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                    if (bonos[i].getRegisSoli()[j].getBeneficiario() == beneficiarios[indice]) {
                        bonos[i].getRegisSoli()[j].setBeneficiario(nuevoBeneficiario);
                    }
                }
            }
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        }
    }

    public static void editarAdministrador(Administrador[] administradores, int[] numAdministradores, int indice, Administrador nuevoAdmin,
                                         BonoDignidad[] bonos, int[] numBonos, 
                                         Beneficiario[] beneficiarios, int[] numBeneficiarios) {
        if (indice >= 0 && indice < numAdministradores[0]) {
            administradores[indice] = nuevoAdmin;
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        }
    }

    public static void borrarBono(BonoDignidad[] bonos, int[] numBonos, int indice,
                                Beneficiario[] beneficiarios, int[] numBeneficiarios, 
                                Administrador[] administradores, int[] numAdministradores) {
        if (indice >= 0 && indice < numBonos[0]) {
            for (int i = indice; i < numBonos[0] - 1; i++) {
                bonos[i] = bonos[i + 1];
            }
            bonos[--numBonos[0]] = null;
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        }
    }

    public static void borrarBeneficiario(Beneficiario[] beneficiarios, int[] numBeneficiarios, int indice,
                                        BonoDignidad[] bonos, int[] numBonos, 
                                        Administrador[] administradores, int[] numAdministradores) {
        if (indice >= 0 && indice < numBeneficiarios[0]) {
            Beneficiario beneficiario = beneficiarios[indice];
            for (int i = 0; i < numBonos[0]; i++) {
                for (int j = bonos[i].getNumSolicitudes() - 1; j >= 0; j--) {
                    if (bonos[i].getRegisSoli()[j].getBeneficiario() == beneficiario) {
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
            beneficiarios[--numBeneficiarios[0]] = null;
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        }
    }

    public static void borrarAdministrador(Administrador[] administradores, int[] numAdministradores, int indice,
                                         BonoDignidad[] bonos, int[] numBonos, 
                                         Beneficiario[] beneficiarios, int[] numBeneficiarios) {
        if (indice >= 0 && indice < numAdministradores[0]) {
            for (int i = indice; i < numAdministradores[0] - 1; i++) {
                administradores[i] = administradores[i + 1];
            }
            administradores[--numAdministradores[0]] = null;
            guardarDatos(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
        }
    }

    private static String serializarBono(BonoDignidad bono) {
        return String.format("%s,%s,%s,%.2f",
            bono.getNombretipo(), bono.getFechaIni(), bono.getFechaFin(), bono.getMonto());
    }

    private static String serializarBeneficiario(Beneficiario beneficiario) {
        return String.format("%s,%s,%d,%s,%s,%s,%s",
            beneficiario.getNombre(), beneficiario.getCi(), beneficiario.getEdad(), beneficiario.getDireccion(),
            beneficiario.getFecha_nacimiento(), beneficiario.getTipodiscapacidad(), beneficiario.getGradodiscapacidad());
    }

    private static String serializarAdministrador(Administrador admin) {
        return String.format("%s,%s,%s,%s,%s,%s",
            admin.getId(), admin.getCargo(), admin.getContacto(), admin.getFecCreCuenta(),
            admin.getNombre(), admin.getCi());
    }

    private static String serializarSolicitud(int bonoIndex, SolicitudBono solicitud) {
        String beneficiarioCI = solicitud.getBeneficiario() != null ? solicitud.getBeneficiario().getCi() : "N/A";
        return String.format("%d,%s,%s,%s,%b",
            bonoIndex, solicitud.getFechaSolicitud(), solicitud.getEstado(), beneficiarioCI, solicitud.isPagada());
    }

    private static String serializarPago(int bonoIndex, int solicitudIndex, PagoBono pago) {
        String beneficiarioCI = pago.getSolicitud().getBeneficiario() != null ? pago.getSolicitud().getBeneficiario().getCi() : "N/A";
        return String.format("%d,%d,%s,%.2f,%s",
            bonoIndex, solicitudIndex, pago.getFechaPago(), pago.getMonto(), beneficiarioCI);
    }

    private static BonoDignidad parseBono(String[] campos) {
        return new BonoDignidad(campos[0].trim(), campos[1].trim(), campos[2].trim(), Double.parseDouble(campos[3].trim()));
    }

    private static Beneficiario parseBeneficiario(String[] campos) {
        return new Beneficiario(campos[0].trim(), campos[1].trim(), Integer.parseInt(campos[2].trim()), 
                              campos[3].trim(), campos[4].trim(), campos[5].trim(), campos[6].trim());
    }

    private static Administrador parseAdministrador(String[] campos) {
        return new Administrador(campos[0].trim(), campos[1].trim(), campos[2].trim(), 
                               campos[3].trim(), campos[4].trim(), campos[5].trim());
    }

    private static SolicitudBono parseSolicitud(String[] campos, Beneficiario[] beneficiarios, int[] numBeneficiarios) {
        String fechaSolicitud = campos[1].trim();
        String estado = campos[2].trim();
        String beneficiarioCI = campos[3].trim();
        boolean pagada = Boolean.parseBoolean(campos[4].trim());

        Beneficiario beneficiario = null;
        if (!beneficiarioCI.equals("N/A")) {
            for (int i = 0; i < numBeneficiarios[0]; i++) {
                if (beneficiarios[i].getCi().equals(beneficiarioCI)) {
                    beneficiario = beneficiarios[i];
                    break;
                }
            }
        }

        SolicitudBono solicitud = new SolicitudBono(fechaSolicitud, estado, beneficiario);
        solicitud.setPagada(pagada);
        return solicitud;
    }

    private static PagoBono parsePago(String[] campos, SolicitudBono solicitud) {
        String fechaPago = campos[2].trim();
        double monto = Double.parseDouble(campos[3].trim());
        return new PagoBono(fechaPago, monto, solicitud);
    }

    private static int buscarIndiceSolicitud(BonoDignidad bono, SolicitudBono solicitud) {
        for (int i = 0; i < bono.getNumSolicitudes(); i++) {
            if (bono.getRegisSoli()[i] == solicitud) {
                return i;
            }
        }
        return -1;
    }

    private static boolean archivosExistenConDatos() {
        try {
            BufferedReader brBonos = new BufferedReader(new FileReader(ARCHIVO_BONOS));
            BufferedReader brBeneficiarios = new BufferedReader(new FileReader(ARCHIVO_BENEFICIARIOS));
            BufferedReader brAdministradores = new BufferedReader(new FileReader(ARCHIVO_ADMINISTRADORES));
            BufferedReader brSolicitudes = new BufferedReader(new FileReader(ARCHIVO_SOLICITUDES));
            BufferedReader brPagos = new BufferedReader(new FileReader(ARCHIVO_PAGOS));

            boolean tieneArchivos = brBonos.readLine() != null &&
                                    brBeneficiarios.readLine() != null &&
                                    brAdministradores.readLine() != null &&
                                    brSolicitudes.readLine() != null &&
                                    brPagos.readLine() != null;

            brBonos.close();
            brBeneficiarios.close();
            brAdministradores.close();
            brSolicitudes.close();
            brPagos.close();

            return tieneArchivos;
        } catch (IOException e) {
            System.err.println("Error al verificar archivos: " + e.getMessage());
            return false;
        }
    }

    private static void inicializarDatosPorDefecto(BonoDignidad[] bonos, int[] numBonos, 
                                                 Beneficiario[] beneficiarios, int[] numBeneficiarios,
                                                 Administrador[] administradores, int[] numAdministradores) {
        for (int i = 0; i < 100; i++) {
            bonos[i] = null;
            beneficiarios[i] = null;
            administradores[i] = null;
        }
        
        bonos[0] = new BonoDignidad("Bono Anual", "01/01/2025", "31/12/2025", 1000.0);
        numBonos[0] = 1;
        
        beneficiarios[0] = new Beneficiario("Juan Perez", "123456", 65, "Calle 1", "15/05/1960", "Física", "Moderada");
        numBeneficiarios[0] = 1;
        
        administradores[0] = new Administrador("A001", "Gestor", "juan@correo.com", "01/01/2020", "Juan Admin", "654321");
        numAdministradores[0] = 1;
    }
}