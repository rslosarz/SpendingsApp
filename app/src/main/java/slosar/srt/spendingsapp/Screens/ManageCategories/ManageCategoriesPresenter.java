package slosar.srt.spendingsapp.Screens.ManageCategories;

import android.content.Context;

import java.util.List;

import slosar.srt.spendingsapp.DataProviders.DbProvider;
import slosar.srt.spendingsapp.DataProviders.IDbProvider;
import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.Exceptions.CategoryNameExistsException;

/**
 * Created by Rafal on 2016-04-24.
 */
public class ManageCategoriesPresenter {

    private CategoryAdapter adapter;
    private IDbProvider dbProvider;
    private List<Category> categoriesList;

    public ManageCategoriesPresenter(Context context) {
        dbProvider = new DbProvider(context);
        categoriesList = dbProvider.getCategoryList();
        adapter = new CategoryAdapter(categoriesList, null);
    }

    public void addCategory(String categoryName) throws CategoryNameExistsException {
        Category newCategory = new Category(null, categoryName);
        dbProvider.addCategory(newCategory);
        adapter.notifyDataSetChanged();
    }

    public CategoryAdapter getDataAdapter() {
        return adapter;
    }
}
