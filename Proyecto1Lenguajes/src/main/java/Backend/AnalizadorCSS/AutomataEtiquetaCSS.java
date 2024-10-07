/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorCSS;

/**
 *
 * @author herson
 */
import java.util.HashSet;
import java.util.Set;

public class AutomataEtiquetaCSS {
    private Set<String> etiquetasValidas;
    private Estado estadoActual;

    public AutomataEtiquetaCSS() {
        etiquetasValidas = new HashSet<>();
        inicializarEtiquetasValidas();
        estadoActual = Estado.Q0;
    }

    private void inicializarEtiquetasValidas() {
        etiquetasValidas.add("body");
        etiquetasValidas.add("header");
        etiquetasValidas.add("main");
        etiquetasValidas.add("nav");
        etiquetasValidas.add("aside");
        etiquetasValidas.add("div");
        etiquetasValidas.add("ul");
        etiquetasValidas.add("ol");
        etiquetasValidas.add("li");
        etiquetasValidas.add("a");
        etiquetasValidas.add("h1");
        etiquetasValidas.add("h2");
        etiquetasValidas.add("h3");
        etiquetasValidas.add("h4");
        etiquetasValidas.add("h5");
        etiquetasValidas.add("h6");
        etiquetasValidas.add("p");
        etiquetasValidas.add("span");
        etiquetasValidas.add("label");
        etiquetasValidas.add("textarea");
        etiquetasValidas.add("button");
        etiquetasValidas.add("section");
        etiquetasValidas.add("article");
        etiquetasValidas.add("footer");
    }

    public boolean esEtiquetaValida(String token) {
        estadoActual = Estado.Q0; // Reiniciar al estado inicial

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (Character.isLetter(c)) {
                        estadoActual = Estado.Q1;
                    } else {
                        return false; // No es una etiqueta válida
                    }
                    break;
                case Q1:
                    if (Character.isLetterOrDigit(c)) {
                        estadoActual = Estado.Q1; // Permanecer en Q1
                    } else {
                        return false; // No es una etiqueta válida
                    }
                    break;
                default:
                    return false; // Estado no reconocido
            }
        }

        // Verificar si el token completo es una etiqueta válida
        if (estadoActual == Estado.Q1 && etiquetasValidas.contains(token)) {
            estadoActual = Estado.QF; // Estado de aceptación
            return true;
        }

        return false;
    }

    private enum Estado {
        Q0, Q1, QF
    }
}

