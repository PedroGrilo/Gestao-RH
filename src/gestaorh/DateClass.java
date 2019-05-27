package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Pedro Grilo
 */
public class DateClass implements Date {

    private int day;
    private int month;
    private int year;

    /**
     *
     * @param day
     * @param month
     * @param year
     */
    public DateClass(int day, int month, int year) {
        if (validateDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {

            throw new GestaoException(GestaoErro.DATA_INVALIDA);
        }
    }

    /**
     *
     */
    public DateClass() {
        this.day = LocalDate.now().getDayOfMonth();
        this.month = LocalDate.now().getMonthValue();
        this.year = LocalDate.now().getYear();
    }

    private boolean validateDate(int day, int month, int year) {
        if (year < 1582) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > daysOfMonth(month, year)) {
            return false;
        }
        if (year == 1582) {
            if (month < 10 || month == 10 && day < 15) {
                return false;
            }
        }
        return true;
    }

    private int daysOfMonth(int month, int year) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }
        return 0;
    }

    private boolean isLeapYear(int year) {
        // O ano é bissexto se for divisível por quatro, exceto anos múltiplos
        // de 100 que não são múltiplos de 400:
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    /**
     *
     * @return
     */
    public int getDay() {
        return day;
    }

    /**
     *
     * @param day
     */
    public void setDay(int day) {
        if (validateDate(day, this.month, this.year)) {
            this.day = day;
        }
    }

    /**
     *
     * @return
     */
    public int getMonth() {
        return month;
    }

    /**
     *
     * @param month
     */
    public void setMonth(int month) {
        if (validateDate(this.day, month, this.year)) {
            this.month = month;
        }
    }

    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        if (validateDate(this.day, this.month, year)) {
            this.year = year;
        }
    }

    /**
     *
     * @return
     */
    public int getMonthsTilToday() {
        int monthsTilToday = getCurrentMonth() - month;
        if (day > getCurrentDay()) {
            monthsTilToday -= 1;
        }
        return monthsTilToday;
    }

    /**
     *
     * @return
     */
    public int getYearsTilToday() {  // era getAge
        return Period.between(LocalDate.of(year, month, day), LocalDate.of(getCurrentYear(), getCurrentMonth(), getCurrentDay())).getYears();
    }

    private int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    private int getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }

    private int getCurrentDay() {
        return LocalDate.now().getDayOfMonth();
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
