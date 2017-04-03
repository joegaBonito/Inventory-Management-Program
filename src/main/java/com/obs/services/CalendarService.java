package com.obs.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalendarService {
	/*
	 * Getting all days for a specific month
	 */
	public List<Date> getAllDays() {
		  Calendar cal = new GregorianCalendar(2017,1,31);
		  cal.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));  //Controls Month.
		  cal.set(Calendar.DAY_OF_MONTH, 1);
		  int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		  List<Date> calTemp = new ArrayList<Date>();
		  for(int i = 0; i < maxDay; i++) {
			  cal.set(Calendar.DAY_OF_MONTH, 1 + i);
		      calTemp.add(cal.getTime());
		  }
		  return calTemp;
	}
}
