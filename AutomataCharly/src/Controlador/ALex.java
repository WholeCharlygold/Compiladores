/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.*;
public class ALex {
    
    public int yylex(String regex, HashMap<Object, HashMap<String, Object>> tabla  ){
        int current_state=0;
        int previous_accepting_state=-1;
        LinkedList<Character> cadena=new LinkedList<Character>();
        ListIterator i=cadena.listIterator();
        for(char c:regex.toCharArray()){
            cadena.add(c);
            
        }
        if(i.hasNext())
            return 0;
        
        while(i.hasNext()){
            HashMap<String,Object> aux=new HashMap<>();
            aux=tabla.get(current_state);
            int s_e=(int)aux.get(i.next());
            if(s_e!=-1){
                current_state=s_e;
                String avanzar=(String)i.next();
                if((int)tabla.get(s_e).get("Token")!=-1){
                    
                }
            }
        }
        return 0;
        
        
    }
    
}
