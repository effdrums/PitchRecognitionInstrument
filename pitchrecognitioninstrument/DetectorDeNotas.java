/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pitchrecognitioninstrument;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchProcessor;
import javax.sound.sampled.LineUnavailableException;
/**
 *
 * @author LENOVO
 */
public class DetectorDeNotas {
    // Aquí usamos TarsosDSP para capturar y procesar audio
    private AudioDispatcher dispatcher;
    private float frecuenciaDetectada; // Última frecuencia capturada
    private boolean frecuenciaActualizada; // Para saber si hay nueva frecuencia lista

    // Parámetros de configuración del audio
    private static final int SAMPLE_RATE = 44100; // Frecuencia de muestreo
    private static final int BUFFER_SIZE = 1024;  // Tamaño del buffer
    private static final int OVERLAP = 512;       // Solapamiento del buffer
    
    private static int MAX_FREC; 
    private static int MIN_FREC;
    
    public DetectorDeNotas() {
        this.frecuenciaDetectada = -1; // Inicialmente ninguna frecuencia detectada
        this.frecuenciaActualizada = false;
    }
    
    /**
     * Inicializa el micrófono y el análisis de audio con TarsosDSP.
     */
    public void inicializarMicrofono() throws LineUnavailableException {
        dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(SAMPLE_RATE, BUFFER_SIZE, OVERLAP);

        // Configuramos el PitchProcessor con YIN como algoritmo
        PitchDetectionHandler handler = (res, event) -> {
            if (res.getPitch() > 0) { // Si se detecta una frecuencia válida
                 synchronized (this) {
                    this.frecuenciaDetectada = res.getPitch();
                    this.frecuenciaActualizada = true;
                }
            }
        };

        dispatcher.addAudioProcessor(new PitchProcessor(
            PitchProcessor.PitchEstimationAlgorithm.YIN, SAMPLE_RATE, BUFFER_SIZE, handler));

        // Inicia la captura en un hilo separado
        new Thread(dispatcher, "Audio Dispatcher").start();
    }
    
    /**
     * Devuelve la frecuencia detectada si es válida y marca que ya ha sido consumida.
     */
    public synchronized float obtenerFrecuenciaDetectada() {

        return frecuenciaDetectada; // No hay nueva frecuencia detectada
    }
    
    public synchronized void resetFrecuenciaDetectada(){
        frecuenciaDetectada = 0.0f;
    }

    
    public String detectarNota(float frecuenciaDetectada) {
        // Lógica para convertir la frecuencia detectada en el nombre de la nota más cercana
        // Por ejemplo, frecuencia 440 Hz → "La"
        return getNotaCercana(frecuenciaDetectada);
    }

    private String getNotaCercana(float frecuencia) {
        // Implementar mapeo de frecuencias a nombres de notas
        // Simplificado aquí como ejemplo
        if (frecuencia >= 430 && frecuencia <= 450) return "La";
        // Otros rangos de notas...
        return "Desconocida";
    }
    
    public int esNotaCorrecta(String notaEsperada, float frecuenciaEsperada) {
        // Define el margen de tolerancia (en Hz)
        float margen = 10.0f; // Puedes ajustar este valor según la precisión deseada

        // Usar la frecuencia actual detectada
        float frecuenciaActual = obtenerFrecuenciaDetectada();
        if (frecuenciaActual == -1) {
            System.out.print("\rNo se ha detectado una frecuencia válida.");
            return -1;
        }

        // Comprobar si está dentro del margen de la frecuencia esperada
        if (Math.abs(frecuenciaEsperada - frecuenciaActual) <= margen) {
            System.out.print("\r¡Correcto! Has tocado un " + notaEsperada);
            return 1;
        } else {
//            System.out.print("\rNota incorrecta. Se esperaba un " + frecuenciaEsperada
//                    + " pero se detectó una frecuencia de " + frecuenciaActual);
            
            if(frecuenciaActual < MIN_FREC - 100.0 || frecuenciaActual > MAX_FREC + 100.0 ){
                return -1;
            }else{
                return 0;
            }
            
        }
    }
}
