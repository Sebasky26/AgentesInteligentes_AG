/**
 * @autor: Sebastian Aisalla
 */

package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Agente3 extends Agent {

    private static final int UMBRAL_APTITUD = 27; //umbral de aptitud segun criterio propio

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage aclmsj = blockingReceive();
            String datos = aclmsj.getContent();

            if (aclmsj.getConversationId().equals("CD-02-03")) { //si el id de la conversacion es CD-02-03
                String[] partes = datos.split(";");
                int x = Integer.parseInt(partes[0]);
                int y = Integer.parseInt(partes[1]);
                int tamanioPoblacion = Integer.parseInt(partes[2]);
                int evoluciones = Integer.parseInt(partes[3]);
                int iteraciones = Integer.parseInt(partes[4]);

                if (Math.abs(x) > UMBRAL_APTITUD && Math.abs(y) > UMBRAL_APTITUD) {
                    // Si es la mejor población, envía los datos al Agente 4
                    System.out.println("------------La mejor configuracion es------------"
                            + "\nTamano Poblacion: " + tamanioPoblacion + ",\nEvoluciones: " + evoluciones + ",\nIteraciones: " + iteraciones);
                    Comunicacion.msj(ACLMessage.INFORM, getAgent(), "Ag4", datos, null, "CD-03-04");
                } else {
                    // Si no es la mejor población, solicita más datos al Agente 2
                    System.out.println("------------------Configuracion------------------"
                            + "\nTamano Poblacion: " + tamanioPoblacion + ",\nEvoluciones: " + evoluciones + ",\nIteraciones: " + iteraciones);
                    System.out.println("-------------------------------------------------");
                    System.out.println("No es la mejor población!");
                    System.out.println("-------------------------------------------------\n");
                    Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag2", null, null, "CD-03-02");
                }
            }
            doWait();
        }
    }
}


