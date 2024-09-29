/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.HTML;

import Backend.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */
public class AnalizadorHTML {
    private AutomataPalabrasReservadasHTML automataPalabrasReservadas;
    private AutomataEtiquetasHTML automataEtiquetas;

    public AnalizadorHTML() {
        this.automataPalabrasReservadas = new AutomataPalabrasReservadasHTML();
        this.automataEtiquetas = new AutomataEtiquetasHTML();
    }

    public List<Token> analizarTokens(List<String> tokens, int fila) {
        List<Token> tokensValidados = new ArrayList<>();
        int columna = 1;
        for (String token : tokens) {
            Token tokenValidado = automataPalabrasReservadas.validarPalabraReservada(token, fila, columna);
            if (tokenValidado != null) {
                tokensValidados.add(tokenValidado);
                System.out.println("Token validado: " + tokenValidado);
            } else {
                tokenValidado = automataEtiquetas.validarEtiqueta(token, fila, columna);
                if (tokenValidado != null) {
                    tokensValidados.add(tokenValidado);
                    System.out.println("Token validado: " + tokenValidado);
                } else {
                    System.out.println("Token no reconocido: " + token);
                }
            }
            columna++;
        }
        return tokensValidados;
    }
}
