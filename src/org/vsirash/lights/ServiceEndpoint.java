package org.vsirash.lights;

import javax.websocket.*;
import java.io.IOException;

/**
 * Created by Volodymyr Sirash on 8/16/16.
 */
public class ServiceEndpoint extends Endpoint {
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        RemoteEndpoint.Basic remoteEndpointBasic = session.getBasicRemote();
        session.addMessageHandler(new EchoMessageHandlerText(remoteEndpointBasic));
        //session.addMessageHandler(new EchoMessageHandlerBinary(remoteEndpointBasic));
    }

    private static class EchoMessageHandlerText
            implements MessageHandler.Partial<String> {

        private final RemoteEndpoint.Basic remoteEndpointBasic;

        private EchoMessageHandlerText(RemoteEndpoint.Basic remoteEndpointBasic) {
            this.remoteEndpointBasic = remoteEndpointBasic;
        }

        @Override
        public void onMessage(String message, boolean last) {
          /*  try {
                if (remoteEndpointBasic != null) {
                    remoteEndpointBasic.sendText(message, last);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
        }
    }
}
