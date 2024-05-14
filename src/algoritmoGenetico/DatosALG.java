/**
 * @autor: Sebastian Aisalla
 */

package algoritmoGenetico;

import jade.util.leap.Serializable;

import java.util.Random;

public class DatosALG implements Serializable {
    private static Random rand = new Random();

    // Metodo que genera numeros aleatorios para el algoritmo genetico
    public static int[] generarDatos() {
        int tamanioPoblacion = rand.nextInt(5) + 1;
        int evoluciones = rand.nextInt(15) + 1;
        int iteracciones = rand.nextInt(8) + 1;
        int longCromosoma = 12;

        return new int[]{tamanioPoblacion, evoluciones, iteracciones, longCromosoma};
    }
}
