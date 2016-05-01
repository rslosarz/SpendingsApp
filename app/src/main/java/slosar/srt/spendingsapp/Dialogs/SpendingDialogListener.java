package slosar.srt.spendingsapp.Dialogs;

import java.util.Date;

/**
 * Created by Rafal on 2016-05-01.
 */
public interface SpendingDialogListener {
    void addSpending(float value, String title, Date date);
}
