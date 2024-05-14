/**
 * @autor: Sebastian Aisalla
 */

package agentes;

import algoritmoGenetico.DatosALG;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class Agente1 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag2", null, DatosALG.generarDatos(), "CD-01-02");
            //System.out.println("Se envian datos aleatorios al Agente 2");

            ACLMessage aclmsj = blockingReceive();
            if (aclmsj.getConversationId().equals("CD-02-01")) {
                Comunicacion.msj(ACLMessage.REQUEST, getAgent(), "Ag2", null, DatosALG.generarDatos(), "CD-01-02");
                //System.out.println("Se recibe mensaje del Agente 2");
            }
            //doWait();
        }
    }
}
