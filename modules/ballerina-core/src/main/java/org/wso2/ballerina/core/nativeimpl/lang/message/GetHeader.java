package org.wso2.ballerina.core.nativeimpl.lang.message;


import org.osgi.service.component.annotations.Component;
import org.wso2.ballerina.core.interpreter.Context;
import org.wso2.ballerina.core.model.types.TypeEnum;
import org.wso2.ballerina.core.model.values.BMessage;
import org.wso2.ballerina.core.model.values.BString;
import org.wso2.ballerina.core.model.values.BValue;
import org.wso2.ballerina.core.nativeimpl.AbstractNativeFunction;
import org.wso2.ballerina.core.nativeimpl.annotations.Argument;
import org.wso2.ballerina.core.nativeimpl.annotations.BallerinaFunction;
import org.wso2.ballerina.core.nativeimpl.annotations.ReturnType;

/**
 * Get the Headers of the Message
 */
@BallerinaFunction(
        packageName = "ballerina.lang.message",
        functionName = "getHeader",
        args = {@Argument(name = "message", type = TypeEnum.MESSAGE),
                @Argument(name = "headerName", type = TypeEnum.STRING)},
        returnType = {@ReturnType(type = TypeEnum.STRING)},
        isPublic = true
)

@Component(
        name = "func.lang.message_getHeader",
        immediate = true,
        service = AbstractNativeFunction.class
)
public class GetHeader extends AbstractNativeFunction {

    public BValue[] execute(Context ctx) {
        BMessage msg = (BMessage) getArgument(ctx, 0);
        String headerName = getArgument(ctx, 1).stringValue();
        String headerValue = msg.getHeader(headerName);
        return getBValues(new BString(headerValue));
    }
}
