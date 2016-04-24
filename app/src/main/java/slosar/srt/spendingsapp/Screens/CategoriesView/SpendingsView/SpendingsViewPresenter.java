package slosar.srt.spendingsapp.Screens.CategoriesView.SpendingsView;

import android.content.Context;

import java.util.List;

import slosar.srt.spendingsapp.DataProviders.DbProvider;
import slosar.srt.spendingsapp.DataProviders.IDbProvider;
import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.DbModule.Spending;

/**
 * Created by Rafal on 2016-04-24.
 */
public class SpendingsViewPresenter {
    private ISpendingsView view;
    private SpendingAdapter adapter;
    private IDbProvider dbProvider;
    private List<Spending> spendingList;

    public SpendingsViewPresenter(ISpendingsView view, SpendingItemClickListener listener, Context context, Category viewedCategory) {
        this.view = view;
        dbProvider = new DbProvider(context);
        spendingList = dbProvider.getSpendingsList(viewedCategory);
        adapter = new SpendingAdapter(spendingList, listener);
    }

    public void editCategory(String oldCategoryName, String categoryName) {
        //dbProvider.editCategory(newCategory);
        //adapter.notifyDataSetChanged();
    }

    public SpendingAdapter getDataAdapter() {
        return adapter;
    }
}