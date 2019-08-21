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
  public void Concadenar(String Text, ArrayList E, ArrayList T)
    {
            for(int i=0;i<Text.length();i++)
            {
                Estado origen= new Estado();
                Estado destino= new Estado();
                Transicion Linea= new Transicion();
                origen.setId(i);
                destino.setId(i+1);
                Linea.set(Text.charAt(i), origen, destino);
                E.add(origen);
                E.add(destino);
                if(i+1<Text.length())
                {
                E.set(i+1,destino);
                }
                T.add(Linea);
                //Orden+=Linea.getOrigen()+" " + Linea.getC()+ " "+Linea.getDestino()+"\n";
            }
    }
}
