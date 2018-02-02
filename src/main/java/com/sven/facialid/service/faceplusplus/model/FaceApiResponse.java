package com.sven.facialid.service.faceplusplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceApiResponse
{
    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("time_used")
    private int timeUsed;

    @JsonProperty("error_message")
    private int errorMessage;

    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId(final String requestId)
    {
        this.requestId = requestId;
    }

    public int getTimeUsed()
    {
        return timeUsed;
    }

    public void setTimeUsed(final int timeUsed)
    {
        this.timeUsed = timeUsed;
    }

    public int getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(final int errorMessage)
    {
        this.errorMessage = errorMessage;
    }
    
    
}
