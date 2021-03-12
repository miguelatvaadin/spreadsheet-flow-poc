package miguel.gwtaddonexporter.client;

import miguel.v8addon.client.MyComponentServerRpc;

import com.vaadin.client.ServerConnector;
import com.vaadin.shared.communication.ServerRpc;

public class RpcProxy {

    static native void consoleLog(String message) /*-{
      console.log( "me:" + message );
  }-*/;

    public RpcProxy() {
    }

    public static <T extends ServerRpc> T create(Class<T> rpcInterface, ServerConnector connector) {

        if (MyComponentServerRpc.class.getName().equals(rpcInterface.getName())) {
            consoleLog("Returning " + MyComponentServerRpcImpl.class.getName() + " from my own fake RpcProxy");
            return (T) new MyComponentServerRpcImpl();
        }

        throw new IllegalStateException("I'm sorry. " + rpcInterface + " is not supported");
    }

}
