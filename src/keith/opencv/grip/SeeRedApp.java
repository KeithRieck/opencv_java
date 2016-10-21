package keith.opencv.grip;

import org.opencv.core.Mat;

import keith.opencv.util.VideoApp;

public class SeeRedApp extends VideoApp {

	final SeeRedProcessor processor;

	public SeeRedApp(int width, int height) {
		super(width, height);
		processor = new SeeRedProcessor();
	}

	@Override
	public Mat run(Mat src) {
		processor.setsource0(src);
		processor.process();
		Mat dst = processor.hsvThresholdOutput();
		return dst;
	}

	public static void main(String[] args) throws Exception {
		(new SeeRedApp(640, 480)).run();
	}

}
