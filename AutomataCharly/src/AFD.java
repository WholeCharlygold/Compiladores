
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chuch
 */
public class AFD {

    public AFD() {
    }

    public  HashSet crearAFD(AFN input) {
        int i=0;
        HashSet <HashSet> tabla= new HashSet<HashSet>();
        for (Estado e : input.estados)
        {
        if(e.getTransiciones().get(i).isEpsilon()==true)
        {
            HashSet<Estado> r = new HashSet<Estado>();
            r=input.cerraduraEpsilon(e, r);
            for (int j=0; j<input.alfabeto.size();j++)
            {
            input.Mover(r, input.getAlfabeto().get(j));
            }
            tabla.add(r);
        }
        i++;
        }
        return tabla;
    }
    
}
