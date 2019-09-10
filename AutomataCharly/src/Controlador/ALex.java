/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import java.util.*;

public class ALex {
    public final int ERROR=-1;
    public final int FIN_CADENA=0;
    public AFD automata_alex;
    private String lexema;
    private boolean PasoPorEdoAcept;
    private int token;
    private String cadena_analizar;
    private Stack<Integer> pila_indices;
    public int ApCarActual;
    public int ApIniLexema;
    public int ApFinLexema;
    
    public ALex(LinkedList<AFN> array_afn,String cadena_analizar){
        this.automata_alex=new AFD(AFN.big_join(array_afn));
        this.cadena_analizar=cadena_analizar;
        this.token=-1;
        this.PasoPorEdoAcept=false;
        this.ApCarActual=0;
        this.ApIniLexema=0;
        this.ApFinLexema=-1;
    }
    
    public void RegresarToken(){
        pila_indices.pop();
    }
    public ALex GetStatus(){
        ALex b=new ALex(new LinkedList<AFN>(), this.cadena_analizar);
        b.ApCarActual=this.ApCarActual;
        b.ApFinLexema=this.ApFinLexema;
        b.ApIniLexema=this.ApIniLexema;
        b.PasoPorEdoAcept=this.PasoPorEdoAcept;
        b.automata_alex=this.automata_alex;
        b.cadena_analizar=this.cadena_analizar;
        b.pila_indices=this.pila_indices;
        b.token=this.token;
        b.lexema=this.lexema;
        
        return b;
        
    }
    public void SetStatus(ALex b){
        this.ApCarActual=b.ApCarActual;
        this.ApFinLexema=b.ApFinLexema;
        this.ApIniLexema=b.ApIniLexema;
        this.PasoPorEdoAcept=b.PasoPorEdoAcept;
        this.automata_alex=b.automata_alex;
        this.cadena_analizar=b.cadena_analizar;
        this.pila_indices=b.pila_indices;
        this.token=b.token;
        this.lexema=b.lexema;
        
        
    }
    
    
    
    public int yylex(  ){
       //int edo_actual=(int)this.automata_alex.getEstadoInicial().getId();
       Estado edo_actual=automata_alex.getEstadoInicial(); 
       
       PasoPorEdoAcept=false;
       char []s=cadena_analizar.toCharArray();
       if(ApCarActual>cadena_analizar.length()){
           return FIN_CADENA;
       }else{
           pila_indices.push(ApCarActual);
           ApIniLexema=ApCarActual;
           while(s[ApCarActual]!=FIN_CADENA){
               if(AFD.obtenerTransicion(edo_actual, s[ApCarActual])!=null){
                   edo_actual=AFD.obtenerTransicion(edo_actual, s[ApCarActual]);
                   ApCarActual++;
                   if(edo_actual.isEstadoFinal()){
                       token=edo_actual.getToken();
                       PasoPorEdoAcept=true;
                       ApFinLexema=ApCarActual; 
                   }
               }else{
                   if(!PasoPorEdoAcept){
                       lexema=cadena_analizar.substring(ApCarActual, ApCarActual);
                       ApCarActual=++ApIniLexema;
                       return ERROR;                   }
               }
               lexema=cadena_analizar.substring(ApIniLexema,ApFinLexema);
               ApCarActual=++ApFinLexema;
             
           }
            
       }
       
        return 0;
    
    }
    public static void main(String ...args){
         AFN a = new AFN('a');
        a.set_Token(100);
        AFN b = new AFN('b');
        b.set_Token(500);
        AFN c = new AFN('c');
        c.set_Token(800);
       // a.concatenar(b);
       LinkedList<AFN> array=new LinkedList<AFN>();
       array.add(a);
       array.add(b);
       array.add(c);
        ALex prueba=new ALex(array, "abc");
       // prueba.yy
        
    }
    
}
