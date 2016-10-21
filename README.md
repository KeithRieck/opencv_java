# OpenCV 3.0 in Java Experiments

This project contains small programs written while learning OpenCV.
In particular, I've been reading:

* _OpenCV 3.0 Computer Vision with Java_ by Daniel Lelis Baggio

OpenCV can be downloaded from http://opencv.org. Your download will contain the
native library files (which will be different for each operating system) but you
will also to compile the Java interfaces into a JAR file

You'll need to use cmake to create the Makefile and then make the JAR file in $OPENCV_HOME/bin/opencv-300.jar

	mkdir build
	cd build
	cmake -DBUILD_SHARED_LIBS=OFF ..
	make

## Running OpenCV in Eclipse

In Eclipse, open the Eclipse Preferences, and then choose Java / Build Path / User Libraries.
Add a new User Library for your version of OpenCV named "opencv-3.0.0".  To this library, add the
new JAR file as an External Jar.  Open this JAR file entry and specify the native libraries which will be under $OPENCV_HOME/lib .

## Running OpenCV examples from the command line.
You'll need to specify the JAR file and the library files.  E.g.:

	java -cp "bin:${OPENCV_HOME}/bin/opencv-300.jar" \
	-Djava.library.path=${OPENCV_HOME}/lib \
	-Ddebug=true \
	keith.opencv.grip.SeeRedApp

