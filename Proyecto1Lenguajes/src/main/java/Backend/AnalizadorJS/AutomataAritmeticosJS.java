/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
public class AutomataAritmeticosJS {
    
    private enum Estado {
        Q0, QF, ERROR
    }

    private Estado estadoActual;

    public AutomataAritmeticosJS() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esAritmeticoValido(String token) {
        estadoActual = Estado.Q0;

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == '+' || c == '-' || c == '*' || c == '/') {
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

