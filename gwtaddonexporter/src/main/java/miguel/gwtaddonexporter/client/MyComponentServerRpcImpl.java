package miguel.gwtaddonexporter.client;

import java.util.function.Consumer;

import miguel.v8addon.client.MyComponentServerRpc;

import com.vaadin.shared.MouseEventDetails;

public class MyComponentServerRpcImpl implements MyComponentServerRpc {

    private Consumer<MouseEventDetails> callback;

    public void setCallback(Consumer<MouseEventDetails> callback) {
        this.callback = callback;
    }

    native void consoleLog(String message) /*-{
      console.log( "me:" + message );
  }-*/;

    native void call(Object f, Object ...args) /*-{
      f(args);
  }-*/;

    @Override
    public void clicked(MouseEventDetails mouseDetails) {
        consoleLog("clicked(" + mouseDetails.serialize() + ")");
        if (callback != null) {
            consoleLog("calling callback..." + callback.getClass().getName());
            call(callback, mouseDetails);
        } else {
            consoleLog("no callback");
        }
    }
}
