package slosar.srt.spendingsapp.DataProviders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.DbModule.CategoryDao;
import slosar.srt.spendingsapp.DbModule.DaoMaster;
import slosar.srt.spendingsapp.DbModule.DaoSession;
import slosar.srt.spendingsapp.DbModule.Spending;
import slosar.srt.spendingsapp.DbModule.SpendingDao;

/**
 * Created by Rafal on 2016-04-23.
 */
public class DbProvider implements IDbProvider {
    private Context context;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DbProvider(Context context) {
        this.context = context;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "spendings-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
    }

    @Override
    public DaoSession getSession() {
        if (daoSession == null)
            daoSession = daoMaster.newSession();
        return daoSession;
    }

    @Override
    public List<Category> getCategoryList() {
        CategoryDao categoryDao = getSession().getCategoryDao();
        return categoryDao.loadAll();
    }

    @Override
    public List<Spending> getSpendingsList(Category category) {
        SpendingDao spendingDao = getSession().getSpendingDao();
        return spendingDao.queryBuilder()
                .where(SpendingDao.Properties.CategoryId.eq(category.getId()))
                .list();
    }

    @Override
    public void addCategory(Category category) {
        CategoryDao categoryDao = getSession().getCategoryDao();
        categoryDao.insert(category);
    }

    @Override
    public void addSpending(Spending spending) {
        SpendingDao spendingDao = getSession().getSpendingDao();
        spendingDao.insert(spending);
    }
}
