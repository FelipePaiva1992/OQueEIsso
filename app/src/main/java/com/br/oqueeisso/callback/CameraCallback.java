package com.br.oqueeisso.callback;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.Log;

public class CameraCallback implements PictureCallback {

	private byte[] picture;
	
	public CameraCallback(byte[] picture) {
		this.picture = picture;
	}
	
	@Override
	public void onPictureTaken(byte[] data, Camera camera) {

        this.picture = data;

//		File mainPicture = new File(filename);

//		try {
			
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(mainPicture));
//			bos.write(data);
//			bos.flush();
//			bos.close();
			
//			FileOutputStream fos = new FileOutputStream(mainPicture);
//			fos.write(data);
//			fos.flush();
//			fos.close();
//		} catch (Exception e) {
//			Log.e(CameraCallback.class.getName(), e.getMessage());
//		}
		
//		camera.stopPreview();
//		camera.release();
//		camera = null;
		
		camera.startPreview();
		
	}



}
