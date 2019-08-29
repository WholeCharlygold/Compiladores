package Controlador;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AFD extends Automata {

    private HashMap<Object, HashMap<String, Object>> tabla;

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
                    celda.put("Token", 1);
                } else {
                    celda.put("Token", -1);
                }
                this.tabla.put(indice.get(help), celda);
            }
        }
        this.alfabeto = input.getAlfabeto();
        System.out.println(estados_para_analizar.size());
    }

    public AFD crear_AFD(HashMap<Object, HashMap<String, Object>> tabulacion, LinkedList<Character> alfabeto){
        HashSet <Estado> estados=new HashSet<Estado>();
       // Estado estado_inicial=new Estado(true, false, new LinkedList<Transicion>());
        for(Map.Entry<Object, HashMap<String, Object>> entry : tabulacion.entrySet()) {
            Estado e=new Estado(false,false,new LinkedList<Transicion>());
            for(Map.Entry<String,Object> entry2: entry.getValue().entrySet()){
                Transicion t=new Transicion(entry2.getKey().charAt(0), e);
            }
        }
        
    } 
    public void display_Tabla() {
        System.out.println(this.tabla);
    }
    

    public static void main(String... strings) {
        AFN a = new AFN('a');
        AFN b = new AFN('b');
        AFN c = new AFN('c');
        a.union(b);
        a.clausura_positiva();
        c.clausura_cierre();

        a.concatenar(c);
        a.displayAFN();
        //a_b_c.displayAFN();
        AFD resultado = new AFD(a);
        resultado.display_Tabla();

    }
}
