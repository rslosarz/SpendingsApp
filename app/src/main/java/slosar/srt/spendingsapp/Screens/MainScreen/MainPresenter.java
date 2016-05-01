package slosar.srt.spendingsapp.Screens.MainScreen;

import android.content.Context;

import java.util.List;

import slosar.srt.spendingsapp.DataProviders.DbProvider;
import slosar.srt.spendingsapp.DataProviders.IDbProvider;
import slosar.srt.spendingsapp.DbModule.Category;

/**
 * Created by Rafal on 2016-04-23.
 */
public class MainPresenter {

    private IMainView view;
    private IDbProvider dbProvider;

    public MainPresenter(IMainView view, Context context) {
        this.view = view;
        dbProvider = new DbProvider(context);
    }

    public void showData() {
        List<Category> categories = dbProvider.getCategoryList();
        if (categories.isEmpty()) {
            view.showDbEmpty();
        } else {
            view.showDbEntry();
        }
    }

    public boolean isDbEmpty() {
        List<Category> categories = dbProvider.getCategoryList();
        return categories.isEmpty();
    }
}
