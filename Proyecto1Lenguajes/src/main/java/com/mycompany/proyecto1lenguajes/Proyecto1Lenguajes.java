/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto1lenguajes;

import Frontend.Interface;
import javax.swing.SwingUtilities;

/**
 *
 * @author herson
 */
public class Proyecto1Lenguajes {

    public static void main(String[] args) {
                       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }
}
