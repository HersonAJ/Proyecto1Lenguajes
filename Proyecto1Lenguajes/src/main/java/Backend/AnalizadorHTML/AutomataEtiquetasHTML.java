/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorHTML;

/**
 *
 * @author herson
 */
import java.util.HashMap;
import java.util.Map;

public class AutomataEtiquetasHTML {
    private enum Estado {
        Q0, Q1, Q2, QF, ERROR
    }

    private Estado estadoActual;
    private static final Map<String, String> etiquetasValidas = new HashMap<>();

    static {
        etiquetasValidas.put("principal", "main");
        etiquetasValidas.put("encabezado", "header");
        etiquetasValidas.put("navegacion", "nav");
        etiquetasValidas.put("apartado", "aside");
        etiquetasValidas.put("listaordenada", "ul");
        etiquetasValidas.put("listadesordenada", "ol");
        etiquetasValidas.put("itemlista", "li");
        etiquetasValidas.put("anclaje", "a");
        etiquetasValidas.put("contenedor", "div");
        etiquetasValidas.put("seccion", "section");
        etiquetasValidas.put("articulo", "article");
        etiquetasValidas.put("parrafo", "p");
        etiquetasValidas.put("span", "span");
        etiquetasValidas.put("entrada", "input");
        etiquetasValidas.put("formulario", "form");
        etiquetasValidas.put("label", "label");
        etiquetasValidas.put("area", "textarea");
        etiquetasValidas.put("boton", "button");
        etiquetasValidas.put("piepagina", "footer");
    }

    public AutomataEtiquetasHTML() {
        this.estadoActual = Estado.Q0;
    }

    public boolean esEtiquetaValida(String etiqueta) {
        estadoActual = Estado.Q0;
        for (char c : etiqueta.toCharArray()) {
            switch (estadoActual) {
                case Q0:
                    if (c == '<') {
                        estadoActual = Estado.Q1;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q1:
                    if (Character.isLetter(c)) {
                        estadoActual = Estado.Q2;
                    } else if (c == '/') {
                        estadoActual = Estado.Q2;
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case Q2:
                    if (Character.isLetter(c) || c == '/' || c == '>') {
                        if (c == '>') {
                            estadoActual = Estado.QF;
                        }
                    } else {
                        estadoActual = Estado.ERROR;
                    }
                    break;
                case QF:
                    break;
                case ERROR:
                    return false;
            }
        }
        return estadoActual == Estado.QF;
    }

    public String traducirEtiqueta(String etiqueta) {
        String etiquetaLimpia = etiqueta.replaceAll("[</>]", "");
        String traduccion = etiquetasValidas.getOrDefault(etiquetaLimpia, "Etiqueta no válida");

        if (etiqueta.endsWith("/>")) {
            traduccion = "</" + traduccion + ">";
        } else {
            traduccion = "<" + traduccion + ">";
        }

        return traduccion;
    }

    public String tipoEtiqueta(String etiqueta) {
        if (etiqueta.startsWith("</") || etiqueta.endsWith("/>")) {
            return "Cierre";
        } else {
            return "Apertura";
        }
    }
}




