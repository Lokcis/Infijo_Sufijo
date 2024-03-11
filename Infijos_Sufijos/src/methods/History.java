package methods;

/**
 *
 * @author lokci
 */
public class History {
    private final String infix;
    private final String sufix;
    private final double answer;
        
    public History(String newInfix, String newSufix, double newAnswer){
        infix = newInfix;
        sufix = newSufix;
        answer = newAnswer;
    }

    @Override
    public String toString() {
        return "Historial: " + "infijo = " + infix + ", sufijo = " + sufix + ", respuesta = " + answer + '.';
    }
    
    
}
