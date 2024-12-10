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
         try {
        detectorDeNotas.inicializarMicrofono();
        System.out.println("¡El juego comienza!");

            while (true) {
                Nota notaEsperada = generadorDeNotas.generarNotaAleatoria();
                System.out.println("Toca la nota: " + notaEsperada.getNombre());

                // Esperar a que el usuario toque la nota o se agote el tiempo límite
                boolean resultado = escucharNotaDelUsuario(notaEsperada);

                if (resultado) {
                    System.out.println("¡Correcto! Vamos con la siguiente nota.");
                } else {
                    System.out.println("Nota incorrecta o tiempo agotado. Inténtalo de nuevo.");
                }

                // Puedes añadir un pequeño descanso entre notas si lo deseas
                Thread.sleep(1000); // Esperar 1 segundo antes de la siguiente nota
            }
        } catch (Exception e) {
            System.err.println("Error durante el juego: " + e.getMessage());
        }
    }
    
    private boolean escucharNotaDelUsuario(Nota notaEsperada) {
        long tiempoInicio = System.currentTimeMillis();

        while (System.currentTimeMillis() - tiempoInicio < tiempoLimite) {
            float frecuenciaDetectada = detectorDeNotas.obtenerFrecuenciaDetectada();

            if (frecuenciaDetectada > 0) { // Si se detecta una frecuencia válida
                return detectorDeNotas.esNotaCorrecta(notaEsperada.getNombre(), notaEsperada.getFrecuencia());
            }
        }

        // Si se agota el tiempo sin detectar una frecuencia correcta
        return false;
    }


    private int calcularTiempoPorNivel(int nivel) {
        // Reduce el tiempo límite según el nivel
        return Math.max(500, 2000 - (nivel * 200));
    }
}