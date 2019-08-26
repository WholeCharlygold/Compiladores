import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Esta clase abstracta nos sirve para definir las operaciones y atributos principales para cualquier tipo de automata
 * */
public abstract class Automata {
	
	protected LinkedList<Character> alfabeto;
	protected HashSet<Estado> estados;	//Todos los estados del automata
	protected HashSet<Estado> estadosFinales; //Estados de aceptacion
	protected Estado estadoInicial;	
	
	/**
	 * Este contructor crea un Automata de acuerdo a lo establecido en los parametros
	 * @param HashSet<Estado> estados - Estados del automata
	 * @param LinkedList<Character> alfabeto - Alfabeto valido en el automata
	 * @param HashSet<Estado> estadosFinales - Estados finales
	 * @param Estado estadoInicial - Estado inicial del automata
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
	 * Funcion para obtener todos los estados de un automata
	 * @return HashSet de Estados
	 */
	public HashSet<Estado> getEstados() {
		return estados;
	}
	/**
	 * Funcion para obtener el alfabeto de un automata
	 * @return LinkedList alfabeto
	 */
	public LinkedList<Character> getAlfabeto() {
		return alfabeto;
	}
	/**
	 * Funcion para obtener los estados finales o aceptables
	 * @return HashSet estados finales
	 */
	public HashSet<Estado> getEstadosFinales() {
		return estadosFinales;
	}
	/**
	 * Funcion para obtener el estado inicial de un automata
	 * @return Estado estadoinicial
	 */
	public Estado getEstadoInicial() {
		return estadoInicial;
	}
	
}	