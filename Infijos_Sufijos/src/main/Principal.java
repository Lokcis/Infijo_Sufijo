package main;

import arrays.*;

import java.util.Scanner;

public class Principal {

    ArrayQueue<String> queueIn;
    ArrayQueue<String> queueOut;
    ArrayStack<String> stack;

    public Principal() {
        queueIn = new ArrayQueue<>();
        queueOut = new ArrayQueue<>();
        stack = new ArrayStack<>();
    }

    public static void main(String[] args) {
        Principal main = new Principal();
        String ecu;
        Scanner in = new Scanner(System.in);
        System.out.println("¡BIENVENIDO!");

        while (true) {
            System.out.println("Por favor, ingresa la ecuación infija a convertir a sufija (o introduce 'exit' para salir):");
            ecu = in.nextLine();

            if (ecu.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                if (ecu.contains(" ")) {
                    throw new Exception("Por favor, ingresa la ecuación sin espacios.");
                }
                
                String[] elements = ecu.split("");
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
                while (!main.queueOut.isEmpty()) {
                    System.out.print(main.queueOut.dequeue() + " ");
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
