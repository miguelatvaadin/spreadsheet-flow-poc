package org.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.shared.Registration;

@Tag("my-element")
@NpmPackage(value = "spreadsheet-poc-lit-element", version = "^0.0.11")
//@JsModule("my-element/my-element.js")
@JsModule("spreadsheet-poc-lit-element/my-element.js")
public class MyElement extends Component {

    public MyElement() {

    }

    public void alert(String msg) {
        getElement().callJsFunction("alert", msg);
    }

    public void updateSharedState(String state) {
        getElement().callJsFunction("updateSharedState", state);
    }

    public Registration addMyEventListener(ComponentEventListener<MyEvent> listener) {
        return addListener(MyEvent.class, listener);
    }

    @DomEvent("my-event")
    public static class MyEvent extends ComponentEvent<MyElement> {

        private final String payload;

        public MyEvent(MyElement source, boolean fromClient, @EventData("event.detail.message") String payload) {
            super(source, fromClient);
            this.payload = payload;
        }

        public String getPayload() {
            return payload;
        }
    }

}
