/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.HTML;

import Backend.Token;

/**
 *
 * @author herson
 */
public class AutomataPalabrasReservadasHTML {

    private enum Estado {
        Q0, QF
    }

    private Estado estadoActual;

    // Método para validar si una cadena es una palabra reservada de HTML
    public Token validarPalabraReservada(String cadena, int fila, int columna) {
        if (cadena.isEmpty()) {
            return null; // La cadena vacía no es aceptada
        }

        estadoActual = Estado.Q0; // Estado inicial

        switch (cadena) {
            case "class":
            case "=":
            case "href":
            case "onClick":
            case "id":
            case "style":
            case "type":
            case "placeholder":
            case "required":
            case "name":
                estadoActual = Estado.QF;
                break;
            default:
                return null;
        }

        if (estadoActual == Estado.QF) {
            return new Token(cadena, "Palabra Reservada", "HTML", "Palabra_Reservada", fila, columna);
        } else {
            return null;
        }
    }
}