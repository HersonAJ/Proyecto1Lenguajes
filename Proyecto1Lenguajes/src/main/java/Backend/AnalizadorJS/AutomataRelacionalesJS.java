/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

import java.util.List;

/**
 *
 * @author herson
 */

public class AutomataRelacionalesJS {
    
    private enum Estado {
        Q0, Q1, QF, ERROR
    }

    private Estado estadoActual;

    public AutomataRelacionalesJS() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esRelacionalValido(String token) {
        estadoActual = Estado.Q0;

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == '=') {
                        estadoActual = Estado.Q1;
                    } else if (c == '<' || c == '>') {
                        estadoActual = Estado.Q1;
                    } else if (c == '!') {
                        estadoActual = Estado.Q1;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q1:
                    if (c == '=') {
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

        // Validar operadores de un solo carácter (<, >)
        if (estadoActual == Estado.Q0 && (token.equals("<") || token.equals(">"))) {
            estadoActual = Estado.QF;
        }

        return estadoActual == Estado.QF;
    }

    public boolean unirYValidarTokens(List<String> tokens) {
        StringBuilder tokenCompleto = new StringBuilder();
        for (String token : tokens) {
            tokenCompleto.append(token);
        }
        return esRelacionalValido(tokenCompleto.toString());
    }

}


