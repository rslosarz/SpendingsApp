package slosar.srt.spendingsapp.Screens.CategoriesView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import slosar.srt.spendingsapp.DataProviders.DbProvider;
import slosar.srt.spendingsapp.DataProviders.IDbProvider;
import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.R;

/**
 * Created by Rafal on 2016-04-24.
 */
public class CategoriesViewPresenter {
    private ICategoriesView view;
    private List<Category> categoriesList;
    private CategoriesAdapter adapter;
    private String[] categoriesIdArray;

    public CategoriesViewPresenter(ICategoriesView view, Context context) {
        this.view = view;

        IDbProvider dbProvider = new DbProvider(context);
        categoriesList = dbProvider.getCategoryList();
        categoriesIdArray = getCategoriesNames(context, categoriesList);
        adapter = new CategoriesAdapter(context, categoriesIdArray);
    }

    public CategoriesAdapter getCategoriesAdapter() {
        return adapter;
    }

    public Category getSelectedCategoryObject(int position) {
        if (position == 0) {
            return new Category(null, "");
        } else
            return categoriesList.get(position - 1);
    }

    private String[] getCategoriesNames(Context context, List<Category> categoriesList) {
        String[] ret = new String[categoriesList.size() + 1];
        ret[0] = context.getResources().getString(R.string.all_categories_identifier);

        for (int i = 1; i <= categoriesList.size(); i++) {
            ret[i] = categoriesList.get(i - 1).getName();
        }

        return ret;
    }

    private static class CategoriesAdapter extends ArrayAdapter<String> {

        public CategoriesAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView text = (TextView) view.findViewById(android.R.id.text1);
            return view;
        }
    }
}
