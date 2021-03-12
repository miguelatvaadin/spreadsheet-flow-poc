package com.example.application.views.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import com.example.application.components.MyElement2;
import com.example.application.views.main.MainView;
import org.example.MyElement;

import com.vaadin.flow.router.RouteAlias;

@CssImport("./views/helloworld/hello-world-view.css")
@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Hello World")
public class HelloWorldView extends VerticalLayout {

    private final MyElement mielemento;
    private final Label received;

    private TextField name;
    private Button updateName;
    private TextField updateSharedStateParam;
    private Button updateSharedState;
    private TextField alertParam;
    private Button callAlert;

    public HelloWorldView() {
        addClassName("hello-world-view");

        //add(new MyElement2());

        add(mielemento = new MyElement());


        add(new H1("These are usual flow server side components"));

        HorizontalLayout l;
        add(l =new HorizontalLayout());
        name = new TextField("Value");
        updateName = new Button("Update webcomponent's name attribute");
        l.add(name, updateName);
        l.setVerticalComponentAlignment(Alignment.END, name, updateName);
        updateName.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
            mielemento.getElement().setAttribute("name", "Hi " + name.getValue());
        });

        add(l =new HorizontalLayout());
        updateSharedStateParam = new TextField("New shared state");
        updateSharedState = new Button("Call webcomponent's updateSharedState method");
        l.add(updateSharedStateParam, updateSharedState);
        l.setVerticalComponentAlignment(Alignment.END, updateSharedState, updateSharedStateParam);
        updateSharedState.addClickListener(e -> {
            mielemento.updateSharedState("Hi " + updateSharedStateParam.getValue());
        });

        add(l =new HorizontalLayout());
        alertParam = new TextField("Parameter");
        callAlert = new Button("Call webcomponent's alert method");
        l.add(alertParam, callAlert);
        l.setVerticalComponentAlignment(Alignment.END, alertParam, callAlert);
        callAlert.addClickListener(e -> {
            mielemento.alert("Hi " + alertParam.getValue());
        });

        received = new Label("");
        add(received);
        mielemento.addMyEventListener(e -> {
            received.setText("Received at server " + e.getPayload());
        });

    }

}
