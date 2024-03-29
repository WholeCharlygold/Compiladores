package Controlador;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Clase que tiene como padre Automata y representa automatas finitos no
 * deterministas
 */
public class AFN extends Automata implements Serializable{
    /**
     * Contruye un AFN con un solo caracter
     * 
     * @param ch - Caracter para la construccion del afn
     */
    public AFN(HashSet<Estado> estados,LinkedList<Character>alfabeto,HashSet<Estado>estadosFinales,Estado estadoInicial){
        super(estados, alfabeto, estadosFinales, estadoInicial);
    }
    public AFN(Character ch) {
        super(null, null, null, null);
        if (!super.getAlfabeto().contains(ch)) {
            super.getAlfabeto().add(ch);
        }
        HashSet<Estado> estados = new HashSet<Estado>();
        Estado estadoFinal = new Estado(false, true, new LinkedList<Transicion>());
        HashSet<Estado> estadosFinales = new HashSet<Estado>();
        estadosFinales.add(estadoFinal);
        LinkedList<Transicion> transicionesEstadoInicial = new LinkedList<Transicion>();
        transicionesEstadoInicial.add(new Transicion(ch, estadoFinal));
        Estado estadoInicial = new Estado(true, false, transicionesEstadoInicial);
        estados.add(estadoInicial);
        estados.add(estadoFinal);

        this.estados = estados;
        this.estadosFinales = estadosFinales;
        this.estadoInicial = estadoInicial;
    }
    public AFN(Character ch1,Character ch2) {
        super(null, null, null, null);
          if (ch1 == 'A' && ch2 == 'Z') {
            char resultado = 'M';
            super.getAlfabeto().add(resultado);
          
        }
        if (ch1 == 'a' && ch2 == 'z') {
            char resultado = 'm';
           super.getAlfabeto().add(resultado);
        }
        if (ch1 == '0' && ch2 == '9') {
            char resultado = 'D';
           super.getAlfabeto().add(resultado);
        }
        HashSet<Estado> estados = new HashSet<Estado>();
        Estado estadoFinal = new Estado(false, true, new LinkedList<Transicion>());
        HashSet<Estado> estadosFinales = new HashSet<Estado>();
        estadosFinales.add(estadoFinal);
        LinkedList<Transicion> transicionesEstadoInicial = new LinkedList<Transicion>();
        transicionesEstadoInicial.add(new Transicion(ch1,ch2 ,estadoFinal));
        Estado estadoInicial = new Estado(true, false, transicionesEstadoInicial);
        estados.add(estadoInicial);
        estados.add(estadoFinal);

        this.estados = estados;
        this.estadosFinales = estadosFinales;
        this.estadoInicial = estadoInicial;
    }
    public AFN(){
        super(new HashSet<Estado>(), new LinkedList<Character>(), new HashSet<Estado>(), null);
    }
    /**
     * Funcion para concatenar el AFN con otro AFN(afnb)
     * 
     * @param afnb - AFN con quien se quiere concatenar el AFN principal
     * @return - AFN © afnb
     */
    public AFN concatenar(AFN afnb) {
        for (Estado estado : this.estadosFinales) {
            estado.getTransiciones().addAll(afnb.getEstadoInicial().getTransiciones());
            estado.setEstadoFinal(false);
        }
        for (Estado estado : afnb.getEstados()) {
            if (!estado.isEstadoInicial()) {
                this.estados.add(estado);
            }
        }
        afnb.getEstadoInicial().setEstadoInicial(false);
        this.estadosFinales = afnb.getEstadosFinales();
        for (Character simbolo : afnb.getAlfabeto()) {
            if (!this.alfabeto.contains(simbolo)) {
                this.alfabeto.add(simbolo);
            }
        }
        return this;
    }

    /**
     * Funcion que une un AFN con otro AFN(afn)
     * 
     * @param afn - AFN que se une con el AFN principal
     * @return - AFN | afn
     */
    public AFN union(AFN afn) {
        Transicion t1 = new Transicion(this.getEstadoInicial());
        Transicion t2 = new Transicion(afn.getEstadoInicial());
        afn.getEstadoInicial().setEstadoInicial(false);
        LinkedList<Transicion> Estados_Iniciales = new LinkedList<Transicion>();
        Estados_Iniciales.add(t1);
        Estados_Iniciales.add(t2);
        Estado e_inicial = new Estado(true, false, Estados_Iniciales);
        this.getEstadoInicial().setEstadoInicial(false);
        this.estadoInicial = e_inicial;
        this.estados.add(e_inicial);
        Estado e_final = new Estado(false, true, new LinkedList<Transicion>());
        this.estados.add(e_final);
        for (Estado e : afn.getEstadosFinales()) {
            e.getTransiciones().add(new Transicion(e_final));
            e.setEstadoFinal(false);
        }
        for (Estado e : this.getEstadosFinales()) {
            e.getTransiciones().add(new Transicion(e_final));
            e.setEstadoFinal(false);
        }

        this.estadosFinales = new HashSet<Estado>();
        estadosFinales.add(e_final);

        for (Character simbolo : afn.getAlfabeto()) {
            if (!this.alfabeto.contains(simbolo)) {
                this.alfabeto.add(simbolo);
            }
        }
        this.estados.addAll(afn.getEstados());

        return this;
    }

    /**
     * Funcion que obtiene AFN+
     * 
     * @return AFN+
     */
    public AFN clausura_positiva() {
        Transicion t1 = new Transicion(this.getEstadoInicial());
        LinkedList<Transicion> Transiciones_Iniciales = new LinkedList<Transicion>();
        Transiciones_Iniciales.add(t1);
        Estado e_inicial = new Estado(true, false, Transiciones_Iniciales);
        this.estados.add(e_inicial);

        Estado e_final = new Estado(false, true, new LinkedList<Transicion>());
        this.estados.add(e_final);
        for (Estado e : this.getEstadosFinales()) {
            e.getTransiciones().add(new Transicion(e_final));
            e.getTransiciones().add(new Transicion(this.getEstadoInicial()));
            e.setEstadoFinal(false);
        }
        this.estadoInicial = e_inicial;
        this.getEstadoInicial().setEstadoInicial(false);
        this.estadosFinales = new HashSet<Estado>();
        estadosFinales.add(e_final);

        return this;
    }

    /**
     * Funcion  que obtiene AFN*
     * @return AFN*
     */
    public AFN clausura_cierre() {
        Transicion t1 = new Transicion(this.getEstadoInicial());
        LinkedList<Transicion> Transiciones_Iniciales = new LinkedList<Transicion>();
        Transiciones_Iniciales.add(t1);

        this.getEstadoInicial().setEstadoInicial(false);
        Estado e_final = new Estado(false, true, new LinkedList<Transicion>());
        Transicion t2 = new Transicion(e_final);
        Transiciones_Iniciales.add(t2);
        Estado e_inicial = new Estado(true, false, Transiciones_Iniciales);
        this.estados.add(e_inicial);
        for (Estado e : this.getEstadosFinales()) {
            e.getTransiciones().add(new Transicion(e_final));
            e.getTransiciones().add(new Transicion(this.getEstadoInicial()));
            e.setEstadoFinal(false);
        }
        this.estadoInicial = e_inicial;
        this.getEstados().add(e_final);

        this.estadosFinales = new HashSet<Estado>();
        estadosFinales.add(e_final);

        return this;

    }
   /**
    * Funcion que obtiene AFN?
    * @return AFN?
    */
    public AFN pregunta() {
        LinkedList<Transicion> Transiciones_Iniciales = new LinkedList<Transicion>();
        Transicion t1 = new Transicion(this.getEstadoInicial());

        Transiciones_Iniciales.add(t1);

        this.getEstadoInicial().setEstadoInicial(false);
        Estado e_final = new Estado(false, true, new LinkedList<Transicion>());
        Transicion t2 = new Transicion(e_final);
        Transiciones_Iniciales.add(t2);
        this.getEstados().add(e_final);
        Estado e_inicial = new Estado(true, false, Transiciones_Iniciales);
        this.estadoInicial = e_inicial;
        this.getEstados().add(e_inicial);

        for (Estado e : this.getEstadosFinales()) {
            e.getTransiciones().add(new Transicion(e_final));
            e.setEstadoFinal(false);

        }
        this.estadosFinales = new HashSet<Estado>();
        this.getEstadosFinales().add(e_final);

        return this;

    }
/**
 * Funcion que obtiene la cerradura epsilon pasandole un estado
 * @param start
 * @return Conjunto de estados
 */
    public HashSet<Estado> cerraduraEpsilon(Estado start) {
        
        HashSet<Estado> r = new HashSet<Estado>();
        r.add(start);
        for (Estado e : this.getEstados()) {
            if (e.getId() == start.getId()) {
                for (Transicion t : e.getTransiciones()) {
                    if (t.isEpsilon()) {
                        r.add(t.getEstadoSiguiente());
                        r = cerraduraEpsilon(t.getEstadoSiguiente(), r);
                    }
                }

            }

        }

        return r;

    }

    /**
     *
     * @param start
     * @param guardar
     * @return
     */
    public HashSet<Estado> cerraduraEpsilon(Estado start, HashSet<Estado> guardar) {
        HashSet<Estado> r = guardar;
        r.add(start);
        for (Estado e : this.getEstados()) {
            if (e.getId() == start.getId()) {
                for (Transicion t : e.getTransiciones()) {
                    if (t.isEpsilon()) {
                        r.add(t.getEstadoSiguiente());
                        r = cerraduraEpsilon(t.getEstadoSiguiente(), r);
                    }
                }

            }

        }

        return r;

    }

    /**
     *
     * @param conjunto
     * @param c
     * @return
     */
    public HashSet<Estado> Mover(HashSet<Estado> conjunto, char c) {
        HashSet<Estado> r = new HashSet<Estado>();
        for (Estado e : conjunto) {
            for (Transicion t : e.getTransiciones()) {
                if (!t.isEpsilon()) {
                    if (t.getCaracterDeTransicion() == c) {
                        r.add(t.getEstadoSiguiente());
                        r = Mover(t.getEstadoSiguiente(), c, r);
                    }
                }
            }
        }
        if (r.isEmpty()) {
           // System.out.println("Operacion Mover vacia");
           r.clear();
            return r;
        } else {
            return r;
        }

    }

    /**
     *
     * @param siguiente
     * @param c
     * @param aux
     * @return
     */
    public HashSet<Estado> Mover(Estado siguiente, char c, HashSet<Estado> aux) {
        HashSet<Estado> r = aux;

        for (Transicion t : siguiente.getTransiciones()) {
            if (!t.isEpsilon()) {
                if (t.getCaracterDeTransicion() == c) {
                    r.add(t.getEstadoSiguiente());
                    r = Mover(t.getEstadoSiguiente(), c, r);
                }
            }
        }
        return r;
    }

    /**
     *
     * @param s
     * @param c
     * @return
     */
    public HashSet<Estado> Ir_A(HashSet<Estado> s, char c) {
        HashSet<Estado> r = new HashSet<Estado>();
        HashSet<Estado> r2 = new HashSet<Estado>();
        r = Mover(s, c);
        for (Estado e : r) {
            r2.addAll(cerraduraEpsilon(e)) ;
        }

        return r2;
    }
    public static long get_lastID(LinkedList <AFN> conjunto_afn){
        long mayor=0L;
        for(AFN a:conjunto_afn){
            for(Estado e:a.getEstados()){
                if(e.getId()>mayor)
                    mayor=e.getId();
            }
        }
        return mayor+1;
    }
    public static AFN big_join(LinkedList<AFN> conjunto_afn){
        LinkedList<Transicion> transiciones = new LinkedList<Transicion>();
		HashSet<Estado> estados = new HashSet<Estado>();
		HashSet<Estado> estadosFinales = new HashSet<Estado>();
		LinkedList<Character> alfabeto = new LinkedList<Character>();
		
		for(AFN afn:conjunto_afn) {
			Transicion transicion = new Transicion(afn.getEstadoInicial());
			afn.getEstadoInicial().setEstadoInicial(false);
			estados.addAll(afn.getEstados());
			estadosFinales.addAll(afn.getEstadosFinales());
			transiciones.add(transicion);
			for(Character simbolo:afn.getAlfabeto()) {
				if(!alfabeto.contains(simbolo)) {
					alfabeto.add(simbolo);
				}
			}
		}
		Estado nuevoEstadoInicial = new Estado(true,false,transiciones);
                nuevoEstadoInicial.id=AFN.get_lastID(conjunto_afn);
		estados.add(nuevoEstadoInicial);
		return new AFN(estados, alfabeto, estadosFinales, nuevoEstadoInicial);
    }
    public void set_Token(int n){
        for(Estado e:this.estadosFinales){
            e.setToken(n);
        }
        
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}