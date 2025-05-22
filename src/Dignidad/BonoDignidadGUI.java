package Dignidad;

import javax.swing.*;
import java.awt.*;

public class BonoDignidadGUI extends javax.swing.JFrame {
    private static final int MAX_REGISTROS = 100;
    private BonoDignidad[] bonos;
    private int[] numBonos;
    private Beneficiario[] beneficiarios;
    private int[] numBeneficiarios;
    private Administrador[] administradores;
    private int[] numAdministradores;

    // Declare labels as class fields
    private JLabel lblTitulo;
    private JLabel lblBonos;
    private JLabel lblBeneficiarios;
    private JLabel lblAdministradores;

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
        // Fondo gradiente
        getContentPane().setBackground(new Color(240, 248, 255));
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(135, 206, 235), 0, getHeight(), new Color(240, 248, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        panel.add(getContentPane(), BorderLayout.CENTER);
        setContentPane(panel);

        // Ajustar fuentes y colores
        lblTitulo.setForeground(new Color(0, 102, 204));
        lblBonos.setForeground(new Color(0, 102, 204));
        lblBeneficiarios.setForeground(new Color(0, 102, 204));
        lblAdministradores.setForeground(new Color(0, 102, 204));
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
        lblTitulo = new JLabel("Sistema Bono Dignidad");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblBonos = new JLabel("Bonos");
        lblBonos.setFont(new Font("Arial", Font.BOLD, 18));

        lblBeneficiarios = new JLabel("Beneficiarios");
        lblBeneficiarios.setFont(new Font("Arial", Font.BOLD, 18));

        lblAdministradores = new JLabel("Administradores");
        lblAdministradores.setFont(new Font("Arial", Font.BOLD, 18));

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBonos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaBeneficiarios = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAdministradores = new javax.swing.JTable();
        btnAgregarBono = new javax.swing.JButton("Agregar Bono");
        btnAgregarBeneficiario = new javax.swing.JButton("Agregar Beneficiario");
        btnAgregarAdministrador = new javax.swing.JButton("Agregar Administrador");
        btnBorrarBono = new javax.swing.JButton("Borrar Bono");
        btnBorrarBeneficiario = new javax.swing.JButton("Borrar Beneficiario");
        btnBorrarAdministrador = new javax.swing.JButton("Borrar Administrador");
        btnGenerarReporte = new javax.swing.JButton("Generar Reporte");
        btnBeneficiosPorDiscapacidad = new javax.swing.JButton("Beneficios por Discap.");
        btnMontoPorPeriodo = new javax.swing.JButton("Monto por Periodo");
        btnVerificarSolicitudes = new javax.swing.JButton("Verificar Solicitudes");
        btnFechasBonoBeneficiario = new javax.swing.JButton("Fechas de Bonos");
        btnMultiplesBonos = new javax.swing.JButton("Múltiples Bonos");
        btnConsola = new javax.swing.JButton("Ejecutar en Consola");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1100, 750));

        tablaBonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"Tipo", "Fecha Inicio", "Fecha Fin", "Monto"}
        ));
        tablaBonos.setBackground(Color.WHITE);
        tablaBonos.setGridColor(new Color(0, 102, 204));
        tablaBonos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaBonos.setRowHeight(25);
        jScrollPane1.setViewportView(tablaBonos);
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));

        tablaBeneficiarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"Nombre", "CI", "Edad", "Dirección", "Fecha Nacimiento", "Tipo Discapacidad", "Grado Discapacidad"}
        ));
        tablaBeneficiarios.setBackground(Color.WHITE);
        tablaBeneficiarios.setGridColor(new Color(0, 102, 204));
        tablaBeneficiarios.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaBeneficiarios.setRowHeight(25);
        jScrollPane2.setViewportView(tablaBeneficiarios);
        jScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));

        tablaAdministradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Cargo", "Contacto", "Fecha Creación", "Nombre", "CI"}
        ));
        tablaAdministradores.setBackground(Color.WHITE);
        tablaAdministradores.setGridColor(new Color(0, 102, 204));
        tablaAdministradores.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaAdministradores.setRowHeight(25);
        jScrollPane3.setViewportView(tablaAdministradores);
        jScrollPane3.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));

        // Estilizar botones
        btnAgregarBono.setBackground(new Color(46, 139, 87)); // Verde oscuro
        btnAgregarBono.setForeground(Color.WHITE);
        btnAgregarBono.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregarBono.addActionListener(evt -> btnAgregarBonoActionPerformed(evt));

        btnAgregarBeneficiario.setBackground(new Color(46, 139, 87));
        btnAgregarBeneficiario.setForeground(Color.WHITE);
        btnAgregarBeneficiario.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregarBeneficiario.addActionListener(evt -> btnAgregarBeneficiarioActionPerformed(evt));

        btnAgregarAdministrador.setBackground(new Color(46, 139, 87));
        btnAgregarAdministrador.setForeground(Color.WHITE);
        btnAgregarAdministrador.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregarAdministrador.addActionListener(evt -> btnAgregarAdministradorActionPerformed(evt));

        btnBorrarBono.setBackground(new Color(255, 69, 0)); // Rojo
        btnBorrarBono.setForeground(Color.WHITE);
        btnBorrarBono.setFont(new Font("Arial", Font.BOLD, 14));
        btnBorrarBono.addActionListener(evt -> btnBorrarBonoActionPerformed(evt));

        btnBorrarBeneficiario.setBackground(new Color(255, 69, 0));
        btnBorrarBeneficiario.setForeground(Color.WHITE);
        btnBorrarBeneficiario.setFont(new Font("Arial", Font.BOLD, 14));
        btnBorrarBeneficiario.addActionListener(evt -> btnBorrarBeneficiarioActionPerformed(evt));

        btnBorrarAdministrador.setBackground(new Color(255, 69, 0));
        btnBorrarAdministrador.setForeground(Color.WHITE);
        btnBorrarAdministrador.setFont(new Font("Arial", Font.BOLD, 14));
        btnBorrarAdministrador.addActionListener(evt -> btnBorrarAdministradorActionPerformed(evt));

        btnGenerarReporte.setBackground(new Color(0, 102, 204)); // Azul
        btnGenerarReporte.setForeground(Color.WHITE);
        btnGenerarReporte.setFont(new Font("Arial", Font.BOLD, 14));
        btnGenerarReporte.addActionListener(evt -> btnGenerarReporteActionPerformed(evt));

        btnBeneficiosPorDiscapacidad.setBackground(new Color(0, 102, 204));
        btnBeneficiosPorDiscapacidad.setForeground(Color.WHITE);
        btnBeneficiosPorDiscapacidad.setFont(new Font("Arial", Font.BOLD, 14));
        btnBeneficiosPorDiscapacidad.addActionListener(evt -> btnBeneficiosPorDiscapacidadActionPerformed(evt));

        btnMontoPorPeriodo.setBackground(new Color(0, 102, 204));
        btnMontoPorPeriodo.setForeground(Color.WHITE);
        btnMontoPorPeriodo.setFont(new Font("Arial", Font.BOLD, 14));
        btnMontoPorPeriodo.addActionListener(evt -> btnMontoPorPeriodoActionPerformed(evt));

        btnVerificarSolicitudes.setBackground(new Color(0, 102, 204));
        btnVerificarSolicitudes.setForeground(Color.WHITE);
        btnVerificarSolicitudes.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerificarSolicitudes.addActionListener(evt -> btnVerificarSolicitudesActionPerformed(evt));

        btnFechasBonoBeneficiario.setBackground(new Color(0, 102, 204));
        btnFechasBonoBeneficiario.setForeground(Color.WHITE);
        btnFechasBonoBeneficiario.setFont(new Font("Arial", Font.BOLD, 14));
        btnFechasBonoBeneficiario.addActionListener(evt -> btnFechasBonoBeneficiarioActionPerformed(evt));

        btnMultiplesBonos.setBackground(new Color(0, 102, 204));
        btnMultiplesBonos.setForeground(Color.WHITE);
        btnMultiplesBonos.setFont(new Font("Arial", Font.BOLD, 14));
        btnMultiplesBonos.addActionListener(evt -> btnMultiplesBonosActionPerformed(evt));

        btnConsola.setBackground(new Color(0, 102, 204));
        btnConsola.setForeground(Color.WHITE);
        btnConsola.setFont(new Font("Arial", Font.BOLD, 14));
        btnConsola.addActionListener(evt -> ejecutarEnConsola());
        
        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBonos)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
                    .addComponent(lblBeneficiarios)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
                    .addComponent(lblAdministradores)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregarBono, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnAgregarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnAgregarAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnBorrarBono, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnBorrarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnBorrarAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnBeneficiosPorDiscapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnMontoPorPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnVerificarSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnFechasBonoBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnMultiplesBonos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(btnConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18)
                .addComponent(lblBonos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18)
                .addComponent(lblBeneficiarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18)
                .addComponent(lblAdministradores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarBono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrarBono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrarBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrarAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBeneficiosPorDiscapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMontoPorPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerificarSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFechasBonoBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMultiplesBonos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnAgregarBonoActionPerformed(java.awt.event.ActionEvent evt) {
        BonoDignidad bono = new BonoDignidad();
        String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo de bono:");
        if (tipo == null || tipo.trim().isEmpty()) return;
        String fechaIni = JOptionPane.showInputDialog(this, "Ingrese la fecha de inicio (dd/mm/yyyy):");
        if (fechaIni == null || fechaIni.trim().isEmpty()) return;
        String fechaFin = JOptionPane.showInputDialog(this, "Ingrese la fecha de fin (dd/mm/yyyy):");
        if (fechaFin == null || fechaFin.trim().isEmpty()) return;
        String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto:");
        if (montoStr == null || montoStr.trim().isEmpty()) return;

        try {
            double monto = Double.parseDouble(montoStr);
            bono.setNombretipo(tipo);
            bono.setFechaIni(fechaIni);
            bono.setFechaFin(fechaFin);
            bono.setMonto(monto);

            if (numBeneficiarios[0] > 0) {
                String[] opciones = new String[numBeneficiarios[0] + 1];
                opciones[0] = "No asociar";
                for (int i = 0; i < numBeneficiarios[0]; i++) {
                    opciones[i + 1] = beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")";
                }
                String seleccion = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione un beneficiario:",
                    "Asociar Beneficiario",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
                );
                if (seleccion != null && !seleccion.equals("No asociar")) {
                    int indice = 0;
                    for (int i = 0; i < numBeneficiarios[0]; i++) {
                        if (opciones[i + 1].equals(seleccion)) {
                            indice = i;
                            break;
                        }
                    }
                    SolicitudBono solicitud = new SolicitudBono(fechaIni, "Aprobada", beneficiarios[indice]);
                    bono.agregarSolicitud(solicitud);
                }
            }

            if (numBonos[0] < MAX_REGISTROS) {
                bonos[numBonos[0]] = bono;
                numBonos[0]++;
                cargarDatosEnTablas();
            } else {
                JOptionPane.showMessageDialog(this, "Límite de bonos alcanzado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Monto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnAgregarBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        Beneficiario beneficiario = new Beneficiario();
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String ci = JOptionPane.showInputDialog(this, "Ingrese la CI:");
        if (ci == null || ci.trim().isEmpty()) return;
        String edadStr = JOptionPane.showInputDialog(this, "Ingrese la edad:");
        if (edadStr == null || edadStr.trim().isEmpty()) return;
        String direccion = JOptionPane.showInputDialog(this, "Ingrese la dirección:");
        if (direccion == null || direccion.trim().isEmpty()) return;
        String fechaNacimiento = JOptionPane.showInputDialog(this, "Ingrese la fecha de nacimiento (dd/mm/yyyy):");
        if (fechaNacimiento == null || fechaNacimiento.trim().isEmpty()) return;
        String tipoDiscapacidad = JOptionPane.showInputDialog(this, "Ingrese el tipo de discapacidad:");
        if (tipoDiscapacidad == null || tipoDiscapacidad.trim().isEmpty()) return;
        String gradoDiscapacidad = JOptionPane.showInputDialog(this, "Ingrese el grado de discapacidad:");
        if (gradoDiscapacidad == null || gradoDiscapacidad.trim().isEmpty()) return;

        try {
            int edad = Integer.parseInt(edadStr);
            beneficiario.setNombre(nombre);
            beneficiario.setCi(ci);
            beneficiario.setEdad(edad);
            beneficiario.setDireccion(direccion);
            beneficiario.setFecha_nacimiento(fechaNacimiento);
            beneficiario.setTipodiscapacidad(tipoDiscapacidad);
            beneficiario.setGradodiscapacidad(gradoDiscapacidad);

            if (numBeneficiarios[0] < MAX_REGISTROS) {
                beneficiarios[numBeneficiarios[0]] = beneficiario;
                numBeneficiarios[0]++;
                cargarDatosEnTablas();
            } else {
                JOptionPane.showMessageDialog(this, "Límite de beneficiarios alcanzado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnAgregarAdministradorActionPerformed(java.awt.event.ActionEvent evt) {
        Administrador administrador = new Administrador();
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID:");
        if (id == null || id.trim().isEmpty()) return;
        String cargo = JOptionPane.showInputDialog(this, "Ingrese el cargo:");
        if (cargo == null || cargo.trim().isEmpty()) return;
        String contacto = JOptionPane.showInputDialog(this, "Ingrese el contacto:");
        if (contacto == null || contacto.trim().isEmpty()) return;
        String fecCreCuenta = JOptionPane.showInputDialog(this, "Ingrese la fecha de creación (dd/mm/yyyy):");
        if (fecCreCuenta == null || fecCreCuenta.trim().isEmpty()) return;
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String ci = JOptionPane.showInputDialog(this, "Ingrese la CI:");
        if (ci == null || ci.trim().isEmpty()) return;

        administrador.setId(id);
        administrador.setCargo(cargo);
        administrador.setContacto(contacto);
        administrador.setFecCreCuenta(fecCreCuenta);
        administrador.setNombre(nombre);
        administrador.setCi(ci);

        if (numAdministradores[0] < MAX_REGISTROS) {
            administradores[numAdministradores[0]] = administrador;
            numAdministradores[0]++;
            cargarDatosEnTablas();
        } else {
            JOptionPane.showMessageDialog(this, "Límite de administradores alcanzado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBorrarBonoActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tablaBonos.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < numBonos[0]) {
            for (int i = selectedRow; i < numBonos[0] - 1; i++) {
                bonos[i] = bonos[i + 1];
            }
            bonos[numBonos[0] - 1] = null;
            numBonos[0]--;
            cargarDatosEnTablas();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un bono para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBorrarBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tablaBeneficiarios.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < numBeneficiarios[0]) {
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
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un beneficiario para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBorrarAdministradorActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tablaAdministradores.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < numAdministradores[0]) {
            for (int i = selectedRow; i < numAdministradores[0] - 1; i++) {
                administradores[i] = administradores[i + 1];
            }
            administradores[numAdministradores[0] - 1] = null;
            numAdministradores[0]--;
            cargarDatosEnTablas();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un administrador para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {
        StringBuilder reporte = new StringBuilder();
        for (int i = 0; i < numBonos[0]; i++) {
            reporte.append("Bono: ").append(bonos[i].getNombretipo()).append(", Monto: ").append(bonos[i].getMonto()).append("\n");
        }
        JOptionPane.showMessageDialog(this, reporte.toString(), "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnBeneficiosPorDiscapacidadActionPerformed(java.awt.event.ActionEvent evt) {
        int[] conteo = new int[100]; // Suponemos un máximo de 100 tipos únicos
        String[] tipos = new String[100];
        int tipoCount = 0;

        for (int i = 0; i < numBonos[0]; i++) {
            for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                String tipoDiscapacidad = bonos[i].getRegisSoli()[j].getBeneficiario().getTipodiscapacidad();
                boolean found = false;
                for (int k = 0; k < tipoCount; k++) {
                    if (tipos[k] != null && tipos[k].equals(tipoDiscapacidad)) {
                        conteo[k]++;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    tipos[tipoCount] = tipoDiscapacidad;
                    conteo[tipoCount] = 1;
                    tipoCount++;
                }
            }
        }

        StringBuilder resultado = new StringBuilder("Beneficios por tipo de discapacidad:\n");
        for (int i = 0; i < tipoCount; i++) {
            if (tipos[i] != null) {
                resultado.append(tipos[i]).append(": ").append(conteo[i]).append(" bono(s)\n");
            }
        }
        JOptionPane.showMessageDialog(this, resultado.toString(), "Beneficios por Discapacidad", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnMontoPorPeriodoActionPerformed(java.awt.event.ActionEvent evt) {
        String fechaInicio = JOptionPane.showInputDialog(this, "Ingrese la fecha de inicio del periodo (dd/mm/yyyy):");
        if (fechaInicio == null || fechaInicio.trim().isEmpty()) return;
        String fechaFin = JOptionPane.showInputDialog(this, "Ingrese la fecha de fin del periodo (dd/mm/yyyy):");
        if (fechaFin == null || fechaFin.trim().isEmpty()) return;

        double montoTotal = 0.0;
        for (int i = 0; i < numBonos[0]; i++) {
            String bonoFechaIni = bonos[i].getFechaIni();
            String bonoFechaFin = bonos[i].getFechaFin();
            if (bonoFechaIni.compareTo(fechaInicio) >= 0 && bonoFechaFin.compareTo(fechaFin) <= 0) {
                montoTotal += bonos[i].getMonto();
            }
        }

        JOptionPane.showMessageDialog(this, "Monto total pagado entre " + fechaInicio + " y " + fechaFin + ": " + montoTotal, "Monto por Periodo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnVerificarSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {
        StringBuilder resultado = new StringBuilder("Estado de las solicitudes:\n");
        for (int i = 0; i < numBonos[0]; i++) {
            for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                SolicitudBono solicitud = bonos[i].getRegisSoli()[j];
                Beneficiario beneficiario = solicitud.getBeneficiario();
                String estado = solicitud.getEstado();
                String pagada = estado.equals("Aprobada") ? "Pagada" : "No pagada";
                resultado.append("Solicitud de ").append(beneficiario.getNombre())
                        .append(" para bono ").append(bonos[i].getNombretipo())
                        .append(": ").append(estado).append(" (").append(pagada).append(")\n");
            }
        }
        JOptionPane.showMessageDialog(this, resultado.toString(), "Verificar Solicitudes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnFechasBonoBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {
        if (numBeneficiarios[0] == 0) {
            JOptionPane.showMessageDialog(this, "No hay beneficiarios registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] opciones = new String[numBeneficiarios[0]];
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            opciones[i] = beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")";
        }
        String seleccion = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione un beneficiario:",
            "Fechas de Bonos",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        if (seleccion == null) return;

        int indice = 0;
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            if (opciones[i].equals(seleccion)) {
                indice = i;
                break;
            }
        }

        Beneficiario beneficiario = beneficiarios[indice];
        StringBuilder resultado = new StringBuilder("Bonos de " + beneficiario.getNombre() + ":\n");
        for (int i = 0; i < numBonos[0]; i++) {
            for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                if (bonos[i].getRegisSoli()[j].getBeneficiario() == beneficiario) {
                    resultado.append("Bono: ").append(bonos[i].getNombretipo())
                            .append(", Inicio: ").append(bonos[i].getFechaIni())
                            .append(", Fin: ").append(bonos[i].getFechaFin()).append("\n");
                }
            }
        }
        JOptionPane.showMessageDialog(this, resultado.toString(), "Fechas de Bonos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnMultiplesBonosActionPerformed(java.awt.event.ActionEvent evt) {
        StringBuilder resultado = new StringBuilder("Beneficiarios con múltiples bonos:\n");
        boolean found = false;
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            int conteo = 0;
            for (int j = 0; j < numBonos[0]; j++) {
                for (int k = 0; k < bonos[j].getNumSolicitudes(); k++) {
                    if (bonos[j].getRegisSoli()[k].getBeneficiario() == beneficiarios[i]) {
                        conteo++;
                    }
                }
            }
            if (conteo > 1) {
                resultado.append(beneficiarios[i].getNombre()).append(": ").append(conteo).append(" bonos\n");
                found = true;
            }
        }
        if (!found) {
            resultado.append("No hay beneficiarios con múltiples bonos.");
        }
        JOptionPane.showMessageDialog(this, resultado.toString(), "Múltiples Bonos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void ejecutarEnConsola() {
        System.out.println("=== Sistema Bono Dignidad - Consola ===");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Cantidad de beneficios por tipo de discapacidad");
        System.out.println("2. Monto total pagado por periodo");
        System.out.println("3. Verificar solicitudes procesadas");
        System.out.println("4. Mostrar fechas de bonos por beneficiario");
        System.out.println("5. Mostrar beneficiarios con múltiples bonos");
        System.out.println("0. Salir");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                ejecutarBeneficiosPorDiscapacidadConsola();
                break;
            case 2:
                ejecutarMontoPorPeriodoConsola();
                break;
            case 3:
                ejecutarVerificarSolicitudesConsola();
                break;
            case 4:
                ejecutarFechasBonoBeneficiarioConsola();
                break;
            case 5:
                ejecutarMultiplesBonosConsola();
                break;
            case 0:
                System.out.println("Saliendo...");
                return;
            default:
                System.out.println("Opción inválida.");
        }
        ejecutarEnConsola(); // Volver al menú
        scanner.close();
    }

    private void ejecutarBeneficiosPorDiscapacidadConsola() {
        int[] conteo = new int[100];
        String[] tipos = new String[100];
        int tipoCount = 0;

        for (int i = 0; i < numBonos[0]; i++) {
            for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                String tipoDiscapacidad = bonos[i].getRegisSoli()[j].getBeneficiario().getTipodiscapacidad();
                boolean found = false;
                for (int k = 0; k < tipoCount; k++) {
                    if (tipos[k] != null && tipos[k].equals(tipoDiscapacidad)) {
                        conteo[k]++;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    tipos[tipoCount] = tipoDiscapacidad;
                    conteo[tipoCount] = 1;
                    tipoCount++;
                }
            }
        }

        System.out.println("Beneficios por tipo de discapacidad:");
        for (int i = 0; i < tipoCount; i++) {
            if (tipos[i] != null) {
                System.out.println(tipos[i] + ": " + conteo[i] + " bono(s)");
            }
        }
    }

    private void ejecutarMontoPorPeriodoConsola() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Ingrese la fecha de inicio del periodo (dd/mm/yyyy): ");
        String fechaInicio = scanner.next();
        System.out.print("Ingrese la fecha de fin del periodo (dd/mm/yyyy): ");
        String fechaFin = scanner.next();

        double montoTotal = 0.0;
        for (int i = 0; i < numBonos[0]; i++) {
            String bonoFechaIni = bonos[i].getFechaIni();
            String bonoFechaFin = bonos[i].getFechaFin();
            if (bonoFechaIni.compareTo(fechaInicio) >= 0 && bonoFechaFin.compareTo(fechaFin) <= 0) {
                montoTotal += bonos[i].getMonto();
            }
        }

        System.out.println("Monto total pagado entre " + fechaInicio + " y " + fechaFin + ": " + montoTotal);
    }

    private void ejecutarVerificarSolicitudesConsola() {
        System.out.println("Estado de las solicitudes:");
        for (int i = 0; i < numBonos[0]; i++) {
            for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                SolicitudBono solicitud = bonos[i].getRegisSoli()[j];
                Beneficiario beneficiario = solicitud.getBeneficiario();
                String estado = solicitud.getEstado();
                String pagada = estado.equals("Aprobada") ? "Pagada" : "No pagada";
                System.out.println("Solicitud de " + beneficiario.getNombre() + " para bono " + bonos[i].getNombretipo() + ": " + estado + " (" + pagada + ")");
            }
        }
    }

    private void ejecutarFechasBonoBeneficiarioConsola() {
        if (numBeneficiarios[0] == 0) {
            System.out.println("No hay beneficiarios registrados.");
            return;
        }
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String[] opciones = new String[numBeneficiarios[0]];
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            opciones[i] = beneficiarios[i].getNombre() + " (CI: " + beneficiarios[i].getCi() + ")";
        }
        System.out.println("Beneficiarios disponibles:");
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        System.out.print("Seleccione un beneficiario (1-" + numBeneficiarios[0] + "): ");
        int indice = scanner.nextInt() - 1;

        if (indice >= 0 && indice < numBeneficiarios[0]) {
            Beneficiario beneficiario = beneficiarios[indice];
            System.out.println("Bonos de " + beneficiario.getNombre() + ":");
            for (int i = 0; i < numBonos[0]; i++) {
                for (int j = 0; j < bonos[i].getNumSolicitudes(); j++) {
                    if (bonos[i].getRegisSoli()[j].getBeneficiario() == beneficiario) {
                        System.out.println("Bono: " + bonos[i].getNombretipo() + ", Inicio: " + bonos[i].getFechaIni() + ", Fin: " + bonos[i].getFechaFin());
                    }
                }
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void ejecutarMultiplesBonosConsola() {
        System.out.println("Beneficiarios con múltiples bonos:");
        boolean found = false;
        for (int i = 0; i < numBeneficiarios[0]; i++) {
            int conteo = 0;
            for (int j = 0; j < numBonos[0]; j++) {
                for (int k = 0; k < bonos[j].getNumSolicitudes(); k++) {
                    if (bonos[j].getRegisSoli()[k].getBeneficiario() == beneficiarios[i]) {
                        conteo++;
                    }
                }
            }
            if (conteo > 1) {
                System.out.println(beneficiarios[i].getNombre() + ": " + conteo + " bonos");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No hay beneficiarios con múltiples bonos.");
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnAgregarAdministrador;
    private javax.swing.JButton btnAgregarBeneficiario;
    private javax.swing.JButton btnAgregarBono;
    private javax.swing.JButton btnBorrarAdministrador;
    private javax.swing.JButton btnBorrarBeneficiario;
    private javax.swing.JButton btnBorrarBono;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnBeneficiosPorDiscapacidad;
    private javax.swing.JButton btnMontoPorPeriodo;
    private javax.swing.JButton btnVerificarSolicitudes;
    private javax.swing.JButton btnFechasBonoBeneficiario;
    private javax.swing.JButton btnMultiplesBonos;
    private javax.swing.JButton btnConsola;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaAdministradores;
    private javax.swing.JTable tablaBeneficiarios;
    private javax.swing.JTable tablaBonos;
    // End of variables declaration
}