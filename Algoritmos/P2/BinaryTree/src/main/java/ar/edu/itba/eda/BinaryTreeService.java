package ar.edu.itba.eda;

import java.io.IOException;
import java.net.URISyntaxException;

public interface BinaryTreeService<T> {
	
	void preorder();

	void postorder();

	void printHierarchy();

	void toFile(String fileName) throws IOException, URISyntaxException;

	public int getHeight();

}