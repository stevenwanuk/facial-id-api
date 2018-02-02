package com.sven.facialid.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megvii.cloud.http.Response;
import com.sven.facialid.service.faceplusplus.FaceService;
import com.sven.facialid.service.faceplusplus.model.FaceSetAddFaceTokenResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/faceset")
@Api(value = "faceset api", tags = { "faceset" })
public class FaceSetController
{

    @Autowired
    private FaceService service;

    @PutMapping
    @ApiOperation(value = "create a face set")
    public String create(@RequestParam final String displayName,
            @RequestParam final String outerId,
            @RequestParam final String tags,
            @RequestParam(required = false, defaultValue = "") final String FaceTokens,
            @RequestParam(required = false, defaultValue = "") final String userData,
            @RequestParam(required = false, defaultValue = "0") final int ForceMerge)
            throws Exception
    {
        Response response = service.createFaceSet(displayName, outerId, tags, FaceTokens,
                userData, ForceMerge);

        return new String(response.getContent());
    }

    @GetMapping
    @ApiOperation(value = "query face set by tags")
    public String getFacesetByTags(@RequestParam final String tags) throws Exception
    {
        Response response = service.getFaceSet(tags);
        return new String(response.getContent());
    }

    @DeleteMapping
    @ApiOperation(value = "delete face set by outerId")
    public String deleteFaceSetByOuterId(@RequestParam final String outerId)
            throws Exception
    {
        Response response = service.delete(outerId, 0);
        return new String(response.getContent());
    }

    @PutMapping("/{outerId}/{faceTokens}")
    public FaceSetAddFaceTokenResponse addFaceByOuterId(
            @PathVariable final String faceTokens, @PathVariable final String outerId)
            throws Exception
    {
        return service.addFaceByOuterId(faceTokens, outerId);
    }

    @DeleteMapping("/{outerId}/{faceTokens}")
    public String delete(@PathVariable final String outerId,
            @PathVariable final String faceTokens) throws Exception
    {
        Response response = service.delete(outerId, faceTokens);
        return new String(response.getContent());
    }

    @DeleteMapping("/{outerId}")
    public String purge(@PathVariable final String outerId) throws Exception
    {
        Response response = service.purge(outerId);
        return new String(response.getContent());
    }

}
