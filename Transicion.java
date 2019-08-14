import java.io.*;

public class Transicion{
    char caracter;
    Estado e=new Estado();
    public Transicion(char caracter,Estado e){
        this.caracter=caracter;
        this.e=e;
    }
    public char getChar(){
        return caracter;
    }
    public Estado getEstado(){
        return e;
    }
    public static void main(String []args){
        char caracter='c';
        System.out.println(caracter);
    }
}