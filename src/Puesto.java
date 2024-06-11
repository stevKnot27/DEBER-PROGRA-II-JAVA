public class Puesto {
    private Carro carro;

    public Puesto() {
        carro = null;
    }

    public boolean estaOcupado() {
        return carro != null;
    }

    public Carro darCarro() {
        return carro;
    }

    public void parquearCarro(Carro carro) {
        this.carro = carro;
    }

    public void sacarCarro() {
        carro = null;
    }
}

