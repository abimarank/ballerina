/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.net.http.nativeimpl.response;

import org.ballerinalang.bre.Context;
import org.ballerinalang.model.types.TypeEnum;
import org.ballerinalang.model.values.BStruct;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.natives.AbstractNativeFunction;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.Attribute;
import org.ballerinalang.natives.annotations.BallerinaAnnotation;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.net.http.Constants;
import org.wso2.carbon.transport.http.netty.message.HTTPCarbonMessage;

/**
 * Native function to set a custom Http Reason Phrase.
 */
@BallerinaFunction(
        packageName = "ballerina.net.http.response",
        functionName = "setReasonPhrase",
        args = {@Argument(name = "res", type = TypeEnum.STRUCT, structType = "Response",
                          structPackage = "ballerina.net.http"),
                @Argument(name = "reasonPhrase", type = TypeEnum.STRING)},
        isPublic = true
)
@BallerinaAnnotation(annotationName = "Description", attributes = {@Attribute(name = "value",
        value = "Sets a custom HTTP Reason phrase") })
@BallerinaAnnotation(annotationName = "Param", attributes = {@Attribute(name = "res",
        value = "A response message") })
@BallerinaAnnotation(annotationName = "Param", attributes = {@Attribute(name = "reasonPhrase",
        value = "Reason phrase value") })
public class SetReasonPhrase extends AbstractNativeFunction {

    @Override
    public BValue[] execute(Context context) {
        BStruct responseStruct  = ((BStruct) getRefArgument(context, 0));
        HTTPCarbonMessage httpCarbonMessage = (HTTPCarbonMessage) responseStruct
                .getNativeData(Constants.TRANSPORT_MESSAGE);
        String reasonPhrase = getStringArgument(context, 0);
        httpCarbonMessage.setProperty(Constants.HTTP_REASON_PHRASE, reasonPhrase);
        return VOID_RETURN;
    }
}
