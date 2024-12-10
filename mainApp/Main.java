/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainApp;

import java.util.Scanner;
import pitchrecognitioninstrument.Juego;

/**
 *
 * @author LENOVO
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al Juego de Notas");
        System.out.println("Selecciona un nivel de dificultad (1-5): ");
        Scanner scanner = new Scanner(System.in);
        int nivel = scanner.nextInt();

        Juego juego = new Juego(nivel);
        juego.iniciar();
    }
}
