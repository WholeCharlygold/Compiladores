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
private Estado e;
private Estado o;
    public String getE() {
        return "Transicion "+c+ " Estado Origen" + o.isTipo()+ " Origen" +o.getId()+ " Estado Destino" + e.isTipo()+ " Destino " +e.getId();
    }

    public char getC() {
        return c;
    }

    public void setE(Estado e) {
        this.e = e;
    }

    public void setC(char c) {
        this.c = c;
    }

    public void setO(Estado o) {
        this.o = o;
    }


    
}
