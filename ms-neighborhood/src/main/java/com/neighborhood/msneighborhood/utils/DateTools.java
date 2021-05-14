package com.neighborhood.msneighborhood.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {

    /**
     * Méthode pour ajouter un nombre de jour à une date
     *
     * @param date Date de départ
     * @param nbDay Nombre de jours à ajouter
     * @return La date mise à jour
     */
    public static Date addDays(Date date, int nbDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, nbDay);
        return cal.getTime();
    }

    /**
     * Méthode pour formater une date et la retourner au format ext.
     * @param date Date à formater
     * @param pattern String du pattern à utiliser (ex : "dd/MM/yyyy")
     * @return Le String de la date au format requis
     */
    public static String dateFormat(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

}
