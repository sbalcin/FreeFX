package com.company.freefx.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author Sinan BALCIN
 */
public class DateUtil {

    public DateUtil() {
    }

    public static final int TODAY = 1;

    public static final int THIS_WEEKEND = 2;

    public static final int NEXT_WEEKEND = 3;

    public static final int NEXT_7DAYS = 4;

    public static final int NEXT_14DAYS = 5;

    public static final int NEXT_30DAYS = 6;

    public static final int THIS_MONTH = 7;

    private static String[] args;

    public static Calendar[] getDatesForInterval(int choise) {

        Calendar startDateTime = null;
        Calendar endDateTime = null;

        switch (choise) {
            case TODAY: {
                startDateTime = getToday();
                endDateTime = getDayLater(startDateTime, 1);
            }
            break;
            case THIS_WEEKEND: {
                startDateTime = getThisWeekend();
                endDateTime = getDayLater(startDateTime, 3);
            }
            break;
            case NEXT_WEEKEND: {
                startDateTime = getNextWeekend();
                endDateTime = getDayLater(startDateTime, 3);
            }
            break;
            case NEXT_7DAYS: {
                startDateTime = getToday();
                endDateTime = getDayLater(startDateTime, 7);
            }
            break;
            case NEXT_14DAYS: {
                startDateTime = getToday();
                endDateTime = getDayLater(startDateTime, 14);
            }
            break;
            case NEXT_30DAYS: {
                startDateTime = getToday();
                endDateTime = getDayLater(startDateTime, 30);
            }
            break;
            case THIS_MONTH: {
                startDateTime = getFirstDayOfMonth(Calendar.getInstance());
                endDateTime = getLastDayOfMonth(Calendar.getInstance());
                break;
            }
        }

        Calendar[] retVal = new Calendar[2];
        retVal[0] = startDateTime;
        retVal[1] = endDateTime;
        return retVal;
    }

    /**
     * @param firsDate
     * @param SecondDate
     * @return 0: firstDate = secondDate 1: firstDate.till > secondDate.till -1:
     * opposite of case 1
     */
    public static int compareTillDates(Date firstDate, Date secondDate) {
        if (firstDate == null && secondDate == null) {
            return 0;
        } else if (firstDate != null && secondDate == null) {
            return -1;
        } else if (firstDate == null && secondDate != null) {
            return 1;
        } else
            return firstDate.compareTo(secondDate);
    }

    /**
     * @param firsDate
     * @param SecondDate
     * @return 0: firstDate = secondDate 1: firstDate.since > secondDate.since
     * -1: opposite of case 1
     */
    public static int compareSinceDates(Date firstDate, Date secondDate) {
        if (firstDate == null && secondDate == null) {
            return 0;
        } else if (firstDate != null && secondDate == null) {
            return 1;
        } else if (firstDate == null && secondDate != null) {
            return -1;
        } else
            return firstDate.compareTo(secondDate);
    }

    public static Calendar getFirstDayOfMonth(Calendar baseDateTime) {
        Calendar startDateTime = (Calendar) baseDateTime.clone();

        startDateTime.set(Calendar.DATE, 1);
        startDateTime.set(Calendar.HOUR_OF_DAY, 0);
        startDateTime.set(Calendar.MINUTE, 0);
        startDateTime.set(Calendar.SECOND, 0);
        startDateTime.set(Calendar.MILLISECOND, 0);

        return startDateTime;
    }


    public static Calendar getFirstDayOfPreviousMonth(Calendar baseDateTime) {
        Calendar startDateTime = (Calendar) baseDateTime.clone();

        startDateTime.set(Calendar.MONTH, -1);
        startDateTime.set(Calendar.DATE, 1);
        startDateTime.set(Calendar.HOUR_OF_DAY, 0);
        startDateTime.set(Calendar.MINUTE, 0);
        startDateTime.set(Calendar.SECOND, 0);
        startDateTime.set(Calendar.MILLISECOND, 0);

        return startDateTime;
    }

    /**
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar beginDate = Calendar.getInstance();
        beginDate.set(year, month - 1, 1);
        beginDate.set(Calendar.HOUR_OF_DAY, 0);
        beginDate.set(Calendar.MINUTE, 0);
        beginDate.set(Calendar.SECOND, 0);
        beginDate.set(Calendar.MILLISECOND, 0);
        return beginDate.getTime();
    }

    /**
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, 1);
        endDate.add(Calendar.DAY_OF_MONTH, -1);
        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);

        return endDate.getTime();
    }

    public static Date getDate(int year, int month, int day) {
        Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, day);
        endDate.add(Calendar.DAY_OF_MONTH, -1);
        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);

        return endDate.getTime();
    }

    public static Date getDateWithoutSubtract(int year, int month, int day) {
        Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, day);
        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);

        return endDate.getTime();
    }

    public static Date getDateWithActualMaximumDay(Integer year, Integer month) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, 1);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
        return date.getTime();
    }

    public static boolean isGivenDateInBetween(Date givenDate, Date startOfInterval, Date endOfInterval) {
        if (givenDate == null) {
            return false;
        }
        if (startOfInterval == null && endOfInterval == null) {
            return true;
        }
        Calendar endOfIntervalCalendar = Calendar.getInstance();
        Calendar givenDateCalendar = Calendar.getInstance();
        givenDateCalendar.setTime(givenDate);
        givenDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        givenDateCalendar.set(Calendar.MINUTE, 0);
        givenDateCalendar.set(Calendar.SECOND, 0);
        givenDateCalendar.set(Calendar.MILLISECOND, 0);

        if (startOfInterval == null && endOfInterval != null) {
            endOfIntervalCalendar.setTime(endOfInterval);
            endOfIntervalCalendar.set(Calendar.HOUR_OF_DAY, 0);
            endOfIntervalCalendar.set(Calendar.MINUTE, 0);
            endOfIntervalCalendar.set(Calendar.SECOND, 0);
            endOfIntervalCalendar.set(Calendar.MILLISECOND, 0);

            return givenDateCalendar.compareTo(endOfIntervalCalendar) <= 0;
        }
        Calendar startOfIntervalCalendar = Calendar.getInstance();
        if (startOfInterval != null && endOfInterval == null) {
            startOfIntervalCalendar.setTime(startOfInterval);
            startOfIntervalCalendar.set(Calendar.HOUR_OF_DAY, 0);
            startOfIntervalCalendar.set(Calendar.MINUTE, 0);
            startOfIntervalCalendar.set(Calendar.SECOND, 0);
            startOfIntervalCalendar.set(Calendar.MILLISECOND, 0);

            return givenDateCalendar.compareTo(startOfIntervalCalendar) >= 0;
        }
        if (startOfInterval != null && endOfInterval != null) {
            endOfIntervalCalendar.setTime(endOfInterval);
            endOfIntervalCalendar.set(Calendar.HOUR_OF_DAY, 0);
            endOfIntervalCalendar.set(Calendar.MINUTE, 0);
            endOfIntervalCalendar.set(Calendar.SECOND, 0);
            endOfIntervalCalendar.set(Calendar.MILLISECOND, 0);

            startOfIntervalCalendar.setTime(startOfInterval);
            startOfIntervalCalendar.set(Calendar.HOUR_OF_DAY, 0);
            startOfIntervalCalendar.set(Calendar.MINUTE, 0);
            startOfIntervalCalendar.set(Calendar.SECOND, 0);
            startOfIntervalCalendar.set(Calendar.MILLISECOND, 0);

            return givenDateCalendar.compareTo(startOfIntervalCalendar) >= 0 & givenDateCalendar.compareTo(endOfIntervalCalendar) <= 0;
        }
        return false;

    }

    public static boolean equalsDate(Date firstDate, Date secondDate) {
        if (firstDate == null && secondDate == null) {
            return true;
        } else if (firstDate == null || secondDate == null) {
            return false;
        }

        Calendar baseCalendar = Calendar.getInstance();
        baseCalendar.setTime(firstDate);

        baseCalendar.set(Calendar.HOUR_OF_DAY, 0);
        baseCalendar.set(Calendar.MINUTE, 0);
        baseCalendar.set(Calendar.SECOND, 0);
        baseCalendar.set(Calendar.MILLISECOND, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(secondDate);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.compareTo(baseCalendar) == 0;
    }

    public static Calendar getLastDayOfMonth(Calendar baseDateTime) {
        Calendar lastDayOfMonth = (Calendar) getFirstDayOfMonth(baseDateTime).clone();

        lastDayOfMonth.add(Calendar.MONTH, 1);
        lastDayOfMonth.add(Calendar.DATE, -1);

        lastDayOfMonth.set(Calendar.HOUR_OF_DAY, 23);
        lastDayOfMonth.set(Calendar.MINUTE, 59);
        lastDayOfMonth.set(Calendar.SECOND, 59);
        lastDayOfMonth.set(Calendar.MILLISECOND, 999);

        return lastDayOfMonth;
    }

    public static Calendar getToday() {

        Calendar startDateTime = Calendar.getInstance();
        startDateTime.set(Calendar.HOUR_OF_DAY, 0);
        startDateTime.set(Calendar.MINUTE, 0);
        startDateTime.set(Calendar.SECOND, 0);
        startDateTime.set(Calendar.MILLISECOND, 0);

        return startDateTime;
    }

    public static Calendar getThisWeekend() {

        Calendar startDateTime = getToday();
        int dayOfWeek = startDateTime.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek <= Calendar.FRIDAY) {
            startDateTime.set(Calendar.DAY_OF_MONTH, startDateTime.get(Calendar.DAY_OF_MONTH) + (Calendar.FRIDAY - dayOfWeek));
        } else {
            startDateTime.set(Calendar.DAY_OF_MONTH, startDateTime.get(Calendar.DAY_OF_MONTH) + (8 - dayOfWeek));
        }

        return startDateTime;
    }

    public static Integer getDayOfWeek(int year, int month, int date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(year, month - 1, date);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static Integer getHourOfDate(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static Calendar getNextWeekend() {

        Calendar startDateTime = getThisWeekend();
        startDateTime.set(Calendar.DAY_OF_MONTH, startDateTime.get(Calendar.DAY_OF_MONTH) + 7);
        return startDateTime;
    }

    public static Calendar getDayLater(int dayCount) {

        Calendar startDateTime = getToday();
        return getDayLater(startDateTime, dayCount);
    }

    public static Calendar getDayLater(Calendar startDateTime, int dayCount) {

        Calendar endDateTime = (Calendar) startDateTime.clone();
        endDateTime.set(Calendar.DAY_OF_MONTH, startDateTime.get(Calendar.DAY_OF_MONTH) + dayCount);
        return endDateTime;
    }

    public static String ddMMyyyyHHssSSToddMMyyyy(String datetime) {
        return datetime.substring(0, 10);
    }

    /**
     * Describe <code>dateToDDMMYYYYString</code> method here.
     *
     * @param calendar a <code>Calendar</code> value
     * @return a <code>String</code> value
     */
    public static String dateToDDMMYYYYString(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(calendar.getTime());
    }

    /**
     * @param calendar
     * @return MM/dd/yyyy
     */
    public static String dateMMddyyyyString(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(calendar.getTime());
    }

    /**
     * @param date
     * @return MM/dd/yyyy
     */
    public static String dateMMddyyyyString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        if (date == null)
            return null;
        return df.format(date.getTime());
    }

    /**
     * @param date
     * @return yyyy/0MM
     */
    public static String dateyyyyMMMString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/0MM");
        if (date == null)
            return null;
        return df.format(date.getTime());
    }

    /**
     * @param date
     * @return yyyy-MM-dd
     * @see yyyy-MM-dd
     */
    public static String dateToYyyyMMddString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null)
            return null;
        return df.format(date.getTime());
    }

    public static String dateToddMMYYYYString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(date.getTime());
    }

    public static String dateToddMMYYYYStringWithSlashes(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date.getTime());
    }

    public static String dateToddMMYYYYStringBySlash(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date.getTime());
    }

    public static String dateToMMYYYYString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("MM/yyyy");
        return df.format(date.getTime());
    }

    /**
     * @param calendar
     * @return "dd/MM/yyyy"
     * @see "dd/MM/yyyy"
     */
    public static String dateddMMyyyyString(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(calendar.getTime());
    }

    public static String dateHHmmsssString(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(calendar.getTime());
    }

    public static String dateHHmmString(Calendar calendar) {
        if (calendar == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(calendar.getTime());
    }

    public static String dateHHmmString(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(date.getTime());
    }

    /**
     * Formats a day with format string "dd.MM.yyyy-HH:mm:ss"
     *
     * @param calendar a <code>Calendar</code> value
     * @return a <code>String</code> value
     */
    public static String dateToddMMyyyyHHmmssString(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss");
        return df.format(calendar.getTime());
    }

    /**
     * Describe <code>dateToddMMyyyyHHssSSString</code> method here.
     *
     * @param calendar a <code>Calendar</code> value
     * @return a <code>String</code> value
     */
    public static String dateToddMMyyyyHHssSSString(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return df.format(calendar.getTime());
    }

    public static String dateToyyyyMMddHHssSSString(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    public static String dateToyyyyMMdd_HHssSSString(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
        return df.format(calendar.getTime());
    }

    public static String dateToyyyyMMddHHssSSString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        return df.format(date);
    }

    public static String dateToyyyyMMddString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    public static String dateToddMMeeeeHHssString(Calendar calendar, Locale locale) {
        SimpleDateFormat df = new SimpleDateFormat("dd MMMM EEEE, HH:mm", locale);
        return df.format(calendar.getTime());
    }

    /**
     * Describe <code>ddMMyyyyHHssSSStringToCalendar</code> method here.
     *
     * @param str a <code>String</code> value
     * @return a <code>Calendar</code> value
     */
    public static Calendar ddMMyyyyHHssSSStringToCalendar(String str) {

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(df.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Describe <code>dateToYYMMDDHHmmString</code> method here.
     *
     * @param calendar a <code>Calendar</code> value
     * @return a <code>String</code> value
     */
    public static String dateToYYYYMMDDHHmmString(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        return df.format(calendar.getTime());
    }

    /**
     * Describe <code>dateToYYMMDDHHmmString</code> method here.
     *
     * @param date a <code>Date</code> value
     * @return a <code>String</code> value
     */
    public static String dateToYYYYMMDDHHmmString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        return df.format(date);
    }

    public static String dateToDDMMYYYYmmString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        return df.format(date);
    }

    /**
     * ddMMyyyy
     */
    public static String dateToddMMyyyy(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        return df.format(date);
    }

    public static String dateToddMMyyyyWithHyphen(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }

    /**
     * Describe <code>yyMMDDhhmmToCalendar</code> method here.
     *
     * @param param a <code>String</code> value
     * @return a <code>Calendar</code> value
     */
    public static Calendar yyMMDDhhmmToCalendar(String param) {
        int yY = Integer.parseInt(param.substring(0, 2)) + 2000;
        int mM = Integer.parseInt(param.substring(2, 4)) - 1;
        int dD = Integer.parseInt(param.substring(4, 6));
        int hh = Integer.parseInt(param.substring(6, 8));
        int mm = Integer.parseInt(param.substring(8, 10));

        GregorianCalendar calendar = new GregorianCalendar(yY, mM, dD, hh, mm);

        return calendar;
    }

    /**
     * Describe <code>yyyy_MM_DDToCalendar</code> method here.
     *
     * @param param a <code>String</code> value
     * @return a <code>Calendar</code> value
     */
    public static Calendar yyyy_MM_DDToCalendar(String param) {
        GregorianCalendar calendar;

        try {
            int yY = Integer.parseInt(param.substring(0, 4));
            int mM = Integer.parseInt(param.substring(5, 7)) - 1;
            int dD = Integer.parseInt(param.substring(8, 10));
            calendar = new GregorianCalendar(yY, mM, dD);
        } catch (RuntimeException e) {
            calendar = new GregorianCalendar(1900, 0, 1);
        }
        return calendar;
    }

    public static Calendar yyyy_MM_DDToCalendar(Calendar param) {
        GregorianCalendar calendar;

        try {
            int yY = param.get(Calendar.YEAR);
            int mM = param.get(Calendar.MONTH);
            int dD = param.get(Calendar.DAY_OF_MONTH);
            calendar = new GregorianCalendar(yY, mM, dD);
        } catch (RuntimeException e) {
            calendar = new GregorianCalendar(1900, 0, 1);
        }
        return calendar;
    }

    /**
     * Describe <code>yyyy_MM_DDToCalendar</code> method here.
     *
     * @param param a <code>String</code> value
     * @return a <code>Calendar</code> value
     */
    public static Calendar dd_MM_YYYYToCalendar(String param) {
        GregorianCalendar calendar;

        try {
            int dD = Integer.parseInt(param.substring(0, 2));
            int mM = Integer.parseInt(param.substring(3, 5)) - 1;
            int yY = Integer.parseInt(param.substring(6, 10));
            calendar = new GregorianCalendar(yY, mM, dD);
        } catch (RuntimeException e) {
            calendar = new GregorianCalendar(1900, 0, 1);
        }

        return calendar;
    }

    public static Calendar dd_MM_YYYYToCCalendar(String param) {
        Calendar calendar = Calendar.getInstance();

        try {
            int dD = Integer.parseInt(param.substring(0, 2));
            int mM = Integer.parseInt(param.substring(3, 5)) - 1;
            int yY = Integer.parseInt(param.substring(6, 10));
            calendar.set(yY, mM, dD);
        } catch (RuntimeException e) {
            calendar.set(1900, 0, 1);
        }
        return calendar;
    }

    public static String dateToMMddyyString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        return df.format(date.getTime());
    }

    public static Date dateToddMMyyyyString(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static Date stringToyyyyMMddDate(String stringDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date();
        try {
            myDate = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

    public static Date stringToddMMyyyyDate(String stringDate) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = new Date();
        try {
            myDate = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

    public static Date stringTohhMMssDate(String stringDate) {
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        Date myDate = new Date();
        try {
            myDate = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

    public static Date addHourToDate(Date date, int hour) {
        Date tomorrow;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        tomorrow = calendar.getTime();
        return tomorrow;
    }

    public static Date addDayToDate(Date date, int day) {
        Date tomorrow;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        tomorrow = calendar.getTime();
        return tomorrow;
    }

    public static Date addMonthToDate(Date date, int month) {
        Date tomorrow;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        tomorrow = calendar.getTime();
        return tomorrow;
    }

    public static Date subtractDayToDate(Date date, int day) throws Exception {
        if (date == null)
            return null;

        // we are expecting day as possitive number
        if (day < 0) {
            throw new Exception("wrong usage in subtract dayToDate method");
        }
        Date targetDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -day);
        targetDate = calendar.getTime();
        return targetDate;
    }

    public static Integer getCurrentDay() {
        GregorianCalendar calendar = new GregorianCalendar();

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Integer getCurrentMonth() {
        GregorianCalendar calendar = new GregorianCalendar();

        return calendar.get(Calendar.MONTH) + 1;
    }

    public static Integer getCurrentYear() {
        GregorianCalendar calendar = new GregorianCalendar();

        return calendar.get(Calendar.YEAR);
    }

    public static Integer getRealDateYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.YEAR);
    }

    public static Integer getRealDateMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH) + 1;
    }

    public static Integer getRealDateDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Integer calculateAge(Date birthDate) {
        GregorianCalendar birthDateCalendar = new GregorianCalendar();
        birthDateCalendar.setTime(birthDate);
        GregorianCalendar now = new GregorianCalendar();

        Integer age = now.get(Calendar.YEAR) - birthDateCalendar.get(Calendar.YEAR);

        return age < 0 ? 0 : age;
    }

    /*
     * Difference Between Two Months
     */
    public static String calculateMonth(Calendar dateTime) {

        Calendar today = Calendar.getInstance();

        long milliseconds1 = dateTime.getTimeInMillis();
        long milliseconds2 = today.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;
        long diffMonths = (diff / (24 * 60 * 60 * 1000)) / 30;
        System.out.println("Time in days: " + diffMonths + " Months.");

        String dateDifference = diffMonths + " month(s) ";
        System.out.println(dateDifference);

        return diffMonths + " AY";
    }

    /*
     * Difference Between Two Days
     */
    public static String calculateDay(Calendar dateTime) {

        Calendar today = Calendar.getInstance();

        long milliseconds1 = dateTime.getTimeInMillis();
        long milliseconds2 = today.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;
        // long diffSeconds = diff / 1000;
        // long diffMinutes = diff / (60 * 1000);
        // long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.println("Time in days: " + diffDays + " days.");

        return diffDays + " G�n";
    }

    public enum IntervalType {
        Month, Week
    }

    public static Calendar[] getDateIntervals(IntervalType type, Calendar reference) {
        if (reference == null) {
            reference = Calendar.getInstance();
        }
        Calendar startDate = (Calendar) reference.clone();
        Calendar endDate = (Calendar) reference.clone();

        if (type == IntervalType.Month) {
            // first date of the month
            startDate.set(Calendar.DATE, 1);
            // previous month
            startDate.add(Calendar.MONTH, -1);

            // first date of the month
            endDate.set(Calendar.DATE, 1);
            // previous month, last date
            endDate.add(Calendar.DATE, -1);
        } else {
            // previous week by convention (monday ... sunday)
            // you will have to adjust this a bit if you want
            // sunday to be considered as the first day of the week.
            // start date : decrement until first sunday then
            // down to monday
            int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
            while (dayOfWeek != Calendar.SUNDAY) {
                startDate.add(Calendar.DATE, -1);
                dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
            }
            while (dayOfWeek != Calendar.MONDAY) {
                startDate.add(Calendar.DATE, -1);
                dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
            }

            // end date , decrement until the first sunday
            dayOfWeek = endDate.get(Calendar.DAY_OF_WEEK);
            while (dayOfWeek != Calendar.SUNDAY) {
                endDate.add(Calendar.DATE, -1);
                dayOfWeek = endDate.get(Calendar.DAY_OF_WEEK);
            }
        }
        return new Calendar[]{startDate, endDate};
    }

    public static String stringYYYYMMDD(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int m = c.get(Calendar.MONTH) + 1;
        int d = c.get(Calendar.DATE);
        String mm = Integer.toString(m);
        String dd = Integer.toString(d);
        return "" + c.get(Calendar.YEAR) + (m < 10 ? "0" + mm : mm) + (d < 10 ? "0" + dd : dd);

        // String formatedDate = "";
        //
        // DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        // try {
        // Date myDate = formatter.parse(date.toString());
        // formatedDate = myDate.toString();
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        //
        // return formatedDate;
    }

    public static Date convertCalendarToDate(Calendar date) {
        Calendar myDate = Calendar.getInstance();
        myDate = date;
        return myDate.getTime();
    }

    public static List<String> getMonthsBetweenTwoDates(Date startDate, Date endDate) {
        List<String> monthAndYearList = new ArrayList<String>();
        try {

            Calendar startDateCal = Calendar.getInstance();
            Calendar endDateCal = Calendar.getInstance();

            startDateCal.setTime(startDate);
            endDateCal.setTime(endDate);

            int i = 1;
            while (startDateCal.getTime().before(endDateCal.getTime())) {
                if ((startDateCal.get(Calendar.MONTH) + 1) != BigDecimal.ZERO.intValue()) {
                    monthAndYearList.add(String.valueOf((startDateCal.get(Calendar.MONTH) + 1)) + "|"
                            + String.valueOf(startDateCal.get(Calendar.YEAR)));
                    startDateCal.set(Calendar.MONTH, (startDateCal.get(Calendar.MONTH) + 1));

                } else {
                    monthAndYearList.add(String.valueOf(12) + "|" + String.valueOf((startDateCal.get(Calendar.YEAR) - 1)));
                    startDateCal.set(Calendar.MONTH, (startDateCal.get(Calendar.MONTH) + 1));
                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return monthAndYearList;
    }

    /**
     * @param start
     * @param end
     * @return
     */
    public static int countDaysBetweenTwoDates(Date start, Date end) {
        if (end.compareTo(start) == -1) {
            System.out.println("Start : " + start);
            System.out.println("End   : " + end);
            throw new IllegalArgumentException("Biti� tarihi ba�lang�� tarihinden �nce olamaz.");
        }

        final long ONE_HOUR = 60 * 60 * 1000L;
        BigDecimal ONE_DAY = new BigDecimal(ONE_HOUR * 24);
        BigDecimal days = (new BigDecimal(end.getTime() - (start.getTime()))).divide(ONE_DAY, 0, BigDecimal.ROUND_HALF_UP);

        return days.intValue();
    }

    public static Date endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return calendar.getTime();
    }

    public static Date startOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    public static Calendar getCalenderDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, day);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar;
    }

    public static Calendar getCalender(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar;
    }

    public static Date differenceBetweenTwoDates(Date start, Date end) {

        if (end == null || start == null) {
            throw new IllegalArgumentException("Girdi�iniz tarih de�erlerini kontrol ediniz.");
        }

        if (end.before(start)) {
            throw new IllegalArgumentException("Biti� tarihi ba�lang�� tarihinden �nce olamaz.");
        }

        Calendar startCalendar = new GregorianCalendar();
        Calendar endCalendar = new GregorianCalendar();
        startCalendar.setTime(start);
        endCalendar.setTime(end);

        int day = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH) + 1;
        if (day < 0) {
            day = day + 30;
            endCalendar.add(Calendar.MONTH, -1);
        }

        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        if (month < 0) {
            month = month + 12;
            endCalendar.add(Calendar.YEAR, -1);
        }

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);

        // 0 y�l olsada tarihi cektigimiz de yil'� 1 olarak getiriyordu.
        if (year == 0)
            year = 1000;

        Calendar differenceCalendar = new GregorianCalendar();
        differenceCalendar.set(year, month, day);

        return differenceCalendar.getTime();

    }

    public static String differenceBetweenTwoDatesAsString(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        if (date1.after(date2)) {
            calendar1.setTime(date1);
            calendar2.setTime(date2);
        } else {
            calendar1.setTime(date2);
            calendar2.setTime(date1);
        }
        long milliseconds1 = calendar1.getTimeInMillis();
        long milliseconds2 = calendar2.getTimeInMillis();
        long diff = milliseconds1 - milliseconds2;
        long diffSeconds = (diff / 1000) % 60;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return "Time: " + diffSeconds + " seconds,   " + diffMinutes + " minutes,   " + diffHours + " hours.";
    }

    public static String differenceBetweenTwoDatesAsString(long diff) {
        long diffSeconds = (diff / 1000) % 60;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return "Time: " + diffSeconds + " seconds,   " + diffMinutes + " minutes,   " + diffHours + " hours.";
    }

    public static void main(String[] args) {
        System.out.println(differenceBetweenTwoDatesAsString(new Date(), new Date()));
    }

    public static Date addTwoDates(Date firstDate, Date secondDate) {

        if (firstDate == null && secondDate == null)
            return null;
        else if (firstDate == null)
            return secondDate;
        else if (secondDate == null)
            return firstDate;

        Calendar firstCalendar = new GregorianCalendar();
        Calendar secondCalendar = new GregorianCalendar();
        firstCalendar.setTime(firstDate);
        secondCalendar.setTime(secondDate);

        int day = secondCalendar.get(Calendar.DAY_OF_MONTH) + firstCalendar.get(Calendar.DAY_OF_MONTH);
        if (day > 30) {
            day = day - 30;
            secondCalendar.add(Calendar.MONTH, 1);
        }
        int month = secondCalendar.get(Calendar.MONTH) + firstCalendar.get(Calendar.MONTH);
        if (month > 12) {
            month = month - 12;
            secondCalendar.add(Calendar.YEAR, 1);
        }
        int year = secondCalendar.get(Calendar.YEAR) + firstCalendar.get(Calendar.YEAR);

        Calendar totalCalendar = new GregorianCalendar();
        totalCalendar.set(year, month, day);
        return totalCalendar.getTime();

    }

    public static int elapsedMonths(Calendar before, Calendar after) {
        return elapsed(before, after, Calendar.MONTH);
    }

    private static int elapsed(Calendar before, Calendar after, int field) {
        Calendar clone = (Calendar) before.clone();
        int elapsed = -1;
        while (!clone.after(after)) {
            clone.add(field, 1);
            elapsed++;
        }
        return elapsed;
    }

    public static int monthDifference(GregorianCalendar begin, GregorianCalendar end, int period) {
        begin = (GregorianCalendar) begin.clone();
        checkBeforeAfter(begin, end);
        int result = 0;
        while (end.compareTo(begin) >= 0) {
            result++;
            begin.add(Calendar.MONTH, period);
        }

        return result;
    }

    private static void checkBeforeAfter(Calendar before, Calendar after) {
        if (before.after(after)) {
            throw new IllegalArgumentException("The first calendar should be dated before the second calendar.");
        }
    }

    public static Calendar dateToCalendar(Date givenDate) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);

        return date;
    }

    public static int getYearOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);

        return year;
    }

    public static int getMonthOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int month = calendar.get(Calendar.MONTH) + 1;

        return month;
    }

    public static int getDayOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return day;
    }

    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getFirstDayOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return calendar.getTime();
    }

    public static Date getLastDayOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return calendar.getTime();
    }

    public static long daysBetween(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startDate);
        end.setTime(endDate);
        return daysBetween(start, end);
    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {
        if (startDate.after(endDate))
            throw new IllegalArgumentException("Biti� tarihi ba�lang�� tarihinden �nce olamaz.");

        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public static Date replaceNullWithCurrentDate(Date date) {
        return date == null ? new Date() : date;
    }

    /**
     * @param date
     * @return for reportName format : "dd.MM.yyyy HH:mm:ss"
     */
    public static String dateToStringdMMyyyyHHmmSS(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return df.format(date);

    }

    public static String dateToStringdMMyyyyHHmm(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return df.format(date);

    }

    public static String dateToStringHHmm(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(date);

    }

    /**
     * @param startDate
     * @param endDate
     * @return difference as int array. index 0 -> year index 1 -> month index 2
     * -> day
     */
    public static int[] differenceBetweenToDatesAsIntArray(Date startDate, Date endDate) {
        int resultYear = 0;
        int resultMonth = 0;
        int resultDay = 0;

        int startYear = DateUtil.getYearOfDate(startDate);
        int startMonth = DateUtil.getMonthOfDate(startDate);
        int startDay = DateUtil.getDayOfDate(startDate);

        int endYear = DateUtil.getYearOfDate(endDate);
        int endMonth = DateUtil.getMonthOfDate(endDate);
        int endDay = DateUtil.getDayOfDate(endDate);
        endDay = endDay + 1;

        int[] startArray = {startYear, startMonth, startDay};
        int[] endArray = {endYear, endMonth, endDay};
        int[] resultArray = {resultYear, resultMonth, resultDay};
        Calendar refCal = DateUtil.getCalender(startDate);
        refCal = DateUtil.getLastDayOfMonth(refCal);
        int[] refArray = {0, 12, refCal.get(Calendar.DAY_OF_MONTH)};

        for (int i = 0; i < 3; i++) {
            resultArray[i] = endArray[i] - startArray[i];
            if (resultArray[i] < 0) {
                resultArray[i] = refArray[i] - (startArray[i] - endArray[i]);
                if (i != 0) {
                    resultArray[i - 1] = resultArray[i - 1] - 1;
                }
            }
        }

        for (int i = 1; i >= 0; i--) {
            if (resultArray[i] < 0) {
                resultArray[i - 1] = resultArray[i - 1] - 1 < 0 ? 0 : resultArray[i - 1] - 1;
                resultArray[i] = refArray[i] + resultArray[i];
            }
        }
        return resultArray;
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        if (date == null) {
            return null;
        }
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date));
        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }
        return xmlCalendar;
    }

    public static XMLGregorianCalendar stringToXMLGregorianCalender(String dateStr) {
        Date temp = stringToddMMyyyyDate(dateStr);
        return toXMLGregorianCalendar(temp);
    }

    public static Date XMLGregorianCalendarToDate(XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar == null) {
            return null;
        }
        return xmlGregorianCalendar.toGregorianCalendar().getTime();

    }

    public static LocalDate getPreviousDay() {
        LocalDate today = LocalDate.now();
        return today.minus(1, ChronoUnit.DAYS);
    }

    public static String dateToMmmmmYyyyString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("MMMMM yyyy");
        if (date == null)
            return null;
        return df.format(date.getTime());
    }

    public static String dateToddMMyyyyHHmmsString(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return dateToddMMyyyyHHmmssString(calendar);
    }

    public static Date millisToDate(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return calendar.getTime();

    }

    public static String dateToMMddyyyyString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        if (date == null)
            return null;
        return df.format(date.getTime());
    }
}
