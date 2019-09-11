package Controlador;

import java.io.Serializable;

/**
 *
 * @author Maria Mireya
 */
public class Transicion implements Serializable{

    private Estado estadoSiguiente;
    private boolean epsilon;
    private Character caracterDeTransicion;
    private Character caracter_Inicial;
    private Character caracter_Final;

    /**
     * Construye una transicion con un estado siguiente y un caracter.
     *
     * @param caracterDeTransicion - Caracter que une al estado siguiente
     * @param estadoSiguiente - Estado que le sigue al estado que se le esta
     * contruyendo la transicion
     */
    public Transicion(Character caracterDeTransicion, Estado estadoSiguiente) {
        super();
        this.epsilon = false;
        this.estadoSiguiente = estadoSiguiente;
        this.caracterDeTransicion = caracterDeTransicion;
        this.caracter_Final = caracterDeTransicion;
        this.caracter_Inicial = caracterDeTransicion;
    }

    /**
     * Construye una transicion con un rango de caracteres de [a-b]
     *
     * @param caracter_Inicial - Caracter a
     * @param caracter_Final - Caracter b
     * @param estadoSiguiente - Estado que le sigue al estado que se le esta
     * contruyendo la transicion
     */
    public Transicion(Character caracter_Inicial, Character caracter_Final, Estado estadoSiguiente) {
        super();
        this.epsilon = false;
        this.estadoSiguiente = estadoSiguiente;
        this.caracter_Final = caracter_Final;
        this.caracter_Inicial = caracter_Inicial;
        if (caracter_Inicial == 'A' && caracter_Final == 'Z') {
            char resultado = 'M';
           this.caracterDeTransicion=resultado;
        }
        if (caracter_Inicial == 'a' && caracter_Final == 'z') {
            char resultado = 'm';
            this.caracterDeTransicion=resultado;
        }
        if (caracter_Inicial == '0' && caracter_Final == '9') {
            char resultado = 'D';
           this.caracterDeTransicion=resultado;
        }
       // this.caracterDeTransicion = 'o';
    }

    /**
     * Este contructor nos ayuda a crear una transicion epsilon a otro estado
     *
     * @param estadoSiguiente - Estado al que se llegara con la transcion
     */
    public Transicion(Estado estadoSiguiente) {
        super();
        this.epsilon = true;
        this.estadoSiguiente = estadoSiguiente;
    }

    /**
     * Funcion para obtener el Estado siguiente de la transicion
     *
     * @return Estado siguiente
     */
    public Estado getEstadoSiguiente() {
        return estadoSiguiente;
    }

    /**
     * Funcion para obtener el caracter de la transicion
     *
     * @return Caracter de la Transicion
     */
    public Character getCaracterDeTransicion() {
        return caracterDeTransicion;
    }

    /**
     * Funcion para verificar si una transicion tiene como caracter de
     * transicion Epsilon
     *
     * @return true si es Epsilon
     */
    public boolean isEpsilon() {
        return epsilon;
    }

    public Character getCaracterDeTransicionInicial() {
        return this.caracter_Inicial;
    }

    public Character getCaracterDeTransicionFinal() {
        return this.caracter_Final;
    }
}
