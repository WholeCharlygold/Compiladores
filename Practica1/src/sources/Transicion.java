/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

/**
 *
 * @author chuch
 */
public class Transicion {
    private char c;
    private Estado origen= new Estado();
    private Estado destino= new Estado();

    public void set(char c,Estado origen, Estado destino) {
        this.c = c; 
        this.origen = origen;
        this.destino = destino;
    }

    public char getC() {
        return c;
    }

    public int getOrigen() {
        return origen.getId();
    }

    public int getDestino() {
        return destino.getId();
    }

    @Override
    public String toString() {
        return "Transicion{" + "c=" + c + ", origen=" + origen + ", destino=" + destino + '}';
    }






    
    
}
