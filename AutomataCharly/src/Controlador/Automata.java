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
	
}	