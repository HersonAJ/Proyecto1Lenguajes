/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorHTML;

import java.util.List;

/**
 *
 * @author herson
 */
import java.util.ArrayList;
import java.util.List;

public class TokenHTML {

    public List<String> tokenizarEtiquetas(String linea) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        boolean inTag = false;

        for (char c : linea.toCharArray()) {
            if (c == '<') {
                if (token.length() > 0) {
                    token.setLength(0); 
                }
                token.append(c);
                inTag = true;
            } else if (c == '>') {
                token.append(c);  
                String etiqueta = token.toString().trim();

                if (etiqueta.contains(" ")) {
                    etiqueta = etiqueta.substring(0, etiqueta.indexOf(' ')) + ">";
                }
                tokens.add(etiqueta);
                token.setLength(0);
                inTag = false;
            } else if (inTag) {
                token.append(c);
            }
        }

        return tokens;
    }

public List<String> tokenizarAtributos(String linea) {
    List<String> atributos = new ArrayList<>();
    StringBuilder atributo = new StringBuilder();
    boolean inTag = false, inAtributo = false;

    for (char c : linea.toCharArray()) {
        if (c == '<') {
            inTag = true;
            atributo.setLength(0);
        } else if (c == '>') {
            inTag = false;
            if (inAtributo) {
                agregarAtributos(atributos, atributo.toString().trim());
            }
            inAtributo = false;
        } else if (inTag) {
            if (c == ' ' && !inAtributo && atributo.length() == 0) {
                inAtributo = true;
            } else if (inAtributo) {
                if (c == ' ' || c == '=') {
                    agregarAtributos(atributos, atributo.toString().trim());
                    atributos.add(String.valueOf(c));
                    atributo.setLength(0);
                } else {
                    atributo.append(c);
                }
            }
        }
    }

    return atributos;
}

private void agregarAtributos(List<String> atributos, String atributo) {
    if (!atributo.isEmpty()) {
        atributos.add(atributo);
    }
}

    public List<String> tokenizarCadenas(String linea) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        boolean inTag = false;

        for (char c : linea.toCharArray()) {
            if (c == '<') {
                inTag = true;
                if (token.length() > 0) {
                    tokens.add(token.toString().trim());
                    token.setLength(0); 
                }
            } else if (c == '>') {
                inTag = false;
            } else if (!inTag) {
                token.append(c);
            }
        }

        if (token.length() > 0) {
            tokens.add(token.toString().trim());
        }

        return tokens;
    }

}