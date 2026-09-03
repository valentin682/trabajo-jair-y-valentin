import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmCajaRegistradora extends JFrame {

    private CajaRegistradora caja = new CajaRegistradora();

    private JComboBox cmbDenominacion;
    private JTextField txtExistencia;
    private JTextField txtValorDevolver;
    private JTable tblDevuelta;

    public FrmCajaRegistradora() {
        // Configuración de la ventana
        setSize(430, 420);
        setTitle("Caja registradora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Componentes de Denominación
        JLabel lblDenominacion = new JLabel("Denominación");
        lblDenominacion.setBounds(20, 20, 100, 25);
        add(lblDenominacion);

        cmbDenominacion = new JComboBox();
        cmbDenominacion.setBounds(140, 20, 150, 25);
        add(cmbDenominacion);

        // Cargar las denominaciones en el ComboBox
        String[] opciones = new String[caja.getDenominaciones().length];
        for (int i = 0; i < caja.getDenominaciones().length; i++) {
            opciones[i] = String.valueOf(caja.getDenominaciones()[i]);
        }
        cmbDenominacion.setModel(new DefaultComboBoxModel(opciones));

        // Componentes de Actualizar Existencia
        JButton btnActualizar = new JButton("Actualizar Existencia");
        btnActualizar.setBounds(20, 60, 160, 25);
        add(btnActualizar);

        txtExistencia = new JTextField();
        txtExistencia.setBounds(190, 60, 100, 25);
        add(txtExistencia);

        // Componentes de Devolución
        JLabel lblValorDevolver = new JLabel("Valor a Devolver");
        lblValorDevolver.setBounds(20, 110, 110, 25);
        add(lblValorDevolver);

        txtValorDevolver = new JTextField();
        txtValorDevolver.setBounds(140, 110, 120, 25);
        add(txtValorDevolver);

        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.setBounds(270, 110, 110, 25);
        add(btnDevolver);

        // Tabla de Salida
        tblDevuelta = new JTable();
        String[] encabezados = {"Cantidad", "Presentacion", "Denominación"};
        tblDevuelta.setModel(new DefaultTableModel(new String[0][3], encabezados));

        JScrollPane spTabla = new JScrollPane(tblDevuelta);
        spTabla.setBounds(20, 150, 360, 200);
        add(spTabla);

        // Eventos
        btnActualizar.addActionListener(evento -> {
            actualizarExistencia();
        });

        btnDevolver.addActionListener(evento -> {
            calcularDevuelta();
        });
    }

    private void actualizarExistencia() {
        try {
            int indice = cmbDenominacion.getSelectedIndex();
            int cantidad = Integer.parseInt(txtExistencia.getText());
            caja.actualizarExistencia(indice, cantidad);
            JOptionPane.showMessageDialog(this, "Existencia actualizada con éxito.");
            txtExistencia.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad entera válida.");
        }
    }

    private void calcularDevuelta() {
        try {
            int valor = Integer.parseInt(txtValorDevolver.getText());
            DefaultTableModel modelo = caja.calcularDevuelta(valor);
            tblDevuelta.setModel(modelo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor a devolver entero válido.");
        }
    }
}