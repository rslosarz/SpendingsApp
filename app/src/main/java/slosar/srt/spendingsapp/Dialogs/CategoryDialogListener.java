package slosar.srt.spendingsapp.Dialogs;

/**
 * Created by Rafal on 2016-04-24.
 */
public interface CategoryDialogListener {
    void addCategory(String categoryName);

    void editCategory(String oldCategoryName, String categoryName);
}
