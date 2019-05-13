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

    int getDay();

    int getMonth();

    int getYear();

    int getYearsTilToday();

    void setDay(int day);

    void setMonth(int month);

    void setYear(int year);

    int getMonthsTilToday();

    String toString();

}
