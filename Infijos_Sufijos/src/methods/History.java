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

    public String getInfix() {
        return infix;
    }

    public String getSufix() {
        return sufix;
    }

    public double getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Historial: " + "infijo = " + infix + ", sufijo = " + sufix + ", respuesta = " + answer + '.';
    }
    
    
}
