import javax.swing.table.DefaultTableModel;

public class CajaRegistradora {

    // Arreglos paralelos de denominaciones y sus existencias
    private int[] denominaciones = {100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50};
    private int[] existencias = new int[11]; // Por defecto inician en 0

    public int[] getDenominaciones() {
        return denominaciones;
    }

    public void actualizarExistencia(int indice, int cantidad) {
        if (indice >= 0 && indice < existencias.length) {
            existencias[indice] = cantidad;
        }
    }

    public DefaultTableModel calcularDevuelta(int valorADevolver) {
        String[] encabezados = {"Cantidad", "Presentacion", "Denominación"};
        
        // Contamos cuántas filas válidas se necesitarán para dimensionar la matriz
        int lineasResultado = 0;
        int copiaValor = valorADevolver;
        int[] existenciasTemporales = existencias.clone();
        int[] cantDevuelta = new int[denominaciones.length];

        for (int i = 0; i < denominaciones.length; i++) {
            int denominacion = denominaciones[i];
            int necesarias = copiaValor / denominacion;
            int aUsar = Math.min(necesarias, existenciasTemporales[i]);

            if (aUsar > 0) {
                cantDevuelta[i] = aUsar;
                copiaValor -= aUsar * denominacion;
                lineasResultado++;
            }
        }

        // Creamos la matriz de salida exacta para la tabla
        String[][] datos = new String[lineasResultado][3];
        int filaActual = 0;

        for (int i = 0; i < denominaciones.length; i++) {
            if (cantDevuelta[i] > 0) {
                datos[filaActual][0] = String.valueOf(cantDevuelta[i]);
                // Según la rúbrica: 2000 o más es billete, de 1000 hacia abajo es moneda/billete según el gráfico
                datos[filaActual][1] = (denominaciones[i] >= 2000) ? "billete" : "moneda";
                datos[filaActual][2] = String.valueOf(denominaciones[i]);
                filaActual++;
            }
        }

        return new DefaultTableModel(datos, encabezados);
    }
}