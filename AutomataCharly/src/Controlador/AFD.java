package Controlador;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AFD extends Automata {
    

    private HashMap<Object, HashMap<String, Object>> tabla;
    public AFD(HashSet<Estado> estados, LinkedList<Character> alfabeto,HashSet<Estado> estadosFinales, Estado estadoInicial,HashMap<Object, HashMap<String, Object>> tabla) {
		super(estados,alfabeto,estadosFinales,estadoInicial);
		this.tabla =  tabla;
	}

    public AFD(AFN input) {
        super(null, null, null, null);
        this.tabla = new HashMap();
        Stack<HashSet<Estado>> pila = new Stack<HashSet<Estado>>();
        Dictionary indice = new Hashtable();
        AtomicInteger conteo = new AtomicInteger(0);
        HashSet<HashSet<Estado>> estados_para_analizar = new HashSet<HashSet<Estado>>();
        estados_para_analizar.add(input.cerraduraEpsilon(input.getEstadoInicial()));
        indice.put(input.cerraduraEpsilon(input.getEstadoInicial()), conteo.getAndIncrement());
        pila.push(input.cerraduraEpsilon(input.getEstadoInicial()));
        while (!pila.isEmpty()) {
            HashSet<Estado> help = pila.pop();
            HashMap<String, Object> celda = new HashMap<String, Object>();
            for (char c : input.getAlfabeto()) {
                HashSet<Estado> aux;
                aux = input.Ir_A(help, c);
                if (!aux.isEmpty()) {
                    if (!estados_para_analizar.contains(aux)) {
                        estados_para_analizar.add(aux);
                        indice.put(aux, conteo.getAndIncrement());
                        pila.push(aux);
                    }
                    celda.put(String.valueOf(c), indice.get(aux));
                } else {
                    celda.put(String.valueOf(c), "-1");
                    //estados_para_analizar.add(aux);
                    // indice.put(aux, conteo.getAndIncrement());
                }
                this.tabla.put(indice.get(help), celda);
            }
            for (Estado e : input.getEstadosFinales()) {
                if (help.contains(e)) {
                    celda.put("Token", e.getToken());
                    this.tabla.put(indice.get(help), celda);
                    break;
                } else {
                    celda.put("Token", -1);
                    this.tabla.put(indice.get(help), celda);
                }
            }
        }
        this.alfabeto = input.getAlfabeto();
        System.out.println(estados_para_analizar.size());
        AFD main=AFD.crear_AFD(this.tabla);
        this.estados=main.estados;
        this.estadosFinales=main.estadosFinales;
        this.estadoInicial=main.estadoInicial;
        
    }

    public static AFD crear_AFD(HashMap<Object, HashMap<String, Object>> tabulacion) {
        HashMap<Integer, Estado> mapaEstados = new HashMap<Integer, Estado>();
        HashSet<Estado> estados = new HashSet<Estado>();
        HashSet<Estado> estados_finales = new HashSet<Estado>();
        Estado e_inicial = null;
        int i = 0;
        for (Map.Entry<Object, HashMap<String, Object>> entry : tabulacion.entrySet()) {
            LinkedList<Transicion> transiciones = new LinkedList<Transicion>();
            Estado estado = new Estado(false, false, transiciones);
            if ((int) entry.getKey() == 0) {
                estado.setEstadoInicial(true);
                e_inicial = estado;
            }
            for (Map.Entry<String, Object> entry2 : entry.getValue().entrySet()) {
                if (entry2.getKey() == "Token") {
                    if ((int) entry2.getValue() != -1) {
                        estado.setEstadoFinal(true);
                        estado.setToken((int)entry2.getValue());
                        estados_finales.add(estado);
                    }
                }
                estados.add(estado);
                mapaEstados.put(i, estado);
            }
            i++;
        }
        i=0;
        for (Map.Entry<Object, HashMap<String, Object>> entry : tabulacion.entrySet()) {
            Estado estado=mapaEstados.get(i);
            for (Map.Entry<String, Object> entry2 : entry.getValue().entrySet()) {
                if(Integer.parseInt(String.valueOf(entry2.getValue()))!=-1&&!"Token".equals(entry2.getKey()))
                estado.getTransiciones().add(new Transicion(entry2.getKey().charAt(0), mapaEstados.get((entry2.getValue()))));
            }
            i++;
        }
       
        return new AFD(estados, null, estados_finales, e_inicial, tabulacion);
    }

    public void display_Tabla() {
        System.out.println(this.tabla);
    }

    public static void main(String... strings) {
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
       AFN resultado=AFN.big_join(array);
       AFD resultado2=new AFD(resultado);
       /* a.union(b);
        a.clausura_positiva();
        c.clausura_cierre();

        a.concatenar(c);
        a.displayAutomata();
        //a_b_c.displayAFN();
        AFD resultado = new AFD(a);
        resultado.display_Tabla();
        resultado.displayAutomata();*/
       resultado2.displayAutomata();
       resultado2.display_Tabla();

    }
}
