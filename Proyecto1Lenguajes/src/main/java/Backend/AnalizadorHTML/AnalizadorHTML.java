/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorHTML;

/**
 *
 * @author herson
 */
import Backend.Token;
import java.util.ArrayList;
import java.util.List;

public class AnalizadorHTML {
    private AutomataEtiquetasHTML automataEtiquetasHTML;
    private AutomataPalabrasReservadasHTML automataPalabrasReservadasHTML;
    private AutomataCadenasHTML automataCadenasHTML;
    private AutomataTextoHTML automataTextoHTML;

   public AnalizadorHTML() {
        this.automataEtiquetasHTML = new AutomataEtiquetasHTML();
        this.automataPalabrasReservadasHTML = new AutomataPalabrasReservadasHTML();
        this.automataCadenasHTML = new AutomataCadenasHTML();
        this.automataTextoHTML = new AutomataTextoHTML();
    }

    public List<Token> analizarTokens(List<String> tokens, int fila) {
        List<Token> tokensValidados = new ArrayList<>();
        int columna = 1;

        for (String token : tokens) {
            String tipo = "Desconocido";
            String traduccion = "N/A";

            if (automataEtiquetasHTML.esEtiquetaValida(token)) {
                tipo = automataEtiquetasHTML.tipoEtiqueta(token);
                traduccion = automataEtiquetasHTML.traducirEtiqueta(token);
            } else if (automataPalabrasReservadasHTML.esPalabraReservada(token)) {
                tipo = "Palabra Reservada";
                traduccion = token;
            } else if (automataCadenasHTML.esCadenaValida(token)) {
                tipo = "Cadena HTML";
                traduccion = token;
            } else if (automataTextoHTML.esTextoValido(token)) {
                tipo = "Texto";
                traduccion = token;
            }
            
            
            
            
            Token nuevoToken = new Token(token, traduccion, "HTML", tipo, fila, columna);
            tokensValidados.add(nuevoToken);
            columna++;
        }
        return tokensValidados;
    }

}


