import java.util.ArrayList;

public class Parqueadero {
    private ArrayList<Puesto> puestos;
    private int tarifa;
    private int horaActual;
    private int montoCaja;

    public static final int NO_HAY_PUESTO = -1;
    public static final int PARQUEADERO_CERRADO = -2;
    public static final int CARRO_YA_EXISTE = -3;
    public static final int CARRO_NO_EXISTE = -4;

    public Parqueadero(int numPuestos, int tarifaInicial) {
        puestos = new ArrayList<>();
        for (int i = 0; i < numPuestos; i++) {
            puestos.add(new Puesto());
        }
        tarifa = tarifaInicial;
        horaActual = 0;
        montoCaja = 0;
    }

    public int entrarCarro(String placa) {
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado() && puesto.darCarro().darPlaca().equals(placa)) {
                return CARRO_YA_EXISTE;
            }
        }

        for (Puesto puesto : puestos) {
            if (!puesto.estaOcupado()) {
                puesto.parquearCarro(new Carro(placa, horaActual));
                return puestos.indexOf(puesto);
            }
        }
        return NO_HAY_PUESTO;
    }

    public int sacarCarro(String placa) {
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado() && puesto.darCarro().darPlaca().equals(placa)) {
                int tiempoParqueado = puesto.darCarro().darTiempoEnParqueadero(horaActual);
                int pago = tiempoParqueado * tarifa;
                montoCaja += pago;
                puesto.sacarCarro();
                return pago;
            }
        }
        return CARRO_NO_EXISTE;
    }

    public int darMontoCaja() {
        return montoCaja;
    }

    public int calcularPuestosLibres() {
        int puestosLibres = 0;
        for (Puesto puesto : puestos) {
            if (!puesto.estaOcupado()) {
                puestosLibres++;
            }
        }
        return puestosLibres;
    }

    public void avanzarHora() {
        horaActual++;
    }

    public int darHoraActual() {
        return horaActual;
    }

    public void cambiarTarifa(int nuevaTarifa) {
        tarifa = nuevaTarifa;
    }

    public int darTarifa() {
        return tarifa;
    }

    public double darTiempoPromedio() {
        int totalTiempo = 0;
        int totalCarros = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                totalTiempo += puesto.darCarro().darTiempoEnParqueadero(horaActual);
                totalCarros++;
            }
        }
        return totalCarros == 0 ? 0 : (double) totalTiempo / totalCarros;
    }

    public Carro darCarroMasHoras() {
        Carro carroMasHoras = null;
        int maxHoras = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                Carro carro = puesto.darCarro();
                int horas = carro.darTiempoEnParqueadero(horaActual);
                if (horas > maxHoras) {
                    maxHoras = horas;
                    carroMasHoras = carro;
                }
            }
        }
        return carroMasHoras;
    }

    public boolean hayCarroMasDeOchoHoras() {
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                if (puesto.darCarro().darTiempoEnParqueadero(horaActual) > 8) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Carro> darCarrosMasDeTresHorasParqueados() {
        ArrayList<Carro> carros = new ArrayList<>();
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                if (puesto.darCarro().darTiempoEnParqueadero(horaActual) > 3) {
                    carros.add(puesto.darCarro());
                }
            }
        }
        return carros;
    }

    public boolean hayCarrosPlacaIgual() {
        ArrayList<String> placas = new ArrayList<>();
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                String placa = puesto.darCarro().darPlaca();
                if (placas.contains(placa)) {
                    return true;
                }
                placas.add(placa);
            }
        }
        return false;
    }

    public int contarCarrosQueComienzanConPlacaPB() {
        int count = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado() && puesto.darCarro().darPlaca().startsWith("PB")) {
                count++;
            }
        }
        return count;
    }

    public boolean hayCarroCon24Horas() {
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                if (puesto.darCarro().darTiempoEnParqueadero(horaActual) >= 24) {
                    return true;
                }
            }
        }
        return false;
    }

    public String metodo1() {
        int carrosPB = contarCarrosQueComienzanConPlacaPB();
        boolean carro24Horas = hayCarroCon24Horas();
        return "Cantidad de carros con placa PB: " + carrosPB + " – Hay carro parqueado por 24 o más horas: " + (carro24Horas ? "Sí" : "No") + ".";
    }

    public int desocuparParqueadero() {
        int cantidadCarros = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                puesto.sacarCarro();
                cantidadCarros++;
            }
        }
        return cantidadCarros;
    }

    public String metodo2() {
        int carrosSacados = desocuparParqueadero();
        return "Cantidad de carros sacados: " + carrosSacados + ".";
    }
}
