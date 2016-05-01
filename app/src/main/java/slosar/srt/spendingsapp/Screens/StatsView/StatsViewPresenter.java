package slosar.srt.spendingsapp.Screens.StatsView;

import android.content.Context;

import java.util.Date;

import slosar.srt.spendingsapp.DataProviders.DbProvider;
import slosar.srt.spendingsapp.DataProviders.IDbProvider;

/**
 * Created by Rafal on 2016-05-01.
 */
public class StatsViewPresenter {

    private IDbProvider dbProvider;
    private IStatsView view;

    public StatsViewPresenter(Context context, IStatsView view) {
        this.view = view;
        dbProvider = new DbProvider(context);
    }


    public void getSum(Date dateFrom, Date dateThrough) {
        float sum = dbProvider.getSum(dateFrom, dateThrough);
        view.showSum(sum);
    }
}
