# Proof of concept project for spreadsheet for flow

This project is a little proof of concept to showcase the main technical solutions which will be used during the implementation of the spreadsheet Vaadin component port for Flow.

The new Flow component will be reusing the existing spreadsheet V8 component as much as possible, in order to get a quick working spreadsheet component for Flow. So, teh current spreadsheet GWT widget must be reused and the java Api must be kept as is, if possible.

## Prerequisites

JDK8

## Modules

This project is composed of several modules:

- v8addon
- gwtaddonexporter
- webcomponent
- component
- demo

### v8addon

This module is a simple V8 addon project, which creates a simple GWT widget with a simple shared state and simple rpc calls in both directions.

### gwtaddonexporter

This module is a pure GWT project which:

- inherits the previous addon module
- overwrites the rpc implementations
- exports a js api (MiApi class)   
- outputs a single js file / module which can be imported from es6 (very convenient for using the api from our webcomponent)

### webcomponent

This module creates a webcomponent for our spreadsheet. The resultant webcomoponent exposes the previous js api (MiApi) properties and methods, and hosts the GWT widget.

### component

This module is a simple java module which holds the java side of the final Flow component.

It outputs a single jar which can be added as a dependency in our Flow applications.

### demo

This module is a simple Flow application, which depends on the previous jar and adds our new component inside the "About" view.

# Run the project

Clone the project, cd to the project directory and run: 

```
mvn clean install
cd demo
mvn spring-boot:run
```

And, after a long while, you will see:

> añadir pantallazo



