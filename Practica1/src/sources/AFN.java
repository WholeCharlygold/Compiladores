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
< */
public class AFN {
    private  Estado es= new Estado();
    private Transicion tr= new Transicion();
    

    public String AFNBasico(int i,char c)
    {
        Estado e1= new Estado();
        Estado e2= new Estado();
        e1.setId(i);
        e1.setTipo(false);
        if(e1.isTipo()!=true)
        {
        e2.setId(i+1); 
        e2.setTipo(true);
        Transicion t= new Transicion();
        t.setC(c);
        t.setO(e1);
        t.setE(e2);
        return t.getE();
        }
        return "Solo exite estado final";
    }

    public String getEs() {
        return es.toString();
    }



    public Transicion getTr() {
        return tr;
    }

    @Override
    public String toString() {
        return "AFN{" + "es=" + es.toString() + + '}';
    }

}
