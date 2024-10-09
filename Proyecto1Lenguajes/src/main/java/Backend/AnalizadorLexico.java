/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.AnalizadorCSS.AnalizadorCSS;
import Backend.AnalizadorJS.AnalizadorJS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */
public class AnalizadorLexico {
    private String codigoFuente;
    private int posicionActual;
    private int lineaActual;
    private int columnaActual;
    private List<Token> tokens;
    private List<String> errores;
    private String analizadorActual;
    private List<Token> tokensValidados;
    private AnalizadorCSS analizadorCSS; // Instancia del analizador CSS
    private AnalizadorJS analizadorJS; // Instancia del analizador JS

    public AnalizadorLexico() {
        this.posicionActual = 0;
        this.lineaActual = 1;
        this.columnaActual = 1;
        this.tokens = new ArrayList<>();
        this.errores = new ArrayList<>();
        this.tokensValidados = new ArrayList<>();
        this.analizadorCSS = new AnalizadorCSS(); // Inicializar el analizador CSS
        this.analizadorJS = new AnalizadorJS(); // Inicializar el analizador JS
    }

    public void analizarCodigo(String codigo) {
        String[] lineas = codigo.split("\n");
        int fila = 1;

        for (String linea : lineas) {
            if (linea.trim().isEmpty()) {
                fila++;
                continue;
            }

            if (linea.startsWith(">>[")) {
                cambiarAnalizador(linea);
                System.out.println("Token de estado: " + linea);
            } else if (analizadorActual != null) {
                System.out.println("Analizando con: " + analizadorActual);
                List<String> tokens;
                if ("HTML".equals(analizadorActual)) {
                    tokens = tokenizarLineaHTML(linea);
                    // Aquí se llamaría al analizadorHTML para validar los tokens
                } else if ("CSS".equals(analizadorActual)) {
                    tokens = tokenizarCSS(linea);
                    System.out.println("Tokens generados: " + tokens); // Depuración
                    List<Token> tokensValidadosLinea = analizadorCSS.analizarTokens(tokens, fila);
                    tokensValidados.addAll(tokensValidadosLinea);
                } else if ("JavaScript".equals(analizadorActual)) {
                    tokens = tokenizarJS(linea);
                    System.out.println("Tokens generados: " + tokens);
                    List<Token> tokensValidadosLinea = analizadorJS.analizarTokens(tokens, fila);
                    tokensValidados.addAll(tokensValidadosLinea);
                }
            }
            fila++;
        }
    }

    private void cambiarAnalizador(String linea) {
        if (linea.startsWith(">>[html]")) {
            analizadorActual = "HTML";
        } else if (linea.startsWith(">>[css]")) {
            analizadorActual = "CSS";
        } else if (linea.startsWith(">>[js]")) {
            analizadorActual = "JavaScript";
        }
    }

    private List<String> tokenizarLineaHTML(String linea) {
        // Implementación para tokenizar HTML
        return new ArrayList<>();
    }

    private List<String> tokenizarCSS(String linea) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (char c : linea.toCharArray()) {
            if (c == '{' || c == '}' || c == ':' || c == ';' || c == ' ' || c == '\n') {
                if (token.length() > 0) {
                    tokens.add(token.toString().trim());
                    token.setLength(0);
                }
                if (c != ' ' && c != '\n') {
                    tokens.add(String.valueOf(c));
                }
            } else {
                token.append(c);
            }
        }

        if (token.length() > 0) {
            tokens.add(token.toString().trim());
        }

        return tokens;
    }
    
    //agregando metodo para descomponer js
    private List<String> tokenizarJS(String linea) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        boolean inString = false;
        char stringDelimiter = ' ';

        for (char c : linea.toCharArray()) {
            if (inString) {
                token.append(c);
                if (c == stringDelimiter) {
                    inString = false;
                    tokens.add(token.toString());
                    token.setLength(0);
                }
            } else {
                if (c == '"' || c == '\'') {
                    if (token.length() > 0) {
                        tokens.add(token.toString().trim());
                        token.setLength(0);
                    }
                    inString = true;
                    stringDelimiter = c;
                    token.append(c);
                } else if (c == '{' || c == '}' || c == '(' || c == ')' || c == '=' || c == ';' || c == ' ' || c == '\n') {
                    if (token.length() > 0) {
                        tokens.add(token.toString().trim());
                        token.setLength(0);
                    }
                    if (c != ' ' && c != '\n') {
                        tokens.add(String.valueOf(c));
                    }
                } else if (c == '.' && token.length() > 0 && Character.isLetterOrDigit(token.charAt(token.length() - 1))) {
                    token.append(c);
                } else {
                    token.append(c);
                }
            }
        }

        if (token.length() > 0) {
            tokens.add(token.toString().trim());
        }

        return tokens;
    }
    
    
    

    public List<Token> getTokens() {
        return tokens;
    }

    public List<String> getErrores() {
        return errores;
    }

    public List<Token> getTokensValidados() {
        return tokensValidados;
    }
}
