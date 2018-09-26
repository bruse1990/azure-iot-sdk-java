/*
 *  Copyright (c) Microsoft. All rights reserved.
 *  Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.android.iothubservices;

import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import com.microsoft.azure.sdk.iot.android.helper.Tools;
import com.microsoft.azure.sdk.iot.common.TestConstants;
import com.microsoft.azure.sdk.iot.common.iothubservices.DeviceTwinWithVersionCommon;
import org.junit.BeforeClass;

import java.io.IOException;

public class DeviceTwinWithVersionIT extends DeviceTwinWithVersionCommon
{
    @BeforeClass
    public static void setup() throws IOException
    {
        Bundle bundle = InstrumentationRegistry.getArguments();
        iotHubConnectionString = Tools.retrieveEnvironmentVariableValue(TestConstants.IOT_HUB_CONNECTION_STRING_ENV_VAR_NAME, bundle);
        DeviceTwinWithVersionCommon.setUp();
    }
}
