package com.sven.facialid.service.faceplusplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceSetAddFaceTokenResponse extends FaceApiResponse
{

    @JsonProperty("faceset_token")
    private String facesetToken;

    @JsonProperty("outer_id")
    private String outerId;

    @JsonProperty("face_added")
    private int faceAdded;

    @JsonProperty("face_count")
    private int faceCount;

    @JsonProperty("failure_detail")
    private String[] failureDetail;

    public String getOuterId()
    {
        return outerId;
    }

    public void setOuterId(final String outerId)
    {
        this.outerId = outerId;
    }

    public int getFaceAdded()
    {
        return faceAdded;
    }

    public void setFaceAdded(final int faceAdded)
    {
        this.faceAdded = faceAdded;
    }

    public int getFaceCount()
    {
        return faceCount;
    }

    public void setFaceCount(final int faceCount)
    {
        this.faceCount = faceCount;
    }

    public String[] getFailureDetail()
    {
        return failureDetail;
    }

    public void setFailureDetail(final String[] failureDetail)
    {
        this.failureDetail = failureDetail;
    }

    public String getFacesetToken()
    {
        return facesetToken;
    }

    public void setFacesetToken(final String facesetToken)
    {
        this.facesetToken = facesetToken;
    }
}
