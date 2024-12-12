/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author LENOVO
 */
public class GameScreen extends JPanel {
    
    private JLabel noteLabel;
    private JButton startGameButton;
    
    public GameScreen(CardLayout cardLayout, JPanel parent){
        super(new BorderLayout());
        noteLabel = new JLabel("", SwingConstants.CENTER);
        noteLabel.setFont(new Font("Serif", Font.BOLD, 24));
        this.add(noteLabel, BorderLayout.CENTER);
        
        startGameButton = new JButton("Salir");
        startGameButton.addActionListener(e -> {
           cardLayout.show(parent, "Pantalla1");
        });

        this.add(startGameButton, BorderLayout.SOUTH);
    }
    
    public void setNoteLabel(String label){
        noteLabel.setText(label);
    }
    
}
