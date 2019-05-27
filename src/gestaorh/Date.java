package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pedro Grilo
 */
public interface Date {

    /**
     *
     * @return
     */
    int getDay();

    /**
     *
     * @return
     */
    int getMonth();

    /**
     *
     * @return
     */
    int getYear();

    /**
     *
     * @return
     */
    int getYearsTilToday();

    /**
     *
     * @param day
     */
    void setDay(int day);

    /**
     *
     * @param month
     */
    void setMonth(int month);

    /**
     *
     * @param year
     */
    void setYear(int year);

    /**
     *
     * @return
     */
    int getMonthsTilToday();

    /**
     *
     * @return
     */
    String toString();

}
