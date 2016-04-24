package slosar.srt.spendingsapp.DataProviders;

import java.util.List;

import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.DbModule.DaoSession;
import slosar.srt.spendingsapp.DbModule.Spending;
import slosar.srt.spendingsapp.Exceptions.CategoryNameExistsException;

/**
 * Created by Rafal on 2016-04-23.
 */
public interface IDbProvider {
    DaoSession getSession();

    List<Category> getCategoryList();

    List<Spending> getSpendingsList(Category category);

    void addCategory(Category category) throws CategoryNameExistsException;

    void addSpending(Spending spending);
}
