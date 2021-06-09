import java.util.Scanner;

public class App {

    static Cafe cafe = new Cafe(300, 20, 60, 3, 500);
    final static Scanner scanner = new Scanner(System.in);

    static void elegirCafe(){
        String elegirCafe;
        TipoCafe tipoCafe;
        do {
            System.out.println("\nElegir tipo café : 1 - Espresso, 2 - Latte, 3 - Cappuccino, regresar - a menú principal");
            elegirCafe = scanner.next();

            if(elegirCafe.equals("regresar")){
                return;
            }

            tipoCafe = TipoCafe.encontrarPorId(Integer.parseInt(elegirCafe));

            switch (tipoCafe) {
                case ESPRESSO:
                    Cafe espresso = new Espresso();
                    cafe.prepararCafe(espresso);
                    break;
                case LATTE:
                    Cafe latte = new Latte();
                    cafe.prepararCafe(latte);
                    break;
                case CAPPUCCINO:
                    Cafe cappuccino = new Cappuccino();
                    cafe.prepararCafe(cappuccino);
                    break;
                default:
                    break;
            }

        } while (tipoCafe.name().equals("NOT_FOUND"));
    }

    static void llenarIngredientes() {
        System.out.println("Ingresar cantidad de agua");
        int agua = scanner.nextInt();
        System.out.println("Ingresar cantidad de leche");
        int leche = scanner.nextInt();
        System.out.println("Ingresar cantidad de granos de Café");
        int granosCafe = scanner.nextInt();
        System.out.println("Ingresar cantidad de vasos");
        int vasos = scanner.nextInt();
        cafe.llenarCafetera(agua, leche, granosCafe, vasos);
    }

    public static void main(String[] args) {
        System.out.println(cafe.toString());
        String opcion;
        do {
            System.out.println("\nEscribir acción a realizar : comprar, llenar, retirar, verificar, salir");
            opcion = scanner.next();
            switch (opcion) {
                case "comprar":
                    elegirCafe();
                    break;
                case "llenar":
                    llenarIngredientes();
                    break;
                case "retirar":
                    cafe.retirarDinero();
                    break;
                case "verificar":
                    System.out.println(cafe.toString());
                    break;
                case "salir":
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!opcion.equals("salir"));
        System.out.println(cafe);

    }

}
