package com.sven.facialid.service.faceplusplus.operator;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.megvii.cloud.http.Key;
import com.sven.facialid.service.faceplusplus.model.FaceDetectResponse;
import com.sven.facialid.util.Constants;

/**
 * 这个类里的所有方法都是网络请求，所以请在异步线程中调用 All the function in class is HTTP request, so please call in
 * an asynchronous thread
 */
@Component
public class CommonOperator extends ApiOperator
{
//    public FaceDetectResponse detectUrl(final String imageUrl, final int landmark,
//            final String attributes) throws Exception
//    {
//
//        String url = baseUrl + Key.SPLIT + Key.DETECT;
//
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
//
//        builder.queryParam(Constants.KEY_FOR_APIKEY, apiKey);
//        builder.queryParam(Constants.KEY_FOR_APISECRET, apiSecret);
//        builder.queryParam(Key.KEY_FOR_IMAGE_URL, imageUrl);
//        builder.queryParam(Key.KEY_FOR_RETURN_LANDMARK, String.valueOf(landmark));
//        if (!StringUtils.isEmpty(attributes))
//        {
//            builder.queryParam(Key.KEY_FOR_RETURN_ATTRIBUTES, attributes);
//        }
//
//        HttpEntity<FaceDetectResponse> response = restTemplate.exchange(url,
//                HttpMethod.POST, null, FaceDetectResponse.class);
//
//        return response.getBody();
//    }

    public FaceDetectResponse detectBytes(final byte[] fileByte, final int landmark,
            final String attributes) throws Exception
    {

        String url = this.getBaseUrl() + Key.SPLIT + Key.DETECT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        builder.queryParam(Constants.KEY_FOR_APIKEY, this.getApiKey());
        builder.queryParam(Constants.KEY_FOR_APISECRET, this.getApiSecret());
        builder.queryParam(Key.KEY_FOR_RETURN_LANDMARK, String.valueOf(landmark));

        MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        ByteArrayResource resource = new ByteArrayResource(fileByte)
        {
            @Override
            public String getFilename()
            {
                return " ";
            }
        };
        data.add(Key.KEY_FOR_IMAGE_FILE, resource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<MultiValueMap<String, Object>>(data, requestHeaders);

        if (!StringUtils.isEmpty(attributes))
        {
            builder.queryParam(Key.KEY_FOR_RETURN_ATTRIBUTES, attributes);
        }

        HttpEntity<FaceDetectResponse> response = this.getRestTemplate().exchange(builder.toUriString(),
                HttpMethod.POST, requestEntity, FaceDetectResponse.class);

        return response.getBody();
    }

    // /**
    // * 调用者提供图片URL，进行人脸检测。 detect api through network image url
    // *
    // * @param imageUrl
    // * 图片链接 image url
    // * @param landmark
    // * 是否返回人脸的关键点，1：返回，0：不返回 Whether or return 83 key points of facial features
    // * and contour，1：return，0：not return
    // * @param attributes
    // * 检测人脸的属性 gender,age,smiling,glass,headpose,facequality,blur detect face
    // * attributes: gender,age,smiling,glass,headpose,facequality,blur
    // * @return
    // * @throws Exception
    // */
    // public Response detectUrl(final String imageUrl, final int landmark,
    // final String attributes) throws Exception
    // {
    // String url = webUrl + Key.SPLIT + Key.DETECT;
    // HashMap<String, String> map = new HashMap<>();
    // map.put(Key.KEY_FOR_APIKEY, apiKey);
    // map.put(Key.KEY_FOR_APISECRET, apiSecret);
    // map.put(Key.KEY_FOR_IMAGE_URL, imageUrl);
    // map.put(Key.KEY_FOR_RETURN_LANDMARK, String.valueOf(landmark));
    // if (!HttpRequest.isEmpty(attributes))
    // {
    // map.put(Key.KEY_FOR_RETURN_ATTRIBUTES, attributes);
    // }
    // return HttpRequest.post(url, map, null);
    // }
    //
    // /**
    // * 调用者提供图片文件，进行人脸检测。 detect api through native image finle
    // *
    // * @param fileByte
    // * 二进制数组 image binary array
    // * @param landmark
    // * 是否返回人脸的关键点，1：返回，0：不返回 Whether or return 83 key points of facial features
    // * and contour，1：return，0：not return
    // * @param attributes
    // * 检测人脸的属性 gender,age,smiling,glass,headpose,facequality,blur detect face
    // * attributes: gender,age,smiling,glass,headpose,facequality,blur
    // * @return
    // * @throws Exception
    // */
    // public Response detectByte(final byte[] fileByte, final int landmark,
    // final String attributes) throws Exception
    // {
    // String url = webUrl + Key.SPLIT + Key.DETECT;
    // HashMap<String, String> map = new HashMap<>();
    // HashMap<String, byte[]> fileMap = new HashMap<>();
    // map.put(Key.KEY_FOR_APIKEY, apiKey);
    // map.put(Key.KEY_FOR_APISECRET, apiSecret);
    // fileMap.put(Key.KEY_FOR_IMAGE_FILE, fileByte);
    // if (landmark != 0)
    // {
    // map.put(Key.KEY_FOR_RETURN_LANDMARK, String.valueOf(landmark));
    // }
    // if (!HttpRequest.isEmpty(attributes))
    // {
    // map.put(Key.KEY_FOR_RETURN_ATTRIBUTES, attributes);
    // }
    // return HttpRequest.post(url, map, fileMap);
    // }
    //
    // /**
    // * 调用者提供图片文件，进行人脸检测。 detect api through native image finle
    // *
    // * @param base64
    // * Base64数据 image data for base64
    // * @param landmark
    // * 是否返回人脸的关键点，1：返回，0：不返回 Whether or return 83 key points of facial features
    // * and contour，1：return，0：not return
    // * @param attributes
    // * 检测人脸的属性 gender,age,smiling,glass,headpose,facequality,blur detect face
    // * attributes: gender,age,smiling,glass,headpose,facequality,blur
    // * @return
    // * @throws Exception
    // */
    // public Response detectBase64(final String base64, final int landmark,
    // final String attributes) throws Exception
    // {
    // String url = webUrl + Key.SPLIT + Key.DETECT;
    // HashMap<String, String> map = new HashMap<>();
    // map.put(Key.KEY_FOR_APIKEY, apiKey);
    // map.put(Key.KEY_FOR_APISECRET, apiSecret);
    // map.put(Key.KEY_FOR_IMAGE_BASE64, base64);
    // if (landmark != 0)
    // {
    // map.put(Key.KEY_FOR_RETURN_LANDMARK, String.valueOf(landmark));
    // }
    // if (!HttpRequest.isEmpty(attributes))
    // {
    // map.put(Key.KEY_FOR_RETURN_ATTRIBUTES, attributes);
    // }
    // return HttpRequest.post(url, map, null);
    // }
    //
    // /**
    // * 将两个人脸进行比对，来判断是否为同一个人。 compare two faces
    // *
    // * @param faceToken1
    // * 第一个人脸标识face_token first face_token
    // * @param image_url1
    // * 第一个人脸的url image url of first face
    // * @param fileByte1
    // * 第一个人脸的图片文件 file of first face 三个参数只需要传一个就行了 only need one of three
    // * parameter
    // *
    // * @param faceToken2
    // * 第二个人脸标识face_token second face_token
    // * @param image_url2
    // * 第二个人脸的url image url of second face
    // * @param fileByte2
    // * 第二个人脸的图片文件 file of second face 三个参数只需要传一个就行了 only need one of three
    // * parameter
    // * @return
    // * @throws Exception
    // */
    // public Response compare(final String faceToken1, final String image_url1,
    // final byte[] fileByte1, final String base64_1, final String faceToken2,
    // final String image_url2, final byte[] fileByte2, final String base64_2)
    // throws Exception
    // {
    // String url = webUrl + Key.SPLIT + Key.COMPARE;
    // HashMap<String, String> map = new HashMap<>();
    // HashMap<String, byte[]> fileByte = new HashMap<>();
    // map.put(Key.KEY_FOR_APIKEY, apiKey);
    // map.put(Key.KEY_FOR_APISECRET, apiSecret);
    // if (!HttpRequest.isEmpty(faceToken1))
    // {
    // map.put(Key.KEY_FOR_FACE_TOKEN1, faceToken1);
    // }
    // if (!HttpRequest.isEmpty(faceToken2))
    // {
    // map.put(Key.KEY_FOR_FACE_TOKEN2, faceToken2);
    // }
    // if (!HttpRequest.isEmpty(image_url1))
    // {
    // map.put(Key.KEY_FOR_IMAGE_URL1, image_url1);
    // }
    // if (!HttpRequest.isEmpty(image_url2))
    // {
    // map.put(Key.KEY_FOR_IMAGE_URL2, image_url2);
    // }
    // if (fileByte1 != null)
    // {
    // fileByte.put(Key.KEY_FOR_IMAGE_FILE1, fileByte1);
    // }
    // if (fileByte2 != null)
    // {
    // fileByte.put(Key.KEY_FOR_IMAGE_FILE2, fileByte2);
    // }
    // if (!HttpRequest.isEmpty(base64_1))
    // {
    // map.put(Key.KEY_FOR_IMAGE_BASE64_1, base64_1);
    // }
    // if (!HttpRequest.isEmpty(base64_2))
    // {
    // map.put(Key.KEY_FOR_IMAGE_BASE64_2, base64_2);
    // }
    // return HttpRequest.post(url, map, fileByte);
    // }
    //
    // /**
    // * 在Faceset中找出与目标人脸最相似的一张或多张人脸。 search the Most similar from FaceSet
    // * faceToken,image_url,image_file,buff四个参数只要传入一个就可以了，其他可以传空（null） only need one of
    // * faceToken,image_url,image_file,buff
    // *
    // * @param faceToken
    // * 与Faceset中人脸比对的face_token Identification of face
    // * @param image_url
    // * 需要比对的人脸的网络图片URL network image url
    // * @param buff
    // * 需要比对的人脸的图片的二进制数组 native image
    // * @param faceSetToken
    // * Faceset的标识 Identification of faceSet
    // * @param returnResultCount
    // * 返回比对置信度最高的n个结果，范围[1,5]。默认值为1 the number of result, 1-5,Defaults to 1
    // * @return
    // * @throws Exception
    // */
    // public Response searchByFaceSetToken(final String faceToken, final String image_url,
    // final byte[] buff, final String faceSetToken,
    // final int returnResultCount) throws Exception
    // {
    // String url = webUrl + Key.SPLIT + Key.SEARCH;
    // HashMap<String, String> map = new HashMap<>();
    // HashMap<String, byte[]> fileMap = new HashMap<>();
    // map.put(Key.KEY_FOR_APIKEY, apiKey);
    // map.put(Key.KEY_FOR_APISECRET, apiSecret);
    // map.put(Key.KEY_FOR_FACESET_TOKEN, faceSetToken);
    // map.put(Key.KEY_FOR_RETURN_RESULT_COUNT, String.valueOf(returnResultCount));
    // if (faceToken != null)
    // {
    // map.put(Key.KEY_FOR_FACE_TOKEN, faceToken);
    // }
    // if (image_url != null)
    // {
    // map.put(Key.KEY_FOR_IMAGE_URL, image_url);
    // }
    // if (buff != null)
    // {
    // fileMap.put(Key.KEY_FOR_IMAGE_FILE, buff);
    // }
    // return HttpRequest.post(url, map, fileMap);
    // }
    //
    // /**
    // * 在Faceset中找出与目标人脸最相似的一张或多张人脸。 search the Most similar from FaceSet
    // * faceToken,image_url,image_file,buff四个参数只要传入一个就可以了，其他可以传空（null） only need one of
    // * faceToken,image_url,image_file,buff
    // *
    // * @param faceToken
    // * 与Faceset中人脸比对的face_token Identification of face
    // * @param image_url
    // * 需要比对的人脸的网络图片URL network image url
    // * @param buff
    // * 需要比对的人脸的图片的二进制数组 native image
    // * @param outerId
    // * Faceset的标识 Identification of faceSet which is definition by youself
    // * @param returnResultCount
    // * 返回比对置信度最高的n个结果，范围[1,5]。默认值为1 the number of result, 1-5,Defaults to 1
    // * @return
    // * @throws Exception
    // */
    // public Response searchByOuterId(final String faceToken, final String image_url,
    // final byte[] buff, final String base64, final String outerId,
    // final int returnResultCount) throws Exception
    // {
    // String url = webUrl + Key.SPLIT + Key.SEARCH;
    // HashMap<String, String> map = new HashMap<>();
    // HashMap<String, byte[]> fileMap = new HashMap<>();
    // map.put(Key.KEY_FOR_APIKEY, apiKey);
    // map.put(Key.KEY_FOR_APISECRET, apiSecret);
    // map.put(Key.KEY_FOR_OUTER_ID, outerId);
    // map.put(Key.KEY_FOR_RETURN_RESULT_COUNT, String.valueOf(returnResultCount));
    // if (faceToken != null)
    // {
    // map.put(Key.KEY_FOR_FACE_TOKEN, faceToken);
    // }
    // if (image_url != null)
    // {
    // map.put(Key.KEY_FOR_IMAGE_URL, image_url);
    // }
    // if (buff != null)
    // {
    // fileMap.put(Key.KEY_FOR_IMAGE_FILE, buff);
    // }
    // if (base64 != null)
    // {
    // map.put(Key.KEY_FOR_IMAGE_BASE64, base64);
    // }
    //
    // return HttpRequest.post(url, map, fileMap);
    // }

}
