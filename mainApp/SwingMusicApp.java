/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainApp;

/**
 *
 * @author LENOVO
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import panels.GameScreen;
import pitchrecognitioninstrument.Juego;

public class SwingMusicApp {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Juego juego;

    public SwingMusicApp() {
        // Configuración inicial
        frame = new JFrame("Práctica de Música");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Pantallas
        mainPanel.add(createWelcomeScreen(), "Pantalla1");
        mainPanel.add(createLevelSelectionScreen(), "Pantalla2");
        mainPanel.add(createGameScreen(), "Pantalla3");

        // Configuración del marco
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private JPanel createWelcomeScreen() {
         JPanel panel = new JPanel(new BorderLayout());

        // Crear un panel central para alinear el texto y el botón
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel label = new JLabel("Bienvenido a Práctica de Música", SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(label, gbc);

        gbc.gridy = 1;
        JButton startButton = new JButton("Iniciar Práctica");
        startButton.setPreferredSize(new Dimension(150, 50));

        // Listener para ir a la pantalla de selección de nivel
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "Pantalla2"));

        centerPanel.add(startButton, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLevelSelectionScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Escoge el Nivel", SwingConstants.CENTER);
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Crear botones de nivel
        for (int i = 0; i <= 5; i++) {
            int nivel = i;
            JButton levelButton = new JButton(String.valueOf(i));
            levelButton.setPreferredSize(new Dimension(50, 50));

            // Listener para establecer el tiempo límite y pasar a la pantalla de juego
            levelButton.addActionListener(e -> {
                // Ejemplo: tiempo basado en el nivel
                juego = new Juego(nivel);
                juego.setGameScreen((GameScreen) mainPanel.getComponent(2));
                cardLayout.show(mainPanel, "Pantalla3");
                new Thread(new Runnable() {
                @Override
                public void run() {
                    juego.iniciar();  // Llama a start() del juego en un hilo separado
                }
                }).start();
            });

            gbc.gridx = i;
            gbc.gridy = 0;
            buttonPanel.add(levelButton, gbc);
        }

        panel.add(label, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createGameScreen() {
        
        return new GameScreen(cardLayout,  mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingMusicApp::new);
    }
}
