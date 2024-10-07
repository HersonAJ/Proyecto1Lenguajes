/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorCSS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author herson
 */

public class AutomataOtrosCSS {
    private enum Estado {
        Q0, Q1, QF
    }

    private Estado estadoActual;

    private static final Set<String> ELEMENTOS_VALIDOS = new HashSet<>(Arrays.asList(
        "px", "%", "rem", "em", "vw", "vh", ":hover", ":active", ":not()", ":nth-child()",
        "odd", "even", "::before", "::after", ":", ";", ",", "(", ")", "{", "}", "'[A-Za-z]'"
    ));

    public AutomataOtrosCSS() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esElementoValido(String token) {
        estadoActual = Estado.Q0;

        if (ELEMENTOS_VALIDOS.contains(token)) {
            estadoActual = Estado.QF;
        } else if (esNumeroPx(token)) {
            estadoActual = Estado.QF;
        }

        return estadoActual == Estado.QF;
    }

    private boolean esNumeroPx(String token) {
        if (token.endsWith("px")) {
            String numeroParte = token.substring(0, token.length() - 2);
            return esNumero(numeroParte);
        }
        return false;
    }

    private boolean esNumero(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
