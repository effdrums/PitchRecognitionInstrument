/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pitchrecognitioninstrument;

import java.util.Scanner;
import panels.GameScreen;

/**
 *
 * @author LENOVO
 */
public class Juego {
    private GeneradorDeNotas generadorDeNotas;
    private DetectorDeNotas detectorDeNotas;
    private int tiempoLimite; // Tiempo en milisegundos
    private GameScreen gs; 

    public Juego(int nivelDificultad) {
        this.generadorDeNotas = new GeneradorDeNotas();
        this.detectorDeNotas = new DetectorDeNotas();
        this.tiempoLimite = calcularTiempoPorNivel(nivelDificultad);
    }
    
    public void setGameScreen(GameScreen gs){
        this.gs = gs;
    }

    public void iniciar() {
         try {
        detectorDeNotas.inicializarMicrofono();
        System.out.println("¡El juego comienza!");
        
        boolean puedeCambiarNota = true;
        Nota notaEsperada = generadorDeNotas.generarNotaAleatoria(); 
            while (true) {
                
                if(puedeCambiarNota){
                    notaEsperada = generadorDeNotas.generarNotaAleatoria();
                    puedeCambiarNota = false;
                    if(gs != null){
                        gs.setNoteLabel(notaEsperada.getNombre());
                        gs.cambiarFondo(-1);
                        
                    }else{
                        System.out.println("Toca la nota: " + notaEsperada.getNombre());
                    }
                }
                

                // Esperar a que el usuario toque la nota o se agote el tiempo límite
                int resultado = escucharNotaDelUsuario(notaEsperada);
                
                
                
                if(gs != null){
                    gs.cambiarFondo(resultado);
                    if(resultado == 1 || resultado == 0){
                        puedeCambiarNota = true;
                        detectorDeNotas.resetFrecuenciaDetectada();
                        Thread.sleep(1000);
                    }
                }else{
                   if (resultado == 1) {
                        System.out.println("¡Correcto! Vamos con la siguiente nota.");
                        
                    } else {
                        System.out.println("Nota incorrecta o tiempo agotado. Inténtalo de nuevo.");
                    } 
                }
                

               
            }
        } catch (Exception e) {
            System.err.println("Error durante el juego: " + e.getMessage());
        }
    }
    
    private int escucharNotaDelUsuario(Nota notaEsperada) {
        long tiempoInicio = System.currentTimeMillis();

        while (System.currentTimeMillis() - tiempoInicio < tiempoLimite) {
            float frecuenciaDetectada = detectorDeNotas.obtenerFrecuenciaDetectada();
            gs.setCurrentFqLabel(Float.toString(frecuenciaDetectada));
            if (frecuenciaDetectada > 0) { // Si se detecta una frecuencia válida
                if(tiempoLimite <= 10000){
                    return detectorDeNotas.esNotaCorrecta(notaEsperada.getNombre(), notaEsperada.getFrecuencia());
                }else{
                    if(detectorDeNotas.esNotaCorrecta(notaEsperada.getNombre(), notaEsperada.getFrecuencia()) == 1){
                        return 1;
                    }
                        
                }
            }
        }

        // Si se agota el tiempo sin detectar una frecuencia correcta
        return 0;
    }


    private int calcularTiempoPorNivel(int nivel) {
        // Reduce el tiempo límite según el nivel
        if(nivel == 0){
            return 10000;
        }
        return Math.max(500, 10000 - (nivel * 1000));
    }
}