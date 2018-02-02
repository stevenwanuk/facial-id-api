package com.sven.facialid.service.faceplusplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceAttachUserIdResponse extends FaceApiResponse
{

    @JsonProperty("face_token")
    private String faceToken;

    public String getFaceToken()
    {
        return faceToken;
    }

    public void setFaceToken(final String faceToken)
    {
        this.faceToken = faceToken;
    }

}
