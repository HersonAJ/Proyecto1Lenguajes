/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frontend;

import Backend.AnalizadorCodigoFuente;
import Backend.Token;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author herson
 */
public class Interface extends JFrame {
    private JComboBox<Integer> rowSelector;
    private JComboBox<Integer> colSelector;
    private JTextArea textArea1;
    private JTable table;

    public Interface() {
        // Aplicar el Look and Feel de FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Analizador Gráfico");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Crear el JTextArea con números de línea
        textArea1 = new JTextArea();
        LineNumberingTextArea lineNumberingTextArea = new LineNumberingTextArea(textArea1);
        lineNumberingTextArea.setEditable(false);

        // Crear un JScrollPane que contenga ambos JTextAreas
        JScrollPane scrollPane = new JScrollPane(textArea1);
        scrollPane.setRowHeaderView(lineNumberingTextArea);

        // Crear los paneles
        JPanel jPanel1 = new JPanel();

        // Configurar el layout de jPanel1
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(scrollPane, BorderLayout.CENTER);

        // Crear la tabla con la estructura especificada
        String[] columnNames = {"Token", "Expresion Regular", "Lenguaje", "Tipo", "Fila", "Columna"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Configurar el layout del JFrame usando GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar jPanel1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(jPanel1, gbc);

        // Configurar tableScrollPane
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(tableScrollPane, gbc);

        // Crear el botón
        JButton boton = new JButton("Aceptar");
        JButton saveButton = new JButton("Generar Reporte HTML");

        // Configurar el botón de Aceptar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(boton, gbc);

        // Configurar el botón de guardar imagen
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(saveButton, gbc);

        // Añadir acción al botón de Aceptar
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoFuente = textArea1.getText();
                AnalizadorCodigoFuente analizador = new AnalizadorCodigoFuente();
                analizador.analizarCodigo(codigoFuente);
                List<Token> tokensValidados = analizador.getTokensValidados();
                actualizarTabla(tokensValidados);
            }
        });
    }

    private void actualizarTabla(List<Token> tokensValidados) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); // Limpiar la tabla

        for (Token token : tokensValidados) {
            Object[] rowData = {
                token.getToken(),
                token.getExpresionRegular(),
                token.getLenguaje(),
                token.getTipo(),
                token.getFila(),
                token.getColumna()
            };
            tableModel.addRow(rowData);
        }
    }
}
