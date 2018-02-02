package com.sven.facialid.service.faceplusplus.operator;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.megvii.cloud.http.Key;
import com.sven.facialid.service.faceplusplus.model.FaceAttachUserIdResponse;
import com.sven.facialid.service.faceplusplus.model.FaceGetDetailResponse;
import com.sven.facialid.util.Constants;

@Component
public class FaceOperator extends ApiOperator {
    /**
     *  为检测出的某一个人脸添加标识信息，该信息会在Search接口结果中返回，用来确定用户身份。
     *  Set user_id for a detected face. user_id can be returned in Search results to determine the identity of user.
     * @param faceToken 人脸标识face_token
     *                  id of the face
     * @param userId 用户自定义的user_id，不超过255个字符，不能包括^@,&=*'"
     *               建议将同一个人的多个face_token设置同样的user_id。
     *               Custom user_id. No more than 255 characters, and must not contain characters ^@,&=*'"
     *               It is recommended that all the face_token belonging to a same person should be set the same user_id
     * @return
     * @throws Exception
     */
    public FaceAttachUserIdResponse faceSetUserId(final String faceToken, final String userId) throws Exception {
        
        String url = this.getBaseUrl() + Key.SPLIT + Key.FACE + Key.SPLIT + Key.SET_USERID;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        builder.queryParam(Constants.KEY_FOR_APIKEY, this.getApiKey());
        builder.queryParam(Constants.KEY_FOR_APISECRET, this.getApiSecret());
        builder.queryParam(Key.KEY_FOR_FACE_TOKEN, faceToken);
        builder.queryParam(Key.KEY_FOR_USER_ID, userId);
        
        HttpEntity<FaceAttachUserIdResponse> response = this.getRestTemplate().exchange(builder.toUriString(),
                HttpMethod.POST, null, FaceAttachUserIdResponse.class);
        
        return response.getBody();
    }
    
    /**
     * 通过传入在Detect API检测出的人脸标识face_token，获取一个人脸的关联信息，包括源图片ID、归属的FaceSet。
     * Get related information to a face by passing its face_token which you can get from Detect API.
     * Face related information includes image_id and FaceSet which it belongs to.
     * @param faceToken 人脸标识face_token
     *                  id of the face
     * @return
     * @throws Exception
     */
    public FaceGetDetailResponse faceGetDetail(final String faceToken) throws Exception {
        String url = this.getBaseUrl() + Key.SPLIT + Key.FACE + Key.SPLIT + Key.GET_DETAIL;
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        builder.queryParam(Constants.KEY_FOR_APIKEY, this.getApiKey());
        builder.queryParam(Constants.KEY_FOR_APISECRET, this.getApiSecret());
        builder.queryParam(Key.KEY_FOR_FACE_TOKEN, faceToken);
        
        HttpEntity<FaceGetDetailResponse> response = this.getRestTemplate().exchange(builder.toUriString(),
                HttpMethod.POST, null, FaceGetDetailResponse.class);
        
        return response.getBody();
    }
}
