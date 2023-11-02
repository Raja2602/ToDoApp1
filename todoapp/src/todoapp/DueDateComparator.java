package todoapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DueDateComparator implements Comparator<TaskBean> {
	public int compare(TaskBean t1, TaskBean t2) {
		SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat sd2 = new SimpleDateFormat("MM-dd-yyyy");
		Date d1 = new Date();
		Date d2 = new Date();
		try {
			d1 = sd1.parse(t1.getEnd_dt());
			d2 = sd2.parse(t2.getEnd_dt());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// System.out.println("line 27"+e.toString());
			return Integer.MAX_VALUE;

		}
		return Long.compare(d1.getTime(), d2.getTime());
	}

}
