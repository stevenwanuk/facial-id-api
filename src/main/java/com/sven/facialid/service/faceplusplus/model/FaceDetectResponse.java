package com.sven.facialid.service.faceplusplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceDetectResponse extends FaceApiResponse
{

    @JsonProperty("image_id")
    private String imageId;

    @JsonProperty("faces")
    private Face[] faces;

    public String getImageId()
    {
        return imageId;
    }

    public void setImageId(final String imageId)
    {
        this.imageId = imageId;
    }

    public Face[] getFaces()
    {
        return faces;
    }

    public void setFaces(final Face[] faces)
    {
        this.faces = faces;
    }

}
