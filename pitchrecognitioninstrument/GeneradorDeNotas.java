/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pitchrecognitioninstrument;

import java.util.List;
import java.util.Random;

/**
 *
 * @author LENOVO
 */
class GeneradorDeNotas {
    private List<Nota> listaNotas; // Todas las notas posibles
    private Random random;

    // Constructor inicializa las notas disponibles
    public GeneradorDeNotas() {
        random = new Random();
        listaNotas = List.of(
            new Nota("Do", 261.63f),
            new Nota("Re", 293.66f),
            new Nota("Mi", 329.63f),
            new Nota("Fa", 349.23f),
            new Nota("Sol", 392.00f),
            new Nota("La", 440.00f),
            new Nota("Si", 493.88f)
        );
    }

    // Genera una nota aleatoria
    public Nota generarNotaAleatoria() {
        return listaNotas.get(random.nextInt(listaNotas.size()));
    }
}
