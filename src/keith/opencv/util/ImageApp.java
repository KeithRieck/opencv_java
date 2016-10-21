package keith.opencv.util;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public abstract class ImageApp extends VideoApp {

	protected final Mat image;

	public ImageApp(Mat src) {
		super(src.width(), src.height());
		this.image = src;
	}

	protected void initializeCamera() {
	}

	protected Mat readCamera() {
		return this.image;
	}

	@Override
	public abstract Mat run(Mat src);

}
