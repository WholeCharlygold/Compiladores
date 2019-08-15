import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AFN {
    char c;

    ArrayList<Transicion> t_array = new ArrayList<Transicion>();
    ArrayList<Estado> e_array = new ArrayList<Estado>();
    ArrayList<Character> alfabeto = new ArrayList<Character>();
    // int contador=1;
    // Estado e_inicial = new Estado();
    Estado e_final = new Estado();
    // Transicion t;

    public AFN() {// Constructor para la creacion de un AFN sin caracter
        e_array.add(new Estado(0));
    }

    public AFN(char c) {// Constructor para la creacion de un AFN con un caracter
        this.c = c;
        if (c != 'E') {
            e_array.add(new Estado(0));
            Estado e_siguiente = new Estado(1);
            e_array.add(e_siguiente);
            this.e_final = e_array.get(e_array.size() - 1);//e_siguiente
            Transicion a = new Transicion(c, e_array.get(0), e_final);
            alfabeto.add(c);
            t_array.add(a);
        } else {
            System.out.println("EPSILOOOON");
        }

    }

    public void set_caracter(char a) {// Metodo para poner el caracter con el que se hara un AFN
        c = a;
    }

    public char get_caracter() {// Metodo que retorna el caracter que tiene un AFN simple
        return c;
    }

    public Estado get_inicial() {// Metodo para obtener el estado inicial de un AFN
        return e_array.get(0);
    }

    public void set_inicial(Estado e) {// Metodo para poner el estado inicial de un AFN
        e_array.set(0, e);
    }

    public Estado get_final() { // Metodo para obtener el estado final de un AFN
        return e_array.get(e_array.size() - 1);
    }

    public void set_final(Estado e) {// Metodo para poner el estado final de un AFN
        e_array.set(e_array.size() - 1, e);
    }
    public void add_e(Estado e){
        e.setID(e_array.size()-1);
        e_array.add(e);

    }
    public void add_alpha(AFN afn1,AFN afn2){

    }
    public static AFN con(AFN afn1,AFN afn2){
        AFN main=new AFN();

       

        return main;
        
    }

    public static AFN concatenar(AFN afn1, AFN afn2) {// Operacion CONCATENAR de dos AFN (afn1 y afn2)
        AFN main = new AFN(); // Se crea el AFN principal que hace el return
        // int j = 0; // Contador auxiliar para colocar el ID en los estados
        int j = 1;
        main.alfabeto.addAll(afn1.alfabeto); // añadimos el alfabeto o caracter del afn1
        main.alfabeto.addAll(afn2.alfabeto); // añadimos el alfabeto o caracter del afn2
        for (int i = 0; i < afn1.e_array.size(); i++) {
            main.e_array.add(afn1.e_array.get(i));
            main.e_array.get(i + 1).setID(j);
            j++;
        }
        if (afn1.e_array.size() != 2) { //Caso en el que son AFNs las que se concatenan y no solo simbolos
            main.e_array.add(new Estado(j));
            for (int i = 0; i < afn2.e_array.size(); i++) {
                main.e_array.add(afn2.e_array.get(i));
                main.e_array.get(j).setID(j);
                j++;
            }
            main.e_array.add(new Estado(j));
            for (int i = 0; i < afn1.t_array.size(); i++) {
                main.t_array.add(new Transicion(afn1.t_array.get(i).getChar(), main.e_array.get(i + 1),
                        main.e_array.get(i + 2)));

            }
            for (int i = 0; i < afn2.t_array.size(); i++) {
                main.t_array.add(new Transicion(afn2.t_array.get(i).getChar(),
                        main.e_array.get(main.e_array.indexOf(afn2.get_inicial())),
                        main.e_array.get(main.e_array.indexOf(afn2.get_inicial()) + 1)));

            }
            return main;
        }//Fin del caso

        for (int i = 0; i < main.alfabeto.size(); i++) { // añadimos por cada caracter del alfabeto una transicion a
                                                         // nuestro AFN
            main.t_array.add(new Transicion(main.alfabeto.get(i), main.e_array.get(i), main.e_array.get(i + 1)));
        }

        return main;

    }

    public static AFN union(AFN afn1, AFN afn2) {// Operacion UNION de dos AFN (afn1 y afn2)
        AFN main = new AFN();

        int j = 1;
        for (int i = 0; i < afn1.e_array.size(); i++) {
            main.e_array.add(afn1.e_array.get(i));
            main.e_array.get(i + 1).setID(j);
            j++;
        }
        // j++;
        for (int i = 0; i < afn2.e_array.size(); i++) {
            main.e_array.add(afn2.e_array.get(i));
            main.e_array.get(j).setID(j);
            j++;
        }

        main.e_array.add(new Estado(j));
        main.alfabeto.addAll(afn1.alfabeto);
        main.alfabeto.addAll(afn2.alfabeto);

        main.t_array.add(
                new Transicion('E', main.get_inicial(), main.e_array.get(main.e_array.indexOf(afn1.get_inicial()))));

        for (int i = 0; i < afn1.t_array.size(); i++) {
            main.t_array.add(
                    new Transicion(afn1.t_array.get(i).getChar(), main.e_array.get(i + 1), main.e_array.get(i + 2)));

        }
        main.t_array
                .add(new Transicion('E', main.e_array.get(main.e_array.indexOf(afn1.get_final())), main.get_final()));

        main.t_array.add(
                new Transicion('E', main.get_inicial(), main.e_array.get(main.e_array.indexOf(afn2.get_inicial()))));

        for (int i = 0; i < afn2.t_array.size(); i++) {
            main.t_array.add(new Transicion(afn2.t_array.get(i).getChar(),
                    main.e_array.get(main.e_array.indexOf(afn2.get_inicial())),
                    main.e_array.get(main.e_array.indexOf(afn2.get_inicial()) + 1)));

        }
        main.t_array
                .add(new Transicion('E', main.e_array.get(main.e_array.indexOf(afn2.get_final())), main.get_final()));

        return main;
    }

    public static void main(String[] args) {

        System.out.println("Teclee un caracter: ");
        Scanner leer = new Scanner(System.in);
        char c = leer.next().charAt(0);
        AFN thomsom = new AFN(c);
        AFN aux = new AFN(c);
        aux = concatenar(new AFN('a'), new AFN('b'));
        //aux = concatenar(new AFN('c'), new AFN('d'));
        //thomsom = aux;
        thomsom= concatenar(aux,thomsom);
        // thomsom = concatenar(thomsom, aux);
        System.out.println("Alfabeto: " + thomsom.alfabeto);
        for (int i = 0; i < thomsom.e_array.size(); i++) {
            System.out.println("Estados: " + thomsom.e_array.get(i).getID());
        }

        for (int i = 0; i < thomsom.t_array.size(); i++) {
            System.out.println("Transiciones: " + thomsom.t_array.get(i).getOrigen() + "--"
                    + thomsom.t_array.get(i).getChar() + "-->" + thomsom.t_array.get(i).getDestino());
        }

    }
}
