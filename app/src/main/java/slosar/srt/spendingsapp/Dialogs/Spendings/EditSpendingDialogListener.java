package slosar.srt.spendingsapp.Dialogs.Spendings;

import java.util.Date;

import slosar.srt.spendingsapp.DbModule.Spending;

/**
 * Created by Rafal on 2016-05-01.
 */
public interface EditSpendingDialogListener {
    void editSpending(Spending item, float value, String s, Date dateGiven);

    void deleteSpending(Spending spending);
}
