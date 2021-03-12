package miguel.gwtaddonexporter.client;

import miguel.v8addon.client.MyComponentClientRpc;
import miguel.v8addon.client.MyComponentServerRpc;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.rpc.ProxyCreator;

public class ServiceInterfaceProxyGenerator extends Generator {

    native void consoleLog( String message) /*-{
      console.log( "me:" + message );
  }-*/;

    @Override
    public String generate(TreeLogger logger, GeneratorContext ctx, String requestedClass) throws UnableToCompleteException {

        TypeOracle typeOracle = ctx.getTypeOracle();
        assert (typeOracle != null);

        JClassType remoteService = typeOracle.findType(requestedClass);
        if (remoteService == null) {
            logger.log(TreeLogger.ERROR, "Unable to find metadata for type '"
                    + requestedClass + "'", null);
            throw new UnableToCompleteException();
        }

        if (remoteService.isInterface() == null) {
            logger.log(TreeLogger.ERROR, remoteService.getQualifiedSourceName()
                    + " is not an interface", null);
            throw new UnableToCompleteException();
        }

        consoleLog("Intercepting generation for type '"
                + requestedClass + "'");

        if (MyComponentServerRpc.class.getName().equals(requestedClass)) {
            consoleLog("Returning " + MyComponentServerRpcImpl.class.getName() + " from ServiceInterfaceProxyGenerator");
            return MyComponentServerRpcImpl.class.getName();
        }

        ProxyCreator proxyCreator = new ProxyCreator(remoteService);

        TreeLogger proxyLogger = logger.branch(TreeLogger.DEBUG,
                "Generating client proxy for remote service interface '"
                        + remoteService.getQualifiedSourceName() + "'", null);

        return proxyCreator.create(proxyLogger, ctx).getResultTypeName();
    }
}
