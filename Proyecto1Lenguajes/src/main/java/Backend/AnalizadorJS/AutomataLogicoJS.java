/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
public class AutomataLogicoJS {
    
    private enum Estado {
        Q0, Q1, QF, ERROR
    }

    private Estado estadoActual;

    public AutomataLogicoJS() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esLogicoValido(String token) {
        estadoActual = Estado.Q0;

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == '!') {
                        estadoActual = Estado.QF;
                    } else if (c == '&') {
                        estadoActual = Estado.Q1;
                    } else if (c == '|') {
                        estadoActual = Estado.Q1;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q1:
                    if (c == '&' || c == '|') {
                        estadoActual = Estado.QF;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case QF:
                    estadoActual = Estado.ERROR; // No debería haber más caracteres después del operador
                    break;
                default:
                    estadoActual = Estado.ERROR;
                    break;
            }
        }

        return estadoActual == Estado.QF;
    }

}
