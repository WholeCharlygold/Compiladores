import java.util.LinkedList;

/**
 * Clase que nos sirve para modelar un estado de un automata
 * */
public class Estado {
	private static long ID_ESTADOS = 0L;
	
	private boolean estadoInicial;
	private boolean estadoFinal;
	private LinkedList<Transicion> transiciones;
	public long id;
	
	/**
	 * Crea un nuevo estado para un automata, el id se asigna de manera automatica por el programa
	 * 
	 * @param boolean estadoInicial - Indica si el estado es un estado inicial
	 * @param boolean estadoFinal - Indica si el estado es un estado de aceptacion o final
	 * @LinkedList<Transicion> transiciones - Lista con las transiciones que presenta el estado
	 * */
	public Estado(boolean estadoInicial, boolean estadoFinal, LinkedList<Transicion> transiciones) {
		super();
		this.estadoInicial = estadoInicial;
		this.estadoFinal = estadoFinal;
		this.transiciones = transiciones;
		this.id = ID_ESTADOS;
		ID_ESTADOS++;
	}
	
	public boolean isEstadoInicial() {
		return estadoInicial;
	}
	
	public void setEstadoInicial(boolean estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public void setEstadoFinal(boolean estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public boolean isEstadoFinal() {
		return estadoFinal;
	}
	public LinkedList<Transicion> getTransiciones() {
		return transiciones;
	}
	public void setTransiciones(LinkedList<Transicion> transiciones){
		this.transiciones=transiciones;
	}
	
	public long getId() {
		return id;
	}
	public void displayTransicion(){
		
		for(Transicion t:this.getTransiciones()){
			System.out.println(this.id+"--"+t.getCaracterDeTransicion()+"->"+t.getEstadoSiguiente().getId());;
		}
	
	}

	

}