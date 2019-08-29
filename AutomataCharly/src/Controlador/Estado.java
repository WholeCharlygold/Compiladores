package Controlador;

import java.util.LinkedList;

/**
 * Clase que nos sirve para modelar un estado de un automata
 */
public class Estado {
	private static long ID_ESTADOS = 0L;
	private int token;
	private boolean estadoInicial;
	private boolean estadoFinal;
	private LinkedList<Transicion> transiciones;

    /**
     *
     */
    public long id;

	/**
	 * Crea un nuevo estado para un automata, el id se asigna de manera automatica
	 * por el programa
	 * 
	 * @param estadoInicial - Indica si el estado es un estado inicial
	 * @param estadoFinal   - Indica si el estado es un estado de aceptacion o final
	 * @param transiciones  - Lista con las transiciones que presenta el estado
	 */
	public Estado(boolean estadoInicial, boolean estadoFinal, LinkedList<Transicion> transiciones) {
		super();
		this.estadoInicial = estadoInicial;
		this.estadoFinal = estadoFinal;
		this.transiciones = transiciones;
		this.id = ID_ESTADOS;
		ID_ESTADOS++;
	}

	/**
	 * Indica si es un Estado Inicial
	 * 
	 * @return true si el estado es un estado inicial
	 */
	public boolean isEstadoInicial() {
		return estadoInicial;
	}

	/**
	 * Estable o quita la propiedad estadoInicial
	 * 
	 * @param estadoInicial - true o false
	 */
	public void setEstadoInicial(boolean estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	/**
	 * Establece o quita la propiedad estadoFinal
	 * 
	 * @param estadoFinal - true o false
	 */
	public void setEstadoFinal(boolean estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	/**
	 * Indica si un estado es Estado Final
	 * 
	 * @return true si el estado es un estadoFinal
	 */
	public boolean isEstadoFinal() {
		return estadoFinal;
	}

	/**
	 * Da la lista de transiciones del estado
	 * 
	 * @return transiciones
	 */
	public LinkedList<Transicion> getTransiciones() {
		return transiciones;
	}

	/**
	 * Establece transiciones al estado
	 * 
	 * @param transiciones - Transiciones que se quieren establecer al estado
	 */
	public void setTransiciones(LinkedList<Transicion> transiciones) {
		this.transiciones = transiciones;
	}

	/**
	 * Da el id del estado
	 * 
	 * @return id del estado
	 */
	public long getId() {
		return id;
	}

	/**
	 * Imprime en consola las transiciones del estado
	 * 
	 * 
	 */
	public void displayTransicion() {

		for (Transicion t : this.getTransiciones()) {
			System.out.println(this.id + "--" + t.getCaracterDeTransicion() + "->" + t.getEstadoSiguiente().getId());
			;
		}

	}
	/**
	 * Funcion para obtener el token de un estado
	 * @return Token del estado
	 */
	public int getToken(){
		return this.token;
	}
	/**
	 * Funcion para configirar el token a un estado
	 * @param token - Token para el estado
	 */
	public void setToken(int token){
		this.token=token;
	}

}