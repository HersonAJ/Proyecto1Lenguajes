/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
public class AutomataDecimalesJS {
    
    private enum Estado {
        Q0, Q1, Q2, QF, ERROR
    }

    private Estado estadoActual;

    public AutomataDecimalesJS() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esDecimalValido(String token) {
        estadoActual = Estado.Q0;

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (Character.isDigit(c)) {
                        estadoActual = Estado.Q1;
                    } else if (c == '.') {
                        estadoActual = Estado.Q2;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q1:
                    if (Character.isDigit(c)) {
                        estadoActual = Estado.Q1;
                    } else if (c == '.') {
                        estadoActual = Estado.Q2;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q2:
                    if (Character.isDigit(c)) {
                        estadoActual = Estado.QF;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case QF:
                    if (Character.isDigit(c)) {
                        estadoActual = Estado.QF;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                default:
                    estadoActual = Estado.ERROR;
                    break;
            }
        }

        return estadoActual == Estado.QF;
    }

}

