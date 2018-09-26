/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service;

/**
 * Enum for device status
 */
public enum DeviceStatus
{
    Enabled("enabled"), Disabled("disabled");

    private final String status;

    DeviceStatus(String status)
    {
        this.status = status;
    }

    public static DeviceStatus fromString(String status)
    {
        for (DeviceStatus deviceStatus : DeviceStatus.values())
        {
            if (deviceStatus.status.equalsIgnoreCase(status))
            {
                return deviceStatus;
            }
        }
        return null;
    }

    public String getValue()
    {
        return status;
    }
}