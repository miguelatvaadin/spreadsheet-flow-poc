package miguel.gwtaddonexporter.client;

import java.util.function.Consumer;

import jsinterop.annotations.JsType;
import miguel.v8addon.client.MyComponentClientRpc;
import miguel.v8addon.client.MyComponentConnector;
import miguel.v8addon.client.MyComponentWidget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.metadata.ConnectorBundleLoader;
import com.vaadin.shared.MouseEventDetails;

import elemental.json.Json;
import elemental.json.JsonObject;

/**
 *
 * this is the public api which we will export to js
 *
 */
//@JsType(namespace = JsPackage.GLOBAL, name = "MyApi")
@JsType
public class MiApi {

    private final MyComponentWidget widget;
    private MyComponentConnector connector;

    native void consoleLog(String message) /*-{
      console.log( "me:" + message );
  }-*/;

    /**
     * receives the element where the widget mut be embedded into, and publishes
     * the methods which can be used from js
     *
     * @param element
     */
    public MiApi(Element element) {
        // Only support eager connectors for now
        ConnectorBundleLoader.get()
                .loadBundle(ConnectorBundleLoader.EAGER_BUNDLE_NAME, null);

        connector = new MyComponentConnector();
        widget = connector.getWidget();

        RootPanel.getForElement(element).add(widget);
        consoleLog("widget appended !");
    }

    /**
     * used to register callbacks for the click event at the button inside the widget
     *
     * @param callback
     */
    public void registerClicked(Consumer<MouseEventDetails> callback) {
        consoleLog("registering callback");
        ((MyComponentServerRpcImpl)connector.getRpc()).setCallback(callback);
    }

    /**
     * used to put some text inside the label inside the widget, from js
     *
     * @param msg
     */
    public void alert(String msg) {
        ((MyComponentClientRpc)connector.getRpcImplementations(MyComponentClientRpc.class.getName()).iterator().next()).alert(msg);
    }

    /**
     * used to update the connector's state from js
     *
     * @param msg
     */
    public void setState(String msg) {
        JsonObject stateJson = Json.createObject();
        stateJson.put("text", msg); // esto no hace falta, realmente. Solo para controlar qué propiedades han cambiado
        boolean initialStateChange = false;
        connector.getState().text = msg;
        connector.onStateChanged(new StateChangeEvent(connector, stateJson, initialStateChange));
    }
}
