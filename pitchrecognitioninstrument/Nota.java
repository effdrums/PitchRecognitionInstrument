/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pitchrecognitioninstrument;

/**
 *
 * @author LENOVO
 */
class Nota {
    private String nombre; // Ejemplo: "Do", "Mi", "La(b)"
    private float frecuencia; // Frecuencia en Hz (opcional si se usa para cálculos futuros)

    // Constructor
    public Nota(String nombre, float frecuencia) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
    }

    // Getters
    public String getNombre() { return nombre; }
    public float getFrecuencia() { return frecuencia; }
}
