

public class Transicion {
    private Estado estadoSiguiente;
    private boolean epsilon;
    private Character caracterDeTransicion;

    public Transicion(Character caracterDeTransicion, Estado estadoSiguiente) {
		super();
		this.epsilon = false;
        this.estadoSiguiente = estadoSiguiente;
        this.caracterDeTransicion=caracterDeTransicion;
	}

   /**
	 * Este contructor nos ayuda a crear una transicion epsilon a otro estado
	 * @param Estado estadoSiguiente - Estado al que se llegara con la transcion
	 * */
	public Transicion(Estado estadoSiguiente) {
		super();
		this.epsilon = true;
		this.estadoSiguiente = estadoSiguiente;
	}
	
	public Estado getEstadoSiguiente() {
		return estadoSiguiente;
    }
    public Character getCaracterDeTransicion() {
		return caracterDeTransicion;
	}
	
	public boolean isEpsilon() {
		return epsilon;
	}
}