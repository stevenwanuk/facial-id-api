package com.sven.facialid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megvii.cloud.http.Response;
import com.sven.facialid.service.ImageService;
import com.sven.facialid.service.faceplusplus.FaceService;
import com.sven.facialid.util.Constants;

@Controller
@RequestMapping("/camera")
public class CameraController {

	@Autowired
	private FaceService faceService;

	@Autowired
	private ImageService imageService;
	
	@GetMapping()
	public String get() {

		return "camera-index";
	}

	@PostMapping(value = "/saveCanvasImage")
	@ResponseBody
	public String saveCanvasImage(@RequestParam(value = "imageBase64") final String data,
	        @RequestParam(value="outerId", defaultValue=Constants.TEST_FACESET_OUTERID, required = false) final String outerId)
			throws Exception {

		String base64Image = imageService.getBase64Image(data);

		Response resonse = faceService.search(base64Image, outerId);

		String json = new String(resonse.getContent());
		return json;
	}

}
