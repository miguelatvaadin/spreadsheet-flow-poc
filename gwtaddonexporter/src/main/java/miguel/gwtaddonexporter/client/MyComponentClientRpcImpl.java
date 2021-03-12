package miguel.gwtaddonexporter.client;

import miguel.v8addon.client.MyComponentClientRpc;

import com.vaadin.shared.communication.ClientRpc;
import com.vaadin.shared.communication.ServerRpc;

public class MyComponentClientRpcImpl implements MyComponentClientRpc, ClientRpc {

    native void consoleLog( String message) /*-{
      console.log( "me:" + message );
  }-*/;

    @Override
    public void alert(String message) {
        consoleLog("alert(" + message + ")");
    }
}
