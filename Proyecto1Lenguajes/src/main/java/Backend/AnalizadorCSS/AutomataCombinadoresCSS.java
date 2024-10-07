/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorCSS;

/**
 *
 * @author herson
 */
public class AutomataCombinadoresCSS {
    private Estado estadoActual;

    public AutomataCombinadoresCSS() {
        estadoActual = Estado.Q0;
    }

    public boolean esCombinadorValido(String token) {
        estadoActual = Estado.Q0; // Reiniciar al estado inicial

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == '>' || c == '+' || c == '~') {
                        estadoActual = Estado.Q1;
                    } else if (Character.isWhitespace(c)) {
                        estadoActual = Estado.Q2;
                    } else {
                        return false; // No es un combinador válido
                    }
                    break;
                case Q1:
                    return token.length() == 1; // Combinadores de un solo carácter
                case Q2:
                    if (Character.isWhitespace(c)) {
                        estadoActual = Estado.Q2; // Permanecer en Q2
                    } else {
                        return false; // No es un combinador válido
                    }
                    break;
                default:
                    return false; // Estado no reconocido
            }
        }

        // Verificar si el token completo es un combinador válido
        if (estadoActual == Estado.Q1 || estadoActual == Estado.Q2) {
            estadoActual = Estado.QF; // Estado de aceptación
            return true;
        }

        return false;
    }

    private enum Estado {
        Q0, Q1, Q2, QF
    }
}

