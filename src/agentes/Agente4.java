/**
 * @author Sebastian Aisalla
 */

package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Agente4 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends Behaviour {

        @Override
        public void action() {
            ACLMessage aclmsj = blockingReceive();
            String datosAGFinales = aclmsj.getContent();

            String[] datosSeparados = datosAGFinales.split(";");

            int X = Integer.parseInt(datosSeparados[0]);
            int Y = Integer.parseInt(datosSeparados[1]);
            int tamanioPoblacion = Integer.parseInt(datosSeparados[2]);
            int evoluciones = Integer.parseInt(datosSeparados[3]);
            int iteraciones = Integer.parseInt(datosSeparados[4]);
            int longCromosoma = Integer.parseInt(datosSeparados[5]);

            if (aclmsj.getConversationId().equals("CD-03-04")) { //si el id de la conversacion es CD-03-04

                System.out.println("------------La mejor configuracion es------------"
                        + "\nTamano Poblacion: " + tamanioPoblacion + ",\nEvoluciones: " + evoluciones
                        + ",\nIteraciones: " + iteraciones + ",\nLongitud Cromosoma: " + longCromosoma);
                System.out.println("------------El mejor individuo es----------------\n" + "X: " + X + "; " + "Y: " + Y);
            }

            doDelete();
        }

        @Override
        public boolean done() {
            return true;
        }
    }
}
