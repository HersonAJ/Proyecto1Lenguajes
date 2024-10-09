/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
public class AutomataBooleanJS {
    
    private enum Estado {
        Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, QF, ERROR
    }

    private Estado estadoActual;

    public AutomataBooleanJS() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esBooleanValido(String token) {
        estadoActual = Estado.Q0;

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == 't') {
                        estadoActual = Estado.Q1;
                    } else if (c == 'f') {
                        estadoActual = Estado.Q5;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q1:
                    if (c == 'r') {
                        estadoActual = Estado.Q2;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q2:
                    if (c == 'u') {
                        estadoActual = Estado.Q3;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q3:
                    if (c == 'e') {
                        estadoActual = Estado.Q4;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q4:
                    estadoActual = Estado.QF;
                    break;
                case Q5:
                    if (c == 'a') {
                        estadoActual = Estado.Q6;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q6:
                    if (c == 'l') {
                        estadoActual = Estado.Q7;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q7:
                    if (c == 's') {
                        estadoActual = Estado.Q8;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q8:
                    if (c == 'e') {
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

