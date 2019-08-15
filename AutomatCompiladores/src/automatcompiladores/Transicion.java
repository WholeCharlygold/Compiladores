

public class Transicion {
    char caracter;
    Estado origen = new Estado();
    Estado destino = new Estado();

    public Transicion(char caracter, Estado origen, Estado destino) {
        this.caracter = caracter;
        this.origen = origen;
        this.destino = destino;
    }

    public char getChar() {
        return caracter;
    }

    public int getOrigen() {
        return origen.getID();
    }

    public void setOrigen(Estado e) {
        origen = e;

    }

    public int getDestino() {
        return destino.getID();
    }

    public void setDestino(Estado e) {
        destino = e;
    }

    public static void main(String[] args) {
        char caracter = 'c';
        System.out.println(caracter);
    }
}