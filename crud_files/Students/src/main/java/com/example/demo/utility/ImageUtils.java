package com.example.demo.utility;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {
	public static byte[] compressImage(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
		byte[] tem = new byte[4 * 1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(tem);

			byteArrayOutputStream.write(tem, 0, size);
		}
		try {
			byteArrayOutputStream.close();
		} catch (Exception ignore) {

		}
		return byteArrayOutputStream.toByteArray();
	}

	public static byte[] decompressImage(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(tmp);
				byteArrayOutputStream.write(tmp, 0, count);
			}
			byteArrayOutputStream.close();
		} catch (Exception ignore) {

		}

		return byteArrayOutputStream.toByteArray();

	}

}
