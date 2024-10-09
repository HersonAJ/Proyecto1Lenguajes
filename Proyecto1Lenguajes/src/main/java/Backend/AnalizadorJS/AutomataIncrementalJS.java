/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
import java.util.List;

public class AutomataIncrementalJS {
    
    private enum Estado {
        Q0, Q1, QF, ERROR
    }

    private Estado estadoActual;

    public AutomataIncrementalJS() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esIncrementalValido(String token) {
        estadoActual = Estado.Q0;

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == '+' || c == '-') {
                        estadoActual = Estado.Q1;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q1:
                    if (c == '+' || c == '-') {
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

    public boolean unirYValidarTokens(List<String> tokens) {
        StringBuilder tokenCompleto = new StringBuilder();
        for (String token : tokens) {
            tokenCompleto.append(token);
        }
        return esIncrementalValido(tokenCompleto.toString());
    }

}


