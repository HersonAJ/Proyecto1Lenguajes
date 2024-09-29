/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.HTML;

import Backend.Token;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author herson
 */
public class AutomataEtiquetasHTML {

    private enum Estado {
        Q0, Q1, QF
    }

    private Estado estadoActual;
    private static final Map<String, String> traducciones = new HashMap<>();

    static {
        traducciones.put("<principal>", "<main>");
        traducciones.put("<principal/>", "</main>");
        traducciones.put("<encabezado>", "<header>");
        traducciones.put("<encabezado/>", "</header>");
        traducciones.put("<navegacion>", "<nav>");
        traducciones.put("<navegacion/>", "</nav>");
        traducciones.put("<apartado>", "<aside>");
        traducciones.put("<apartado/>", "</aside>");
        traducciones.put("<listaordenada>", "<ul>");
        traducciones.put("<listaordenada/>", "</ul>");
        traducciones.put("<listadesordenada>", "<ol>");
        traducciones.put("<listadesordenada/>", "</ol>");
        traducciones.put("<itemlista>", "<li>");
        traducciones.put("<itemlista/>", "</li>");
        traducciones.put("<anclaje>", "<a>");
        traducciones.put("<anclaje/>", "</a>");
        traducciones.put("<contenedor>", "<div>");
        traducciones.put("<contenedor/>", "</div>");
        traducciones.put("<seccion>", "<section>");
        traducciones.put("<seccion/>", "</section>");
        traducciones.put("<articulo>", "<article>");
        traducciones.put("<articulo/>", "</article>");
        traducciones.put("<parrafo>", "<p>");
        traducciones.put("<parrafo/>", "</p>");
        traducciones.put("<span>", "<span>");
        traducciones.put("<span/>", "</span>");
        traducciones.put("<entrada/>", "<input/>");
        traducciones.put("<formulario>", "<form>");
        traducciones.put("<formulario/>", "</form>");
        traducciones.put("<label>", "<label>");
        traducciones.put("<label/>", "</label>");
        traducciones.put("<area/>", "<textarea/>");
        traducciones.put("<boton>", "<button>");
        traducciones.put("<boton/>", "</button>");
        traducciones.put("<piepagina>", "<footer>");
        traducciones.put("<piepagina/>", "</footer>");
    }

    public Token validarEtiqueta(String etiqueta, int fila, int columna) {
        estadoActual = Estado.Q0;

        if (traducciones.containsKey(etiqueta)) {
            estadoActual = Estado.Q1;
        }

        if (estadoActual == Estado.Q1) {
            estadoActual = Estado.QF;
        }

        if (estadoActual == Estado.QF) {
            String traduccion = traducciones.get(etiqueta);
            String tipo = "Etiqueta";
            System.out.println(new Token(etiqueta, traduccion, "HTML", tipo, fila, columna));
            return new Token(etiqueta, traduccion, "HTML", tipo, fila, columna);
            
        }

        return null;
    }
}