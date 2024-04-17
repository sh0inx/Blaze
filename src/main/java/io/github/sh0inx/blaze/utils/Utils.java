package io.github.sh0inx.blaze.utils;

public class Utils {

	public boolean classExists(String className) {
		try {
			Class.forName(className);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
}
