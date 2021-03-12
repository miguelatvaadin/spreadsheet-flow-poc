package miguel.v8addon.client;

import miguel.v8addon.MyComponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(MyComponent.class)
public class MyComponentConnector extends AbstractComponentConnector {

    // ServerRpc is used to send events to server. Communication implementation
    // is automatically created here
    MyComponentServerRpc rpc = RpcProxy.create(MyComponentServerRpc.class, this);

    native void consoleLog( String message) /*-{
      console.log( "me:" + message );
  }-*/;

    public MyComponentConnector() {
        
        // To receive RPC events from server, we register ClientRpc implementation 
        //registerRpc(MyComponentClientRpc.class, Window::alert);
        registerRpc(MyComponentClientRpc.class, s -> getWidget().setLabelText(s));

        // We choose listed for mouse clicks for the widget
        getWidget().addClickHandler(event -> {
            consoleLog("clicked inside connector");
                final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
                        .buildMouseEventDetails(event.getNativeEvent(),
                                getWidget().getElement());
                consoleLog("calling server rpc...");
                // When the widget is clicked, the event is sent to server with ServerRpc
                rpc.clicked(mouseDetails);
            }
        );

    }

    //miguel
    public MyComponentServerRpc getRpc() {
        return rpc;
    }

    // We must implement getWidget() to cast to correct type 
    // (this will automatically create the correct widget type)
    @Override
    public MyComponentWidget getWidget() {
        return (MyComponentWidget) super.getWidget();
    }

    // We must implement getState() to cast to correct type
    @Override
    public MyComponentState getState() {
        return (MyComponentState) super.getState();
    }

    // Whenever the state changes in the server-side, this method is called
    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        //super.onStateChanged(stateChangeEvent);

        final String text = getState().text;
        getWidget().setOutputText(text);
    }
}
