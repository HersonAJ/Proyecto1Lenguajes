/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author herson
 */
public class Token {
    private String token;
    private String expresionRegular;
    private String lenguaje;
    private String tipo;
    private int fila;
    private int columna;

    public Token(String token, String expresionRegular, String lenguaje, String tipo, int fila, int columna) {
        this.token = token;
        this.expresionRegular = expresionRegular;
        this.lenguaje = lenguaje;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Token{ " +
                " token=   '" + token + '\'' +
                ", expresionRegular=      '" + expresionRegular + '\'' +
                ", lenguaje=    '" + lenguaje + '\'' +
                ", tipo=    '" + tipo + '\'' +
                ", fila=   " + fila +
                ", columna=   " + columna +
                '}';
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getToken() {
        return token;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public String getTipo() {
        return tipo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}