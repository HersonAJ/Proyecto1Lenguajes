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

public class AutomataReglasCSS {
    private Set<String> reglasValidas;
    private Estado estadoActual;

    public AutomataReglasCSS() {
        reglasValidas = new HashSet<>();
        inicializarReglasValidas();
        estadoActual = Estado.Q0;
    }

    private void inicializarReglasValidas() {
        reglasValidas.add("color");
        reglasValidas.add("background-color");
        reglasValidas.add("background");
        reglasValidas.add("font-size");
        reglasValidas.add("font-weight");
        reglasValidas.add("font-family");
        reglasValidas.add("font-align");
        reglasValidas.add("width");
        reglasValidas.add("height");
        reglasValidas.add("min-width");
        reglasValidas.add("min-height");
        reglasValidas.add("max-width");
        reglasValidas.add("max-height");
        reglasValidas.add("display");
        reglasValidas.add("inline");
        reglasValidas.add("block");
        reglasValidas.add("inline-block");
        reglasValidas.add("flex");
        reglasValidas.add("grid");
        reglasValidas.add("none");
        reglasValidas.add("margin");
        reglasValidas.add("border");
        reglasValidas.add("padding");
        reglasValidas.add("content");
        reglasValidas.add("border-color");
        reglasValidas.add("border-style");
        reglasValidas.add("border-width");
        reglasValidas.add("border-top");
        reglasValidas.add("border-bottom");
        reglasValidas.add("border-left");
        reglasValidas.add("border-right");
        reglasValidas.add("box-sizing");
        reglasValidas.add("border-box");
        reglasValidas.add("position");
        reglasValidas.add("static");
        reglasValidas.add("relative");
        reglasValidas.add("absolute");
        reglasValidas.add("sticky");
        reglasValidas.add("fixed");
        reglasValidas.add("top");
        reglasValidas.add("bottom");
        reglasValidas.add("left");
        reglasValidas.add("right");
        reglasValidas.add("z-index");
        reglasValidas.add("justify-content");
        reglasValidas.add("align-items");
        reglasValidas.add("border-radius");
        reglasValidas.add("auto");
        reglasValidas.add("float");
        reglasValidas.add("list-style");
        reglasValidas.add("text-align");
        reglasValidas.add("box-shadow");
    }

    public boolean esReglaValida(String token) {
        estadoActual = Estado.Q0; // Reiniciar al estado inicial

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (Character.isLetter(c)) {
                        estadoActual = Estado.Q1;
                    } else {
                        return false; // No es una regla válida
                    }
                    break;
                case Q1:
                    if (Character.isLetterOrDigit(c) || c == '-' || c == '_') {
                        estadoActual = Estado.Q1; // Permanecer en Q1
                    } else {
                        return false; // No es una regla válida
                    }
                    break;
                default:
                    return false; // Estado no reconocido
            }
        }

        // Verificar si el token completo es una regla válida
        if (estadoActual == Estado.Q1 && reglasValidas.contains(token)) {
            estadoActual = Estado.QF; // Estado de aceptación
            return true;
        }

        return false;
    }

    private enum Estado {
        Q0, Q1, QF
    }
}

