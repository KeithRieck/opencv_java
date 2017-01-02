package keith.opencv.grip;

import org.opencv.core.Mat;

import keith.opencv.util.ImageProcessor;
import keith.opencv.util.VideoApp;

public class SeeRedApp implements ImageProcessor {

	final SeeRedProcessor processor;

	public SeeRedApp() {
		processor = new SeeRedProcessor();
	}

	public Mat process(Mat src) {
		processor.process(src);
		return processor.hsvThresholdOutput();
	}

	public static void main(String[] args) throws Exception {
		SeeRedApp imageProcessor = new SeeRedApp();
		(new VideoApp(imageProcessor, 640, 480)).run();
	}

}
