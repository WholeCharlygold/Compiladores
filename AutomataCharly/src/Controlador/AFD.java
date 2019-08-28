package Controlador;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chuch
 */
public class AFD extends Automata {
   private  HashSet <LinkedList> tabla;


    public  AFD(AFN input) {
        int i=0;
       // HashSet <HashSet> tabla= new HashSet<HashSet>();
       this.tabla
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
