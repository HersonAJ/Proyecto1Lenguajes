/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
import Backend.Token;
import java.util.HashSet;
import java.util.Set;

public class AutomataPalabrasReservadasJS {
    private Set<String> palabrasReservadas;
    private Estado estadoActual;

    public AutomataPalabrasReservadasJS() {
        palabrasReservadas = new HashSet<>();
        inicializarPalabrasReservadas();
        estadoActual = Estado.Q0;
    }

    private void inicializarPalabrasReservadas() {
        // Añadir todas las palabras reservadas
        palabrasReservadas.add("function");
        palabrasReservadas.add("const");
        palabrasReservadas.add("let");
        palabrasReservadas.add("document");
        palabrasReservadas.add("event");
        palabrasReservadas.add("alert");
        palabrasReservadas.add("for");
        palabrasReservadas.add("while");
        palabrasReservadas.add("if");
        palabrasReservadas.add("else");
        palabrasReservadas.add("return");
        palabrasReservadas.add("console.log");
        palabrasReservadas.add("null");
        palabrasReservadas.add("var");
        palabrasReservadas.add("switch");
        palabrasReservadas.add("case");
        palabrasReservadas.add("break");
        palabrasReservadas.add("default");
        palabrasReservadas.add("try");
        palabrasReservadas.add("catch");
        palabrasReservadas.add("finally");
        palabrasReservadas.add("throw");
        palabrasReservadas.add("new");
        palabrasReservadas.add("typeof");
        palabrasReservadas.add("instanceof");
        palabrasReservadas.add("this");
        palabrasReservadas.add("super");
        palabrasReservadas.add("class");
        palabrasReservadas.add("extends");
        palabrasReservadas.add("import");
        palabrasReservadas.add("export");
        palabrasReservadas.add("from");
        palabrasReservadas.add("as");
        palabrasReservadas.add("async");
        palabrasReservadas.add("await");
        palabrasReservadas.add("yield");
        palabrasReservadas.add("delete");
        palabrasReservadas.add("in");
        palabrasReservadas.add("of");
        palabrasReservadas.add("with");
        palabrasReservadas.add("void");
        palabrasReservadas.add("true");
        palabrasReservadas.add("false");
    }

    public boolean esPalabraReservada(String token) {
        return palabrasReservadas.contains(token);
    }

    public Token procesarToken(String token, int fila, int columna) {
        estadoActual = Estado.Q0; // Reiniciar al estado inicial
        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (Character.isLetter(c)) {
                        estadoActual = Estado.Q1;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q1:
                    if (Character.isLetterOrDigit(c)) {
                        estadoActual = Estado.Q1;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                default:
                    estadoActual = Estado.ERROR;
                    break;
            }
        }

        if (estadoActual == Estado.Q1 && esPalabraReservada(token)) {
            estadoActual = Estado.QF;
            return new Token(token, "Palabra Reservada", "JavaScript", "Palabra Reservada", fila, columna);
        }

        return null; // No es una palabra reservada
    }


    private enum Estado {
        Q0, Q1, QF, ERROR
    }
}

