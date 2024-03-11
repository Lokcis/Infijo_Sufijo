package methods;

/**
 *
 * @author lokci
 */
public class History {
    private String infix;
    private String sufix;
    private double answer;
        
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
