package slosar.srt.spendingsapp.DataProviders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.List;

import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.DbModule.CategoryDao;
import slosar.srt.spendingsapp.DbModule.DaoMaster;
import slosar.srt.spendingsapp.DbModule.DaoSession;
import slosar.srt.spendingsapp.DbModule.Spending;
import slosar.srt.spendingsapp.DbModule.SpendingDao;
import slosar.srt.spendingsapp.Exceptions.CategoryNameExistsException;

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

        if (category.getName().isEmpty()) {
            return spendingDao.loadAll();
        } else {
            return spendingDao.queryBuilder()
                    .where(SpendingDao.Properties.CategoryId.eq(category.getId()))
                    .list();
        }
    }

    @Override
    public void addCategory(Category category) throws CategoryNameExistsException {

        CategoryDao categoryDao = getSession().getCategoryDao();

        List<Category> categoryList = categoryDao.queryBuilder()
                .where(CategoryDao.Properties.Name.eq(category.getName()))
                .list();

        if (!categoryList.isEmpty()) {
            throw new CategoryNameExistsException();
        }

        categoryDao.insert(category);
    }

    @Override
    public void addSpending(Spending spending) {
        SpendingDao spendingDao = getSession().getSpendingDao();
        spendingDao.insert(spending);
    }

    @Override
    public void editSpending(Spending spending, float value, String title, Date date) {
        spending.setTitle(title);
        spending.setValue(value);
        spending.setDate(date);
        spending.update();
    }

    @Override
    public void deleteSpending(Spending spending) {
        spending.delete();
    }
}
