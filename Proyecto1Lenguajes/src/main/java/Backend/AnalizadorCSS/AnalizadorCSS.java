/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorCSS;

import Backend.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */
public class AnalizadorCSS {
    private AutomataEtiquetaCSS automataEtiquetaCSS;
    private AutomataReglasCSS automataReglasCSS;
    private AutomataCombinadoresCSS automataCombinadoresCSS;
    private AutomataColoresCSS automataColoresCSS;
    private AutomataCadenasCSS automataCadenasCSS;
    private AutomataEnterosCSS automataEnterosCSS;
    private AutomataOtrosCSS automataOtrosCSS;
    private AutomataIdentificadoresCSS automataIdentificadoresCSS;
    private AutomataClaseCSS automataClaseCSS;
    private AutomataIdCSS automataIdCSS;

    public AnalizadorCSS() {
        this.automataEtiquetaCSS = new AutomataEtiquetaCSS();
        this.automataReglasCSS = new AutomataReglasCSS();
        this.automataCombinadoresCSS = new AutomataCombinadoresCSS();
        this.automataColoresCSS = new AutomataColoresCSS();
        this.automataCadenasCSS = new AutomataCadenasCSS();
        this.automataEnterosCSS = new AutomataEnterosCSS();
        this.automataOtrosCSS = new AutomataOtrosCSS();
        this.automataIdentificadoresCSS = new AutomataIdentificadoresCSS();
        this.automataClaseCSS = new AutomataClaseCSS();
        this.automataIdCSS = new AutomataIdCSS();
    }

    public List<Token> analizarTokens(List<String> tokens, int fila) {
        List<Token> tokensValidados = new ArrayList<>();
        int columna = 1;

        for (String token : tokens) {
            String tipo = "Desconocido";
            if (automataEtiquetaCSS.esEtiquetaValida(token)) {
                tipo = "Etiqueta o Tipo";
            } else if (automataReglasCSS.esReglaValida(token)) {
                tipo = "Regla CSS";
            } else if (automataCombinadoresCSS.esCombinadorValido(token)) {
                tipo = "Combinador CSS";
            } else if (automataColoresCSS.esColorValido(token)) {
                tipo = "Color CSS";
            } else if (automataCadenasCSS.esCadenaValida(token)) {
                tipo = "Cadena CSS";
            } else if (automataEnterosCSS.esEnteroValido(token)) {
                tipo = "Entero CSS";
            } else if (automataOtrosCSS.esElementoValido(token)) {
                tipo = "Otros CSS";
            } else if (automataIdentificadoresCSS.esIdentificadorValido(token)) {
                tipo = "Identificador CSS";
            } else if (automataClaseCSS.esClaseValida(token)) {
                tipo = "Clase CSS";
            } else if (automataIdCSS.esIdValido(token)) {
                tipo = "ID CSS";
            }
            Token nuevoToken = new Token(token, token, "CSS", tipo, fila, columna);
            tokensValidados.add(nuevoToken);
            columna++;

            // Imprimir el token validado
            System.out.println("Token validado: " + nuevoToken);
        }

        return tokensValidados;
    }
}

