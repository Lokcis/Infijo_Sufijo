package main;
import arrays.*;
import java.util.Scanner;
/**
 *
 * @author lokci
 */
public class Principal {
    
    ArrayQueue<String> queueIn, queueOut;
    ArrayBag<String> bag;
    ArrayStack<String> stack;
    
    public Principal(){
        queueIn = new ArrayQueue<>();
        queueOut = new ArrayQueue<>();
        bag = new ArrayBag<>();
        stack = new ArrayStack<>();
    }
    
    public static void main(String[] args) {
        Principal main;
        String ecu;
        Scanner in = new Scanner(System.in);
        ecu = in.next();
        while(!ecu.isEmpty()){
            
        }
    }
    
}
