package slosar.srt.spendingsapp.DataProviders;

import java.util.Calendar;
import java.util.Date;

import slosar.srt.spendingsapp.Exceptions.DateFieldEmptyException;
import slosar.srt.spendingsapp.Exceptions.DateFromFutureException;
import slosar.srt.spendingsapp.Exceptions.TitleTooShortException;
import slosar.srt.spendingsapp.Exceptions.ValueEmptyException;

/**
 * Created by Rafal on 2016-05-01.
 */
public class SpendingsDataValidator {
    public static void validate(String valueString, String title, Date dateGiven) throws DateFieldEmptyException, TitleTooShortException, DateFromFutureException, ValueEmptyException {

        if (valueString == null) {
            throw new ValueEmptyException();
        } else if (title == null || dateGiven == null) {
            throw new DateFieldEmptyException();
        } else if (title.isEmpty()) {
            throw new TitleTooShortException();
        } else if (dateGiven.after(Calendar.getInstance().getTime())) {
            throw new DateFromFutureException();
        }
    }
}
