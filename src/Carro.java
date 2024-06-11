public class Carro {
    private String placa;
    private int horaEntrada;

    public Carro(String placa, int horaEntrada) {
        this.placa = placa;
        this.horaEntrada = horaEntrada;
    }

    public String darPlaca() {
        return placa;
    }

    public int darTiempoEnParqueadero(int horaActual) {
        return horaActual - horaEntrada;
    }
}
