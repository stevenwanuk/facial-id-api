package com.sven.facialid.service.faceplusplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceGetDetailResponse extends FaceApiResponse
{

    @JsonProperty("face_token")
    private String faceToken;
    
    @JsonProperty("user_id")
    private String userId;

    public String getFaceToken()
    {
        return faceToken;
    }

    public void setFaceToken(final String faceToken)
    {
        this.faceToken = faceToken;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(final String userId)
    {
        this.userId = userId;
    }

}
