package methods;

/**
 *
 * @author lokci
 */
public class History {
    private String infix;
    private String sufix;
    private int answer;
        
    public History(String newInfix, String newSufix, int newAnswer){
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

    public int getAnswer() {
        return answer;
    }
            
}
