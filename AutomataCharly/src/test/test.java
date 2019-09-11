/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Controlador.AFD;
import Controlador.AFN;
import Controlador.Estado;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author Albarran Cruz Carlos Alberto
 */
public class test {

    public static AFN afn1() {
        AFN afnmas = new AFN('+');
        AFN afnmenos = new AFN('-');
        AFN afnpunto = new AFN('.');
        AFN afndigitos = new AFN('0', '9');
        AFN afndigitos2 = new AFN('0', '9');
        AFN afn1;
        afnmas.union(afnmenos);
        afnmas.pregunta();
        afndigitos.clausura_positiva();
        afndigitos2.clausura_positiva();
        afnmas.concatenar(afndigitos);
        afnmas.concatenar(afnpunto);
        afn1 = afnmas.concatenar(afndigitos2);
        afn1.set_Token(10);

        return afn1;
    }

    public static AFN afn2() {
        AFN afnmas = new AFN('+');
        AFN afnmenos = new AFN('-');
        AFN afndigitos = new AFN('0', '9');
        AFN afn1;
        afnmas.union(afnmenos);
        afnmas.pregunta();

        afndigitos.clausura_positiva();

        afnmas.concatenar(afndigitos);
        afn1 = afnmas;
        afn1.set_Token(20);

        return afn1;
    }

    public static AFN afn3() {
        AFN minusculas = new AFN('a', 'z');
        AFN mayusculas = new AFN('A', 'Z');
        AFN minusculas2 = new AFN('a', 'z');
        AFN mayusculas2 = new AFN('A', 'Z');
        AFN digitos = new AFN('0', '9');
        AFN resultado;
        minusculas.union(mayusculas);
        minusculas2.union(mayusculas2);
        minusculas2.union(digitos);
        minusculas2.clausura_cierre();
        minusculas.concatenar(minusculas2);
        resultado = minusculas;
        resultado.set_Token(30);

        return resultado;

    }

    public static void display_lista(LinkedList<Nodo> lista) {
        for (Nodo a : lista) {
            System.out.print(a.titulo + ",");
        }
        System.out.println("");
    }

    public static AFN afn4() {
        AFN mas1 = new AFN('+');
        AFN mas2 = new AFN('+');
        mas1.concatenar(mas2);
        mas1.set_Token(40);

        return mas1;

    }

    public static AFN afn5() {
        AFN resultado = new AFN('+');
        resultado.set_Token(50);

        return resultado;

    }

    public static void WriteObjectToFile(Object serObj, String filepath) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Object ReadObjectFromFile(String filepath) {

        try {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String... args) {
        /*AFN recuperado1=(AFN)test.ReadObjectFromFile("C:\\Users\\Maria Mireya\\source\\repos\\Compiladores\\AutomataCharly\\AFN5");
        recuperado1.set_Token(50);
        test.WriteObjectToFile(recuperado1, "AFN5");
        System.exit(0);*/
        int contador=0;
        Scanner in = new Scanner(System.in);
        LinkedList<Nodo> lista = new LinkedList<>();
        LinkedList<Nodo> afd = new LinkedList<Nodo>();
        AFN grande = new AFN();
        AFD main;
        String A, B;
        for (;;) {
            System.out.println("Teclee la opcion a elegir\n"
                    + "1. Crear AFN\n"
                    + "2. Unir AFNs creados( crear AFN grande)\n"
                    + "3. Crear AFD a partir de AFN grande y ver tabla\n"
                    + "4. Operacion con AFN\n"
                    + "5. Add AFN a AFN grande\n"
                    + "6. Recuperar AFN desde archivo");
            String opcion = in.nextLine();
            switch (opcion) {
                case "1":
                    System.out.println("Teclee el caracter o el rango de caracteres para el AFN[0-9]");
                    String cadena = in.nextLine();
                    System.out.println("Teclee el titulo del afn");
                    String titulo = in.nextLine();
                    if (cadena.length() > 1) {
                        lista.add(new Nodo(new AFN(cadena.charAt(1), cadena.charAt(3)), titulo));
                    } else {
                        lista.add(new Nodo(new AFN(cadena.charAt(0)), titulo));
                    }
                    break;
                case "2":
                    System.out.print("Uniendo ");
                    LinkedList<AFN> conjunto_afn = new LinkedList<>();
                    System.out.println(afd.size());
                    for (Nodo a : afd) {
                        System.out.print(a.titulo + " ");
                        conjunto_afn.add(a.automata);
                    }
                    grande = AFN.big_join(conjunto_afn);
                    grande.displayAutomata();
                    System.out.println("AFN grande creado");
                    break;
                case "3":
                    System.out.println("Creando AFD");
                    if (grande != null) {
                        main = new AFD(grande);
                        main.display_Tabla();
                    } else {
                        System.out.println("AFN grande aun no se ha creado");
                    }
                    break;
                case "4":
                    System.out.println("Teclee la operacion que quiera hacer\n"
                            + "1. Concatenar\n"
                            + "2. Union\n"
                            + "3. Cerradura Kleen\n"
                            + "4. Cerradura Positiva\n"
                            + "5. Operador Pregunta");
                    String opcion2 = in.nextLine();
                    switch (opcion2) {
                        case "1":
                            AFN a = new AFN();
                            AFN b = new AFN();
                            System.out.println("A concatenar B");
                            test.display_lista(lista);
                            System.out.println("Teclee el titulo de A");
                            A = in.nextLine();
                            for (Nodo x : lista) {
                                if (x.titulo.equals(A)) {
                                    a = x.automata;
                                }
                            }
                            System.out.println("Teclee el titulo de B");
                            B = in.nextLine();
                            for (Nodo x : lista) {
                                if (x.titulo == null ? B == null : x.titulo.equals(B)) {
                                    b = x.automata;
                                }
                            }
                            lista.add(new Nodo(a.concatenar(b), A + "&" + B));
                            break;
                        case "2":
                            AFN c = new AFN();
                            AFN d = new AFN();
                            System.out.println("A union B");
                            test.display_lista(lista);
                            System.out.println("Teclee el titulo de A");
                            A = in.nextLine();
                            for (Nodo x : lista) {
                                if (x.titulo.equals(A)) {
                                    c = x.automata;
                                }
                            }
                            System.out.println("Teclee el titulo de B");
                            B = in.nextLine();
                            for (Nodo x : lista) {
                                if (x.titulo == null ? B == null : x.titulo.equals(B)) {
                                    d = x.automata;
                                }
                            }
                            lista.add(new Nodo(c.union(d), A + "|" + B));
                            break;
                        case "3":
                            AFN e = new AFN();
                            System.out.println("Teclee el titulo del AFN para aplicar la operacion cerradura kleen");
                            test.display_lista(lista);
                            A = in.nextLine();
                            for (Nodo x : lista) {
                                if (x.titulo.equals(A)) {
                                    e = x.automata;
                                }
                            }
                            lista.add(new Nodo(e.clausura_cierre(), A + "*"));
                            break;
                        case "4":
                            AFN f = new AFN();
                            System.out.println("Teclee el titulo del AFN para aplicar la operacion cerradura positiva");
                            test.display_lista(lista);
                            A = in.nextLine();
                            for (Nodo x : lista) {
                                if (x.titulo.equals(A)) {
                                    f = x.automata;
                                }
                            }
                            lista.add(new Nodo(f.clausura_positiva(), A + "+"));
                            break;
                        case "5":
                            AFN g = new AFN();
                            System.out.println("Teclee el titulo del AFN para aplicar la operacion pregunta");
                            test.display_lista(lista);
                            A = in.nextLine();
                            for (Nodo x : lista) {
                                if (x.titulo.equals(A)) {
                                    g = x.automata;

                                }
                            }
                            lista.add(new Nodo(g.pregunta(), A + "?"));
                            break;
                        default:
                            System.out.println("Operacion no permitida");
                            break;

                    }
                    break;
                case "5":
                    AFN resultado = new AFN();
                    System.out.println("Teclee el titulo del AFN que desea añadir a la lista");
                    test.display_lista(lista);
                    A = in.nextLine();
                    System.out.println("Teclee un token para el estado final");
                    int token_recibido=Integer.parseInt(in.nextLine());
                    
                    // LinkedList <Nodo> nuevo=new LinkedList<Nodo>();
                    int i = 0;
                    for (Nodo x : lista) {
                        if (x.titulo.equals(A)) {
                            resultado=x.automata;
                            resultado.set_Token(token_recibido);
                            afd.add(new Nodo(resultado, x.titulo));
                            System.out.println("Teclee un titulo para el archivo");
                            String titulo_archivo=in.nextLine();
                            
                            for(Estado e:x.automata.getEstadosFinales()){
                                e.setToken(token_recibido);
                            }
                            test.WriteObjectToFile(x.automata, titulo_archivo);
                        }
                        i++;
                    }
                    lista = new LinkedList<Nodo>();

                    break;
                case "6":
                     //System.out.println(afd.isEmpty());
                    JFileChooser jf = new JFileChooser();
                    File workingDirectory = new File(System.getProperty("user.dir"));
                    jf.setCurrentDirectory(workingDirectory);
                    int r = jf.showOpenDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        File f = jf.getSelectedFile();// manejador
                        String archivo = f.getAbsolutePath();// Dirección
                        String nombre = f.getName();// Nombre
                        long tam = f.length();// Tamaño
                        AFN recuperado=(AFN)test.ReadObjectFromFile(archivo);
                        recuperado.displayAutomata();
                       
                        afd.add(new Nodo(recuperado,nombre));
                    }
                  //afd.get(contador).automata.displayAutomata();
                   contador++;

                    break;
                default:
                    //System.out.println("Opcion no permitda");
                    break;
            }
        }


        /*
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
         //tablee.display_Tabla();*/
    }

}

class Nodo {

    public String titulo;
    public AFN automata;

    public Nodo(AFN automata, String titulo) {
        this.titulo = titulo;
        this.automata = automata;
    }

}
