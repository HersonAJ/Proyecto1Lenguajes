/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorCSS;

/**
 *
 * @author herson
 */
public class AutomataIdCSS {
    private enum Estado {
        Q0, Q1, Q2, QF
    }

    private Estado estadoActual;

    public AutomataIdCSS() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esIdValido(String token) {
        estadoActual = Estado.Q0;

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == '#') {
                        estadoActual = Estado.Q1;
                    } else {
                        return false;
                    }
                    break;
                case Q1:
                    if (Character.isLowerCase(c)) {
                        estadoActual = Estado.Q2;
                    } else {
                        return false;
                    }
                    break;
                case Q2:
                    if (Character.isLowerCase(c) || Character.isDigit(c)) {
                        estadoActual = Estado.Q2;
                    } else if (c == '-') {
                        estadoActual = Estado.Q1;
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }

        return estadoActual == Estado.Q2;
    }
}

