/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.AnalizadorJS;

/**
 *
 * @author herson
 */
public class AutomataIdentificadoresJS {
    private enum Estado {
        Q0, Q1, Q2, QF
    }
    private Estado estadoActual;

    public AutomataIdentificadoresJS() {
        this.estadoActual = Estado.Q0;
    }
    
    public boolean esIdentificadorValido(String token) {
        estadoActual = Estado.Q0;
        
        for(char c : token.toCharArray()){
            switch (estadoActual) {
                case Q0:
                    if(Character.isLetter(c)){
                        estadoActual = Estado.Q1;
                    } else {
                        return false;
                    }
                    break;
                case Q1:
                    if(Character.isLetterOrDigit(c)) {
                        estadoActual = Estado.Q1;
                    }else if (c == '-') {
                        estadoActual = Estado.Q2;
                    } else {
                        return false;
                    }
                    break;
                case Q2:
                    if(Character.isLetterOrDigit(c)) {
                        estadoActual = Estado.Q1;
                    } else {
                        return false;
                    }
                    break;
                    default:
                        return false;
            } 
        }
        return estadoActual == Estado.Q1;
    }
    
}
