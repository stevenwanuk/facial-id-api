package com.sven.facialid.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

	public String getBase64Image(final String data) {
		return data.split(",")[1];
	}

	public byte[] base64ToImageBytes(final String base64Image) throws IOException {

		byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
		// BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
		return imageBytes;
	}

	public byte[] getPngBytes(final File file) throws IOException {
		// read a jpeg from a inputFile
		BufferedImage bufferedImage = ImageIO.read(file);

		// this writes the bufferedImage into a byte array called resultingBytes
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", byteArrayOut);
		return byteArrayOut.toByteArray();
	}
}
