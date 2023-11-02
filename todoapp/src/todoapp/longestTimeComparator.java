package todoapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class longestTimeComparator implements Comparator<TaskBean> {
	public int compare(TaskBean t1, TaskBean t2) {
		SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat sd2 = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat sd3 = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat sd4 = new SimpleDateFormat("MM-dd-yyyy");
		Date d1 = new Date();
		Date d2 = new Date();
		Date d3 = new Date();
		Date d4 = new Date();
		try {
			d1 = sd1.parse(t1.getCr_dt());
			d2 = sd2.parse(t1.getEnd_dt());
			d3 = sd3.parse(t2.getCr_dt());
			d4 = sd4.parse(t2.getEnd_dt());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("line 27" + e.toString());
			return 0;

		}
		long duration1 = d2.getTime() - d1.getTime();
		long duration2 = d4.getTime() - d3.getTime();
		if ((duration1 - duration2) == 0)
			return -1;
		return Long.compare(duration2, duration1);
	}
}
