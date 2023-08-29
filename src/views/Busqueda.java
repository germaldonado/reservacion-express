package views;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.huespedesController;
import Controller.reservaController;
import modelo.Huespedes;
import modelo.Reserva;

import java.awt.Color;

import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloH;
    private JLabel labelAtras;
    private JLabel labelExit;
    private huespedesController huespedesController;
    private reservaController reservaController;

    int xMouse, yMouse;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() throws SQLException {
        // Inicializamos la variable reservaController
        this.reservaController = new reservaController();
        this.huespedesController = new huespedesController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(172, 106, 105));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(251, 227, 225));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);


        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");

        modelo.addRow(new Object[]{"ID", "FECHA-ENTRADA", "FECHA-SALIDA", "VALOR", "FORMA DE PAGO"});
        // Establecemos el background y el foreground de la primera fila
        tbReservas.setRowSelectionInterval(0, 0);
//        ((JComponent) tbReservas.getCellRenderer(0, 0)).setBackground(Color.YELLOW);
        ((JComponent) tbReservas.getCellRenderer(0, 0)).setForeground(Color.BLACK);

        cargarTablaReservas();


        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
        modeloH = (DefaultTableModel) tbHuespedes.getModel();
        modeloH.addColumn("Numero de Huesped");
        modeloH.addColumn("Nombre");
        modeloH.addColumn("Apellido");
        modeloH.addColumn("Fecha de Nacimiento");
        modeloH.addColumn("Nacionalidad");
        modeloH.addColumn("Telefono");
        modeloH.addColumn("Numero de Reserva");

        modeloH.addRow(new Object[]{"ID", "NOMBRE", "APELLIDO", "FECHA DE NACIMIENTO", "NACIONALIDAD", "TELEFONO",
                "ID RESERVA"});
        tbHuespedes.setRowSelectionInterval(0, 0);
//        ((JComponent) tbReservas.getCellRenderer(0, 0)).setBackground(Color.YELLOW);
        ((JComponent) tbHuespedes.getCellRenderer(0, 0)).setForeground(Color.BLACK);

        cargarTablaHuespedes();

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(172, 106, 105));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(172, 106, 105));
        separator_1_2.setBackground(new Color(172, 106, 105));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(172, 106, 105));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String busquedaIngresada = txtBuscar.getText();

                if (busquedaIngresada.trim().isEmpty()) {
                    limpiarTabla();
                    cargarTablaReservas();
                    try {
                        cargarTablaHuespedes();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (tbReservas.isVisible()) {
                    buscarTablaReservas(busquedaIngresada);
                } else if (tbHuespedes.isVisible()) {
                    buscarTablaHuespedes(busquedaIngresada);
                }
            }
        });


        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(172, 106, 105));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaReservas = tbReservas.getSelectedRow();
                int filaHuespedes = tbHuespedes.getSelectedRow();

                if (filaReservas >= 0) {
                    try {
                        modificarReserva();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    limpiarTabla();
                    cargarTablaReservas();
                    try {
                        cargarTablaHuespedes();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (filaHuespedes >= 0) {
                    try {
                        modificarHuesped();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    limpiarTabla();
                    try {
                        cargarTablaHuespedes();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    cargarTablaReservas();
                }
            }
        });


        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(172, 106, 105));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminar();
                limpiarTabla();
                try {
                    cargarTablaHuespedes();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                cargarTablaReservas();
            }

        });
    }

    /* ---------- RESERVAS ---------- */

    private void cargarTablaReservas() {
        List<Reserva> reservas = this.reservaController.getAll();

        reservas.forEach(reserva -> modelo.addRow(new Object[]{
                reserva.getId(),
                reserva.getFecha_entrada(),
                reserva.getFecha_salida(),
                reserva.getValor(),
                reserva.getForma_de_pago()
        }));
    }


    private void buscarTablaReservas(String numeroReserva) {

        limpiarTabla();

        // Validamos que el número de reserva sea un número entero
        try {
            Integer idReserva = Integer.parseInt(numeroReserva);

            // Buscamos la reserva en la base de datos
            Reserva reserva = reservaController.buscarPorId(idReserva);

            // Si la reserva no se encuentra, mostramos un mensaje
            if (reserva == null) {
                JOptionPane.showMessageDialog(this, "La reserva no se encuentra.");
                return;
            }

            // Añadimos la reserva a la tabla
            modelo.addRow(new Object[]{
                    reserva.getId(),
                    reserva.getFecha_entrada(),
                    reserva.getFecha_salida(),
                    reserva.getValor(),
                    reserva.getForma_de_pago()
            });
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El número de reserva debe ser un número entero.");
        }
    }


    private void modificarReserva() throws ParseException {

        // Inicializamos la variable reservaController
        this.reservaController = new reservaController();

        // Obtenemos el ID de la reserva seleccionada
        int idReserva = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());

        // Buscamos la reserva en la base de datos
        Reserva reserva = reservaController.buscarPorId(idReserva);

        if (reserva == null) {
            JOptionPane.showMessageDialog(this, "La reserva no se encuentra.");
            return;
        }

        // Obtenemos los nuevos datos de la reserva
        String fechaEntrada = modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString();
        String fechaSalida = modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
        String valor = modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString();
        String formaPago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();

        // Convertimos las fechas de tipo String a tipo java.sql.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fechaEntradaSqlDate = new java.sql.Date(sdf.parse(fechaEntrada).getTime());
        java.sql.Date fechaSalidaSqlDate = new java.sql.Date(sdf.parse(fechaSalida).getTime());

        // Validamos que el ID no se pueda modificar
        if (idReserva != Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString())) {
            JOptionPane.showMessageDialog(this, "El ID no se puede modificar.");
            return;
        }

        // Actualizamos los datos de la reserva
        reserva.setFecha_entrada(fechaEntradaSqlDate);
        reserva.setFecha_salida(fechaSalidaSqlDate);
        reserva.setValor(valor);
        reserva.setForma_de_pago(formaPago);

        // Llamamos al método modificar() del controlador
        reservaController.modificar(reserva);

        // Imprimimos los datos de la reserva
        System.out.println("ID: " + idReserva);
        System.out.println("Fecha de entrada: " + fechaEntrada);
        System.out.println("Fecha de salida: " + fechaSalida);
        System.out.println("Valor: " + valor);
        System.out.println("Forma de pago: " + formaPago);

        Exito exito = new Exito();
        exito.setVisible(true);

//        JOptionPane.showMessageDialog(this, "Reserva modificada con éxito!");
    }


    /* ---------- HUESPEDES ---------- */

    private void cargarTablaHuespedes() throws SQLException {

        List<Huespedes> huespedes = this.huespedesController.findAll();

        huespedes.forEach(huesped -> modeloH.addRow(new Object[]{
                huesped.getId(),
                huesped.getNombre(),
                huesped.getApellido(),
                huesped.getFecha_nacimiento(),
                huesped.getNacionalidad(),
                huesped.getTelefono(),
                huesped.getId_reserva()
        }));
    }

    private void buscarTablaHuespedes(String apellido) {

        limpiarTabla();

        try {

            List<Huespedes> huespedes = huespedesController.findByApellido(apellido);

            if (!huespedes.isEmpty()) {
                for (Huespedes huesped : huespedes) {
                    modeloH.addRow(new Object[]{
                            huesped.getId(),
                            huesped.getNombre(),
                            huesped.getApellido(),
                            huesped.getFecha_nacimiento(),
                            huesped.getNacionalidad(),
                            huesped.getTelefono(),
                            huesped.getId_reserva()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "El huesped no se encuentra.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar el huesped.");
        }
    }

    private void modificarHuesped() throws ParseException {

        // Inicializamos la variable reservaController
        this.huespedesController = new huespedesController();

        // Obtenemos el ID de la reserva seleccionada
        int idHuesped = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

        // Buscamos la reserva en la base de datos
        Huespedes huespedes = huespedesController.buscarPorId(idHuesped);

        if (huespedes == null) {
            JOptionPane.showMessageDialog(this, "El huesped no se encuentra.");
            return;
        }

        // Obtenemos los nuevos datos de la reserva
        String nombre = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
        String apellido = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
        String fechaNacimiento = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();

        // Convertimos las fechas de tipo String a tipo java.sql.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fechaNacimientoSqlDate = new java.sql.Date(sdf.parse(fechaNacimiento).getTime());

        String nacionalidad = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
        String telefono = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
        String idReserva = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString();

        // Validamos que el ID no se pueda modificar
        if (idHuesped != Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString())) {
            JOptionPane.showMessageDialog(this, "El ID no se puede modificar.");
            return;
        }

        // Actualizamos los datos de la reserva
        huespedes.setNombre(nombre);
        huespedes.setApellido(apellido);
        huespedes.setFecha_nacimiento(fechaNacimientoSqlDate);
        huespedes.setNacionalidad(nacionalidad);
        huespedes.setTelefono(telefono);
        huespedes.setId_reserva(Integer.valueOf(idReserva));

        // Llamamos al método modificar() del controlador
        huespedesController.modificarHuespedes(huespedes);

        // Imprimimos los datos de la reserva
        System.out.println("ID: " + idHuesped);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Fecha de nacimiento: " + fechaNacimiento);
        System.out.println("Nacionalidad: " + nacionalidad);
        System.out.println("Telefono: " + telefono);
        System.out.println("ID de reserva: " + idReserva);

        Exito exito = new Exito();
        exito.setVisible(true);
//        JOptionPane.showMessageDialog(this, "Huesped modificado con éxito!");
    }



    /* ---------- ELIMINAR ---------- */

    private boolean noTieneFilaElegidaHuespedes() {
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }

    private boolean noTieneFilaElegidaReservas() {
        return tbReservas.getSelectedColumnCount() == 0 || tbReservas.getSelectedRowCount() == 0;
    }


    private void eliminar() {

        reservaController reservaController = new reservaController();
        huespedesController huespedController = new huespedesController();

        if (noTieneFilaElegidaHuespedes() && noTieneFilaElegidaReservas()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }
        if (noTieneFilaElegidaHuespedes()) {
            Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                    .ifPresentOrElse(fila -> {
                        Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                        reservaController.eliminar(id);

//                        Exito.main(null);
                        Exito exito = new Exito();
                        exito.setVisible(true);
                    }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        }
        if (noTieneFilaElegidaReservas()) {
            Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                    .ifPresentOrElse(fila -> {
                        Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                        huespedController.eliminarHuesped(id);

//                        Exito.main(null);
                        Exito exito = new Exito();
                        exito.setVisible(true);
                    }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        }

    }

        /* ---------- LIMPIAR ---------- */

    private void limpiarTabla() {

        while (modeloH.getRowCount() != 1) {
            modeloH.removeRow(1);
        }
        while (modelo.getRowCount() != 1) {
            modelo.removeRow(1);
        }

    }


    //Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}

