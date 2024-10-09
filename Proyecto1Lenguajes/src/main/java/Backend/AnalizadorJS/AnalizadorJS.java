/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
import Backend.Token;
import java.util.ArrayList;
import java.util.List;

public class AnalizadorJS {
    private AutomataPalabrasReservadasJS automataPalabrasReservadasJS;
    private AutomataIdentificadoresJS automataIdentificadoresJS;
    private AutomataBooleanJS automataBooleanJS;
    private AutomataCadenaJS automataCadenaJS;
    private AutomataDecimalesJS automataDecimalesJS;
    private AutomataEnterosJS automataEnterosJS;
    private AutomataAritmeticosJS automataAritmeticosJS;
    private AutomataRelacionalesJS automataRelacionalesJS;
    private AutomataSimbolosJS automataSimbolosJS;
    private AutomataLogicoJS automataLogicoJS;
    private AutomataIncrementalJS automataIncrementalJS;

    public AnalizadorJS() {
        this.automataPalabrasReservadasJS = new AutomataPalabrasReservadasJS();
        this.automataIdentificadoresJS = new AutomataIdentificadoresJS();
        this.automataBooleanJS = new AutomataBooleanJS();
        this.automataCadenaJS = new AutomataCadenaJS();
        this.automataDecimalesJS = new AutomataDecimalesJS();
        this.automataEnterosJS = new AutomataEnterosJS();
        this.automataAritmeticosJS = new AutomataAritmeticosJS();
        this.automataRelacionalesJS = new AutomataRelacionalesJS();
        this.automataSimbolosJS = new AutomataSimbolosJS();
        this.automataLogicoJS = new AutomataLogicoJS();
        this.automataIncrementalJS = new AutomataIncrementalJS();
    }

    public List<Token> analizarTokens(List<String> tokens, int fila) {
        List<Token> tokensValidados = new ArrayList<>();
        int columna = 1;

        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            String tipo = "Desconocido";

            if (automataPalabrasReservadasJS.esPalabraReservada(token)) {
                tipo = "Palabra Reservada JS";
            } else if (automataIdentificadoresJS.esIdentificadorValido(token)) {
                tipo = "Identificador";
            } else if (automataBooleanJS.esBooleanValido(token)) {
                tipo = "Boolean JS";
            } else if (automataCadenaJS.esCadenaValida(token)) {
                tipo = "Cadena JS";
            } else if (automataDecimalesJS.esDecimalValido(token)) {
                tipo = "Decimal JS";
            } else if (automataEnterosJS.esEnteroValido(token)) {
                tipo = "Entero JS";
            } else if (automataAritmeticosJS.esAritmeticoValido(token)) {
                tipo = "Aritmetico";
            } else if (i < tokens.size() - 1 && automataRelacionalesJS.unirYValidarTokens(tokens.subList(i, i + 2))) {
                tipo = "Relacional";
                token = tokens.get(i) + tokens.get(i + 1); // Unir los tokens
                i++; // Saltar el siguiente token ya que fue unido
            } else if (automataSimbolosJS.esSimboloValido(token)){
                tipo = "Simbolo";
            } else if (automataLogicoJS.esLogicoValido(token)) {
                tipo = "Logico";
            } else if (i < tokens.size() - 1 && automataIncrementalJS.unirYValidarTokens(tokens.subList(i, i + 2))) {
                tipo = "Incremental";
                token = tokens.get(i) + tokens.get(i + 1);
                i++;
            }

            Token nuevoToken = new Token(token, token, "Javascript", tipo, fila, columna);
            tokensValidados.add(nuevoToken);
            columna++;
        }
        return tokensValidados;
    }

}

