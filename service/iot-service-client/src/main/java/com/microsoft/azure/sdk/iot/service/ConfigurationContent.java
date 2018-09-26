package com.microsoft.azure.sdk.iot.service;

import com.microsoft.azure.sdk.iot.deps.serializer.ConfigurationContentParser;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationContent
{
    protected Map<String, Map<String, Object>> modulesContent;
    protected Map<String, Object> deviceContent;

    public ConfigurationContent()
    {
        // Codes_SRS_SERVICE_SDK_JAVA_CONFIGURATION_CONTENT_28_001: [The constructor shall initialize modulesContent and deviceContent fields.]
        this.modulesContent = new HashMap<>();
        this.deviceContent = new HashMap<>();
    }

    public Map<String, Map<String, Object>> getModulesContent()
    {
        return modulesContent;
    }

    // Codes_SRS_SERVICE_SDK_JAVA_CONFIGURATION_CONTENT_28_002: [The ConfigurationContent class shall have the following properties: modulesContent and deviceContent
    public void setModulesContent(Map<String, Map<String, Object>> modulesContent)
    {
        this.modulesContent = modulesContent;
    }

    public Map<String, Object> getDeviceContent()
    {
        return deviceContent;
    }

    public void setDeviceContent(Map<String, Object> deviceContent)
    {
        this.deviceContent = deviceContent;
    }

    public ConfigurationContentParser toConfigurationContentParser()
    {
        // Codes_SRS_SERVICE_SDK_JAVA_CONFIGURATION_CONTENT_34_003: [This function shall return a configuration parser instance with the same modules content and device content as this object.]
        ConfigurationContentParser parser = new ConfigurationContentParser();
        parser.setModulesContent(this.modulesContent);
        parser.setDeviceContent(this.deviceContent);
        return parser;
    }
}
