package keith.opencv.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
 * Repeatedly capture images from the camera, transform, and then display.
 */
public class VideoApp {

	final boolean DEBUG = "true".equalsIgnoreCase(System.getProperty("debug"));

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	final protected int width;
	final protected int height;
	VideoViewer videoViewer;
	public Character keyPressed = null;
	public int lastDigitPressed = 5;
	public char lastKeyPressed = ' ';
	protected int mouseX = -1;
	protected int mouseY = -1;
	protected VideoCapture camera = null;
	protected double exposure = -1.0;
	public final ImageProcessor imageProcessor;

	public VideoApp(ImageProcessor imageProcessor, int width, int height) {
		this.imageProcessor = imageProcessor;
		this.width = width;
		this.height = height;
	}

	public void run() throws Exception {

		initializeCamera();

		videoViewer = new VideoViewer("OpenCV", width, height);
		videoViewer.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				keyPressed = Character.valueOf(e.getKeyChar());
				lastKeyPressed = e.getKeyChar();
				if (keyPressed.charValue() >= '0' && keyPressed.charValue() <= '9') {
					lastDigitPressed = keyPressed.charValue() - '0';
				}
				if (DEBUG) {
					System.out.println("keyPressed(" + keyPressed + ") \t" + lastDigitPressed + "\t" + lastKeyPressed);
				}
			}

			public void keyReleased(KeyEvent e) {
				keyPressed = null;
				if (DEBUG) {
					System.out.println("keyReleased()\t" + lastDigitPressed + "\t" + lastKeyPressed);
				}
			}
		});
		videoViewer.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		while (true) {
			Mat src = readCamera();
			if (keyPressed == null) {
				src = imageProcessor.process(src);
			}
			videoViewer.imshow(src);
			if ((mouseX > -1) && (src instanceof MatOfByte)) {
				byte[] v = new byte[src.channels()];
				int z = src.get(mouseY, mouseX, v);
				if (DEBUG) {
					System.out.print("mouse(" + mouseX + "," + mouseY + "): " + z + "\t");
					for (int i=0; i<v.length; i++) System.out.print(v[i] + " ");
					System.out.println();
				}
				mouseX = -1;
				mouseY = -1;
			}
		}
	}

	protected void initializeCamera() {
		this.camera = new VideoCapture(0);
		this.camera.set(Videoio.CAP_PROP_FRAME_WIDTH, this.width);
		this.camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, this.height);
		if (exposure > -1.0) {
			System.out.println("\t" + exposure);
			this.camera.set(Videoio.CAP_PROP_AUTO_EXPOSURE, 0);
			this.camera.set(Videoio.CAP_PROP_EXPOSURE, exposure);
		}
		if (!this.camera.isOpened()) {
			throw new RuntimeException("Camera will not open");
		}
	}

	protected Mat readCamera() {
		Mat img = new Mat();
		this.camera.read(img);
		return img;
	}

}
