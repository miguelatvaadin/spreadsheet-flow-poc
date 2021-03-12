package miguel.v8addon.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.client.ui.VButton;

// Extend any GWT Widget
public class MyComponentWidget extends HorizontalPanel {

    private final Label label;
    private final VButton boton;
    private final Label estado;

    public MyComponentWidget() {

        add(boton = new VButton());
        boton.setText("Click me");
        boton.addStyleName("boton");

        Label l;
        add(l = new Label("Alert called for:"));
        l.addStyleName("label");
        add(label = new Label("Etiqueta"));
        label.addStyleName("label0");

        add(l = new Label("Shared state:"));
        l.addStyleName("label");
        add(estado = new Label(""));
        estado.addStyleName("label1");

        // CSS class-name should not be v- prefixed
        setStyleName("v8addon");

        // State is set to widget in MyComponentConnector
    }

    public void addClickHandler(ClickHandler handler) {
        boton.addClickHandler(handler);
    }

    public void setLabelText(String text) {
        label.setText(text);
    }

    public void setOutputText(String text) {
        estado.setText(text);
    }

}