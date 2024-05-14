/**
 * @author Sebastian Aisalla
 */

package agentes;

import algoritmoGenetico.Genetica;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Agente2 extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            try {
                ACLMessage aclmsj = blockingReceive();
                if (aclmsj.getConversationId().equals("CD-01-02")) {//si el id de la conversacion es CD-01-02
                    int[] datos = (int[]) aclmsj.getContentObject();
                    int tamanioPoblacion = datos[0];
                    int evoluciones = datos[1];
                    int iteraciones = datos[2];
                    int longCromosoma = datos[3];

                    Genetica genetica = new Genetica();
                    int[] XY = genetica.evolucionar(genetica.get_Poblacion(genetica.configurarAG(tamanioPoblacion, longCromosoma)), evoluciones, iteraciones);

                    int x = XY[0];
                    int y = XY[1];

                    Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag3", x + ";" + y
                            + ";" + tamanioPoblacion + ";" + evoluciones + ";" + iteraciones, null, "CD-02-03");
                    //System.out.println("Se envian datos al Agente 3");
                } else {
                    if (aclmsj.getConversationId().equals("CD-03-02")) { //si el id de la conversacion es CD-03-02
                        Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag1", null, null, "CD-02-01");
                        //System.out.println("Pedir mas datos al agente 1");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            doWait();
        }
    }
}
