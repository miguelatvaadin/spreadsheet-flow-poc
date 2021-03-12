package com.vaadin.client.communication;

import miguel.gwtaddonexporter.client.MyComponentServerRpcImpl;
import miguel.v8addon.client.MyComponentServerRpc;

import com.vaadin.client.ServerConnector;
import com.vaadin.shared.communication.ServerRpc;

/**
 * spreadsheet: we override this class to provide our own rpc implementation
 */
public class RpcProxy {

    static native void consoleLog(String message) /*-{
      console.log( "me:" + message );
  }-*/;

    public RpcProxy() {
    }

    public static <T extends ServerRpc> T create(Class<T> rpcInterface, ServerConnector connector) {


        if (MyComponentServerRpc.class.equals(rpcInterface)) {
            consoleLog("Returning " + MyComponentServerRpcImpl.class.getName() + " from fake RpcProxy");
            return (T) new MyComponentServerRpcImpl();
        }


        throw new IllegalStateException("" + rpcInterface + " is not supported");
    }

}
