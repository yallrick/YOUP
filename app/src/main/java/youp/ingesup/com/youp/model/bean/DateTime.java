package youp.ingesup.com.youp.model.bean;

import android.util.Log;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vincent del Valle on 14/11/2014.
 */
public class DateTime {


    // doc : http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html#rfc822timezone
    public final static String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final Pattern PATTERN_DATETIME = Pattern.compile("([0-9]{4})\\-([0-9]{2})\\-([0-9]{2})T([0-9]{2}):([0-9]{2}):([0-9]{2})");
    private static final Pattern PATTERN_DATE_ymd = Pattern.compile("([0-9]{4})\\-([0-9]{2})\\-([0-9]{2})");
    private static final Pattern PATTERN_DATE_dmy = Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{4})");

    private String value;
    private boolean isDateOnly;
    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;
    private int seconds;

    /**
     * Crée une instance de DateTime à partir d'une chaine de caractère.
     * @param value Représentation de la date. Ex : 2014-05-27T20:12:05+0000
     */
    public DateTime(String value){
        if(value != null) {
            if (isValidFormatDateTime(value)) {

                readDateTimeFromString(value);

            }else {

                Matcher m = PATTERN_DATE_ymd.matcher(value);
                Matcher m2 = PATTERN_DATE_dmy.matcher(value);

                if(m.matches()){

                    this.value = value;
                    year = Integer.valueOf(m.group(1));
                    month = Integer.valueOf(m.group(2));
                    day = Integer.valueOf(m.group(3));
                    hours = 0;
                    minutes = 0;
                    seconds = 0;

                    isDateOnly = true;

                }else if(m2.matches()){

                    this.value = value;
                    year = Integer.valueOf(m2.group(3));
                    month = Integer.valueOf(m2.group(2));
                    day = Integer.valueOf(m2.group(1));
                    hours = 0;
                    minutes = 0;
                    seconds = 0;

                    isDateOnly = true;

                }else Log.e("DateTime", "ERREUR : le format de date utilisé n'est pas compatible avec le système actuel (" + value + ").");
            }
        }
    }

    public DateTime(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;

        generateValue();

        isDateOnly = true;
    }

    public DateTime(int year, int month, int day, int hours, int minutes, int seconds){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

        generateValue();

        isDateOnly = false;
    }

    public DateTime(int year, int month, int day, int hours, int minutes){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;

        generateValue();

        isDateOnly = false;
    }

    public static DateTime now(){
        Calendar cal = Calendar.getInstance();
        return new DateTime(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE));
    }

    public static DateTime nowDate(){
        Calendar cal = Calendar.getInstance();
        return new DateTime(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Contrôle si la chaine décrit bien une date valide
     * @param dateTime Date au format ISO. Ex: 2014-05-27T20:12:05+0000
     * @return Vrai si la date est valide
     */
    public static boolean isValidFormatDateTime(String dateTime){

        try {
            DateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            df.setLenient(false);
            df.parse(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
        /**/
    }

    @SuppressWarnings("ResourceType")
    private void readDateTimeFromString(String dateTime){
        Matcher m = PATTERN_DATETIME.matcher(dateTime);

        if (m.matches()) {

            this.value = dateTime;
            year = Integer.valueOf(m.group(1));
            month = Integer.valueOf(m.group(2));
            day = Integer.valueOf(m.group(3));
            hours = Integer.valueOf(m.group(4));
            minutes = Integer.valueOf(m.group(5));
            seconds = Integer.valueOf(m.group(6));

            isDateOnly = (hours == 0 && minutes == 0 && seconds == 0);

            // l'heure de base est toujours au fomat du serveur, c'est-à-dire +0000
            // il faut donc corriger son format pour qu'il soit compatible avec celui du téléphone

            DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            Date date = null;

            try {
                date = dateFormat.parse(dateTime);
            } catch (ParseException ignored) {}

            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hours = calendar.get(Calendar.HOUR_OF_DAY);
            minutes = calendar.get(Calendar.MINUTE);

        }else
            Log.e("DateTime", "ERREUR : le format de date utilisé n'est pas compatible avec le système actuel (" + value + "). Le problème n'a pas été détecté par la méthode de vérification.");
    }

    public String generateValue(){

        return    year
                + "-" +
                ((month <= 9) ? "0" : "") + month
                + "-" +
                ((day <= 9) ? "0" : "") + day
                + "T" +
                ((hours <= 9) ? "0" : "") + hours
                + ":" +
                ((minutes <= 9) ? "0" : "") + minutes
                + ":" +
                ((seconds <= 9) ? "0" : "") + seconds
                + "+0000";
    }

    /**
     * @return Vrai si c'est la date d'aujourd'hui
     */
    public boolean isToday(){
        Calendar cal = Calendar.getInstance();
        return (cal.get(Calendar.YEAR) == year
                && cal.get(Calendar.MONTH) == month - 1
                && cal.get(Calendar.DAY_OF_MONTH) == day);
    }

    /**
     * @return Vrai si c'est la date de hier
     */
    public boolean isYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return (cal.get(Calendar.YEAR) == year
                && cal.get(Calendar.MONTH) == month - 1
                && cal.get(Calendar.DAY_OF_MONTH) == day);
    }

    public boolean equals(DateTime dateTime){
        return (getYear() == dateTime.getYear() &&
                getMonth() == dateTime.getMonth() &&
                getDay() == dateTime.getDay() &&
                getHours() == dateTime.getHours() &&
                getMinutes() == dateTime.getMinutes());
    }

    public String getDateInFrench(){

        String date = "";

        if(isToday())
            date += "Aujourd'hui ";
        else if(isYesterday())
            date += "Hier ";
        else {
            date = "le ";
            date += this.day + " ";
            date += getMonthInFrench() + " ";
            date += year + " ";
        }

        if(!isDateOnly) {
            date += "à ";
            date += String.format("%02d", hours) + "h";
            date += String.format("%02d", minutes);
        }

        return date;
    }

    public String getMonthInFrench(){
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.FRENCH);
        return dfs.getMonths()[(month != 0) ? month - 1 : month];
    }

    public boolean isSameHourAs(DateTime dateTime){
        return (getYear() == dateTime.getYear() &&
                getMonth() == dateTime.getMonth() &&
                getDay() == dateTime.getDay() &&
                getHours() == dateTime.getHours());
    }

    public boolean isAfter(DateTime dateTime){

        return (yearIsAfter(dateTime.getYear()) ||
                monthIsAfter(dateTime) ||
                dayIsAfter(dateTime) ||
                hourIsAfter(dateTime) ||
                minuteIsAfter(dateTime));
    }

    private boolean yearIsAfter(int year){
        return getYear() > year;
    }

    private boolean monthIsAfter(DateTime dateTime){
        return getYear() == dateTime.getYear() && getMonth() > dateTime.getMonth();
    }

    private boolean dayIsAfter(DateTime dateTime){
        return  getYear() == dateTime.getYear() && getMonth() == dateTime.getMonth() && getDay() > dateTime.getDay();
    }

    private boolean hourIsAfter(DateTime dateTime){
        return getYear() == dateTime.getYear() && getMonth() == dateTime.getMonth() && getDay() == dateTime.getDay() &&
                getHours() > dateTime.getHours();
    }

    private boolean minuteIsAfter(DateTime dateTime){
        return getYear() == dateTime.getYear() && getMonth() == dateTime.getMonth() && getDay() == dateTime.getDay() &&
                getHours() == dateTime.getHours() && getMinutes() > dateTime.getMinutes();
    }

    /**
     * Renvoie la valeur de l'instance sous forme d'une chaine de caractère.
     * @return Une chaine de caractère. Ex : 2014-05-27T20:12:05+0000
     */
    public String getValue() {
        return value;
    }

    /**
     * Définie la valeur de l'insatance à partir d'une chaine de caractère.
     * @param value Représentation de la date. Ex : 2014-05-27T20:12:05+0000
     */
    public void setValue(String value) {
        this.value = value;
    }

    public String getDate(){
        return year + "-" + (month <= 9 ? "0" : "") + month + "-" +  (day <= 9 ? "0" : "") + day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

}
