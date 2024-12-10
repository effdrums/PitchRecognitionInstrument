/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pitchrecognitioninstrument;

import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class Juego {
    private GeneradorDeNotas generadorDeNotas;
    private DetectorDeNotas detectorDeNotas;
    private int tiempoLimite; // Tiempo en milisegundos

    public Juego(int nivelDificultad) {
        this.generadorDeNotas = new GeneradorDeNotas();
        this.detectorDeNotas = new DetectorDeNotas();
        this.tiempoLimite = calcularTiempoPorNivel(nivelDificultad);
    }

    public void iniciar() {
        

        while (true) {
            Nota notaActual = generadorDeNotas.generarNotaAleatoria();
            System.out.println("Toca la nota: " + notaActual.getNombre());

            long inicioTiempo = System.currentTimeMillis();
            float frecuenciaDetectada = escucharNotaDelUsuario();
            long finTiempo = System.currentTimeMillis();

            if ((finTiempo - inicioTiempo) > tiempoLimite) {
                System.out.println("¡Fallo! Tiempo excedido.");
            } else if (!notaActual.getNombre().equals(detectorDeNotas.detectarNota(frecuenciaDetectada))) {
                System.out.println("¡Fallo! Nota incorrecta.");
            } else {
                System.out.println("¡Correcto!");
            }
        }
    }

    private float escucharNotaDelUsuario() {
        // Aquí llamamos al DetectorDeNotas y capturamos audio real con TarsosDSP
        return 440.0f; // Simulación de nota "La" (esto será reemplazado por el audio real)
    }

    private int calcularTiempoPorNivel(int nivel) {
        // Reduce el tiempo límite según el nivel
        return Math.max(500, 2000 - (nivel * 200));
    }
}