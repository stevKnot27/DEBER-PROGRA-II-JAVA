import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero(40,25); // Ejemplo de inicialización con 10 puestos y tarifa de 100
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nAdministración de Parqueadero:");
            System.out.println("1. Ingresar un carro");
            System.out.println("2. Dar salida a un carro");
            System.out.println("3. Informar los ingresos del parqueadero");
            System.out.println("4. Consultar cantidad de puestos disponibles");
            System.out.println("5. Avanzar el reloj del parqueadero");
            System.out.println("6. Cambiar la tarifa del parqueadero");
            System.out.println("7. Consultar tiempo promedio de carros en el parqueadero");
            System.out.println("8. Consultar carro con más horas en el parqueadero");
            System.out.println("9. Consultar si hay carros con más de 8 horas parqueados");
            System.out.println("10. Consultar carros con más de 3 horas parqueados");
            System.out.println("11. Consultar si hay carros con la misma placa");
            System.out.println("12. Contar carros con placa que comienza con 'PB'");
            System.out.println("13. Consultar si hay carro con más de 24 horas parqueado");
            System.out.println("14. Desocupar parqueadero");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Ingrese la placa del carro: ");
                    String placa = scanner.nextLine();
                    int resultado = parqueadero.entrarCarro(placa);
                    if (resultado == Parqueadero.NO_HAY_PUESTO) {
                        System.out.println("El parqueadero está lleno.");
                    } else if (resultado == Parqueadero.CARRO_YA_EXISTE) {
                        System.out.println("El carro ya está en el parqueadero.");
                    } else {
                        System.out.println("Carro parqueado en el puesto: " + resultado);
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la placa del carro: ");
                    placa = scanner.nextLine();
                    resultado = parqueadero.sacarCarro(placa);
                    if (resultado == Parqueadero.CARRO_NO_EXISTE) {
                        System.out.println("El carro no está en el parqueadero.");
                    } else {
                        System.out.println("Monto a pagar: " + resultado);
                    }
                    break;

                case 3:
                    System.out.println("Monto en caja: " + parqueadero.darMontoCaja());
                    break;

                case 4:
                    System.out.println("La cantidad de puestos disponibles es: " + parqueadero.calcularPuestosLibres());
                    break;

                case 5:
                    parqueadero.avanzarHora();
                    System.out.println("La hora actual es: " + parqueadero.darHoraActual());
                    break;

                case 6:
                    System.out.print("Ingrese la nueva tarifa del parqueadero: ");
                    int tarifa = scanner.nextInt();
                    parqueadero.cambiarTarifa(tarifa);
                    System.out.println("La nueva tarifa del parqueadero es: " + parqueadero.darTarifa());
                    break;

                case 7:
                    System.out.println("El tiempo promedio de los carros en el parqueadero es: " + parqueadero.darTiempoPromedio() + " horas.");
                    break;

                case 8:
                    Carro carroMasHoras = parqueadero.darCarroMasHoras();
                    if (carroMasHoras != null) {
                        System.out.println("El carro con más horas es: " + carroMasHoras.darPlaca() + " con " + carroMasHoras.darTiempoEnParqueadero(parqueadero.darHoraActual()) + " horas.");
                    } else {
                        System.out.println("No hay carros en el parqueadero.");
                    }
                    break;

                case 9:
                    System.out.println("¿Hay carros con más de 8 horas parqueados? " + (parqueadero.hayCarroMasDeOchoHoras() ? "Sí" : "No"));
                    break;

                case 10:
                    ArrayList<Carro> carrosMasDeTresHoras = parqueadero.darCarrosMasDeTresHorasParqueados();
                    if (carrosMasDeTresHoras.size() > 0) {
                        System.out.println("Carros con más de 3 horas parqueados:");
                        for (Carro c : carrosMasDeTresHoras) {
                            System.out.println(c.darPlaca());
                        }
                    } else {
                        System.out.println("No hay carros con más de 3 horas parqueados.");
                    }
                    break;

                case 11:
                    System.out.println("¿Hay carros con la misma placa? " + (parqueadero.hayCarrosPlacaIgual() ? "Sí" : "No"));
                    break;

                case 12:
                    System.out.println("Cantidad de carros con placa que comienza con 'PB': " + parqueadero.contarCarrosQueComienzanConPlacaPB());
                    break;

                case 13:
                    System.out.println("¿Hay carro con más de 24 horas parqueado? " + (parqueadero.hayCarroCon24Horas() ? "Sí" : "No"));
                    break;

                case 14:
                    System.out.println(parqueadero.metodo2());
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (option != 0);

        scanner.close();

    }
}

