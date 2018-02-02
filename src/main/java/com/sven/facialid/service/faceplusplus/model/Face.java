package com.sven.facialid.service.faceplusplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Face
{

    @JsonProperty("face_token")
    private String faceToken;

    @JsonProperty("face_rectangle")
    private FaceRectangle faceRectangle;

    @JsonProperty("attributes")
    private FaceAttributes attributes;

    public String getFaceToken()
    {
        return faceToken;
    }

    public void setFaceToken(final String faceToken)
    {
        this.faceToken = faceToken;
    }

    public FaceRectangle getFaceRectangle()
    {
        return faceRectangle;
    }

    public void setFaceRectangle(final FaceRectangle faceRectangle)
    {
        this.faceRectangle = faceRectangle;
    }

    public FaceAttributes getAttributes()
    {
        return attributes;
    }

    public void setAttributes(final FaceAttributes attributes)
    {
        this.attributes = attributes;
    }

}
