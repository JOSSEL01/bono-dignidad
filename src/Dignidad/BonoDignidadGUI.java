package Dignidad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class BonoDignidadGUI extends javax.swing.JFrame {
    private ArrayList<BonoDignidad> bonos;
    private ArrayList<Beneficiario> beneficiarios;
    private ArrayList<Administrador> administradores;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private javax.swing.JTabbedPane tabbedPane;

    private javax.swing.JButton btnAgregarAdministrador;
    private javax.swing.JButton btnAgregarBeneficiario;
    private javax.swing.JButton btnAgregarBono;
    private javax.swing.JButton btnBeneficiariosPorDiscapacidad;
    private javax.swing.JButton btnMontoPorPeriodo;
    private javax.swing.JButton btnVerificarSolicitudes;
    private javax.swing.JButton btnFechasBonoBeneficiario;
    private javax.swing.JButton btnListarBeneficiariosConPagos;
    private javax.swing.JButton btnBorrarBono;
    private javax.swing.JButton btnBorrarBeneficiario;
    private javax.swing.JButton btnBorrarAdministrador;
    private javax.swing.JLabel emptyLabel1;
    private javax.swing.JLabel emptyLabel2;
    private javax.swing.JLabel emptyLabel3;
    private javax.swing.JLabel lblCargoAdministrador;
    private javax.swing.JLabel lblCIAdministrador;
    private javax.swing.JLabel lblCIBeneficiario;
    private javax.swing.JLabel lblContactoAdministrador;
    private javax.swing.JLabel lblDireccionBeneficiario;
    private javax.swing.JLabel lblEdadBeneficiario;
    private javax.swing.JLabel lblFechaCreacion;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblGradoDiscapacidad;
    private javax.swing.JLabel lblIDAdministrador;
    private javax.swing.JLabel lblNombreAdministrador;
    private javax.swing.JLabel lblNombreBeneficiario;
    private javax.swing.JLabel lblTipoBono;
    private javax.swing.JLabel lblTipoDiscapacidad;
    private javax.swing.JPanel panelAdministradores;
    private javax.swing.JPanel panelBeneficiarios;
    private javax.swing.JPanel panelBonos;
    private javax.swing.JPanel panelReportes;
    private javax.swing.JPanel panelFormularioAdministradores;
    private javax.swing.JPanel panelFormularioBeneficiarios;
    private javax.swing.JPanel panelFormularioBonos;
    private javax.swing.JScrollPane scrollTablaAdministradores;
    private javax.swing.JScrollPane scrollTablaBeneficiarios;
    private javax.swing.JScrollPane scrollTablaBonos;
    private javax.swing.JTable tablaAdministradores;
    private javax.swing.JTable tablaBeneficiarios;
    private javax.swing.JTable tablaBonos;
    private javax.swing.JTextField txtCIAdministrador;
    private javax.swing.JTextField txtCIBeneficiario;
    private javax.swing.JTextField txtCargoAdministrador;
    private javax.swing.JTextField txtContactoAdministrador;
    private javax.swing.JTextField txtDireccionBeneficiario;
    private javax.swing.JTextField txtEdadBeneficiario;
    private javax.swing.JTextField txtFechaCreacion;
    private javax.swing.JTextField txtFechaFin;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtGradoDiscapacidad;
    private javax.swing.JTextField txtIDAdministrador;
    private javax.swing.JTextField txtNombreAdministrador;
    private javax.swing.JTextField txtNombreBeneficiario;
    private javax.swing.JTextField txtTipoBono;
    private javax.swing.JTextField txtTipoDiscapacidad;

    public BonoDignidadGUI(ArrayList<BonoDignidad> bonos, ArrayList<Beneficiario> beneficiarios, ArrayList<Administrador> administradores) {
        this.bonos = bonos;
        this.beneficiarios = beneficiarios;
        this.administradores = administradores;

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            UIManager.put("nimbusBase", new Color(30, 144, 255));
            UIManager.put("nimbusBlueGrey", new Color(200, 220, 240));
            UIManager.put("control", new Color(240, 248, 255));
        } catch (Exception e) {
            // Manejar silenciosamente
        }

        // Cargar datos automáticamente al abrir
        Persistencia.cargarDatos(bonos, beneficiarios, administradores);

        initComponents();
        if (tablaBonos != null) customizeTableBonos(tablaBonos);
        if (tablaBeneficiarios != null) customizeTable(tablaBeneficiarios);
        if (tablaAdministradores != null) customizeTable(tablaAdministradores);

        cargarDatosEnTablas();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Guardar datos automáticamente al cerrar
                Persistencia.guardarDatos(bonos, beneficiarios, administradores);
                System.exit(0);
            }
        });
    }

    private void customizeTable(javax.swing.JTable table) {
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(30, 144, 255));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setSelectionForeground(Color.BLACK);
        table.setForeground(Color.BLACK);
        table.setBackground(new Color(245, 245, 245));
        table.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    }

    private void customizeTableBonos(javax.swing.JTable table) {
        table.setRowHeight(30);
        table.getTableHeader().setBackground(new Color(30, 144, 255));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setSelectionForeground(Color.BLACK);
        table.setForeground(Color.BLACK);
        table.setBackground(new Color(200, 220, 240));
        table.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    }

    private void cargarDatosEnTablas() {
        DefaultTableModel modelBonos = (DefaultTableModel) tablaBonos.getModel();
        modelBonos.setRowCount(0);
        for (BonoDignidad bono : bonos) {
            modelBonos.addRow(new Object[]{bono.getNombretipo(), bono.getFechaIni(), bono.getFechaFin()});
        }

        DefaultTableModel modelBeneficiarios = (DefaultTableModel) tablaBeneficiarios.getModel();
        modelBeneficiarios.setRowCount(0);
        for (Beneficiario beneficiario : beneficiarios) {
            modelBeneficiarios.addRow(new Object[]{beneficiario.getNombre(), beneficiario.getCi(), beneficiario.getEdad(),
                    beneficiario.getDireccion(), beneficiario.getFecha_nacimiento(),
                    beneficiario.getTipodiscapacidad(), beneficiario.getGradodiscapacidad()});
        }

        DefaultTableModel modelAdministradores = (DefaultTableModel) tablaAdministradores.getModel();
        modelAdministradores.setRowCount(0);
        for (Administrador administrador : administradores) {
            modelAdministradores.addRow(new Object[]{administrador.getId(), administrador.getCargo(), administrador.getContacto(),
                    administrador.getFecCreCuenta(), administrador.getNombre(), administrador.getCi()});
        }
    }

    private boolean esFechaValida(String fechaTexto) {
        try {
            LocalDate.parse(fechaTexto, FORMATO_FECHA);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void initComponents() {
        tabbedPane = new javax.swing.JTabbedPane();
        panelBonos = new javax.swing.JPanel();
        panelFormularioBonos = new javax.swing.JPanel();
        lblTipoBono = new javax.swing.JLabel();
        txtTipoBono = new javax.swing.JTextField();
        lblFechaInicio = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        lblFechaFin = new javax.swing.JLabel();
        txtFechaFin = new javax.swing.JTextField();
        emptyLabel1 = new javax.swing.JLabel();
        btnAgregarBono = new javax.swing.JButton();
        btnBorrarBono = new javax.swing.JButton();
        scrollTablaBonos = new javax.swing.JScrollPane();
        tablaBonos = new javax.swing.JTable();
        panelBeneficiarios = new javax.swing.JPanel();
        panelFormularioBeneficiarios = new javax.swing.JPanel();
        lblNombreBeneficiario = new javax.swing.JLabel();
        txtNombreBeneficiario = new javax.swing.JTextField();
        lblCIBeneficiario = new javax.swing.JLabel();
        txtCIBeneficiario = new javax.swing.JTextField();
        lblEdadBeneficiario = new javax.swing.JLabel();
        txtEdadBeneficiario = new javax.swing.JTextField();
        lblDireccionBeneficiario = new javax.swing.JLabel();
        txtDireccionBeneficiario = new javax.swing.JTextField();
        lblFechaNacimiento = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        lblTipoDiscapacidad = new javax.swing.JLabel();
        txtTipoDiscapacidad = new javax.swing.JTextField();
        lblGradoDiscapacidad = new javax.swing.JLabel();
        txtGradoDiscapacidad = new javax.swing.JTextField();
        emptyLabel2 = new javax.swing.JLabel();
        btnAgregarBeneficiario = new javax.swing.JButton();
        btnBorrarBeneficiario = new javax.swing.JButton();
        scrollTablaBeneficiarios = new javax.swing.JScrollPane();
        tablaBeneficiarios = new javax.swing.JTable();
        panelAdministradores = new javax.swing.JPanel();
        panelFormularioAdministradores = new javax.swing.JPanel();
        lblIDAdministrador = new javax.swing.JLabel();
        txtIDAdministrador = new javax.swing.JTextField();
        lblCargoAdministrador = new javax.swing.JLabel();
        txtCargoAdministrador = new javax.swing.JTextField();
        lblContactoAdministrador = new javax.swing.JLabel();
        txtContactoAdministrador = new javax.swing.JTextField();
        lblFechaCreacion = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JTextField();
        lblNombreAdministrador = new javax.swing.JLabel();
        txtNombreAdministrador = new javax.swing.JTextField();
        lblCIAdministrador = new javax.swing.JLabel();
        txtCIAdministrador = new javax.swing.JTextField();
        emptyLabel3 = new javax.swing.JLabel();
        btnAgregarAdministrador = new javax.swing.JButton();
        btnBorrarAdministrador = new javax.swing.JButton();
        scrollTablaAdministradores = new javax.swing.JScrollPane();
        tablaAdministradores = new javax.swing.JTable();
        panelReportes = new javax.swing.JPanel();
        btnBeneficiariosPorDiscapacidad = new javax.swing.JButton();
        btnMontoPorPeriodo = new javax.swing.JButton();
        btnVerificarSolicitudes = new javax.swing.JButton();
        btnFechasBonoBeneficiario = new javax.swing.JButton();
        btnListarBeneficiariosConPagos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema Bono Dignidad");
        setPreferredSize(new java.awt.Dimension(900, 650));

        tabbedPane.setBackground(new java.awt.Color(30, 144, 255));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));

        panelBonos.setBackground(new java.awt.Color(240, 248, 255));
        panelBonos.setLayout(new java.awt.BorderLayout());

        panelFormularioBonos.setBackground(new java.awt.Color(240, 248, 255));
        panelFormularioBonos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Bono", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16), new java.awt.Color(30, 144, 255)));
        panelFormularioBonos.setLayout(new java.awt.GridLayout(5, 2, 15, 15));

        lblTipoBono.setText("Tipo de Bono:");
        lblTipoBono.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBonos.add(lblTipoBono);
        txtTipoBono.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBonos.add(txtTipoBono);

        lblFechaInicio.setText("Fecha Inicio (DD/MM/AAAA):");
        lblFechaInicio.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBonos.add(lblFechaInicio);
        txtFechaInicio.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBonos.add(txtFechaInicio);

        lblFechaFin.setText("Fecha Fin (DD/MM/AAAA):");
        lblFechaFin.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBonos.add(lblFechaFin);
        txtFechaFin.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBonos.add(txtFechaFin);

        emptyLabel1.setText("");
        panelFormularioBonos.add(emptyLabel1);

        btnAgregarBono.setText("Agregar Bono");
        btnAgregarBono.setBackground(new Color(30, 144, 255));
        btnAgregarBono.setForeground(Color.WHITE);
        btnAgregarBono.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnAgregarBono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBonoActionPerformed(evt);
            }
        });
        panelFormularioBonos.add(btnAgregarBono);

        btnBorrarBono.setText("Borrar Bono");
        btnBorrarBono.setBackground(new Color(255, 99, 71));
        btnBorrarBono.setForeground(Color.WHITE);
        btnBorrarBono.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnBorrarBono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarBonoActionPerformed(evt);
            }
        });
        panelFormularioBonos.add(btnBorrarBono);

        panelBonos.add(panelFormularioBonos, java.awt.BorderLayout.NORTH);

        tablaBonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Tipo", "Fecha Inicio", "Fecha Fin"}
        ));
        scrollTablaBonos.setViewportView(tablaBonos);
        scrollTablaBonos.setMinimumSize(new java.awt.Dimension(800, 300)); // Asegurar tamaño mínimo
        panelBonos.add(scrollTablaBonos, java.awt.BorderLayout.CENTER);
        tabbedPane.addTab("Bonos", panelBonos);

        panelBeneficiarios.setBackground(new java.awt.Color(240, 248, 255));
        panelBeneficiarios.setLayout(new java.awt.BorderLayout());

        panelFormularioBeneficiarios.setBackground(new java.awt.Color(240, 248, 255));
        panelFormularioBeneficiarios.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Beneficiario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16), new java.awt.Color(30, 144, 255)));
        panelFormularioBeneficiarios.setLayout(new java.awt.GridLayout(9, 2, 15, 15));

        lblNombreBeneficiario.setText("Nombre:");
        lblNombreBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(lblNombreBeneficiario);
        txtNombreBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(txtNombreBeneficiario);

        lblCIBeneficiario.setText("CI:");
        lblCIBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(lblCIBeneficiario);
        txtCIBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(txtCIBeneficiario);

        lblEdadBeneficiario.setText("Edad:");
        lblEdadBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(lblEdadBeneficiario);
        txtEdadBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(txtEdadBeneficiario);

        lblDireccionBeneficiario.setText("Dirección:");
        lblDireccionBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(lblDireccionBeneficiario);
        txtDireccionBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(txtDireccionBeneficiario);

        lblFechaNacimiento.setText("Fecha Nacimiento (DD/MM/AAAA):");
        lblFechaNacimiento.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(lblFechaNacimiento);
        txtFechaNacimiento.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(txtFechaNacimiento);

        lblTipoDiscapacidad.setText("Tipo Discapacidad:");
        lblTipoDiscapacidad.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(lblTipoDiscapacidad);
        txtTipoDiscapacidad.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(txtTipoDiscapacidad);

        lblGradoDiscapacidad.setText("Grado Discapacidad:");
        lblGradoDiscapacidad.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(lblGradoDiscapacidad);
        txtGradoDiscapacidad.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioBeneficiarios.add(txtGradoDiscapacidad);

        emptyLabel2.setText("");
        panelFormularioBeneficiarios.add(emptyLabel2);

        btnAgregarBeneficiario.setText("Agregar Beneficiario");
        btnAgregarBeneficiario.setBackground(new Color(30, 144, 255));
        btnAgregarBeneficiario.setForeground(Color.WHITE);
        btnAgregarBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnAgregarBeneficiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBeneficiarioActionPerformed(evt);
            }
        });
        panelFormularioBeneficiarios.add(btnAgregarBeneficiario);

        btnBorrarBeneficiario.setText("Borrar Beneficiario");
        btnBorrarBeneficiario.setBackground(new Color(255, 99, 71));
        btnBorrarBeneficiario.setForeground(Color.WHITE);
        btnBorrarBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnBorrarBeneficiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarBeneficiarioActionPerformed(evt);
            }
        });
        panelFormularioBeneficiarios.add(btnBorrarBeneficiario);

        panelBeneficiarios.add(panelFormularioBeneficiarios, java.awt.BorderLayout.NORTH);

        tablaBeneficiarios.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"Nombre", "CI", "Edad", "Dirección", "Fecha Nacimiento", "Tipo Discapacidad", "Grado Discapacidad"}));
        scrollTablaBeneficiarios.setViewportView(tablaBeneficiarios);
        scrollTablaBeneficiarios.setMinimumSize(new java.awt.Dimension(800, 300)); // Asegurar tamaño mínimo
        panelBeneficiarios.add(scrollTablaBeneficiarios, java.awt.BorderLayout.CENTER);
        tabbedPane.addTab("Beneficiarios", panelBeneficiarios);

        panelAdministradores.setBackground(new java.awt.Color(240, 248, 255));
        panelAdministradores.setLayout(new java.awt.BorderLayout());

        panelFormularioAdministradores.setBackground(new java.awt.Color(240, 248, 255));
        panelFormularioAdministradores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Administrador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16), new java.awt.Color(30, 144, 255)));
        panelFormularioAdministradores.setLayout(new java.awt.GridLayout(8, 2, 15, 15));

        lblIDAdministrador.setText("ID:");
        lblIDAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(lblIDAdministrador);
        txtIDAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(txtIDAdministrador);

        lblCargoAdministrador.setText("Cargo:");
        lblCargoAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(lblCargoAdministrador);
        txtCargoAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(txtCargoAdministrador);

        lblContactoAdministrador.setText("Contacto:");
        lblContactoAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(lblContactoAdministrador);
        txtContactoAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(txtContactoAdministrador);

        lblFechaCreacion.setText("Fecha Creación (DD/MM/AAAA):");
        lblFechaCreacion.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(lblFechaCreacion);
        txtFechaCreacion.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(txtFechaCreacion);

        lblNombreAdministrador.setText("Nombre:");
        lblNombreAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(lblNombreAdministrador);
        txtNombreAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(txtNombreAdministrador);

        lblCIAdministrador.setText("CI:");
        lblCIAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(lblCIAdministrador);
        txtCIAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        panelFormularioAdministradores.add(txtCIAdministrador);

        emptyLabel3.setText("");
        panelFormularioAdministradores.add(emptyLabel3);

        btnAgregarAdministrador.setText("Agregar Administrador");
        btnAgregarAdministrador.setBackground(new Color(30, 144, 255));
        btnAgregarAdministrador.setForeground(Color.WHITE);
        btnAgregarAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnAgregarAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAdministradorActionPerformed(evt);
            }
        });
        panelFormularioAdministradores.add(btnAgregarAdministrador);

        btnBorrarAdministrador.setText("Borrar Administrador");
        btnBorrarAdministrador.setBackground(new Color(255, 99, 71));
        btnBorrarAdministrador.setForeground(Color.WHITE);
        btnBorrarAdministrador.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnBorrarAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarAdministradorActionPerformed(evt);
            }
        });
        panelFormularioAdministradores.add(btnBorrarAdministrador);

        panelAdministradores.add(panelFormularioAdministradores, java.awt.BorderLayout.NORTH);

        tablaAdministradores.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"ID", "Cargo", "Contacto", "Fecha Creación", "Nombre", "CI"}));
        scrollTablaAdministradores.setViewportView(tablaAdministradores);
        scrollTablaAdministradores.setMinimumSize(new java.awt.Dimension(800, 300)); // Asegurar tamaño mínimo
        panelAdministradores.add(scrollTablaAdministradores, java.awt.BorderLayout.CENTER);
        tabbedPane.addTab("Administradores", panelAdministradores);

        panelReportes.setBackground(new java.awt.Color(240, 248, 255));
        panelReportes.setLayout(new java.awt.GridLayout(5, 1, 10, 10));

        btnBeneficiariosPorDiscapacidad.setText("Beneficiarios por Tipo de Discapacidad");
        btnBeneficiariosPorDiscapacidad.setBackground(new Color(30, 144, 255));
        btnBeneficiariosPorDiscapacidad.setForeground(Color.WHITE);
        btnBeneficiariosPorDiscapacidad.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnBeneficiariosPorDiscapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeneficiariosPorDiscapacidadActionPerformed(evt);
            }
        });
        panelReportes.add(btnBeneficiariosPorDiscapacidad);

        btnMontoPorPeriodo.setText("Monto Total Pagado por Período");
        btnMontoPorPeriodo.setBackground(new Color(30, 144, 255));
        btnMontoPorPeriodo.setForeground(Color.WHITE);
        btnMontoPorPeriodo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnMontoPorPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMontoPorPeriodoActionPerformed(evt);
            }
        });
        panelReportes.add(btnMontoPorPeriodo);

        btnVerificarSolicitudes.setText("Verificar Solicitudes Pagadas");
        btnVerificarSolicitudes.setBackground(new Color(30, 144, 255));
        btnVerificarSolicitudes.setForeground(Color.WHITE);
        btnVerificarSolicitudes.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnVerificarSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarSolicitudesActionPerformed(evt);
            }
        });
        panelReportes.add(btnVerificarSolicitudes);

        btnFechasBonoBeneficiario.setText("Fechas de Bono por Beneficiario");
        btnFechasBonoBeneficiario.setBackground(new Color(30, 144, 255));
        btnFechasBonoBeneficiario.setForeground(Color.WHITE);
        btnFechasBonoBeneficiario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnFechasBonoBeneficiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechasBonoBeneficiarioActionPerformed(evt);
            }
        });
        panelReportes.add(btnFechasBonoBeneficiario);

        btnListarBeneficiariosConPagos.setText("Listar Beneficiarios con Pagos");
        btnListarBeneficiariosConPagos.setBackground(new Color(30, 144, 255));
        btnListarBeneficiariosConPagos.setForeground(Color.WHITE);
        btnListarBeneficiariosConPagos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnListarBeneficiariosConPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarBeneficiariosConPagosActionPerformed(evt);
            }
        });
        panelReportes.add(btnListarBeneficiariosConPagos);

        tabbedPane.addTab("Reportes", panelReportes);

        getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);
        pack();
    }

    private void btnAgregarBonoActionPerformed(java.awt.event.ActionEvent evt) {
        String tipo = txtTipoBono.getText();
        String fechaInicio = txtFechaInicio.getText();
        String fechaFin = txtFechaFin.getText();

        if (tipo.isEmpty() || fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!esFechaValida(fechaInicio) || !esFechaValida(fechaFin)) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            BonoDignidad bono = new BonoDignidad(tipo, fechaInicio, fechaFin, null);
            bonos.add(bono);

            DefaultTableModel model = (DefaultTableModel) tablaBonos.getModel();
            model.addRow(new Object[]{tipo, fechaInicio, fechaFin});

            txtTipoBono.setText("");
            txtFechaInicio.setText("");
            txtFechaFin.setText("");
            JOptionPane.showMessageDialog(this, "Bono agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar bono: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnAgregarBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        String nombre = txtNombreBeneficiario.getText();
        String ci = txtCIBeneficiario.getText();
        String edadTexto = txtEdadBeneficiario.getText();
        String direccion = txtDireccionBeneficiario.getText();
        String fechaNacimiento = txtFechaNacimiento.getText();
        String tipoDiscapacidad = txtTipoDiscapacidad.getText();
        String gradoDiscapacidad = txtGradoDiscapacidad.getText();

        if (nombre.isEmpty() || ci.isEmpty() || edadTexto.isEmpty() || direccion.isEmpty() || fechaNacimiento.isEmpty() || tipoDiscapacidad.isEmpty() || gradoDiscapacidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!esFechaValida(fechaNacimiento)) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int edad = Integer.parseInt(edadTexto);
            Beneficiario beneficiario = new Beneficiario(edad, direccion, fechaNacimiento, tipoDiscapacidad, gradoDiscapacidad, null, nombre, ci, null);
            beneficiarios.add(beneficiario);

            DefaultTableModel model = (DefaultTableModel) tablaBeneficiarios.getModel();
            model.addRow(new Object[]{nombre, ci, edad, direccion, fechaNacimiento, tipoDiscapacidad, gradoDiscapacidad});

            txtNombreBeneficiario.setText("");
            txtCIBeneficiario.setText("");
            txtEdadBeneficiario.setText("");
            txtDireccionBeneficiario.setText("");
            txtFechaNacimiento.setText("");
            txtTipoDiscapacidad.setText("");
            txtGradoDiscapacidad.setText("");
            JOptionPane.showMessageDialog(this, "Beneficiario agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar beneficiario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnAgregarAdministradorActionPerformed(java.awt.event.ActionEvent evt) {
        String id = txtIDAdministrador.getText();
        String cargo = txtCargoAdministrador.getText();
        String contactoTexto = txtContactoAdministrador.getText();
        String fechaCreacion = txtFechaCreacion.getText();
        String nombre = txtNombreAdministrador.getText();
        String ci = txtCIAdministrador.getText();

        if (id.isEmpty() || cargo.isEmpty() || contactoTexto.isEmpty() || fechaCreacion.isEmpty() || nombre.isEmpty() || ci.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!esFechaValida(fechaCreacion)) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double contacto = Double.parseDouble(contactoTexto);
            Administrador administrador = new Administrador(id, cargo, contacto, fechaCreacion, nombre, ci);
            administradores.add(administrador);

            DefaultTableModel model = (DefaultTableModel) tablaAdministradores.getModel();
            model.addRow(new Object[]{id, cargo, contacto, fechaCreacion, nombre, ci});

            txtIDAdministrador.setText("");
            txtCargoAdministrador.setText("");
            txtContactoAdministrador.setText("");
            txtFechaCreacion.setText("");
            txtNombreAdministrador.setText("");
            txtCIAdministrador.setText("");
            JOptionPane.showMessageDialog(this, "Administrador agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El contacto debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar administrador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBorrarBonoActionPerformed(java.awt.event.ActionEvent evt) {
        if (bonos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay bonos registrados para borrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int selectedRow = tablaBonos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un bono de la tabla para borrarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar este bono y sus datos asociados?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            BonoDignidad bono = bonos.get(selectedRow);
            bonos.remove(selectedRow);
            cargarDatosEnTablas();
            JOptionPane.showMessageDialog(this, "Bono y datos asociados eliminados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnBorrarBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        if (beneficiarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados para borrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int selectedRow = tablaBeneficiarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un beneficiario de la tabla para borrarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar este beneficiario y sus solicitudes asociadas?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Beneficiario beneficiario = beneficiarios.get(selectedRow);
            for (BonoDignidad bono : bonos) {
                bono.getRegisSoli().removeIf(s -> s.getBeneficiario() == beneficiario);
            }
            beneficiarios.remove(selectedRow);
            cargarDatosEnTablas();
            JOptionPane.showMessageDialog(this, "Beneficiario y solicitudes asociadas eliminados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnBorrarAdministradorActionPerformed(java.awt.event.ActionEvent evt) {
        if (administradores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay administradores registrados para borrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int selectedRow = tablaAdministradores.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un administrador de la tabla para borrarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar este administrador?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            administradores.remove(selectedRow);
            cargarDatosEnTablas();
            JOptionPane.showMessageDialog(this, "Administrador eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnBeneficiariosPorDiscapacidadActionPerformed(java.awt.event.ActionEvent evt) {
        if (beneficiarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        HashMap<String, Integer> conteoDiscapacidades = new HashMap<>();
        for (Beneficiario beneficiario : beneficiarios) {
            String tipo = beneficiario.getTipodiscapacidad();
            conteoDiscapacidades.put(tipo, conteoDiscapacidades.getOrDefault(tipo, 0) + 1);
        }

        StringBuilder reporte = new StringBuilder("Beneficiarios por Tipo de Discapacidad:\n\n");
        for (String tipo : conteoDiscapacidades.keySet()) {
            reporte.append(tipo).append(": ").append(conteoDiscapacidades.get(tipo)).append(" beneficiarios\n");
        }

        JOptionPane.showMessageDialog(this, reporte.toString(), "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnMontoPorPeriodoActionPerformed(java.awt.event.ActionEvent evt) {
        String fechaInicioStr = JOptionPane.showInputDialog(this, "Ingrese la fecha de inicio del período (DD/MM/AAAA):");
        if (fechaInicioStr == null) return;
        String fechaFinStr = JOptionPane.showInputDialog(this, "Ingrese la fecha de fin del período (DD/MM/AAAA):");
        if (fechaFinStr == null) return;

        LocalDate fechaInicio, fechaFin;
        try {
            fechaInicio = LocalDate.parse(fechaInicioStr, FORMATO_FECHA);
            fechaFin = LocalDate.parse(fechaFinStr, FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(this, "Fecha de pago inválida: " + pago.getFechaPago(), "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Monto Total Pagado\nPeríodo: " + fechaInicioStr + " - " + fechaFinStr + "\nMonto Total: " + montoTotal, "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnVerificarSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {
        if (bonos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay bonos registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder reporte = new StringBuilder("Verificación de Solicitudes:\n\n");
        for (BonoDignidad bono : bonos) {
            reporte.append("Bono: ").append(bono.getNombretipo()).append("\n");
            for (SolicitudBono solicitud : bono.getRegisSoli()) {
                reporte.append("- Fecha: ").append(solicitud.getFechaSolicitud()).append("\n");
                reporte.append("  Beneficiario: ").append(solicitud.getBeneficiario() != null ? solicitud.getBeneficiario().getNombre() : "No asignado").append("\n");
                reporte.append("  Estado: ").append(solicitud.getEstado()).append("\n");
                reporte.append("  Pagada: ").append(solicitud.isPagada() ? "Sí" : "No").append("\n");
            }
            reporte.append("\n");
        }

        JOptionPane.showMessageDialog(this, reporte.toString(), "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnFechasBonoBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        if (beneficiarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] opciones = new String[beneficiarios.size()];
        for (int i = 0; i < beneficiarios.size(); i++) {
            opciones[i] = beneficiarios.get(i).getNombre() + " (CI: " + beneficiarios.get(i).getCi() + ")";
        }
        String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione un Beneficiario:", "Selección", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (seleccion == null) return;

        String ciSeleccionado = seleccion.substring(seleccion.indexOf("(") + 1, seleccion.indexOf(")"));
        Beneficiario beneficiario = null;
        for (Beneficiario b : beneficiarios) {
            if (b.getCi().equals(ciSeleccionado)) {
                beneficiario = b;
                break;
            }
        }

        StringBuilder reporte = new StringBuilder("Fechas de Bonos para: " + beneficiario.getNombre() + "\n\n");
        boolean tieneBono = false;
        for (BonoDignidad bono : bonos) {
            for (SolicitudBono solicitud : bono.getRegisSoli()) {
                if (solicitud.getBeneficiario() == beneficiario) {
                    reporte.append("- Bono: ").append(bono.getNombretipo()).append("\n");
                    reporte.append("  Inicio: ").append(bono.getFechaIni()).append("\n");
                    reporte.append("  Fin: ").append(bono.getFechaFin()).append("\n");
                    tieneBono = true;
                }
            }
        }
        if (!tieneBono) {
            reporte.append("No tiene bonos asignados.");
        }

        JOptionPane.showMessageDialog(this, reporte.toString(), "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnListarBeneficiariosConPagosActionPerformed(java.awt.event.ActionEvent evt) {
        StringBuilder reporte = new StringBuilder("Beneficiarios con Pagos:\n\n");
        boolean hayPagos = false;
        for (Beneficiario beneficiario : beneficiarios) {
            for (SolicitudBono solicitud : beneficiario.getSolicitudes()) {
                if (solicitud.isPagada()) {
                    reporte.append("- ").append(beneficiario.getNombre()).append(" (CI: ").append(beneficiario.getCi()).append(")\n");
                    hayPagos = true;
                    break;
                }
            }
        }
        if (!hayPagos) {
            reporte.append("No hay beneficiarios con pagos.");
        }

        JOptionPane.showMessageDialog(this, reporte.toString(), "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }
}