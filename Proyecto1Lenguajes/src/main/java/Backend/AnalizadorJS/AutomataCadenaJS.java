/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
public class AutomataCadenaJS {
    private Estado estadoActual;

    public AutomataCadenaJS() {
        estadoActual = Estado.Q0;
    }

    public boolean esCadenaValida(String token) {
        estadoActual = Estado.Q0; // Reiniciar al estado inicial
        char delimitador = '\0'; // Para almacenar el delimitador de la cadena (' o ")

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == '\'' || c == '"') {
                        delimitador = c;
                        estadoActual = Estado.Q1;
                    } else {
                        return false; // No es una cadena válida
                    }
                    break;
                case Q1:
                    if (c != delimitador) {
                        estadoActual = Estado.Q1; // Permanecer en Q1
                    } else {
                        estadoActual = Estado.QF; // Estado de aceptación
                    }
                    break;
                case QF:
                    return false; // No debería haber más caracteres después de la comilla de cierre
                default:
                    return false; 
            }
        }

        return estadoActual == Estado.QF;
    }

    private enum Estado {
        Q0, Q1, QF
    }

}

