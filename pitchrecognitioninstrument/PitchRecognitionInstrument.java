/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pitchrecognitioninstrument;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchProcessor;
import javax.sound.sampled.*;
/**
 *
 * @author LENOVO
 */
public class PitchRecognitionInstrument {

     public static void main(String[] args) {
        // Configura el dispositivo de audio
        try {
            // Frecuencia de muestreo (44100 es común para audio)
            float sampleRate = 44100;
            int bufferSize = 1024;
            int overlap = 512;

            // Configura la fuente de audio (micrófono)
            AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            microphone.start();

            // Envuelve el audio del micrófono en un InputStream para TarsosDSP
            AudioInputStream audioStream = new AudioInputStream(microphone);
            JVMAudioInputStream jvmAudioInputStream = new JVMAudioInputStream(audioStream);

            // Crea un despachador de audio para procesar el tono en tiempo real
            AudioDispatcher dispatcher = new AudioDispatcher(jvmAudioInputStream, bufferSize, overlap);

            // Maneja los eventos de detección de tono
            PitchDetectionHandler pitchHandler = (result, event) -> {
                float pitchInHz = result.getPitch();
                if (pitchInHz != -1) { // -1 significa que no se detecta tono
                    String note = getNoteFromFrequency(pitchInHz);
                    System.out.println("Nota detectada: " + note + " (" + pitchInHz + " Hz)");
                }
            };

            // Agrega un procesador de tono al despachador
            dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, bufferSize, pitchHandler));

            // Inicia el procesamiento en un nuevo hilo
            new Thread(dispatcher, "Audio Dispatcher").start();

        } catch (LineUnavailableException e) {
            System.out.println("Error al acceder al dispositivo de audio: " + e.getMessage());
        }
    }

    // Método auxiliar para convertir frecuencia en una nota musical
    public static String getNoteFromFrequency(float frequency) {
        // Lógica para convertir frecuencia a nota (A4 = 440 Hz, etc.)
        // Puedes usar fórmulas matemáticas para identificar notas específicas (A, B, C...) según la frecuencia.
        return "C4"; // Este es solo un ejemplo; implementar una fórmula aquí.
    }
    
}
