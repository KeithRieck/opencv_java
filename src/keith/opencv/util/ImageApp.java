package keith.opencv.util;

import org.opencv.core.Mat;

public abstract class ImageApp extends VideoApp {

	protected final Mat image;

	public ImageApp(ImageProcessor imageProcessor, Mat src) {
		super(imageProcessor, src.width(), src.height());
		this.image = src;
	}

	protected void initializeCamera() {
	}

	protected Mat readCamera() {
		return this.image;
	}

}
