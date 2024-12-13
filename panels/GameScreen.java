/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
    private JLabel currentFqLabel;
    private JButton startGameButton;
    private JPanel gridPanel;
    
    public GameScreen(CardLayout cardLayout, JPanel parent){
        super(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(2, 1, 0, 10));
                
        noteLabel = new JLabel("", SwingConstants.CENTER);
        noteLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gridPanel.add(noteLabel, BorderLayout.CENTER);
        
        currentFqLabel = new JLabel("", SwingConstants.CENTER);
        currentFqLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gridPanel.add(currentFqLabel, BorderLayout.CENTER);
        
        this.add(gridPanel, BorderLayout.CENTER);
        
        startGameButton = new JButton("Salir");
        startGameButton.addActionListener(e -> {
           cardLayout.show(parent, "Pantalla1");
        });

        this.add(startGameButton, BorderLayout.SOUTH);
    }
    
    public void setNoteLabel(String label){
        noteLabel.setText(label);
    }
    
    public void setCurrentFqLabel(String label){
        currentFqLabel.setText(label);
    }
    
    /**
     * 0 for green 1 for reed else default
     * @param i 
     */
    public void cambiarFondo(int i){
        
        switch(i){
            case 1: 
                this.getComponent(0).setBackground(Color.GREEN);
                break;
            case 0: 
                this.getComponent(0).setBackground(Color.RED);
                break;
            default:
                this.getComponent(0).setBackground(Color.GRAY);
                break;
        }
    }
    
   
    
   
    
}
