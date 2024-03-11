package methods;

/**
 *
 * @author lokci
 */
public class History {

    private final String infix;
    private final String sufix;
    private final double answer;

    /**
     * Contructor de los objetos de tipo historia
     *
     * @param newInfix
     * @param newSufix
     * @param newAnswer
     */
    public History(String newInfix, String newSufix, double newAnswer) {
        infix = newInfix;
        sufix = newSufix;
        answer = newAnswer;
    }

    /**
     *Convierte el objeto en texto
     * 
     * @return
     */
    @Override
    public String toString() {
        return "Historial: " + "infijo = " + infix + ", sufijo = " + sufix + ", respuesta = " + answer + '.';
    }

}
