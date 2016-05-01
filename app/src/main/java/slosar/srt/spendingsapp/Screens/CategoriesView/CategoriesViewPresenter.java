package slosar.srt.spendingsapp.Screens.CategoriesView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.Date;
import java.util.List;

import slosar.srt.spendingsapp.DataProviders.DbProvider;
import slosar.srt.spendingsapp.DataProviders.IDbProvider;
import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.DbModule.Spending;

/**
 * Created by Rafal on 2016-04-24.
 */
public class CategoriesViewPresenter {
    private List<Category> categoriesList;
    private CategoriesAdapter adapter;
    private String[] categoriesIdArray;
    private IDbProvider dbProvider;

    public CategoriesViewPresenter(Context context) {
        dbProvider = new DbProvider(context);
        categoriesList = dbProvider.getCategoryList();
        categoriesIdArray = getCategoriesNames(categoriesList);
        adapter = new CategoriesAdapter(context, categoriesIdArray);
    }

    public CategoriesAdapter getCategoriesAdapter() {
        return adapter;
    }

    public Category getSelectedCategoryObject(int position) {
        return categoriesList.get(position);
    }

    private String[] getCategoriesNames(List<Category> categoriesList) {
        String[] ret = new String[categoriesList.size()];

        for (int i = 0; i < categoriesList.size(); i++) {
            ret[i] = categoriesList.get(i).getName();
        }

        return ret;
    }

    public void addSpending(float value, String title, Date date, int categoryIndex) {
        Category category = categoriesList.get(categoryIndex);
        Spending newSpending = new Spending(null, value, title, date, category.getId());
        dbProvider.addSpending(newSpending);
        adapter.notifyDataSetChanged();
    }

    private static class CategoriesAdapter extends ArrayAdapter<String> {

        public CategoriesAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }
}
