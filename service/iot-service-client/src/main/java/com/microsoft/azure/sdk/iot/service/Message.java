/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * IotHub specific message container
 */
public class Message
{
    /**
     * Used in message responses and feedback
     **/
    public String correlationId;
    private String deliveryAcknowledgementPropertyName = "iothub-ack";
    /**
     * [Required for two way requests] Used to correlate two-way communication.
     * Format: A case-sensitive string (up to 128 char long) of ASCII 7-bit alphanumeric chars
     * plus {'-', ':', '/', '\', '.', '+', '%', '_', '#', '*', '?', '!', '(', ')', ',', '=', '@', ';', '$', '''}.
     * Non-alphanumeric characters are from URN RFC.
     **/
    private String messageId;
    /**
     * Destination of the message
     **/
    private String to;
    /**
     * Expiry time in UTC Interpreted by hub on C2D messages. Ignored in other cases.
     **/
    private Date expiryTimeUtc;
    /**
     * Used by receiver to Abandon, Reject or Complete the message
     **/
    private String lockToken;
    /**
     * [Required in feedback messages] Used to specify the origin of messages generated by device hub.
     * Possible value: "{hub name}/"
     **/
    private String userId;
    /**
     * [Optional] Used when batching on HTTP Default: false.
     **/
    private Boolean httpBatchSerializeAsString;
    /**
     * [Optional] Used when batching on HTTP Default: UTF-8.
     **/
    private StandardCharsets httpBatchEncoding;
    /**
     * [Stamped on servicebound messages by IoT Hub] The authenticated deviceId used to send this message.
     **/
    private String connectionDeviceId;
    /**
     * [Stamped on servicebound messages by IoT Hub] The generationId of the authenticated device used to send this message.
     **/
    private String connectionDeviceGenerationId;
    /**
     * [Stamped on servicebound messages by IoT Hub] The authentication type used to send this message, format as in IoT Hub Specs
     **/
    private String connectionAuthenticationMethod;
    /**
     * [Required in feedback messages] Used in feedback messages generated by IoT Hub.
     * 0 = success 1 = message expired 2 = max delivery count exceeded 3 = message rejected
     **/
    private FeedbackStatusCode feedbackStatusCode;
    /**
     * [Required in feedback messages] Used in feedback messages generated by IoT Hub. "success", "Message expired", "Max delivery count exceeded", "Message rejected"
     **/
    private String feedbackDescription;
    /**
     * [Required in feedback messages] Used in feedback messages generated by IoT Hub.
     **/
    private String feedbackDeviceId;
    /**
     * [Required in feedback messages] Used in feedback messages generated by IoT Hub.
     **/
    private String feedbackDeviceGenerationId;
    /**
     * [Required in feedback messages] Specifies the different acknowledgement levels for message delivery.
     **/
    private DeliveryAcknowledgement deliveryAcknowledgement;
    /**
     * A bag of user-defined properties. Value can only be strings. These do not contain system properties.
     **/
    private Map<String, String> properties;
    /**
     * The message body
     **/
    private byte[] body;

    /**
     * Basic constructor
     **/
    public Message()
    {
        this.properties = new HashMap<String, String>(1);
        this.setDeliveryAcknowledgement(DeliveryAcknowledgement.Full);
    }

    /**
     * stream: a stream containing the body of the message
     *
     * @param stream The stream containing the message body
     **/
    public Message(ByteArrayInputStream stream)
    {
        this();
        if (stream != null)
        {
            this.body = stream.toString().getBytes();
        }
    }

    /**
     * byteArray: a byte array containing the body of the message
     *
     * @param byteArray The byte array containing the message body
     **/
    public Message(byte[] byteArray)
    {
        this();
        this.body = byteArray;
    }

    /**
     * @param string - a string containing the body of the message.
     *               Important: If a string is passed, the HttpBatch.SerializeAsString is set to true,
     *               and the internal byte representation is serialized as UTF-8,
     *               with HttpBatch.Encoding set to UTF-8.
     * @throws UnsupportedEncodingException This exception is thrown if unsupported encoding used
     */
    public Message(String string) throws UnsupportedEncodingException
    {
        this();
        this.body = string.getBytes(StandardCharsets.UTF_8);
    }

    public String getMessageId()
    {
        return messageId;
    }

    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String deviceId)
    {
        this.to = "/devices/" + deviceId + "/messages/devicebound";
    }

    public Date getExpiryTimeUtc()
    {
        return expiryTimeUtc;
    }

    public void setExpiryTimeUtc(Date expiryTimeUtc)
    {
        this.expiryTimeUtc = expiryTimeUtc;
    }

    public String getLockToken()
    {
        return lockToken;
    }

    public String getCorrelationId()
    {
        return correlationId;
    }

    public void setCorrelationId(String correlationId)
    {
        this.correlationId = correlationId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    /**
     * The stream content of the body.
     *
     * @return The ByteArrayOutputStream object containing the message body
     **/
    public ByteArrayOutputStream getBodyStream()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(this.body.length);
        byteArrayOutputStream.write(this.body, 0, this.body.length);
        return byteArrayOutputStream;
    }

    /**
     * The byte content of the body.
     *
     * @return The byte array of the message body
     **/
    public byte[] getBytes()
    {
        return this.body;
    }

    public DeliveryAcknowledgement getDeliveryAcknowledgement()
    {
        return this.deliveryAcknowledgement;
    }

    public void setDeliveryAcknowledgement(DeliveryAcknowledgement deliveryAcknowledgement)
    {
        this.deliveryAcknowledgement = deliveryAcknowledgement;
        this.properties.put(deliveryAcknowledgementPropertyName, deliveryAcknowledgement.name().toLowerCase());
    }

    public Map<String, String> getProperties()
    {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties)
    {
        for (Map.Entry<String, String> entry : properties.entrySet())
        {
            this.properties.put(entry.getKey(), entry.getValue());
        }
    }

    public void clearCustomProperties()
    {
        this.properties.clear();
        setDeliveryAcknowledgement(this.deliveryAcknowledgement);
    }
}
