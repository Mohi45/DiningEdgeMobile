package DiningEdgeAutomation.Mobile.utils;

import java.io.File;
import java.io.FileFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class CustomFunctions {
	/**
	 * This is used to timestamp with date and time
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd] [HH:mm:ss:SSS]");
		Date dt = new Date();
		return dateFormat.format(dt);
	}

	/**
	 * This is used to timestamp with date and time
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat(" [yyyy-MM-dd]");
		Date dt = new Date();
		return dateFormat.format(dt);
	}

	/**
	 * This method is used to get latest file from specific path
	 * 
	 * @param dirPath
	 * @param fileType
	 * @return
	 */
	public static File getLatestFilefromDir(String dirPath, String fileType) {
		File getLatestFilefromDir = null;
		File dir = new File(dirPath);
		FileFilter fileFilter = new WildcardFileFilter("*." + fileType);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			getLatestFilefromDir = files[0];
		}

		return getLatestFilefromDir;
	}

	public static void hardWaitForScript() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
