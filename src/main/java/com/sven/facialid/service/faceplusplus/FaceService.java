package com.sven.facialid.service.faceplusplus;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.megvii.cloud.http.CommonOperate;
import com.megvii.cloud.http.FaceOperate;
import com.megvii.cloud.http.FaceSetOperate;
import com.megvii.cloud.http.Response;
import com.sven.facialid.service.faceplusplus.model.FaceAttachUserIdResponse;
import com.sven.facialid.service.faceplusplus.model.FaceDetectResponse;
import com.sven.facialid.service.faceplusplus.model.FaceGetDetailResponse;
import com.sven.facialid.service.faceplusplus.model.FaceSetAddFaceTokenResponse;
import com.sven.facialid.service.faceplusplus.operator.CommonOperator;
import com.sven.facialid.service.faceplusplus.operator.FaceOperator;
import com.sven.facialid.service.faceplusplus.operator.FaceSetOperator;

@Service
public class FaceService {

	@Value("${face.api.key}")
	private String apiKey;
	@Value("${face.api.secret}")
	private String apiSecret;
	@Value("${face.api.base}")
	private String baseUrl;
	

	private FaceSetOperate faceSetOperator;
	private FaceOperate faceOperator;
	private CommonOperate commonOperator;
	
	@Autowired
	private CommonOperator commonOperator1;
	@Autowired
	private FaceOperator faceOperator1;
	@Autowired
	private FaceSetOperator faceSetOperator1;

	@PostConstruct
	public void init() {
		faceSetOperator = new FaceSetOperate(apiKey, apiSecret, true);
		faceOperator = new FaceOperate(apiKey, apiSecret, true);
		commonOperator = new CommonOperate(apiKey, apiSecret, true);
	} 

	public FaceDetectResponse detect(final byte[] fileByte) throws Exception {
	    return commonOperator1.detectBytes(fileByte, 0, "none");
		//return commonOperator.detectByte(fileByte, 0, "none");
	}
	
	public FaceGetDetailResponse getFacialIdDetail(final String faceToken) throws Exception {
	    return faceOperator1.faceGetDetail(faceToken);
	}

	public Response delete(final String outerId, final String faceTokens) throws Exception {
		return faceSetOperator.removeFaceFromFaceSetByOuterId(outerId, faceTokens);
	}

	public Response purge(final String outerId) throws Exception {
		return faceSetOperator.removeFaceFromFaceSetByOuterId(outerId, "RemoveAllFaceTokens");
	}

	public FaceAttachUserIdResponse attachUserIdToFacialId(final String faceToken, final String userId) throws Exception {

		return faceOperator1.faceSetUserId(faceToken, userId);
	}

	public Response search(final byte[] fileByte, final String outerId) throws Exception {

		return commonOperator.searchByOuterId(null, null, fileByte, null, outerId, 1);
	}

	public Response search(final String base64, final String outerId) throws Exception {

		return commonOperator.searchByOuterId(null, null, null, base64, outerId, 1);
	}

	public Response createFaceSet(final String displayName, final String outerId, final String tags,
			final String FaceTokens, final String userData, final int ForceMerge) throws Exception {

		return faceSetOperator.createFaceSet(displayName, outerId, tags, FaceTokens, userData, ForceMerge);

	}

	public Response getFaceSet(final String tags) throws Exception {

		return faceSetOperator.getFaceSets(tags);

	}

	public Response delete(final String outerId, final int checkEmpty) throws Exception {

		return faceSetOperator.deleteFaceSetByOuterId(outerId, checkEmpty);

	}

	public FaceSetAddFaceTokenResponse addFaceByOuterId(final String FaceTokens, final String outerId) throws Exception {

		return faceSetOperator1.addFaceByOuterId(FaceTokens, outerId);

	}
}
