package com.sven.facialid.controller.api;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megvii.cloud.http.Response;
import com.sven.facialid.service.faceplusplus.FaceService;
import com.sven.facialid.service.faceplusplus.model.FaceAttachUserIdResponse;
import com.sven.facialid.service.faceplusplus.model.FaceDetectResponse;
import com.sven.facialid.service.faceplusplus.model.FaceGetDetailResponse;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/face")
@Api(value = "face api", tags = { "face" })
public class FaceController
{


    @Autowired
    private FaceService faceService;

    @PostMapping
    public FaceDetectResponse recognize(@RequestBody final String base64String)
            throws IOException, Exception
    {

        return faceService.detect(Base64.decodeBase64(base64String));
    }

    @PostMapping("/user")
    public FaceAttachUserIdResponse recognize(@RequestParam final String faceToken,
            @RequestParam final String userId) throws IOException, Exception
    {
        return faceService.attachUserIdToFacialId(faceToken, userId);
    }
    
    @GetMapping("/{faceToken}")
    public FaceGetDetailResponse getDetail(@PathVariable final String faceToken) throws IOException, Exception
    {
        return faceService.getFacialIdDetail(faceToken);
    }

    @PostMapping("/search")
    public String search(@RequestParam final String outerId,
            @RequestBody final String base64String) throws IOException, Exception
    {
        Response response = faceService.search(Base64.decodeBase64(base64String), outerId);
        return new String(response.getContent());
    }

}
