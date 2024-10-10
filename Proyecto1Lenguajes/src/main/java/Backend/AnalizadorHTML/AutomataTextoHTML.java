/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorHTML;

/**
 *
 * @author herson
 */
public class AutomataTextoHTML {
    private Estado estadoActual;

    public AutomataTextoHTML() {
        estadoActual = Estado.Q0;
    }

    public boolean esTextoValido(String token) {
        estadoActual = Estado.Q0; // Reiniciar al estado inicial
        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (Character.isLetterOrDigit(c) || Character.isWhitespace(c) || esCaracterEspecial(c)) {
                        estadoActual = Estado.Q1;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q1:
                    if (Character.isLetterOrDigit(c) || Character.isWhitespace(c) || esCaracterEspecial(c)) {
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

        return estadoActual == Estado.Q1;
    }

    private boolean esCaracterEspecial(char c) {
        //otros caracteres
        return "!@#$%^&*()_+-=[]{}|;':\",./<>?".indexOf(c) >= 0;
    }

    private enum Estado {
        Q0, Q1, ERROR
    }

}
