/**
 * @author Sebastian Aisalla
 */

package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Agente4 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage aclmsj = blockingReceive();
            String datos = aclmsj.getContent();

            if (aclmsj.getConversationId().equals("CD-03-04")) { //si el id de la conversacion es CD-03-04
                String[] partes = datos.split(";");
                System.out.println("------------El mejor individuo es----------------\n" + "X: " + partes[0] + "; " + "Y: " + partes[1]);
            }
            doDelete();
        }
    }
}
