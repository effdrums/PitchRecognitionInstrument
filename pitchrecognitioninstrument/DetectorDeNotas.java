/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pitchrecognitioninstrument;

/**
 *
 * @author LENOVO
 */
public class DetectorDeNotas {
    // Aquí usamos TarsosDSP para capturar y procesar audio

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
    
    public boolean esNotaCorrecta(String notaEsperada, float frecuenciaEsperada, float frecuenciaDetectada) {
    // Define el margen de tolerancia (en Hz)
    float margen = 2.0f; // Puedes ajustar este valor según la precisión deseada
    
    // Verifica si la frecuencia detectada está dentro del margen de la frecuencia esperada
    if (Math.abs(frecuenciaEsperada - frecuenciaDetectada) <= margen) {
        System.out.println("¡Correcto! Has tocado un " + notaEsperada);
        return true;
    } else {
        System.out.println("Nota incorrecta. Se esperaba un " + notaEsperada);
        return false;
    }
}
}
