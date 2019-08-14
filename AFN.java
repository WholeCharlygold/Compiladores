import java.io.*;
import java.util.Scanner;

public class AFN{
    Estado e_inicial=new Estado();
    String regex;
    
    public AFN(Estado e_inicial){
        this.e_inicial=e_inicial;

    }
    public getE_Inicial(){
        return e_inicial;
    }

    public analisis_lexico(){

        regex.matches(".");
    }
    public static void main(String []args){
        System.out.println("Ingresa la expresion regular:\n");
        Scanner leer=new Scanner(System.in);
        String regex=leer.nextLine();
        leer.close();
        analisis_lexico(regex);

    }
}