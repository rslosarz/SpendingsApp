package slosar.srt.spendingsapp.DataProviders;

import slosar.srt.spendingsapp.Exceptions.CategoryNameEmptyException;

/**
 * Created by Rafal on 2016-05-01.
 */
public class CategoryDataValidator {
    public static void validate(String title) throws CategoryNameEmptyException {
        if (title == null)
            throw new CategoryNameEmptyException();
        else if (title.isEmpty())
            throw new CategoryNameEmptyException();
    }
}
