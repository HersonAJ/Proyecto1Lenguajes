/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import javax.swing.JTextArea;

/**
 *
 * @author herson
 */

public class OptimizarCodigo {
    private String codigoFuente;
    private JTextArea textArea2;

    public OptimizarCodigo(String codigoFuente, JTextArea textArea2) {
        this.codigoFuente = codigoFuente;
        this.textArea2 = textArea2;
    }

    public void optimizar() {
        StringBuilder codigoOptimizado = new StringBuilder();
        String[] lineas = codigoFuente.split("\n");

        for (String linea : lineas) {
            linea = linea.trim(); // Eliminar espacios al inicio y al final de la línea
            if (!linea.startsWith("//") && !linea.isEmpty()) {
                codigoOptimizado.append(linea).append("\n");
            }
        }

        textArea2.setText(codigoOptimizado.toString());
    }
}


