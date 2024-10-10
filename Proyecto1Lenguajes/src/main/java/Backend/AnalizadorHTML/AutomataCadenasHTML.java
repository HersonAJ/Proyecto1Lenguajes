/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorHTML;

/**
 *
 * @author herson
 */
public class AutomataCadenasHTML {
    private Estado estadoActual;

    public AutomataCadenasHTML() {
        estadoActual = Estado.Q0;
    }

    public boolean esCadenaValida(String token) {
        estadoActual = Estado.Q0; // Reiniciar al estado inicial
        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == '"') {
                        estadoActual = Estado.Q1;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q1:
                    if (c != '"') {
                        estadoActual = Estado.Q1;
                    } else {
                        estadoActual = Estado.QF;
                    }
                    break;
                case QF:
                    estadoActual = Estado.ERROR;
                    break;
                default:
                    estadoActual = Estado.ERROR;
                    break;
            }
        }

        return estadoActual == Estado.QF;
    }

    private enum Estado {
        Q0, Q1, QF, ERROR
    }
}
