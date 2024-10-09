/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
import java.util.HashMap;
import java.util.Map;

public class AutomataSimbolosJS {
    
    private enum Estado {
        Q0, QF, ERROR
    }

    private Estado estadoActual;
    private Map<Character, String> simbolosValidos;

    public AutomataSimbolosJS() {
        this.estadoActual = Estado.Q0;
        this.simbolosValidos = new HashMap<>();
        inicializarSimbolosValidos();
    }

    private void inicializarSimbolosValidos() {
        simbolosValidos.put('(', "Paréntesis");
        simbolosValidos.put(')', "Paréntesis");
        simbolosValidos.put('[', "Corchete");
        simbolosValidos.put(']', "Corchete");
        simbolosValidos.put('{', "Llave");
        simbolosValidos.put('}', "Llave");
        simbolosValidos.put('=', "Asignación");
        simbolosValidos.put(';', "Punto y Coma");
        simbolosValidos.put(',', "Coma");
        simbolosValidos.put('.', "Punto");
        simbolosValidos.put(':', "Dos Puntos");
    }

    public boolean esSimboloValido(String token) {
        estadoActual = Estado.Q0;

        for (char c : token.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (simbolosValidos.containsKey(c)) {
                        estadoActual = Estado.QF;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case QF:
                    estadoActual = Estado.ERROR; // No debería haber más caracteres después del símbolo
                    break;
                default:
                    estadoActual = Estado.ERROR;
                    break;
            }
        }

        return estadoActual == Estado.QF;
    }

    public String obtenerTipoSimbolo(String token) {
        if (esSimboloValido(token)) {
            return simbolosValidos.get(token.charAt(0));
        }
        return "Desconocido";
    }

}


