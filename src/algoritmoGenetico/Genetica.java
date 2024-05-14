/**
 * @author Sebastian Aisalla
 */

package algoritmoGenetico;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

public class Genetica {

    //Metodo que configura el algoritmo genetico
    public Configuration configurarAG(int tamanioPoblacion, int longGene) {
        try {
            Configuration.reset(); //reseteamos la configuracion

            Configuration conf = new DefaultConfiguration(); //configuracion por defecto
            conf.setFitnessFunction(new FunAptitud()); //Funcion de aptitud
            conf.setPopulationSize(tamanioPoblacion); //tamaño de la poblacion

            Chromosome ejemplo = new Chromosome(conf, genesLong(longGene, conf)); //cromosoma de ejemplo
            conf.setSampleChromosome(ejemplo);
            return conf;
        } catch (Exception e) {
            return null;
        }
    }

    public Genotype get_Poblacion(Configuration conf) {
        try {
            Genotype poblacion = Genotype.randomInitialGenotype(conf); //poblacion inicial
            return poblacion;
        } catch (InvalidConfigurationException e) {
            return null;
        }
    }

    //Metodo que evoluciona la poblacion
    public int[] evolucionar(Genotype poblacion, int numeroEvoluciones, int numeroIteraccion) {
        int x = 0;
        int y = 0;
        int[] XY;

        System.out.println("-----------------Poblacion-----------------------");
        descomponerTodo(poblacion.getChromosomes()); //descomponemos la poblacion
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < numeroIteraccion; i++) {
            poblacion.evolve(numeroEvoluciones);
        }
        XY = descomponerIndividuo(poblacion.getFittestChromosome()); //descomponemos el mejor individuo
        return XY;
    }

    public void descomponerTodo(IChromosome[] ics) {
        for (IChromosome ic : ics) {
            descomponerIndividuo(ic);
        }

    }

    private int[] descomponerIndividuo(IChromosome ic) {
        int[] XY = new int[2];

        int sx = (int) ic.getGene(0).getAllele();
        int sy = (int) ic.getGene(6).getAllele();
        String vX = (int) ic.getGene(1).getAllele() + ""
                + (int) ic.getGene(2).getAllele() + ""
                + (int) ic.getGene(3).getAllele() + ""
                + (int) ic.getGene(4).getAllele() + ""
                + (int) ic.getGene(5).getAllele();

        String vY = (int) ic.getGene(7).getAllele() + ""
                + (int) ic.getGene(8).getAllele() + ""
                + (int) ic.getGene(9).getAllele() + ""
                + (int) ic.getGene(10).getAllele() + ""
                + (int) ic.getGene(11).getAllele();

        int valX = Integer.parseInt(vX, 2);
        int valY = Integer.parseInt(vY, 2);

        if (sx == 0) {
            valX = -valX;
        }

        if (sy == 0) {
            valY = -valY;
        }

        System.out.println(valX + "; " + valY); //imprimimos los valores de X y Y

        XY[0] = valX;
        XY[1] = valY;

        return XY;
    }

    private Gene[] genesLong(int longGen, Configuration conf) {
        Gene[] genes = new Gene[longGen];
        for (int i = 0; i < longGen; i++) {
            try {
                genes[i] = new IntegerGene(conf, 0, 1);
            } catch (InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return genes;
    }
}
