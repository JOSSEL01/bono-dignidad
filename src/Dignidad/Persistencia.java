package Dignidad;

import java.io.*;
import java.util.ArrayList;

public class Persistencia {
    private static final String BENEFICIARIOS_FILE = "beneficiarios.csv";
    private static final String BONOS_FILE = "bonos.csv";
    private static final String ADMINISTRADORES_FILE = "administradores.csv";
    private static final String SOLICITUDES_FILE = "solicitudes.csv";
    private static final String PAGOS_FILE = "pagos.csv";

    public static void guardarDatos(ArrayList<BonoDignidad> bonos, ArrayList<Beneficiario> beneficiarios, ArrayList<Administrador> administradores) {
        try (PrintWriter writerBeneficiarios = new PrintWriter(BENEFICIARIOS_FILE);
             PrintWriter writerBonos = new PrintWriter(BONOS_FILE);
             PrintWriter writerAdministradores = new PrintWriter(ADMINISTRADORES_FILE);
             PrintWriter writerSolicitudes = new PrintWriter(SOLICITUDES_FILE);
             PrintWriter writerPagos = new PrintWriter(PAGOS_FILE)) {

            for (Beneficiario b : beneficiarios) {
                writerBeneficiarios.println(b.toCSV());
            }

            for (BonoDignidad b : bonos) {
                writerBonos.println(b.toCSV());
                for (SolicitudBono s : b.getRegisSoli()) {
                    writerSolicitudes.println(s.toCSV());
                }
                for (PagoBono p : b.getRegisPago()) {
                    writerPagos.println(p.toCSV());
                }
            }

            for (Administrador a : administradores) {
                writerAdministradores.println(a.toCSV());
            }
        } catch (IOException e) {
        }
    }

    public static void cargarDatos(ArrayList<BonoDignidad> bonos, ArrayList<Beneficiario> beneficiarios, ArrayList<Administrador> administradores) {
        try (BufferedReader readerBeneficiarios = new BufferedReader(new FileReader(BENEFICIARIOS_FILE));
             BufferedReader readerBonos = new BufferedReader(new FileReader(BONOS_FILE));
             BufferedReader readerAdministradores = new BufferedReader(new FileReader(ADMINISTRADORES_FILE));
             BufferedReader readerSolicitudes = new BufferedReader(new FileReader(SOLICITUDES_FILE));
             BufferedReader readerPagos = new BufferedReader(new FileReader(PAGOS_FILE))) {

            String line;
            beneficiarios.clear();
            while ((line = readerBeneficiarios.readLine()) != null) {
                Beneficiario b = Beneficiario.fromCSV(line, null);
                if (b != null) beneficiarios.add(b);
            }

            bonos.clear();
            ArrayList<SolicitudBono> solicitudesTemp = new ArrayList<>();
            ArrayList<PagoBono> pagosTemp = new ArrayList<>();
            while ((line = readerBonos.readLine()) != null) {
                BonoDignidad b = BonoDignidad.fromCSV(line, beneficiarios);
                if (b != null) {
                    bonos.add(b);
                    while ((line = readerSolicitudes.readLine()) != null && !line.isEmpty()) {
                        SolicitudBono s = SolicitudBono.fromCSV(line, beneficiarios);
                        if (s != null) {
                            solicitudesTemp.add(s);
                            b.getRegisSoli().add(s);
                        }
                    }
                    while ((line = readerPagos.readLine()) != null && !line.isEmpty()) {
                        PagoBono p = PagoBono.fromCSV(line, b.getRegisSoli());
                        if (p != null) {
                            pagosTemp.add(p);
                            b.getRegisPago().add(p);
                        }
                    }
                }
            }

            administradores.clear();
            while ((line = readerAdministradores.readLine()) != null) {
                Administrador a = Administrador.fromCSV(line);
                if (a != null) administradores.add(a);
            }
        } catch (IOException e) {
        }
    }
}