/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.HTML.AnalizadorHTML;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */
public class AnalizadorCodigoFuente {
    private String analizadorActual;
    private List<Token> tokensValidados;
    private AnalizadorHTML analizadorHTML;

    public AnalizadorCodigoFuente() {
        tokensValidados = new ArrayList<>();
        analizadorHTML = new AnalizadorHTML();
    }

    public void analizarCodigo(String codigo) {
        String[] lineas = codigo.split("\n");
        int totalTokens = 0; // Contador de tokens totales
        int fila = 1;

        for (String linea : lineas) {
            if (linea.startsWith(">>[")) {
                cambiarAnalizador(linea);
                totalTokens++; // Contar el token de estado
                System.out.println("Token de estado: " + linea);
            } else if (analizadorActual != null) {
                System.out.println("Analizando con: " + analizadorActual);
                if ("HTML".equals(analizadorActual)) {
                    List<String> tokens = tokenizarLinea(linea);
                    List<Token> tokensValidadosLinea = analizadorHTML.analizarTokens(tokens, fila);
                    tokensValidados.addAll(tokensValidadosLinea);
                }
                // condiciones faltantes para css y javascript
            }
            fila++;
        }

        // Imprimir el conteo total de tokens generados
        System.out.println("Total de tokens generados: " + totalTokens);
    }

    private void cambiarAnalizador(String token) {
        switch (token) {
            case ">>[html]":
                analizadorActual = "HTML";
                break;
            case ">>[css]":
                analizadorActual = "CSS";
                break;
            case ">>[js]":
                analizadorActual = "JavaScript";
                break;
            default:
                analizadorActual = null;
                System.out.println("Token desconocido: " + token);
                break;
        }
        if (analizadorActual != null) {
            System.out.println("Token de estado encontrado: " + analizadorActual);
        }
    }

    private List<String> tokenizarLinea(String linea) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        boolean dentroDeEtiqueta = false;
        boolean dentroDeComillas = false;

        for (char c : linea.toCharArray()) {
            if (c == '<') {
                dentroDeEtiqueta = true;
                if (token.length() > 0) {
                    tokens.add(token.toString().trim());  // Agregar cualquier texto antes de la etiqueta
                    token.setLength(0);
                }
                token.append(c);  // Empezar la etiqueta
            } else if (c == '>') {
                dentroDeEtiqueta = false;
                token.append(c);  // Completar la etiqueta
                tokens.add(token.toString().trim());  // Agregar la etiqueta completa
                token.setLength(0);
            } else if (dentroDeEtiqueta) {
                if (c == '"') {
                    dentroDeComillas = !dentroDeComillas;  // Alternar dentro/fuera de comillas
                }
                if (!dentroDeComillas && (c == ' ' || c == '=')) {
                    if (token.length() > 0) {
                        tokens.add(token.toString().trim());  // Agregar el atributo o valor
                        token.setLength(0);
                    }
                    tokens.add(String.valueOf(c));  // Agregar el espacio o signo igual como token
                } else {
                    token.append(c);  // Seguir construyendo el atributo o valor
                }
            } else {
                token.append(c);  // Agregar cualquier texto fuera de las etiquetas
            }
        }

        if (token.length() > 0) {
            tokens.add(token.toString().trim());  // Agregar cualquier texto restante
        }

        return tokens;
    }

    public List<Token> getTokensValidados() {
        return tokensValidados;
    }
}
