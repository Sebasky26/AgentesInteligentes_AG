/**
 * @autor: Sebastian Aisalla
 */

package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;

public class Comunicacion {
    public static void msj(int tipoMSJ, Agent emisor, String receptor,
                            String contenidoStr, Serializable contenidoObj, String conversationId) {
        ACLMessage acl = new ACLMessage(tipoMSJ);
        AID receptorID = new AID();
        receptorID.setLocalName(receptor);
        acl.addReceiver(receptorID);
        acl.setSender(emisor.getAID());
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);

        if (contenidoStr == null) {
            try {
                acl.setContentObject(contenidoObj);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            acl.setContent(contenidoStr);
        }

        acl.setConversationId(conversationId);

        emisor.send(acl);
    }
}
