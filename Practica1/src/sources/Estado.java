/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import java.util.ArrayList;

/**
 *
 * @author chuch
 */
public class Estado {
    private int id;
    private boolean tipo;
    //false 0 no es final
    //true 1 es final
    public int getId() {
        return id;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        
        return "Estado{" + "id=" + id + ", tipo=" + tipo + "}";
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }


    
}
