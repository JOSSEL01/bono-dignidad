package Dignidad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BonoDignidadGUI extends javax.swing.JFrame {
    private static final int MAX_REGISTROS = 100;
    private BonoDignidad[] bonos;
    private int[] numBonos;
    private Beneficiario[] beneficiarios;
    private int[] numBeneficiarios;
    private Administrador[] administradores;
    private int[] numAdministradores;

    // Declare Swing components as class fields
    private JPanel backgroundPanel;
    private JPanel headerPanel;
    private JLabel lblTitulo;
    private JTabbedPane tabbedPane;
    private JPanel bonosPanel;
    private JScrollPane jScrollPane1;
    private JTable tablaBonos;
    private JPanel bonosButtonsPanel;
    private JButton btnAgregarBono;
    private JButton btnBorrarBono;
    private JButton btnRegistrarPago;
    private JPanel beneficiariosPanel;
    private JScrollPane jScrollPane2;
    private JTable tablaBeneficiarios;
    private JPanel beneficiariosButtonsPanel;
    private JButton btnAgregarBeneficiario;
    private JButton btnBorrarBeneficiario;
    private JPanel administradoresPanel;
    private JScrollPane jScrollPane3;
    private JTable tablaAdministradores;
    private JPanel administradoresButtonsPanel;
    private JButton btnAgregarAdministrador;
    private JButton btnBorrarAdministrador;
    private JPanel reportesPanel;
    private JButton btnGenerarReporte;
    private JButton btnBeneficiosPorDiscapacidad;
    private JButton btnMontoPorPeriodo;
    private JButton btnVerificarSolicitudes;
    private JButton btnFechasBonoBeneficiario;
    private JButton btnMultiplesBonos;

    public BonoDignidadGUI(BonoDignidad[] bonos, int[] numBonos, Beneficiario[] beneficiarios, int[] numBeneficiarios, Administrador[] administradores, int[] numAdministradores) {
        this.bonos = bonos;
        this.numBonos = numBonos;
        this.beneficiarios = beneficiarios;
        this.numBeneficiarios = numBeneficiarios;
        this.administradores = administradores;
        this.numAdministradores = numAdministradores;
        initComponents();
        cargarDatosEnTablas();
        customDesign();
    }

    private void customDesign() {
        // Fondo degradado moderno
        backgroundPanel.setOpaque(false);

        // Estilo del título
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(new EmptyBorder(15, 0, 15, 0));

        // Panel superior para el título con fondo azul oscuro
        headerPanel.setBackground(new Color(25, 50, 75));
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(20, 40, 60)),
            new EmptyBorder(0, 0, 10, 0)
        ));

        // Estilo del JTabbedPane
        tabbedPane.setBackground(new Color(25, 50, 75));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Estilo de las tablas
        estilizarTabla(tablaBonos, jScrollPane1);
        estilizarTabla(tablaBeneficiarios, jScrollPane2);
        estilizarTabla(tablaAdministradores, jScrollPane3);

        // Estilo de los paneles de las pestañas
        stylePanel(bonosPanel);
        stylePanel(beneficiariosPanel);
        stylePanel(administradoresPanel);
        stylePanel(reportesPanel);

        // Estilo de los paneles de botones
        bonosButtonsPanel.setOpaque(false);
        beneficiariosButtonsPanel.setOpaque(false);
        administradoresButtonsPanel.setOpaque(false);

        // Estilo de los botones
        estilizarBoton(btnAgregarBono, new Color(50, 168, 82), "Agregar");
        estilizarBoton(btnBorrarBono, new Color(235, 64, 52), "Borrar");
        estilizarBoton(btnRegistrarPago, new Color(50, 168, 82), "Pagar");
        estilizarBoton(btnAgregarBeneficiario, new Color(50, 168, 82), "Agregar");
        estilizarBoton(btnBorrarBeneficiario, new Color(235, 64, 52), "Borrar");
        estilizarBoton(btnAgregarAdministrador, new Color(50, 168, 82), "Agregar");
        estilizarBoton(btnBorrarAdministrador, new Color(235, 64, 52), "Borrar");
        estilizarBoton(btnGenerarReporte, new Color(70, 130, 180), "Reporte");
        estilizarBoton(btnBeneficiosPorDiscapacidad, new Color(70, 130, 180), "Discapacidad");
        estilizarBoton(btnMontoPorPeriodo, new Color(70, 130, 180), "Período");
        estilizarBoton(btnVerificarSolicitudes, new Color(70, 130, 180), "Solicitudes");
        estilizarBoton(btnFechasBonoBeneficiario, new Color(70, 130, 180), "Fechas");
        estilizarBoton(btnMultiplesBonos, new Color(70, 130, 180), "Múltiples");
    }

    private void stylePanel(JPanel panel) {
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
    }

    private void estilizarTabla(JTable tabla, JScrollPane scrollPane) {
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(30);
        tabla.setBackground(new Color(245, 245, 245));
        tabla.setForeground(new Color(40, 40, 40));
        tabla.setGridColor(new Color(200, 200, 200));
        tabla.setShowGrid(true);

        // Centrar el texto en las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Estilo del encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(25, 50, 75));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createLineBorder(new Color(20, 40, 60)));

        // Estilo del JScrollPane
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true)
        ));
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));
    }

    private void estilizarBoton(JButton boton, Color colorBase, String tipo) {
        boton.setBackground(colorBase);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto de hover
        Color colorHover;
        switch (tipo.toLowerCase()) {
            case "agregar":
            case "pagar":
                colorHover = new Color(70, 188, 102);
                break;
            case "borrar":
                colorHover = new Color(255, 84, 72);
                break;
            default:
                colorHover = new Color(90, 150, 200);
                break;
        }

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorBase);
            }
        });

        // Añadir ícono simulado (puedes reemplazar con imágenes reales)
        String iconPath;
        switch (tipo.toLowerCase()) {
            case "agregar":
                iconPath = "icons/add.png";
                break;
            case "borrar":
                iconPath = "icons/delete.png";
                break;
            case "pagar":
                iconPath = "icons/pay.png";
                break;
            case "reporte":
                iconPath = "icons/report.png";
                break;
            case "discapacidad":
                iconPath = "icons/disability.png";
                break;
            case "período":
                iconPath = "icons/period.png";
                break;
            case "solicitudes":
                iconPath = "icons/verify.png";
                break;
            case "fechas":
                iconPath = "icons/dates.png";
                break;
            case "múltiples":
                iconPath = "icons/multiple.png";
                break;
            default:
                iconPath = null;
        }
        if (iconPath != null) {
            // Simulamos el ícono con un texto, reemplaza con ImageIcon real si tienes los archivos
            boton.setIcon(new ImageIcon(iconPath));
            boton.setIconTextGap(10);
        }
    }

    private void cargarDatosEnTablas() {
        tablaBonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"Tipo", "Fecha Inicio", "Fecha Fin", "Monto"}
        ));
        tablaBeneficiarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"Nombre", "CI", "Edad", "Dirección", "Fecha Nacimiento", "Tipo Discapacidad", "Grado Discapacidad"}
        ));
        tablaAdministradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Cargo", "Contacto", "Fecha Creación", "Nombre", "CI"}
        ));

        javax.swing.table.DefaultTableModel modelBonos = (javax.swing.table.DefaultTableModel) tablaBonos.getModel();
        for (int i = 0; i < numBonos[0]; i++) {
            modelBonos.addRow(new Object[]{
                bonos[i].getNombretipo(),
                bonos[i].getFechaIni(),
                bonos[i].getFechaFin(),
                bonos[i].getMonto()
            });
        }

        javax.swing.table.DefaultTableModel modelBeneficiarios = (javax.swing.table.DefaultTableModel) tablaBeneficiarios.getModel();
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            modelBeneficiarios.addRow(new Object[]{
                beneficiarios[i].getNombre(),
                beneficiarios[i].getCi(),
                beneficiarios[i].getEdad(),
                beneficiarios[i].getDireccion(),
                beneficiarios[i].getFecha_nacimiento(),
                beneficiarios[i].getTipodiscapacidad(),
                beneficiarios[i].getGradodiscapacidad()
            });
        }

        javax.swing.table.DefaultTableModel modelAdministradores = (javax.swing.table.DefaultTableModel) tablaAdministradores.getModel();
        for (int i = 0; i < numAdministradores[0]; i++) {
            modelAdministradores.addRow(new Object[]{
                administradores[i].getId(),
                administradores[i].getCargo(),
                administradores[i].getContacto(),
                administradores[i].getFecCreCuenta(),
                administradores[i].getNombre(),
                administradores[i].getCi()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        bonosPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBonos = new javax.swing.JTable();
        bonosButtonsPanel = new javax.swing.JPanel();
        btnAgregarBono = new javax.swing.JButton();
        btnBorrarBono = new javax.swing.JButton();
        btnRegistrarPago = new javax.swing.JButton();
        beneficiariosPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaBeneficiarios = new javax.swing.JTable();
        beneficiariosButtonsPanel = new javax.swing.JPanel();
        btnAgregarBeneficiario = new javax.swing.JButton();
        btnBorrarBeneficiario = new javax.swing.JButton();
        administradoresPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAdministradores = new javax.swing.JTable();
        administradoresButtonsPanel = new javax.swing.JPanel();
        btnAgregarAdministrador = new javax.swing.JButton();
        btnBorrarAdministrador = new javax.swing.JButton();
        reportesPanel = new javax.swing.JPanel();
        btnGenerarReporte = new javax.swing.JButton();
        btnBeneficiosPorDiscapacidad = new javax.swing.JButton();
        btnMontoPorPeriodo = new javax.swing.JButton();
        btnVerificarSolicitudes = new javax.swing.JButton();
        btnFechasBonoBeneficiario = new javax.swing.JButton();
        btnMultiplesBonos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Bono Dignidad");
        setPreferredSize(new java.awt.Dimension(1200, 800));

        backgroundPanel.setLayout(new java.awt.BorderLayout());

        headerPanel.setLayout(new java.awt.BorderLayout());

        lblTitulo.setText("Sistema Bono Dignidad");
        headerPanel.add(lblTitulo, java.awt.BorderLayout.CENTER);

        backgroundPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        bonosPanel.setLayout(new java.awt.BorderLayout(10, 10));

        tablaBonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Tipo", "Fecha Inicio", "Fecha Fin", "Monto"
            }
        ));
        jScrollPane1.setViewportView(tablaBonos);

        bonosPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        bonosButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));

        btnAgregarBono.setText("Agregar Bono");
        btnAgregarBono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBonoActionPerformed(evt);
            }
        });
        bonosButtonsPanel.add(btnAgregarBono);

        btnBorrarBono.setText("Borrar Bono");
        btnBorrarBono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarBonoActionPerformed(evt);
            }
        });
        bonosButtonsPanel.add(btnBorrarBono);

        btnRegistrarPago.setText("Registrar Pago");
        btnRegistrarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPagoActionPerformed(evt);
            }
        });
        bonosButtonsPanel.add(btnRegistrarPago);

        bonosPanel.add(bonosButtonsPanel, java.awt.BorderLayout.SOUTH);

        tabbedPane.addTab("Bonos", bonosPanel);

        beneficiariosPanel.setLayout(new java.awt.BorderLayout(10, 10));

        tablaBeneficiarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Nombre", "CI", "Edad", "Dirección", "Fecha Nacimiento", "Tipo Discapacidad", "Grado Discapacidad"
            }
        ));
        jScrollPane2.setViewportView(tablaBeneficiarios);

        beneficiariosPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        beneficiariosButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));

        btnAgregarBeneficiario.setText("Agregar Beneficiario");
        btnAgregarBeneficiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBeneficiarioActionPerformed(evt);
            }
        });
        beneficiariosButtonsPanel.add(btnAgregarBeneficiario);

        btnBorrarBeneficiario.setText("Borrar Beneficiario");
        btnBorrarBeneficiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarBeneficiarioActionPerformed(evt);
            }
        });
        beneficiariosButtonsPanel.add(btnBorrarBeneficiario);

        beneficiariosPanel.add(beneficiariosButtonsPanel, java.awt.BorderLayout.SOUTH);

        tabbedPane.addTab("Beneficiarios", beneficiariosPanel);

        administradoresPanel.setLayout(new java.awt.BorderLayout(10, 10));

        tablaAdministradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Cargo", "Contacto", "Fecha Creación", "Nombre", "CI"
            }
        ));
        jScrollPane3.setViewportView(tablaAdministradores);

        administradoresPanel.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        administradoresButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));

        btnAgregarAdministrador.setText("Agregar Administrador");
        btnAgregarAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAdministradorActionPerformed(evt);
            }
        });
        administradoresButtonsPanel.add(btnAgregarAdministrador);

        btnBorrarAdministrador.setText("Borrar Administrador");
        btnBorrarAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarAdministradorActionPerformed(evt);
            }
        });
        administradoresButtonsPanel.add(btnBorrarAdministrador);

        administradoresPanel.add(administradoresButtonsPanel, java.awt.BorderLayout.SOUTH);

        tabbedPane.addTab("Administradores", administradoresPanel);

        reportesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 15));

        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });
        reportesPanel.add(btnGenerarReporte);

        btnBeneficiosPorDiscapacidad.setText("Beneficios por Discap.");
        btnBeneficiosPorDiscapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeneficiosPorDiscapacidadActionPerformed(evt);
            }
        });
        reportesPanel.add(btnBeneficiosPorDiscapacidad);

        btnMontoPorPeriodo.setText("Monto por Período");
        btnMontoPorPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMontoPorPeriodoActionPerformed(evt);
            }
        });
        reportesPanel.add(btnMontoPorPeriodo);

        btnVerificarSolicitudes.setText("Verificar Solicitudes");
        btnVerificarSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarSolicitudesActionPerformed(evt);
            }
        });
        reportesPanel.add(btnVerificarSolicitudes);

        btnFechasBonoBeneficiario.setText("Fechas de Beneficiario");
        btnFechasBonoBeneficiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechasBonoBeneficiarioActionPerformed(evt);
            }
        });
        reportesPanel.add(btnFechasBonoBeneficiario);

        btnMultiplesBonos.setText("Múltiples Bonos");
        btnMultiplesBonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiplesBonosActionPerformed(evt);
            }
        });
        reportesPanel.add(btnMultiplesBonos);

        tabbedPane.addTab("Reportes", reportesPanel);

        backgroundPanel.add(tabbedPane, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnAgregarBonoActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBonos[0] >= MAX_REGISTROS) {
            JOptionPane.showMessageDialog(this, "No se pueden agregar más bonos, límite alcanzado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JTextField tipoField = new JTextField(20);
        JTextField fechaIniField = new JTextField(10);
        JTextField fechaFinField = new JTextField(10);
        JTextField montoField = new JTextField(10);
        JComboBox<String> beneficiarioCombo = new JComboBox<>();
        beneficiarioCombo.addItem("Ninguno");
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            beneficiarioCombo.addItem(beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
        }

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Tipo:"));
        panel.add(tipoField);
        panel.add(new JLabel("Fecha Inicio (dd/mm/yyyy):"));
        panel.add(fechaIniField);
        panel.add(new JLabel("Fecha Fin (dd/mm/yyyy):"));
        panel.add(fechaFinField);
        panel.add(new JLabel("Monto:"));
        panel.add(montoField);
        panel.add(new JLabel("Beneficiario:"));
        panel.add(beneficiarioCombo);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Bono", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String tipo = tipoField.getText().trim();
                String fechaIni = fechaIniField.getText().trim();
                String fechaFin = fechaFinField.getText().trim();
                double monto = Double.parseDouble(montoField.getText().trim());

                if (tipo.isEmpty() || fechaIni.isEmpty() || fechaFin.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!validarFormatoFecha(fechaIni) || !validarFormatoFecha(fechaFin)) {
                    JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                BonoDignidad bono = new BonoDignidad(tipo, fechaIni, fechaFin, monto);
                int beneficiarioIndex = beneficiarioCombo.getSelectedIndex() - 1;
                if (beneficiarioIndex >= 0) {
                    SolicitudBono solicitud = new SolicitudBono(fechaIni, "Aprobada", beneficiarios[beneficiarioIndex]);
                    bono.agregarSolicitud(solicitud);
                }

                bonos[numBonos[0]] = bono;
                numBonos[0]++;
                cargarDatosEnTablas();
                JOptionPane.showMessageDialog(this, "Bono registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnAgregarBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBeneficiarios[0] >= MAX_REGISTROS) {
            JOptionPane.showMessageDialog(this, "No se pueden agregar más beneficiarios, límite alcanzado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JTextField nombreField = new JTextField(20);
        JTextField ciField = new JTextField(10);
        JTextField edadField = new JTextField(5);
        JTextField direccionField = new JTextField(20);
        JTextField fechaNacField = new JTextField(10);
        JTextField tipoDiscField = new JTextField(15);
        JTextField gradoDiscField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("CI:"));
        panel.add(ciField);
        panel.add(new JLabel("Edad:"));
        panel.add(edadField);
        panel.add(new JLabel("Dirección:"));
        panel.add(direccionField);
        panel.add(new JLabel("Fecha Nacimiento (dd/mm/yyyy):"));
        panel.add(fechaNacField);
        panel.add(new JLabel("Tipo Discapacidad:"));
        panel.add(tipoDiscField);
        panel.add(new JLabel("Grado Discapacidad:"));
        panel.add(gradoDiscField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Beneficiario", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText().trim();
                String ci = ciField.getText().trim();
                int edad = Integer.parseInt(edadField.getText().trim());
                String direccion = direccionField.getText().trim();
                String fechaNac = fechaNacField.getText().trim();
                String tipoDisc = tipoDiscField.getText().trim();
                String gradoDisc = gradoDiscField.getText().trim();

                if (nombre.isEmpty() || ci.isEmpty() || direccion.isEmpty() || fechaNac.isEmpty() || tipoDisc.isEmpty() || gradoDisc.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!validarFormatoFecha(fechaNac)) {
                    JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Beneficiario beneficiario = new Beneficiario(nombre, ci, edad, direccion, fechaNac, tipoDisc, gradoDisc);
                beneficiarios[numBeneficiarios[0]] = beneficiario;
                numBeneficiarios[0]++;
                cargarDatosEnTablas();
                JOptionPane.showMessageDialog(this, "Beneficiario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnAgregarAdministradorActionPerformed(java.awt.event.ActionEvent evt) {
        if (numAdministradores[0] >= MAX_REGISTROS) {
            JOptionPane.showMessageDialog(this, "No se pueden agregar más administradores, límite alcanzado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JTextField idField = new JTextField(10);
        JTextField cargoField = new JTextField(15);
        JTextField contactoField = new JTextField(20);
        JTextField fechaCreacionField = new JTextField(10);
        JTextField nombreField = new JTextField(20);
        JTextField ciField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Cargo:"));
        panel.add(cargoField);
        panel.add(new JLabel("Contacto:"));
        panel.add(contactoField);
        panel.add(new JLabel("Fecha Creación (dd/mm/yyyy):"));
        panel.add(fechaCreacionField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("CI:"));
        panel.add(ciField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Administrador", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String cargo = cargoField.getText().trim();
            String contacto = contactoField.getText().trim();
            String fechaCreacion = fechaCreacionField.getText().trim();
            String nombre = nombreField.getText().trim();
            String ci = ciField.getText().trim();

            if (id.isEmpty() || cargo.isEmpty() || contacto.isEmpty() || fechaCreacion.isEmpty() || nombre.isEmpty() || ci.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validarFormatoFecha(fechaCreacion)) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Administrador administrador = new Administrador(id, cargo, contacto, fechaCreacion, nombre, ci);
            administradores[numAdministradores[0]] = administrador;
            numAdministradores[0]++;
            cargarDatosEnTablas();
            JOptionPane.showMessageDialog(this, "Administrador registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnBorrarBonoActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBonos[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay bonos registrados para borrar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = tablaBonos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un bono para borrar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar este bono?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            for (int i = selectedRow; i < numBonos[0] - 1; i++) {
                bonos[i] = bonos[i + 1];
            }
            bonos[numBonos[0] - 1] = null;
            numBonos[0]--;
            cargarDatosEnTablas();
            JOptionPane.showMessageDialog(this, "Bono eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnBorrarBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBeneficiarios[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados para borrar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = tablaBeneficiarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un beneficiario para borrar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar este beneficiario?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Eliminar solicitudes asociadas
            for (int i = 0; i < numBonos[0]; i++) {
                for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                    if (bonos[i].getRegisSoli()[j].getBeneficiario() == beneficiarios[selectedRow]) {
                        for (int k = j; k < bonos[i].getNumSolicitudes() - 1; k++) {
                            bonos[i].getRegisSoli()[k] = bonos[i].getRegisSoli()[k + 1];
                        }
                        bonos[i].getRegisSoli()[bonos[i].getNumSolicitudes() - 1] = null;
                        bonos[i].setNumSolicitudes(bonos[i].getNumSolicitudes() - 1);
                    }
                }
            }
            for (int i = selectedRow; i < numBeneficiarios[0] - 1; i++) {
                beneficiarios[i] = beneficiarios[i + 1];
            }
            beneficiarios[numBeneficiarios[0] - 1] = null;
            numBeneficiarios[0]--;
            cargarDatosEnTablas();
            JOptionPane.showMessageDialog(this, "Beneficiario y solicitudes asociadas eliminados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnBorrarAdministradorActionPerformed(java.awt.event.ActionEvent evt) {
        if (numAdministradores[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay administradores registrados para borrar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = tablaAdministradores.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un administrador para borrar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar este administrador?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            for (int i = selectedRow; i < numAdministradores[0] - 1; i++) {
                administradores[i] = administradores[i + 1];
            }
            administradores[numAdministradores[0] - 1] = null;
            numAdministradores[0]--;
            cargarDatosEnTablas();
            JOptionPane.showMessageDialog(this, "Administrador eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnRegistrarPagoActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBonos[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay bonos registrados para registrar un pago.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Seleccionar el bono
        JComboBox<String> bonoCombo = new JComboBox<>();
        for (int i = 0; i < numBonos[0]; i++) {
            bonoCombo.addItem(bonos[i].getNombretipo() + " (Inicio: " + bonos[i].getFechaIni() + ")");
        }

        JPanel bonoPanel = new JPanel(new GridLayout(1, 2));
        bonoPanel.add(new JLabel("Seleccione Bono:"));
        bonoPanel.add(bonoCombo);

        int bonoResult = JOptionPane.showConfirmDialog(this, bonoPanel, "Seleccionar Bono", JOptionPane.OK_CANCEL_OPTION);
        if (bonoResult != JOptionPane.OK_OPTION) {
            return;
        }

        int bonoIndex = bonoCombo.getSelectedIndex();
        BonoDignidad bonoSeleccionado = bonos[bonoIndex];

        // Verificar si el bono tiene solicitudes
        SolicitudBono solicitudSeleccionada = null;
        if (bonoSeleccionado.getNumSolicitudes() > 0) {
            // Mostrar solicitudes existentes
            JComboBox<String> solicitudCombo = new JComboBox<>();
            for (int i = 0; i < bonoSeleccionado.getNumSolicitudes(); i++) {
                SolicitudBono solicitud = bonoSeleccionado.getRegisSoli()[i];
                String beneficiarioNombre = solicitud.getBeneficiario() != null ? solicitud.getBeneficiario().getNombre() : "Sin beneficiario";
                solicitudCombo.addItem("Solicitud " + (i + 1) + " - Fecha: " + solicitud.getFechaSolicitud() + " - Beneficiario: " + beneficiarioNombre);
            }
            solicitudCombo.addItem("Crear nueva solicitud");

            JPanel solicitudPanel = new JPanel(new GridLayout(1, 2));
            solicitudPanel.add(new JLabel("Seleccione Solicitud:"));
            solicitudPanel.add(solicitudCombo);

            int solicitudResult = JOptionPane.showConfirmDialog(this, solicitudPanel, "Seleccionar Solicitud", JOptionPane.OK_CANCEL_OPTION);
            if (solicitudResult != JOptionPane.OK_OPTION) {
                return;
            }

            int solicitudIndex = solicitudCombo.getSelectedIndex();
            if (solicitudIndex == bonoSeleccionado.getNumSolicitudes()) {
                // Crear nueva solicitud
                solicitudSeleccionada = crearNuevaSolicitud(bonoSeleccionado);
                if (solicitudSeleccionada == null) {
                    return; // El usuario canceló o no hay beneficiarios
                }
            } else {
                solicitudSeleccionada = bonoSeleccionado.getRegisSoli()[solicitudIndex];
            }
        } else {
            // No hay solicitudes, crear una nueva
            solicitudSeleccionada = crearNuevaSolicitud(bonoSeleccionado);
            if (solicitudSeleccionada == null) {
                return; // El usuario canceló o no hay beneficiarios
            }
        }

        // Registrar el pago
        JTextField fechaPagoField = new JTextField(10);
        JTextField montoField = new JTextField(10);

        JPanel pagoPanel = new JPanel(new GridLayout(2, 2));
        pagoPanel.add(new JLabel("Fecha Pago (dd/mm/yyyy):"));
        pagoPanel.add(fechaPagoField);
        pagoPanel.add(new JLabel("Monto:"));
        pagoPanel.add(montoField);

        int pagoResult = JOptionPane.showConfirmDialog(this, pagoPanel, "Registrar Pago", JOptionPane.OK_CANCEL_OPTION);
        if (pagoResult == JOptionPane.OK_OPTION) {
            try {
                String fechaPago = fechaPagoField.getText().trim();
                double monto = Double.parseDouble(montoField.getText().trim());

                if (!validarFormatoFecha(fechaPago)) {
                    JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Pago pago = new Pago(fechaPago, monto, solicitudSeleccionada);
                bonoSeleccionado.agregarPago(pago);
                solicitudSeleccionada.setPagada(true);
                JOptionPane.showMessageDialog(this, "Pago registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private SolicitudBono crearNuevaSolicitud(BonoDignidad bono) {
        if (numBeneficiarios[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados para asociar una solicitud.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        JComboBox<String> beneficiarioCombo = new JComboBox<>();
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            beneficiarioCombo.addItem(beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
        }

        JTextField fechaSolicitudField = new JTextField(10);

        JPanel solicitudPanel = new JPanel(new GridLayout(2, 2));
        solicitudPanel.add(new JLabel("Seleccione Beneficiario:"));
        solicitudPanel.add(beneficiarioCombo);
        solicitudPanel.add(new JLabel("Fecha Solicitud (dd/mm/yyyy):"));
        solicitudPanel.add(fechaSolicitudField);

        int result = JOptionPane.showConfirmDialog(this, solicitudPanel, "Crear Nueva Solicitud", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String fechaSolicitud = fechaSolicitudField.getText().trim();
            if (!validarFormatoFecha(fechaSolicitud)) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            int beneficiarioIndex = beneficiarioCombo.getSelectedIndex();
            SolicitudBono solicitud = new SolicitudBono(fechaSolicitud, "Aprobada", beneficiarios[beneficiarioIndex]);
            bono.agregarSolicitud(solicitud);
            return solicitud;
        }
        return null;
    }

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte del Sistema Bono Dignidad\n\n");
        reporte.append("Bonos Registrados:\n");
        for (int i = 0; i < numBonos[0]; i++) {
            reporte.append(String.format("Bono #%d\n", i + 1));
            reporte.append(String.format("Tipo: %s\n", bonos[i].getNombretipo()));
            reporte.append(String.format("Fecha Inicio: %s\n", bonos[i].getFechaIni()));
            reporte.append(String.format("Fecha Fin: %s\n", bonos[i].getFechaFin()));
            reporte.append(String.format("Monto: %.2f\n", bonos[i].getMonto()));
            reporte.append("\n");
        }

        reporte.append("Beneficiarios Registrados:\n");
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            reporte.append(String.format("Beneficiario #%d\n", i + 1));
            reporte.append(String.format("Nombre: %s\n", beneficiarios[i].getNombre()));
            reporte.append(String.format("CI: %s\n", beneficiarios[i].getCi()));
            reporte.append(String.format("Edad: %d\n", beneficiarios[i].getEdad()));
            reporte.append(String.format("Dirección: %s\n", beneficiarios[i].getDireccion()));
            reporte.append(String.format("Fecha Nacimiento: %s\n", beneficiarios[i].getFecha_nacimiento()));
            reporte.append(String.format("Tipo Discapacidad: %s\n", beneficiarios[i].getTipodiscapacidad()));
            reporte.append(String.format("Grado Discapacidad: %s\n", beneficiarios[i].getGradodiscapacidad()));
            reporte.append("\n");
        }

        reporte.append("Administradores Registrados:\n");
        for (int i = 0; i < numAdministradores[0]; i++) {
            reporte.append(String.format("Administrador #%d\n", i + 1));
            reporte.append(String.format("ID: %s\n", administradores[i].getId()));
            reporte.append(String.format("Cargo: %s\n", administradores[i].getCargo()));
            reporte.append(String.format("Contacto: %s\n", administradores[i].getContacto()));
            reporte.append(String.format("Fecha Creación: %s\n", administradores[i].getFecCreCuenta()));
            reporte.append(String.format("Nombre: %s\n", administradores[i].getNombre()));
            reporte.append(String.format("CI: %s\n", administradores[i].getCi()));
            reporte.append("\n");
        }

        JTextArea textArea = new JTextArea(reporte.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        JOptionPane.showMessageDialog(this, scrollPane, "Reporte General", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnBeneficiosPorDiscapacidadActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBeneficiarios[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
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

        StringBuilder reporte = new StringBuilder();
        reporte.append("Beneficiarios por Tipo de Discapacidad\n\n");
        for (int i = 0; i < tiposCount; i++) {
            reporte.append(String.format("%s: %d beneficiarios\n", tipos[i], conteoDiscapacidades[i]));
        }

        JTextArea textArea = new JTextArea(reporte.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        JOptionPane.showMessageDialog(this, scrollPane, "Beneficiarios por Discapacidad", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnMontoPorPeriodoActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField fechaInicioField = new JTextField(10);
        JTextField fechaFinField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Fecha Inicio (dd/mm/yyyy):"));
        panel.add(fechaInicioField);
        panel.add(new JLabel("Fecha Fin (dd/mm/yyyy):"));
        panel.add(fechaFinField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Monto por Período", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String fechaInicioStr = fechaInicioField.getText().trim();
            String fechaFinStr = fechaFinField.getText().trim();

            if (!validarFormatoFecha(fechaInicioStr) || !validarFormatoFecha(fechaFinStr)) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use DD/MM/AAAA.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double montoTotal = 0.0;
            for (int i = 0; i < numBonos[0]; i++) {
                for (int j = 0; j < bonos[i].getNumPagos(); j++) {
                    String fechaPago = bonos[i].getRegisPago()[j].getFechaPago();
                    if (!validarFormatoFecha(fechaPago)) continue;
                    if (fechaEsDentroDelRango(fechaPago, fechaInicioStr, fechaFinStr)) {
                        montoTotal += bonos[i].getRegisPago()[j].getMonto();
                    }
                }
            }

            String mensaje = String.format("Monto Total Pagado\n\nPeríodo: %s - %s\nMonto Total: %.2f", fechaInicioStr, fechaFinStr, montoTotal);
            JOptionPane.showMessageDialog(this, mensaje, "Monto por Período", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnVerificarSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBonos[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay bonos registrados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("Verificación de Solicitudes\n\n");
        for (int i = 0; i < numBonos[0]; i++) {
            reporte.append(String.format("Bono: %s\n", bonos[i].getNombretipo()));
            for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                SolicitudBono solicitud = bonos[i].getRegisSoli()[j];
                reporte.append(String.format("- Fecha: %s\n", solicitud.getFechaSolicitud()));
                reporte.append(String.format("  Beneficiario: %s\n", solicitud.getBeneficiario() != null ? solicitud.getBeneficiario().getNombre() : "No asignado"));
                reporte.append(String.format("  Estado: %s\n", solicitud.getEstado()));
                reporte.append(String.format("  Pagada: %s\n", solicitud.isPagada() ? "Sí" : "No"));
            }
            reporte.append("\n");
        }

        JTextArea textArea = new JTextArea(reporte.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        JOptionPane.showMessageDialog(this, scrollPane, "Verificación de Solicitudes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnFechasBonoBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBeneficiarios[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JComboBox<String> beneficiarioCombo = new JComboBox<>();
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            beneficiarioCombo.addItem(beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
        }

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Seleccione Beneficiario:"));
        panel.add(beneficiarioCombo);

        int result = JOptionPane.showConfirmDialog(this, panel, "Fechas de Bono por Beneficiario", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int beneficiarioIndex = beneficiarioCombo.getSelectedIndex();
            Beneficiario beneficiario = beneficiarios[beneficiarioIndex];
            StringBuilder reporte = new StringBuilder();
            reporte.append(String.format("Bonos para %s\n\n", beneficiario.getNombre()));

            for (int i = 0; i < numBonos[0]; i++) {
                for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                    SolicitudBono solicitud = bonos[i].getRegisSoli()[j];
                    if (solicitud.getBeneficiario() == beneficiario) {
                        reporte.append(String.format("Bono: %s\n", bonos[i].getNombretipo()));
                        reporte.append(String.format("Fecha Solicitud: %s\n", solicitud.getFechaSolicitud()));
                        reporte.append(String.format("Fecha Inicio Bono: %s\n", bonos[i].getFechaIni()));
                        reporte.append(String.format("Fecha Fin Bono: %s\n", bonos[i].getFechaFin()));
                        reporte.append("\n");
                    }
                }
            }

            JTextArea textArea = new JTextArea(reporte.toString());
            textArea.setEditable(false);
            textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            textArea.setBackground(new Color(245, 245, 245));
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(400, 200));
            scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            JOptionPane.showMessageDialog(this, scrollPane, "Fechas de Bono por Beneficiario", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnMultiplesBonosActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBeneficiarios[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("Beneficiarios con Múltiples Bonos\n\n");
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            Beneficiario beneficiario = beneficiarios[i];
            int conteoBonos = 0;
            for (int j = 0; j < numBonos[0]; j++) {
                for (int k = 0; k < bonos[j].getNumSolicitudes(); k++) {
                    if (bonos[j].getRegisSoli()[k].getBeneficiario() == beneficiario) {
                        conteoBonos++;
                    }
                }
            }
            if (conteoBonos > 1) {
                reporte.append(String.format("%s (CI: %s) - %d bonos\n", beneficiario.getNombre(), beneficiario.getCi(), conteoBonos));
            }
        }

        JTextArea textArea = new JTextArea(reporte.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        JOptionPane.showMessageDialog(this, scrollPane, "Beneficiarios con Múltiples Bonos", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean validarFormatoFecha(String fecha) {
        if (fecha == null || !fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }
        try {
            String[] partes = fecha.split("/");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anio = Integer.parseInt(partes[2]);
            if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || anio < 1900 || anio > 2100) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean fechaEsDentroDelRango(String fecha, String fechaInicio, String fechaFin) {
        try {
            String[] partesFecha = fecha.split("/");
            String[] partesInicio = fechaInicio.split("/");
            String[] partesFin = fechaFin.split("/");

            int dia = Integer.parseInt(partesFecha[0]);
            int mes = Integer.parseInt(partesFecha[1]);
            int anio = Integer.parseInt(partesFecha[2]);

            int diaInicio = Integer.parseInt(partesInicio[0]);
            int mesInicio = Integer.parseInt(partesInicio[1]);
            int anioInicio = Integer.parseInt(partesInicio[2]);

            int diaFin = Integer.parseInt(partesFin[0]);
            int mesFin = Integer.parseInt(partesFin[1]);
            int anioFin = Integer.parseInt(partesFin[2]);

            // Convertir a un valor comparable (año * 10000 + mes * 100 + dia)
            int valorFecha = anio * 10000 + mes * 100 + dia;
            int valorInicio = anioInicio * 10000 + mesInicio * 100 + diaInicio;
            int valorFin = anioFin * 10000 + mesFin * 100 + diaFin;

            return valorFecha >= valorInicio && valorFecha <= valorFin;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BonoDignidad[] bonos = new BonoDignidad[MAX_REGISTROS];
            int[] numBonos = {0};
            Beneficiario[] beneficiarios = new Beneficiario[MAX_REGISTROS];
            int[] numBeneficiarios = {0};
            Administrador[] administradores = new Administrador[MAX_REGISTROS];
            int[] numAdministradores = {0};

            // Agregar datos por defecto
            // Bonos
            bonos[0] = new BonoDignidad("Bono Anual", "01/01/2025", "31/12/2025", 1200.50);
            bonos[1] = new BonoDignidad("Bono Semestral", "01/01/2025", "30/06/2025", 600.00);
            numBonos[0] = 2;

            // Beneficiarios
            beneficiarios[0] = new Beneficiario("Juan Perez", "12345678", 65, "Calle Falsa 123", "15/05/1959", "Motriz", "Moderado");
            beneficiarios[1] = new Beneficiario("Maria Lopez", "87654321", 70, "Av. Siempre Viva 456", "22/03/1954", "Visual", "Severo");
            beneficiarios[2] = new Beneficiario("Carlos Gomez", "11223344", 62, "Calle Luna 789", "10/11/1962", "Auditiva", "Leve");
            numBeneficiarios[0] = 3;

            // Administradores
            administradores[0] = new Administrador("ADM001", "Coordinador", "jefe@bono.com", "01/01/2020", "Ana Torres", "99887766");
            administradores[1] = new Administrador("ADM002", "Asistente", "asistente@bono.com", "15/06/2021", "Pedro Ruiz", "66554433");
            numAdministradores[0] = 2;

            // Asociar un beneficiario a un bono por defecto
            SolicitudBono solicitud1 = new SolicitudBono("01/01/2025", "Aprobada", beneficiarios[0]);
            bonos[0].agregarSolicitud(solicitud1);

            new BonoDignidadGUI(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores).setVisible(true);
        });
    }
}