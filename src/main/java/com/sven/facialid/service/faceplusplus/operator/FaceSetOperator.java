package com.sven.facialid.service.faceplusplus.operator;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.sven.facialid.service.faceplusplus.model.FaceSetAddFaceTokenResponse;
import com.sven.facialid.util.Constants;

@Component
public class FaceSetOperator extends ApiOperator
{
//
//    /**
//     * 为一个已经创建的FaceSet添加人脸标识face_token。一个FaceSet最多存储1,000个face_token。 Add face_token into
//     * an existing FaceSet. One FaceSet can hold up to 1000 face_token.
//     * 
//     * @param faceTokens
//     *            人脸标识face_token组成的字符串，可以是一个或者多个，用逗号分隔。最多不超过5个face_token One or more
//     *            face_token, comma-seperated. The number of face_token must not be larger
//     *            than 5.
//     * @param faceSetToken
//     *            FaceSet的标识 The id of Faceset.
//     * @return
//     * @throws Exception
//     */
//    public Response addFaceByFaceToken(final String faceTokens, final String faceSetToken)
//            throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.ADDFACE;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_FACE_TOKENS, faceTokens);
//        map.put(Constants.KEY_FOR_FACESET_TOKEN, faceSetToken);
//        return HttpRequest.post(url, map, null);
//    }
//
    /**
     * 为一个已经创建的FaceSet添加人脸标识face_token。一个FaceSet最多存储1,000个face_token。 Add face_token into
     * an existing FaceSet. One FaceSet can hold up to 1000 face_token.
     * 
     * @param FaceTokens
     *            人脸标识face_token组成的字符串，可以是一个或者多个，用逗号分隔。最多不超过5个face_token One or more
     *            face_token, comma-seperated. The number of face_token must not be larger
     *            than 5.
     * @param outerId
     *            用户提供的FaceSet标识 User-defined id of Faceset.
     * @return
     * @throws Exception
     */
    public FaceSetAddFaceTokenResponse addFaceByOuterId(final String FaceTokens, final String outerId)
            throws Exception
    {
    
        String url = this.getBaseUrl() + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
                + Constants.ADDFACE;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        builder.queryParam(Constants.KEY_FOR_APIKEY, this.getApiKey());
        builder.queryParam(Constants.KEY_FOR_APISECRET, this.getApiSecret());
        builder.queryParam(Constants.KEY_FOR_FACE_TOKENS, FaceTokens);
        builder.queryParam(Constants.KEY_FOR_OUTER_ID, outerId);
        HttpEntity<FaceSetAddFaceTokenResponse> response = this.getRestTemplate().exchange(builder.toUriString(),
                HttpMethod.POST, null, FaceSetAddFaceTokenResponse.class);
        
        return response.getBody();
    }
//    
//    /**
//     * 创建一个人脸的集合FaceSet，用于存储人脸标识face_token。一个FaceSet能够存储1,000个face_token。 Create a face
//     * collection called FaceSet to store face_token. One FaceSet can hold up to 1000
//     * face_token
//     * 
//     * @param displayName
//     *            人脸集合的名字，256个字符，不能包括字符^@,&=*'" The name of FaceSet. No more than 256
//     *            characters, and must not contain characters ^@,&=*'"
//     * @param outerId
//     *            账号下全局唯一的FaceSet自定义标识，可以用来管理FaceSet对象。最长255个字符，不能包括字符^@,&=*'" Custom
//     *            unique id of Faceset under your account, used for managing FaceSet
//     *            objects. No more than 255 characters, and must not contain characters
//     *            ^@,&=*'"
//     * @param tags
//     *            FaceSet自定义标签组成的字符串，用来对FaceSet分组。最长255个字符，多个tag用逗号分隔， 每个tag不能包括字符^@,&=*'"
//     *            String consists of FaceSet custom tags, used for categorizing FaceSet,
//     *            comma-seperated. No more than 255 characters, and must not contain
//     *            characters ^@,&=*'"
//     * @param FaceTokens
//     *            人脸标识face_token，可以是一个或者多个，用逗号分隔。最多不超过5个face_token One or more face_token,
//     *            comma-seperated. The number of face_token must not be larger than 5.
//     * @param userData
//     *            自定义用户信息，不大于16KB，不能包括字符^@,&=*'" Custom user information. No larger than
//     *            16KB, and must not contain characters ^@,&=*'"
//     * @param ForceMerge
//     *            在传入outer_id的情况下，如果outer_id已经存在，是否将face_token加入已经存在的FaceSet中
//     *            0：不将face_tokens加入已存在的FaceSet中，直接返回FACESET_EXIST错误
//     *            1：将face_tokens加入已存在的FaceSet中 默认值为0 Determine whether or not add
//     *            face_token into existing FaceSet, if outer_id is passed and outer_id
//     *            already exists. 0: face_tokens will not be added into existing FaceSet,
//     *            and return FACESET_EXIST error message instead. 1: add face_tokens into
//     *            existing FaceSet. The default value is 0.
//     * @return
//     * @throws Exception
//     */
//    public FaceSetCreateResponse createFaceSet(final String displayName,
//            final String outerId, final String tags, final String FaceTokens,
//            final String userData, final int ForceMerge)
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.CREATE;
//
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
//
//        builder.queryParam(Constants.KEY_FOR_APIKEY, apiKey);
//        builder.queryParam(Constants.KEY_FOR_APISECRET, apiSecret);
//        if (!StringUtils.isEmpty(displayName))
//        {
//            builder.queryParam(Constants.KEY_FOR_DISPLAY_NAME, displayName);
//        }
//        if (!StringUtils.isEmpty(outerId))
//        {
//            builder.queryParam(Constants.KEY_FOR_OUTER_ID, outerId);
//        }
//        if (!StringUtils.isEmpty(tags))
//        {
//            builder.queryParam(Constants.KEY_FOR_TAGS, tags);
//        }
//        if (!StringUtils.isEmpty(FaceTokens))
//        {
//            builder.queryParam(Constants.KEY_FOR_FACE_TOKENS, FaceTokens);
//        }
//        if (!StringUtils.isEmpty(userData))
//        {
//            builder.queryParam(Constants.KEY_FOR_USER_DATA, userData);
//        }
//        builder.queryParam(Constants.KEY_FOR_FORCE_MERGE,
//                String.valueOf(ForceMerge));
//
//        HttpEntity<FaceSetCreateResponse> response = restTemplate.exchange(url,
//                HttpMethod.POST, null, FaceSetCreateResponse.class);
//
//        return response.getBody();
//    }
//
//
//    /**
//     * 删除一个人脸集合。 Delete a FaceSet.
//     * 
//     * @param faceSetToken
//     *            FaceSet的标识 The id of Faceset.
//     * @param checkEmpty
//     *            删除时是否检查FaceSet中是否存在face_token，默认值为1 0：不检查 1：检查
//     *            如果设置为1，当FaceSet中存在face_token则不能删除 Check if the FaceSet contains
//     *            face_token when deleting. 0: do not check 1: check The default value is
//     *            1. If the value is 1, when the FaceSet contains face_token, it cannot be
//     *            deleted.
//     * @return
//     * @throws Exception
//     */
//    public Response deleteFaceSetByToken(final String faceSetToken, final int checkEmpty)
//            throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.DELETE;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_FACESET_TOKEN, faceSetToken);
//        map.put(Constants.KEY_FOR_CHECK_EMPTY, String.valueOf(checkEmpty));
//        return HttpRequest.post(url, map, null);
//    }
//
//    /**
//     * 删除一个人脸集合。 Delete a FaceSet.
//     * 
//     * @param outerId
//     *            用户提供的FaceSet标识 User-defined id of Faceset.
//     * @param checkEmpty
//     *            删除时是否检查FaceSet中是否存在face_token，默认值为1 0：不检查 1：检查
//     *            如果设置为1，当FaceSet中存在face_token则不能删除 Check if the FaceSet contains
//     *            face_token when deleting. 0: do not check 1: check The default value is
//     *            1. If the value is 1, when the FaceSet contains face_token, it cannot be
//     *            deleted.
//     * @return 返回结果,Response实例
//     * @throws Exception
//     */
//    public Response deleteFaceSetByOuterId(final String outerId, final int checkEmpty)
//            throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.DELETE;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_OUTER_ID, outerId);
//        map.put(Constants.KEY_FOR_CHECK_EMPTY, String.valueOf(checkEmpty));
//        return HttpRequest.post(url, map, null);
//    }
//
//    /**
//     * 获取一个FaceSet的所有信息 Get details about a FaceSet.
//     * 
//     * @param faceSetToken
//     *            FaceSet的标识 The id of Faceset.
//     * @return
//     * @throws Exception
//     */
//    public Response getDetailByFaceToken(final String faceSetToken) throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.GET_DETAIL;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_FACESET_TOKEN, faceSetToken);
//        return HttpRequest.post(url, map, null);
//    }
//
//    /**
//     * 获取一个FaceSet的所有信息 Get details about a FaceSet.
//     * 
//     * @param outerId
//     *            用户提供的FaceSet标识 User-defined id of Faceset.
//     * @return
//     * @throws Exception
//     */
//    public Response getDetailByOuterId(final String outerId) throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.GET_DETAIL;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_OUTER_ID, outerId);
//        return HttpRequest.post(url, map, null);
//    }
//
//    /**
//     * 移除一个FaceSet中的某些或者全部face_token Remove all or part of face_token within a FaceSet.
//     * 
//     * @param faceSetToken
//     *            FaceSet的标识 The id of Faceset.
//     * @param faceTokens
//     *            需要移除的人脸标识字符串，可以是一个或者多个face_token组成，用逗号分隔。最多不能超过1,000个face_token
//     *            注：face_tokens字符串传入“RemoveAllFaceTokens”则会移除FaceSet内所有的face_token The
//     *            face_token to be removed, comma-seperated. The number of face_token must
//     *            not be larger than 1000. Note: if this string passed
//     *            "RemoveAllFaceTokens", all the face_token within FaceSet will be removed.
//     * @return
//     * @throws Exception
//     */
//    public Response removeFaceFromFaceSetByFaceSetToken(final String faceSetToken,
//            final String faceTokens) throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.REMOVE_FACE;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_FACESET_TOKEN, faceSetToken);
//        map.put(Constants.KEY_FOR_FACE_TOKENS, faceTokens);
//        return HttpRequest.post(url, map, null);
//    }
//
//    /**
//     * 移除一个FaceSet中的某些或者全部face_token Remove all or part of face_token within a FaceSet.
//     * 
//     * @param outerId
//     *            用户自定义的FaceSet标识 User-defined id of Faceset.
//     * @param faceTokens
//     *            需要移除的人脸标识字符串，可以是一个或者多个face_token组成，用逗号分隔。最多不能超过1,000个face_token
//     *            注：face_tokens字符串传入“RemoveAllFaceTokens”则会移除FaceSet内所有的face_token The
//     *            face_token to be removed, comma-seperated. The number of face_token must
//     *            not be larger than 1000. Note: if this string passed
//     *            "RemoveAllFaceTokens", all the face_token within FaceSet will be removed.
//     * @return
//     * @throws Exception
//     */
//    public Response removeFaceFromFaceSetByOuterId(final String outerId,
//            final String faceTokens) throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.REMOVE_FACE;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_OUTER_ID, outerId);
//        map.put(Constants.KEY_FOR_FACE_TOKENS, faceTokens);
//        return HttpRequest.post(url, map, null);
//    }
//
//    /**
//     * 获取所有的FaceSet Get all the FaceSet.
//     * 
//     * @param tags
//     *            包含需要查询的FaceSet标签的字符串，用逗号分隔 Tags of the FaceSet to be searched,
//     *            comma-seperated
//     * @return
//     * @throws Exception
//     */
//    public Response getFaceSets(final String tags) throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.GET_FACESETS;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_TAGS, tags);
//        return HttpRequest.post(url, map, null);
//    }
//
//    /**
//     * 更新一个人脸集合的属性 Update the attributes of a FaceSet.
//     * 
//     * @param faceSetToken
//     *            FaceSet的标识 The id of Faceset.
//     * @param newOuterId
//     *            在api_key下全局唯一的FaceSet自定义标识，可以用来管理FaceSet对象。最长255个字符，不能包括字符^@,&=*'" Custom
//     *            unique id of Faceset under your account, used for managing FaceSet
//     *            objects. No more than 255 characters, and must not contain characters
//     *            ^@,&=*'"
//     * @param displayName
//     *            人脸集合的名字，256个字符 The name of FaceSet. No more than 256 characters, and must
//     *            not contain characters ^@,&=*'"
//     * @param userData
//     *            自定义用户信息，不大于16KB, 1KB=1024B Custom user information. No larger than 16KB,
//     *            and must not contain characters ^@,&=*'"
//     * @param tags
//     *            FaceSet自定义标签组成的字符串，用来对FaceSet分组。最长255个字符，多个tag用逗号分隔，每个tag不能包括字符^@,&=*'"
//     *            String consists of FaceSet custom tags, used for categorizing FaceSet,
//     *            comma-seperated. No more than 255 characters, and must not contain
//     *            characters ^@,&=*'"
//     * @return
//     * @throws Exception
//     */
//    public Response updateFaceSetByFaceSetToken(final String faceSetToken,
//            final String newOuterId, final String displayName, final String userData,
//            final String tags) throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.UPDATE;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_FACESET_TOKEN, faceSetToken);
//        map.put(Constants.KEY_FOR_NEW_OUTER_ID, newOuterId);
//        map.put(Constants.KEY_FOR_DISPLAY_NAME, displayName);
//        map.put(Constants.KEY_FOR_USER_DATA, userData);
//        map.put(Constants.KEY_FOR_TAGS, tags);
//        return HttpRequest.post(url, map, null);
//    }
//
//    /**
//     * 更新一个人脸集合的属性 Update the attributes of a FaceSet.
//     * 
//     * @param outerId
//     *            用户自定义的FaceSet标识 User-defined id of Faceset.
//     * @param newOuterId
//     *            在api_key下全局唯一的FaceSet自定义标识，可以用来管理FaceSet对象。最长255个字符，不能包括字符^@,&=*'" Custom
//     *            unique id of Faceset under your account, used for managing FaceSet
//     *            objects. No more than 255 characters, and must not contain characters
//     *            ^@,&=*'"
//     * @param displayName
//     *            人脸集合的名字，256个字符 The name of FaceSet. No more than 256 characters, and must
//     *            not contain characters ^@,&=*'"
//     * @param userData
//     *            自定义用户信息，不大于16KB, 1KB=1024B Custom user information. No larger than 16KB,
//     *            and must not contain characters ^@,&=*'"
//     * @param tags
//     *            FaceSet自定义标签组成的字符串，用来对FaceSet分组。最长255个字符，多个tag用逗号分隔，每个tag不能包括字符^@,&=*'"
//     *            String consists of FaceSet custom tags, used for categorizing FaceSet,
//     *            comma-seperated. No more than 255 characters, and must not contain
//     *            characters ^@,&=*'"
//     * @return
//     * @throws Exception
//     */
//    public Response updateFaceSetByOuterId(final String outerId, final String newOuterId,
//            final String displayName, final String userData, final String tags)
//            throws Exception
//    {
//        String url = baseUrl + Constants.SPLIT + Constants.FACESET + Constants.SPLIT
//                + Constants.UPDATE;
//        HashMap<String, String> map = new HashMap<>();
//        map.put(Constants.KEY_FOR_APIKEY, apiKey);
//        map.put(Constants.KEY_FOR_APISECRET, apiSecret);
//        map.put(Constants.KEY_FOR_OUTER_ID, outerId);
//        map.put(Constants.KEY_FOR_NEW_OUTER_ID, newOuterId);
//        map.put(Constants.KEY_FOR_DISPLAY_NAME, displayName);
//        map.put(Constants.KEY_FOR_USER_DATA, userData);
//        map.put(Constants.KEY_FOR_TAGS, tags);
//        return HttpRequest.post(url, map, null);
//    }

}
