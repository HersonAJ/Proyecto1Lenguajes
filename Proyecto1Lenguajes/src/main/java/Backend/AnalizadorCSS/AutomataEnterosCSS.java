/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorCSS;

/**
 *
 * @author herson
 */
public class AutomataEnterosCSS {
    private enum Estado {
        Q0, Q1, QF
    }

    private Estado estadoActual;

    public AutomataEnterosCSS() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esEnteroValido(String token) {
        estadoActual = Estado.Q0;

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (Character.isDigit(c)) {
                        estadoActual = Estado.Q1;
                    } else {
                        return false;
                    }
                    break;
                case Q1:
                    if (Character.isDigit(c)) {
                        estadoActual = Estado.Q1;
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }

        return estadoActual == Estado.Q1;
    }
}
