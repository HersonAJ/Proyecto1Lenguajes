/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorHTML;


import Backend.Token;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author herson
 */
public class AutomataPalabrasReservadasHTML {
    private Set<String> palabrasReservadas;
    private Estado estadoActual;

    public AutomataPalabrasReservadasHTML() {
        palabrasReservadas = new HashSet<>();
        inicializarPalabrasReservadas();
        estadoActual = Estado.Q0;
    }

    private void inicializarPalabrasReservadas() {
        // Añadir todas las palabras reservadas de HTML
        palabrasReservadas.add("class");
        palabrasReservadas.add("href");
        palabrasReservadas.add("onClick");
        palabrasReservadas.add("id");
        palabrasReservadas.add("style");
        palabrasReservadas.add("type");
        palabrasReservadas.add("placeholder");
        palabrasReservadas.add("required");
        palabrasReservadas.add("name");
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
            return new Token(token, "Palabra Reservada", "HTML", "Palabra Reservada", fila, columna);
        }

        return null; // No es una palabra reservada
    }

    private enum Estado {
        Q0, Q1, QF, ERROR
    }
}
