public class Cine {

    private final int fila;
    private final int columna;
    private Character[][] sala;
    private int precio;
    private int ticket;
    private int ingresoActual;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Cine(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.sala = new Character[fila + 1][columna + 1];
        this.ticket = 0;
        this.precio = 0;
    }

    public void llenarSalaPorDefecto() {
        for (int i = 0; i < sala.length; i++) {
            for (int j = 0; j < sala[0].length; j++) {
                if (i == 0 && j == 0) sala[i][j] = ' ';
                else if (i == 0) sala[i][j] = Character.forDigit(j, 10);
                else if (j == 0) sala[i][j] = Character.forDigit(i, 10);
                else sala[i][j] = 'D';
            }
        }
    }

    public void imprimirSala() {
        System.out.println("\nSala de cine: \n");
        for (Character[] asientos : sala) {
            System.out.print("Fila ");
            for (int j = 0; j < sala[0].length; j++) {
                if (asientos[j] == 'D') {
                    System.out.print(ANSI_GREEN + asientos[j] + " " + ANSI_RESET);
                } else if (asientos[j] == 'O'){
                    System.out.print(ANSI_RED + asientos[j] + " " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_BLACK + asientos[j] + " " + ANSI_RESET);
                }
            }
            System.out.println("\n");
        }
    }

    public boolean verificarAsiento(int comprarFila, int comprarColumna) {
        boolean verificar = comprarFila > fila ||
                            comprarColumna > columna ||
                            comprarFila == 0 || comprarColumna == 0;
        if (verificar) {
            System.out.println("Entrada incorrecta");
            return false;
        } else if (sala[comprarFila][comprarColumna].equals("O")) {
            System.out.println("Este asiento está ocupado");
            return false;
        }
        return true;
    }

    public void comprarBoleto(int comprarFila, int comprarColumna) {
        this.precio = calcularPrecio(comprarFila);
        ticket += 1;
        ingresoActual += precio;
        sala[comprarFila][comprarColumna] = 'O';
        System.out.println("\nPrecio del boleto: $ " + precio);
    }

    public int calcularPrecio(int comprarFila) {
        return (fila * columna <= 50) || (fila / 2 >= comprarFila) ? 10 : 8;
    }

    public int totalIngresosEsperado() {
        int totalIngresos;
        if (fila * columna <= 50) {
            totalIngresos = 10 * fila * columna;
        } else {
            int precioUno = 10 * (fila / 2 * columna);
            int precioDos = 8 * (fila - fila / 2) * columna;
            totalIngresos = precioUno + precioDos;
        }
        return totalIngresos;
    }

    public float porcentajeVentas() {
        int cantidadAsientos = fila * columna;
        float porcentaje = (float) ticket / cantidadAsientos;
        return porcentaje * 100;
    }

    public void estadisticas() {
        System.out.println("\nNúmero de boletos comprados: " + ticket + "\n" +
                    String.format("Porcentaje de asientos ocupados: %.2f%%", porcentajeVentas()) + "\n" +
                    "Ingreso Actual : $ " + ingresoActual + "\n" +
                    "Total ingresos esperados : $ " + totalIngresosEsperado());
    }

}
