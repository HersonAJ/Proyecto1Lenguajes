/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorCSS;

/**
 *
 * @author herson
 */
public class AutomataColoresCSS {
    private boolean agrupandoRGBA = false;
    private StringBuilder rgbaToken = new StringBuilder();

    public boolean esColorValido(String token) {
        // Agrupar el token en un rgba(...)
        if (agrupandoRGBA) {
            rgbaToken.append(token);

           
            if (token.endsWith(")")) {
                token = rgbaToken.toString(); 
                agrupandoRGBA = false; 
                rgbaToken.setLength(0); 
            } else {
                rgbaToken.append(" "); 
                return false; 
            }
        } else if (token.startsWith("rgba(")) {
            agrupandoRGBA = true;
            rgbaToken.append(token);
            rgbaToken.append(" "); 
            return false; 
        }

        // Estado inicial Q0
        if (token.startsWith("#")) {
            return validarHexadecimal(token); // Validar colores hexadecimales
        } else if (token.startsWith("rgba")) {
            return validarRGBA(token); // Validar colores en formato rgba()
        }
        return false; // Si no empieza con # o rgba
    }

    // Valida colores hexadecimales (3 o 6 caracteres)
    private boolean validarHexadecimal(String token) {
        // Estado Q1: Se espera un token con # y longitud 4 o 7
        if (token.length() != 4 && token.length() != 7) {
            return false; 
        }

        // Estado Q2: Verificar que cada carácter sea un hexadecimal válido
        for (int i = 1; i < token.length(); i++) {
            if (!esHexadecimal(token.charAt(i))) {
                return false; // Encontró un carácter no válido
            }
        }

        // Estado QF: Aceptación, color hexadecimal válido
        return true;
    }

    // Valida colores en formato rgba
    private boolean validarRGBA(String token) {
        // Estado Q3: Se espera el formato rgba(...)
        if (!token.startsWith("rgba(") || !token.endsWith(")")) {
            return false; // Debe tener el formato correcto
        }

        // Extraer solo los valores dentro de rgba(...)
        String parametros = token.substring(5, token.length() - 1); // Quitar 'rgba(' y ')'
        String[] valores = parametros.split(",");

        // Estado Q4: Deben ser 4 parámetros
        if (valores.length != 4) {
            return false; // Deben ser 4 parámetros (r, g, b, a)
        }

        // Validar los 3 primeros parámetros (r, g, b)
        for (int i = 0; i < 3; i++) {
            if (!esEntero(valores[i].trim(), 0, 255)) {
                return false; // Valores no válidos para r, g, b
            }
        }

        // Validar el cuarto parámetro (a), puede ser decimal o entero
        if (!esDecimalOEntero(valores[3].trim(), 0.0, 1.0)) {
            return false; // Último valor no válido
        }

        // Estado QF: Aceptación, color rgba válido
        return true;
    }

    // Función para validar si un carácter es hexadecimal
    private boolean esHexadecimal(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }

    // Función para validar si un string es un entero en un rango dado
    private boolean esEntero(String s, int min, int max) {
        try {
            int valor = Integer.parseInt(s);
            return valor >= min && valor <= max;
        } catch (NumberFormatException e) {
            return false; // No es un número válido
        }
    }

    // Función para validar si un string es un decimal o entero dentro de un rango
    private boolean esDecimalOEntero(String s, double min, double max) {
        try {
            double valor = Double.parseDouble(s);
            return valor >= min && valor <= max;
        } catch (NumberFormatException e) {
            return false; // No es un número válido
        }
    }
}

