/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Controlador.AFD;
import Controlador.AFN;
import java.util.LinkedList;

/**
 *
 * @author Albarran Cruz Carlos Alberto
 */
public class test {
    public static AFN afn1(){
        AFN afnmas=new AFN('+');
        AFN afnmenos=new AFN('-');
        AFN afnpunto=new AFN('.');
        AFN afndigitos=new AFN('0','9');
         AFN afndigitos2=new AFN('0','9');
        AFN afn1;
        afnmas.union(afnmenos);
        afnmas.pregunta();
        afndigitos.clausura_positiva();
        afndigitos2.clausura_positiva();
        afnmas.concatenar(afndigitos);
        afnmas.concatenar(afnpunto);
        afn1=afnmas.concatenar(afndigitos2);
        afn1.set_Token(10);
        
        return afn1;
    }
     public static AFN afn2(){
        AFN afnmas=new AFN('+');
        AFN afnmenos=new AFN('-');
        AFN afndigitos=new AFN('0','9');
        AFN afn1;
        afnmas.union(afnmenos);
        afnmas.pregunta();
      
        afndigitos.clausura_positiva();
     
        afnmas.concatenar(afndigitos);
        afn1=afnmas;
        afn1.set_Token(20);
    
        return afn1;
    }
       public static AFN afn3(){
        AFN minusculas=new AFN('a','z');
        AFN mayusculas=new AFN('A','Z');
        AFN minusculas2=new AFN('a','z');
        AFN mayusculas2=new AFN('A','Z');
        AFN digitos=new AFN('0','9');
        AFN resultado;
        minusculas.union(mayusculas);
        minusculas2.union(mayusculas2);
        minusculas2.union(digitos);
        minusculas2.clausura_cierre();
        minusculas.concatenar(minusculas2);
        resultado=minusculas;
        resultado.set_Token(30);
        
        return resultado;
        
    }
        public static AFN afn4(){
        AFN mas1=new AFN('+');
        AFN mas2=new AFN('+');
        mas1.concatenar(mas2);
        mas1.set_Token(40);
        
        
        return mas1;
        
    }
         public static AFN afn5(){
        AFN resultado=new AFN('+');
        resultado.set_Token(50);
        
        return resultado;
        
    }
       
    public static void main (String...args){
      
        AFN afn1=test.afn1();
        //afn1.displayAutomata();
        AFN afn2=test.afn2();
        //afn2.displayAutomata();
        AFN afn3=test.afn3();
        //}afn3.displayAutomata();
        AFN afn4=test.afn4();
        //afn4.displayAutomata();
         AFN afn5=test.afn5();
         //afn5.displayAutomata();
         LinkedList<AFN> resultado=new LinkedList<>();
         resultado.add(afn1);
         resultado.add(afn2);
         resultado.add(afn3);
         resultado.add(afn4);
         resultado.add(afn5);
         AFN grande=AFN.big_join(resultado);
       //grande.displayAutomata();
         AFD table=new AFD(grande);
        //table.displayAutomata();
         table.display_Tabla();
         
      
         AFN afna=new AFN('a');
         AFN afnb=new AFN('b');
         afna.union(afnb);
         afna.clausura_positiva();
         AFN afnc=new AFN('c');
         afnc.clausura_cierre();
         afna.concatenar(afnc);
         AFD tablee=new AFD(afna);
         //tablee.display_Tabla();
         
       
        
    }
    
}
