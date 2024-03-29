package main;

import methods.ArrayQueue;
import methods.ArrayStack;
import methods.ArrayBag;
import methods.History;

import java.util.Scanner;

/**
 *
 * @author lokci
 */
public class Principal {

    ArrayQueue<String> queueIn;
    ArrayQueue<String> queueOut;
    ArrayStack<String> stack;
    ArrayBag<History> bag;

    /**
     * Inicializa las estructuras de datos
     */
    public Principal() {
        queueIn = new ArrayQueue<>();
        queueOut = new ArrayQueue<>();
        stack = new ArrayStack<>();
        bag = new ArrayBag<>();
    }

    /**
     * Main, no hay contexto, solo, main
     *
     * @param args
     */
    public static void main(String[] args) {
        Principal main = new Principal();
        String ecu, infix, sufix;
        String[] elements;
        double result, leftOperand, rightOperand = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("¡BIENVENIDO!");

        while (true) {
            System.out.println("Por favor, ingresa la ecuación infija a convertir a sufija (o introduce 'exit' para salir):");
            System.out.println("Por favor, escriba la ecuacion con espacios entre los números y los operadores. ");
            ecu = in.nextLine();
            if (ecu.equalsIgnoreCase("exit")) {
                break;
            }
            infix = ecu;
            sufix = "";

            try {

                if (!ecu.matches("(\\d+\\s\\D\\s\\d+)+")) {
                    System.out.println("Escriba la Ecuacion con espacios entre los números y los operadores. ");
                    continue; // Salta al inicio del bucle
                }

                elements = ecu.split("\\s+");
                for (String each : elements) {
                    main.queueIn.enqueue(each);
                }

                // Lógica de conversión de infijo a posfijo
                while (!main.queueIn.isEmpty()) {
                    String s = main.queueIn.dequeue();
                    if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
                        main.queueOut.enqueue(s); // Agregar dígitos a la cola de salida
                    } else if (s.equals("(")) {
                        main.stack.push(s);
                    } else if (s.equals(")")) {
                        while (!main.stack.isEmpty() && !main.stack.peek().equals("(")) {
                            main.queueOut.enqueue(main.stack.pop()); // Agregar operadores a la cola de salida
                        }
                        main.stack.pop(); // Descartar el paréntesis izquierdo
                    } else {
                        while (!main.stack.isEmpty() && !main.stack.peek().equals("(")
                                && !((s.equals("*") || s.equals("/")) && (main.stack.peek().equals("+") || main.stack.peek().equals("-")))) {
                            main.queueOut.enqueue(main.stack.pop()); // Agregar operadores a la cola de salida
                        }
                        main.stack.push(s);
                    }
                }

                while (!main.stack.isEmpty()) {
                    main.queueOut.enqueue(main.stack.pop()); // Agregar los operadores restantes a la cola de salida
                }

                // Imprimir la cola de salida (expresión posfija)
                System.out.println("Expresión posfija:");
                ArrayQueue<String> queueOutCopy = new ArrayQueue<>();
                while (!main.queueOut.isEmpty()) {
                    String element = main.queueOut.dequeue();
                    sufix += element;
                    System.out.print(element + " ");
                    queueOutCopy.enqueue(element);  // Guardar una copia de la cola de salida
                }
                System.out.println();

                main.queueOut = queueOutCopy;  // Actualizar queueOut con la copia

                // Evaluar la expresión posfija
                ArrayStack<Double> evaluation = new ArrayStack<>();
                while (!main.queueOut.isEmpty()) {
                    String s = main.queueOut.dequeue();
                    if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
                        evaluation.push(Double.valueOf(s));
                    } else {
                        rightOperand = evaluation.pop();
                        leftOperand = evaluation.pop();

                        switch (s) {
                            case "+":
                                evaluation.push(leftOperand + rightOperand);
                                break;
                            case "-":
                                evaluation.push(leftOperand - rightOperand);
                                break;
                            case "*":
                                evaluation.push(leftOperand * rightOperand);
                                break;
                            case "/":
                                if (rightOperand != 0) {
                                    evaluation.push(leftOperand / rightOperand);
                                } else {
                                    throw new UnsupportedOperationException("No se puede dividir por cero.");
                                }
                                break;
                        }
                    }
                }
                if (rightOperand != 0) {
                    result = evaluation.pop();
                    System.out.println("Resultado: " + result);
                    History history = new History(infix, sufix, result);
                    main.bag.add(history);
                    for (History h : main.bag) {
                        System.out.println(h);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
