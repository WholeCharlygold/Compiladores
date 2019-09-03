package Controlador;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Esta clase abstracta nos sirve para definir las operaciones y atributos principales para cualquier tipo de automata
 * */
public abstract class Automata {
	
    /**
     *
     */
    protected LinkedList<Character> alfabeto;

    /**
     *
     */
    protected HashSet<Estado> estados;

    /**
     *
     */
    protected HashSet<Estado> estadosFinales; //Estados de aceptacion

    /**
     *
     */
    protected Estado estadoInicial;
	
	/**
	 * Este contructor crea un Automata de acuerdo a lo establecido en los parametros
     * @param estados
     * @param alfabeto
     * @param estadosFinales
     * @param estadoInicial
	 * */
	public Automata(HashSet<Estado> estados, LinkedList<Character> alfabeto,HashSet<Estado> estadosFinales, Estado estadoInicial) {
		super();
		this.estados = estados;
		if(alfabeto == null) {
			this.alfabeto = new LinkedList<Character>();
		}else {
			this.alfabeto=alfabeto;
		}
		this.estadosFinales = estadosFinales;
		this.estadoInicial = estadoInicial;
	}
	
    /**
     *
     * @return
     */
    public HashSet<Estado> getEstados() {
		return estados;
	}
	
    /**
     *
     * @return
     */
    public LinkedList<Character> getAlfabeto() {
		return alfabeto;
	}
	
    /**
     *
     * @return
     */
    public HashSet<Estado> getEstadosFinales() {
		return estadosFinales;
	}
	
    /**
     *
     * @return
     */
    public Estado getEstadoInicial() {
		return estadoInicial;
	}
	
    public void displayAutomata(){
          System.out.println(this.getAlfabeto());
          System.out.println("Numero de estados: "+this.getEstados().size());
        for (Estado estado : this.getEstados()) {
            System.out.println(estado.getId());
        }
        for (Estado estado : this.getEstados()) {
            estado.displayTransicion();
        }
        System.out.print("Estados finales: ");
        for (Estado e : this.getEstadosFinales()) {
            System.out.print(e.getId() + " ");
        }
        System.out.println(" ");
        System.out.println("Estado Inicial: " + this.getEstadoInicial().getId());
    }
}	