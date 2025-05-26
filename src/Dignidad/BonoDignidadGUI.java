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

    private JPanel backgroundPanel;
    private JPanel headerPanel;
    private JLabel lblTitulo;
    private JTabbedPane tabbedPane;
    private JPanel bonosPanel;
    private JScrollPane jScrollPane1;
    private JTable tablaBonos;
    private JPanel bonosButtonsPanel;
    private JButton btnAgregarBono;
    private JButton btnEditarBono;
    private JButton btnBorrarBono;
    private JButton btnRegistrarPago;
    private JPanel beneficiariosPanel;
    private JScrollPane jScrollPane2;
    private JTable tablaBeneficiarios;
    private JPanel beneficiariosButtonsPanel;
    private JButton btnAgregarBeneficiario;
    private JButton btnEditarBeneficiario;
    private JButton btnBorrarBeneficiario;
    private JPanel administradoresPanel;
    private JScrollPane jScrollPane3;
    private JTable tablaAdministradores;
    private JPanel administradoresButtonsPanel;
    private JButton btnAgregarAdministrador;
    private JButton btnEditarAdministrador;
    private JButton btnBorrarAdministrador;
    private JPanel reportesPanel;
    private JButton btnGenerarReporte;
    private JButton btnBeneficiosPorDiscapacidad;
    private JButton btnMontoPorPeriodo;
    private JButton btnVerificarSolicitudes;
    private JButton btnFechasBonoBeneficiario;
    private JButton btnMultiplesBonos;
    private JButton btnTiempoTotalPago;

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
        backgroundPanel.setOpaque(false);

        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(new EmptyBorder(15, 0, 15, 0));

        headerPanel.setBackground(new Color(25, 50, 75));
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(20, 40, 60)),
            new EmptyBorder(0, 0, 10, 0)
        ));

        tabbedPane.setBackground(new Color(25, 50, 75));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        estilizarTabla(tablaBonos, jScrollPane1);
        estilizarTabla(tablaBeneficiarios, jScrollPane2);
        estilizarTabla(tablaAdministradores, jScrollPane3);

        stylePanel(bonosPanel);
        stylePanel(beneficiariosPanel);
        stylePanel(administradoresPanel);
        stylePanel(reportesPanel);

        bonosButtonsPanel.setOpaque(false);
        beneficiariosButtonsPanel.setOpaque(false);
        administradoresButtonsPanel.setOpaque(false);

        estilizarBoton(btnAgregarBono, new Color(50, 168, 82), "Agregar");
        estilizarBoton(btnEditarBono, new Color(255, 165, 0), "Editar");
        estilizarBoton(btnBorrarBono, new Color(235, 64, 52), "Borrar");
        estilizarBoton(btnRegistrarPago, new Color(50, 168, 82), "Pagar");
        estilizarBoton(btnAgregarBeneficiario, new Color(50, 168, 82), "Agregar");
        estilizarBoton(btnEditarBeneficiario, new Color(255, 165, 0), "Editar");
        estilizarBoton(btnBorrarBeneficiario, new Color(235, 64, 52), "Borrar");
        estilizarBoton(btnAgregarAdministrador, new Color(50, 168, 82), "Agregar");
        estilizarBoton(btnEditarAdministrador, new Color(255, 165, 0), "Editar");
        estilizarBoton(btnBorrarAdministrador, new Color(235, 64, 52), "Borrar");
        estilizarBoton(btnGenerarReporte, new Color(70, 130, 180), "Reporte");
        estilizarBoton(btnBeneficiosPorDiscapacidad, new Color(70, 130, 180), "Discapacidad");
        estilizarBoton(btnMontoPorPeriodo, new Color(70, 130, 180), "Período");
        estilizarBoton(btnVerificarSolicitudes, new Color(70, 130, 180), "Solicitudes");
        estilizarBoton(btnFechasBonoBeneficiario, new Color(70, 130, 180), "Fechas");
        estilizarBoton(btnMultiplesBonos, new Color(70, 130, 180), "Múltiples");
        estilizarBoton(btnTiempoTotalPago, new Color(70, 130, 180), "Tiempo");
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

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(25, 50, 75));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createLineBorder(new Color(20, 40, 60)));

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
        boton.setPreferredSize(new Dimension(200, 40));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color colorHover;
        if (tipo.equalsIgnoreCase("agregar") || tipo.equalsIgnoreCase("pagar")) {
            colorHover = new Color(70, 188, 102);
        } else if (tipo.equalsIgnoreCase("borrar")) {
            colorHover = new Color(255, 84, 72);
        } else if (tipo.equalsIgnoreCase("editar")) {
            colorHover = new Color(255, 185, 20);
        } else {
            colorHover = new Color(90, 150, 200);
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

        String iconPath;
        if (tipo.equalsIgnoreCase("agregar")) {
            iconPath = "icons/add.png";
        } else if (tipo.equalsIgnoreCase("borrar")) {
            iconPath = "icons/delete.png";
        } else if (tipo.equalsIgnoreCase("pagar")) {
            iconPath = "icons/pay.png";
        } else if (tipo.equalsIgnoreCase("editar")) {
            iconPath = "icons/edit.png";
        } else if (tipo.equalsIgnoreCase("reporte")) {
            iconPath = "icons/report.png";
        } else if (tipo.equalsIgnoreCase("discapacidad")) {
            iconPath = "icons/disability.png";
        } else if (tipo.equalsIgnoreCase("período")) {
            iconPath = "icons/period.png";
        } else if (tipo.equalsIgnoreCase("solicitudes")) {
            iconPath = "icons/verify.png";
        } else if (tipo.equalsIgnoreCase("fechas")) {
            iconPath = "icons/dates.png";
        } else if (tipo.equalsIgnoreCase("múltiples")) {
            iconPath = "icons/multiple.png";
        } else if (tipo.equalsIgnoreCase("tiempo")) {
            iconPath = "icons/time.png";
        } else {
            iconPath = null;
        }

        if (iconPath != null) {
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
        btnEditarBono = new javax.swing.JButton();
        btnBorrarBono = new javax.swing.JButton();
        btnRegistrarPago = new javax.swing.JButton();
        beneficiariosPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaBeneficiarios = new javax.swing.JTable();
        beneficiariosButtonsPanel = new javax.swing.JPanel();
        btnAgregarBeneficiario = new javax.swing.JButton();
        btnEditarBeneficiario = new javax.swing.JButton();
        btnBorrarBeneficiario = new javax.swing.JButton();
        administradoresPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAdministradores = new javax.swing.JTable();
        administradoresButtonsPanel = new javax.swing.JPanel();
        btnAgregarAdministrador = new javax.swing.JButton();
        btnEditarAdministrador = new javax.swing.JButton();
        btnBorrarAdministrador = new javax.swing.JButton();
        reportesPanel = new javax.swing.JPanel();
        btnGenerarReporte = new javax.swing.JButton();
        btnBeneficiosPorDiscapacidad = new javax.swing.JButton();
        btnMontoPorPeriodo = new javax.swing.JButton();
        btnVerificarSolicitudes = new javax.swing.JButton();
        btnFechasBonoBeneficiario = new javax.swing.JButton();
        btnMultiplesBonos = new javax.swing.JButton();
        btnTiempoTotalPago = new javax.swing.JButton();

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
            new Object[][] {},
            new String[] {"Tipo", "Fecha Inicio", "Fecha Fin", "Monto"}
        ));
        jScrollPane1.setViewportView(tablaBonos);
        bonosPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        bonosButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));
        btnAgregarBono.setText("Agregar Bono");
        btnAgregarBono.addActionListener(this::btnAgregarBonoActionPerformed);
        bonosButtonsPanel.add(btnAgregarBono);
        btnEditarBono.setText("Editar Bono");
        btnEditarBono.addActionListener(this::btnEditarBonoActionPerformed);
        bonosButtonsPanel.add(btnEditarBono);
        btnBorrarBono.setText("Borrar Bono");
        btnBorrarBono.addActionListener(this::btnBorrarBonoActionPerformed);
        bonosButtonsPanel.add(btnBorrarBono);
        btnRegistrarPago.setText("Registrar Pago");
        btnRegistrarPago.addActionListener(this::btnRegistrarPagoActionPerformed);
        bonosButtonsPanel.add(btnRegistrarPago);
        bonosPanel.add(bonosButtonsPanel, java.awt.BorderLayout.SOUTH);
        tabbedPane.addTab("Bonos", bonosPanel);
        beneficiariosPanel.setLayout(new java.awt.BorderLayout(10, 10));
        tablaBeneficiarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"Nombre", "CI", "Edad", "Dirección", "Fecha Nacimiento", "Tipo Discapacidad", "Grado Discapacidad"}
        ));
        jScrollPane2.setViewportView(tablaBeneficiarios);
        beneficiariosPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);
        beneficiariosButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));
        btnAgregarBeneficiario.setText("Agregar Beneficiario");
        btnAgregarBeneficiario.addActionListener(this::btnAgregarBeneficiarioActionPerformed);
        beneficiariosButtonsPanel.add(btnAgregarBeneficiario);
        btnEditarBeneficiario.setText("Editar Beneficiario");
        btnEditarBeneficiario.addActionListener(this::btnEditarBeneficiarioActionPerformed);
        beneficiariosButtonsPanel.add(btnEditarBeneficiario);
        btnBorrarBeneficiario.setText("Borrar Beneficiario");
        btnBorrarBeneficiario.addActionListener(this::btnBorrarBeneficiarioActionPerformed);
        beneficiariosButtonsPanel.add(btnBorrarBeneficiario);
        beneficiariosPanel.add(beneficiariosButtonsPanel, java.awt.BorderLayout.SOUTH);
        tabbedPane.addTab("Beneficiarios", beneficiariosPanel);
        administradoresPanel.setLayout(new java.awt.BorderLayout(10, 10));
        tablaAdministradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Cargo", "Contacto", "Fecha Creación", "Nombre", "CI"}
        ));
        jScrollPane3.setViewportView(tablaAdministradores);
        administradoresPanel.add(jScrollPane3, java.awt.BorderLayout.CENTER);
        administradoresButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));
        btnAgregarAdministrador.setText("Agregar Administrador");
        btnAgregarAdministrador.addActionListener(this::btnAgregarAdministradorActionPerformed);
        administradoresButtonsPanel.add(btnAgregarAdministrador);
        btnEditarAdministrador.setText("Editar Administrador");
        btnEditarAdministrador.addActionListener(this::btnEditarAdministradorActionPerformed);
        administradoresButtonsPanel.add(btnEditarAdministrador);
        btnBorrarAdministrador.setText("Borrar Administrador");
        btnBorrarAdministrador.addActionListener(this::btnBorrarAdministradorActionPerformed);
        administradoresButtonsPanel.add(btnBorrarAdministrador);
        administradoresPanel.add(administradoresButtonsPanel, java.awt.BorderLayout.SOUTH);
        tabbedPane.addTab("Administradores", administradoresPanel);
        reportesPanel.setLayout(new java.awt.GridLayout(3, 3, 15, 15));
        btnGenerarReporte.setText("Reporte General");
        btnGenerarReporte.addActionListener(this::btnGenerarReporteActionPerformed);
        btnGenerarReporte.setToolTipText("Generar Reporte General");
        reportesPanel.add(btnGenerarReporte);
        btnBeneficiosPorDiscapacidad.setText("Por Discapacidad");
        btnBeneficiosPorDiscapacidad.addActionListener(this::btnBeneficiosPorDiscapacidadActionPerformed);
        btnBeneficiosPorDiscapacidad.setToolTipText("Beneficiarios por Tipo de Discapacidad");
        reportesPanel.add(btnBeneficiosPorDiscapacidad);
        btnMontoPorPeriodo.setText("Monto Período");
        btnMontoPorPeriodo.addActionListener(this::btnMontoPorPeriodoActionPerformed);
        btnMontoPorPeriodo.setToolTipText("Monto Total Pagado por Período");
        reportesPanel.add(btnMontoPorPeriodo);
        btnVerificarSolicitudes.setText("Verif. Solicitudes");
        btnVerificarSolicitudes.addActionListener(this::btnVerificarSolicitudesActionPerformed);
        btnVerificarSolicitudes.setToolTipText("Verificar Solicitudes Procesadas");
        reportesPanel.add(btnVerificarSolicitudes);
        btnFechasBonoBeneficiario.setText("Fechas Beneficiario");
        btnFechasBonoBeneficiario.addActionListener(this::btnFechasBonoBeneficiarioActionPerformed);
        btnFechasBonoBeneficiario.setToolTipText("Fechas de Bonos por Beneficiario");
        reportesPanel.add(btnFechasBonoBeneficiario);
        btnMultiplesBonos.setText("Múltiples Bonos");
        btnMultiplesBonos.addActionListener(this::btnMultiplesBonosActionPerformed);
        btnMultiplesBonos.setToolTipText("Beneficiarios con Múltiples Bonos");
        reportesPanel.add(btnMultiplesBonos);
        btnTiempoTotalPago.setText("Tiempo Pago");
        btnTiempoTotalPago.addActionListener(this::btnTiempoTotalPagoActionPerformed);
        btnTiempoTotalPago.setToolTipText("Tiempo Total de Pago al Beneficiario");
        reportesPanel.add(btnTiempoTotalPago);
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
    }

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

    private void btnEditarBonoActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBonos[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay bonos registrados para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedRow = tablaBonos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un bono para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        BonoDignidad bono = bonos[selectedRow];
        JTextField tipoField = new JTextField(bono.getNombretipo(), 20);
        JTextField fechaIniField = new JTextField(bono.getFechaIni(), 10);
        JTextField fechaFinField = new JTextField(bono.getFechaFin(), 10);
        JTextField montoField = new JTextField(String.valueOf(bono.getMonto()), 10);
        JComboBox<String> beneficiarioCombo = new JComboBox<>();
        beneficiarioCombo.addItem("Ninguno");
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            beneficiarioCombo.addItem(beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")");
        }
        if (bono.getNumSolicitudes() > 0 && bono.getRegisSoli()[0] != null && bono.getRegisSoli()[0].getBeneficiario() != null) {
            Beneficiario currentBeneficiario = bono.getRegisSoli()[0].getBeneficiario();
            for (int i = 0; i < numBeneficiarios[0]; i++) {
                if (beneficiarios[i] == currentBeneficiario) {
                    beneficiarioCombo.setSelectedIndex(i + 1);
                    break;
                }
            }
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
        int result = JOptionPane.showConfirmDialog(this, panel, "Editar Bono", JOptionPane.OK_CANCEL_OPTION);
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
                bono = new BonoDignidad(tipo, fechaIni, fechaFin, monto);
                int beneficiarioIndex = beneficiarioCombo.getSelectedIndex() - 1;
                if (beneficiarioIndex >= 0) {
                    SolicitudBono solicitud = new SolicitudBono(fechaIni, "Aprobada", beneficiarios[beneficiarioIndex]);
                    bono.agregarSolicitud(solicitud);
                }
                bonos[selectedRow] = bono;
                cargarDatosEnTablas();
                JOptionPane.showMessageDialog(this, "Bono editado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    private void btnRegistrarPagoActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBonos[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay bonos registrados para registrar un pago.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
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
        SolicitudBono solicitudSeleccionada = null;
        if (bonoSeleccionado.getNumSolicitudes() > 0) {
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
                solicitudSeleccionada = crearNuevaSolicitud(bonoSeleccionado);
                if (solicitudSeleccionada == null) {
                    return;
                }
            } else {
                solicitudSeleccionada = bonoSeleccionado.getRegisSoli()[solicitudIndex];
            }
        } else {
            solicitudSeleccionada = crearNuevaSolicitud(bonoSeleccionado);
            if (solicitudSeleccionada == null) {
                return;
            }
        }
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
                PagoBono pago = new PagoBono(fechaPago, monto, solicitudSeleccionada);
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

    private void btnEditarBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBeneficiarios[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedRow = tablaBeneficiarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un beneficiario para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Beneficiario beneficiario = beneficiarios[selectedRow];
        JTextField nombreField = new JTextField(beneficiario.getNombre(), 20);
        JTextField ciField = new JTextField(beneficiario.getCi(), 10);
        JTextField edadField = new JTextField(String.valueOf(beneficiario.getEdad()), 5);
        JTextField direccionField = new JTextField(beneficiario.getDireccion(), 20);
        JTextField fechaNacField = new JTextField(beneficiario.getFecha_nacimiento(), 10);
        JTextField tipoDiscField = new JTextField(beneficiario.getTipodiscapacidad(), 15);
        JTextField gradoDiscField = new JTextField(beneficiario.getGradodiscapacidad(), 15);
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
        int result = JOptionPane.showConfirmDialog(this, panel, "Editar Beneficiario", JOptionPane.OK_CANCEL_OPTION);
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
                beneficiarios[selectedRow] = new Beneficiario(nombre, ci, edad, direccion, fechaNac, tipoDisc, gradoDisc);
                cargarDatosEnTablas();
                JOptionPane.showMessageDialog(this, "Beneficiario editado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    private void btnEditarAdministradorActionPerformed(java.awt.event.ActionEvent evt) {
        if (numAdministradores[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay administradores registrados para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedRow = tablaAdministradores.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un administrador para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Administrador administrador = administradores[selectedRow];
        JTextField idField = new JTextField(administrador.getId(), 10);
        JTextField cargoField = new JTextField(administrador.getCargo(), 15);
        JTextField contactoField = new JTextField(administrador.getContacto(), 20);
        JTextField fechaCreacionField = new JTextField(administrador.getFecCreCuenta(), 10);
        JTextField nombreField = new JTextField(administrador.getNombre(), 20);
        JTextField ciField = new JTextField(administrador.getCi(), 10);
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
        int result = JOptionPane.showConfirmDialog(this, panel, "Editar Administrador", JOptionPane.OK_CANCEL_OPTION);
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
            administradores[selectedRow] = new Administrador(id, cargo, contacto, fechaCreacion, nombre, ci);
            cargarDatosEnTablas();
            JOptionPane.showMessageDialog(this, "Administrador editado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
        mostrarReporteEnDialogo(reporte.toString(), "Reporte General");
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
        mostrarReporteEnDialogo(reporte.toString(), "Beneficiarios por Discapacidad");
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

            StringBuilder reporte = new StringBuilder();
            reporte.append("REPORTE DETALLADO DE PAGOS POR PERÍODO\n\n");
            reporte.append(String.format("Período: %s - %s\n\n", fechaInicioStr, fechaFinStr));
            
            double montoTotal = 0.0;
            int totalPagos = 0;
            
            reporte.append("Detalle de pagos:\n");
            reporte.append("--------------------------------------------------\n");
            
            for (int i = 0; i < numBonos[0]; i++) {
                double montoBono = 0.0;
                int pagosBono = 0;
                
                for (int j = 0; j < bonos[i].getNumPagos(); j++) {
                    String fechaPago = bonos[i].getRegisPago()[j].getFechaPago();
                    if (!validarFormatoFecha(fechaPago)) continue;
                    
                    if (fechaEsDentroDelRango(fechaPago, fechaInicioStr, fechaFinStr)) {
                        double montoPago = bonos[i].getRegisPago()[j].getMonto();
                        Beneficiario beneficiario = bonos[i].getRegisPago()[j].getSolicitud().getBeneficiario();
                        
                        reporte.append(String.format("Bono: %s\n", bonos[i].getNombretipo()));
                        reporte.append(String.format("Fecha Pago: %s\n", fechaPago));
                        reporte.append(String.format("Monto: $%.2f\n", montoPago));
                        if (beneficiario != null) {
                            reporte.append(String.format("Beneficiario: %s (CI: %s)\n", 
                                beneficiario.getNombre(), beneficiario.getCi()));
                        }
                        reporte.append("--------------------------------------------------\n");
                        
                        montoBono += montoPago;
                        montoTotal += montoPago;
                        pagosBono++;
                        totalPagos++;
                    }
                }
                
                if (pagosBono > 0) {
                    reporte.append(String.format("Total para bono '%s': $%.2f (%d pagos)\n\n", 
                        bonos[i].getNombretipo(), montoBono, pagosBono));
                }
            }

            reporte.append("\nRESUMEN FINAL\n");
            reporte.append("--------------------------------------------------\n");
            reporte.append(String.format("Total pagos realizados: %d\n", totalPagos));
            reporte.append(String.format("Monto total pagado: $%.2f\n", montoTotal));
            
            if (totalPagos == 0) {
                reporte.append("\nNo se encontraron pagos en el período especificado.\n");
            }

            mostrarReporteEnDialogo(reporte.toString(), "Reporte Detallado de Pagos");
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
        mostrarReporteEnDialogo(reporte.toString(), "Verificación de Solicitudes");
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
        int result = JOptionPane.showConfirmDialog(this, panel, "Fechas de Bonos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int beneficiarioIndex = beneficiarioCombo.getSelectedIndex();
            Beneficiario beneficiario = beneficiarios[beneficiarioIndex];
            StringBuilder reporte = new StringBuilder();
            reporte.append("Fechas de Bonos para " + beneficiario.getNombre() + "\n\n");
            boolean tieneBono = false;
            for (int i = 0; i < numBonos[0]; i++) {
                for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                    if (bonos[i].getRegisSoli()[j].getBeneficiario() == beneficiario) {
                        reporte.append(String.format("Bono: %s\n", bonos[i].getNombretipo()));
                        reporte.append(String.format("Inicio: %s\n", bonos[i].getFechaIni()));
                        reporte.append(String.format("Fin: %s\n", bonos[i].getFechaFin()));
                        reporte.append("\n");
                        tieneBono = true;
                    }
                }
            }
            if (!tieneBono) {
                reporte.append("No tiene bonos asignados.");
            }
            mostrarReporteEnDialogo(reporte.toString(), "Fechas de Bonos");
        }
    }

    private void btnMultiplesBonosActionPerformed(java.awt.event.ActionEvent evt) {
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

        int result = JOptionPane.showConfirmDialog(this, panel, "Múltiples Bonos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int beneficiarioIndex = beneficiarioCombo.getSelectedIndex();
            Beneficiario beneficiario = beneficiarios[beneficiarioIndex];

            StringBuilder reporte = new StringBuilder();
            reporte.append("REPORTE DETALLADO DE BONOS PARA BENEFICIARIO\n\n");
            reporte.append(String.format("Beneficiario: %s\n", beneficiario.getNombre()));
            reporte.append(String.format("CI: %s\n", beneficiario.getCi()));
            reporte.append(String.format("Edad: %d\n", beneficiario.getEdad()));
            reporte.append(String.format("Tipo Discapacidad: %s\n", beneficiario.getTipodiscapacidad()));
            reporte.append(String.format("Grado Discapacidad: %s\n\n", beneficiario.getGradodiscapacidad()));
            
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
                            reporte.append(String.format("Bono: %s\n", bonos[i].getNombretipo()));
                            reporte.append(String.format("Monto Base: $%.2f\n", bonos[i].getMonto()));
                            reporte.append(String.format("Período: %s - %s\n", 
                                bonos[i].getFechaIni(), bonos[i].getFechaFin()));
                            reporte.append("Solicitudes:\n");
                            tieneBono = true;
                            totalBonos++;
                        }
                        
                        SolicitudBono solicitud = bonos[i].getRegisSoli()[j];
                        reporte.append(String.format("  - Fecha: %s, Estado: %s, Pagada: %s\n", 
                            solicitud.getFechaSolicitud(), 
                            solicitud.getEstado(),
                            solicitud.isPagada() ? "Sí" : "No"));
                        
                        solicitudesParaEsteBono++;
                    }
                }

                if (tieneBono) {
                    reporte.append("Pagos:\n");
                    for (int j = 0; j < bonos[i].getNumPagos(); j++) {
                        PagoBono pago = bonos[i].getRegisPago()[j];
                        if (pago.getSolicitud() != null && pago.getSolicitud().getBeneficiario() == beneficiario) {
                            reporte.append(String.format("  - Fecha: %s, Monto Pagado: $%.2f\n", 
                                pago.getFechaPago(), pago.getMonto()));
                            montoPagado += pago.getMonto();
                            pagosRealizados++;
                        }
                    }
                    if (pagosRealizados == 0) {
                        reporte.append("  No hay pagos registrados.\n");
                    }
                    montoTotal += montoPagado;
                    reporte.append(String.format("Total solicitudes: %d\n", solicitudesParaEsteBono));
                    reporte.append(String.format("Total pagado: $%.2f (%d pagos)\n", montoPagado, pagosRealizados));
                    reporte.append("--------------------------------------------------\n");
                    tieneBonos = true;
                }
            }

            if (tieneBonos) {
                reporte.append("\nRESUMEN FINAL\n");
                reporte.append("--------------------------------------------------\n");
                reporte.append(String.format("Total bonos asignados: %d\n", totalBonos));
                reporte.append(String.format("Monto total pagado: $%.2f\n", montoTotal));
            } else {
                reporte.append("\nEl beneficiario no tiene bonos asignados.\n");
            }

            mostrarReporteEnDialogo(reporte.toString(), "Reporte de Bonos por Beneficiario");
        }
    }

    private void btnTiempoTotalPagoActionPerformed(java.awt.event.ActionEvent evt) {
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

        int result = JOptionPane.showConfirmDialog(this, panel, "Tiempo Total de Pago", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int beneficiarioIndex = beneficiarioCombo.getSelectedIndex();
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
            reporte.append(String.format("Beneficiario: %s (CI: %s)\n\n", beneficiario.getNombre(), beneficiario.getCi()));

            if (paymentCount == 0) {
                reporte.append("No se encontraron pagos para este beneficiario.\n");
            } else {
                long daysBetween = calcularDiasEntreFechas(earliestDate, latestDate);
                reporte.append(String.format("Primer pago: %s\n", earliestDate));
                reporte.append(String.format("Último pago: %s\n", latestDate));
                reporte.append(String.format("Total de pagos: %d\n", paymentCount));
                reporte.append(String.format("Tiempo total entre pagos: %d días\n", daysBetween));
            }

            mostrarReporteEnDialogo(reporte.toString(), "Tiempo Total de Pago");
        }
    }

    private long calcularDiasEntreFechas(String fechaInicio, String fechaFin) {
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

    private void mostrarReporteEnDialogo(String contenido, String titulo) {
        JTextArea textArea = new JTextArea(contenido);
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
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
        long valorFecha = convertirFechaAValor(fecha);
        long valorInicio = convertirFechaAValor(fechaInicio);
        long valorFin = convertirFechaAValor(fechaFin);
        return valorFecha >= valorInicio && valorFecha <= valorFin;
    }

    private long convertirFechaAValor(String fecha) {
        try {
            String[] partes = fecha.split("/");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anio = Integer.parseInt(partes[2]);
            return anio * 10000L + mes * 100L + dia;
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BonoDignidad[] bonos = new BonoDignidad[MAX_REGISTROS];
            int[] numBonos = new int[1];
            Beneficiario[] beneficiarios = new Beneficiario[MAX_REGISTROS];
            int[] numBeneficiarios = new int[1];
            Administrador[] administradores = new Administrador[MAX_REGISTROS];
            int[] numAdministradores = new int[1];
            BonoDignidadGUI gui = new BonoDignidadGUI(bonos, numBonos, beneficiarios, numBeneficiarios, administradores, numAdministradores);
            gui.setVisible(true);
        });
    }
}