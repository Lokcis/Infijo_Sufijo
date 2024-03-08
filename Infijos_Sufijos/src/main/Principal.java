package main;

import arrays.*;
import java.util.Scanner;

/**
 *
 * @author lokci
 */
public class Principal {

    ArrayQueue<String> queueIn;
    ArrayQueue<Integer> queueOut;
    ArrayBag<String> bag;
    ArrayStack<String> stack;

    public Principal() {
        queueIn = new ArrayQueue<>();
        queueOut = new ArrayQueue<>();
        bag = new ArrayBag<>();
        stack = new ArrayStack<>();
    }

    public static void main(String[] args) {
        Principal main;
        main = new Principal();
        String ecu = "-", each;
        char temp;
        Scanner in = new Scanner(System.in);
        System.out.println("""
                           ¡BIENVENIDO!
                           """);
        while (!ecu.isEmpty()) {
            System.out.println("""
                           Por favor, ingresa la ecuación infija a convertir a sufija.
                           """);
            ecu = in.next();
            for (int i = ecu.length() - 1; i >= 0; i--) {
                temp = ecu.charAt(i);
                each = String.valueOf(temp);
                main.queueIn.enqueue(each);
            }
            for (String s : main.queueIn) {
                if (s.equals("(")) {
                } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                    main.stack.push(s);
                } else if (s.equals(")")) {
                    String operator = main.stack.pop();
                    double operand = main.queueOut.dequeue();
                    if (operator.equals("+")) {
                        main.bag.add(s);
                        operand = main.queueOut.dequeue() + operand;
                    }
                    if (operator.equals("-")) {
                        main.bag.add(s);
                        operand = main.queueOut.dequeue() - operand;
                    }
                    if (operator.equals("*")) {
                        main.bag.add(s);
                        operand = main.queueOut.dequeue() * operand;
                    }
                    if (operator.equals("/")) {                        
                        main.bag.add(s);
                        operand = main.queueOut.dequeue() / operand;
                    }
                } else {
                    main.queueOut.enqueue(Integer.parseInt(s));
                    main.bag.add(s);
                }
            }
            for(String s : main.bag){
                System.out.println(s);
            }
        }
    }
}
