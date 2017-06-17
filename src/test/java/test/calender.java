package test;

import java.util.Calendar;
import java.util.Date;

public class calender {

	public static void main(String[] args) {
		Calendar buyCalendar = Calendar.getInstance();
		Calendar recentCalendar = Calendar.getInstance();
		recentCalendar.add(Calendar.MINUTE,5 );
		System.out.println(buyCalendar.getTimeInMillis());
		System.out.println(recentCalendar.getTimeInMillis());
		
		
	}
}
