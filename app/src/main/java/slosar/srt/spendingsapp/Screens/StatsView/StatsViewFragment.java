package slosar.srt.spendingsapp.Screens.StatsView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import slosar.srt.spendingsapp.Commons.Commons;
import slosar.srt.spendingsapp.Exceptions.ExceptionEvent;
import slosar.srt.spendingsapp.R;

public class StatsViewFragment extends Fragment implements IStatsView {

    @Bind(R.id.tv_date_from)
    TextView mDateFrom;
    @Bind(R.id.tv_date_through)
    TextView mDateThrough;
    @Bind(R.id.tv_spendings_sum)
    TextView mSpendingsSum;

    private DatePickerDialog datePickerDialog = null;
    DatePickerDialog.OnDateSetListener dateFromSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            Date newDate = calendar.getTime();
            mDateFrom.setText(Commons.dateFormat.format(newDate));
            datePickerDialog.dismiss();
        }
    };
    DatePickerDialog.OnDateSetListener dateThroughSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            Date newDate = calendar.getTime();
            mDateThrough.setText(Commons.dateFormat.format(newDate));
            datePickerDialog.dismiss();
        }
    };
    private int mYear;
    private int mMonth;
    private int mDay;
    private StatsViewPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stats_view, container, false);
        ButterKnife.bind(this, rootView);
        mPresenter = new StatsViewPresenter(getActivity(), this);
        setDate();
        return rootView;
    }

    @OnClick(R.id.bt_get_sum)
    public void onGetSumClick() {
        try {
            Date dateFrom = Commons.dateFormat.parse(mDateFrom.getText().toString());
            Date dateThrough = Commons.dateFormat.parse(mDateThrough.getText().toString());
            mPresenter.getSum(dateFrom, dateThrough);
        } catch (ParseException e) {
            e.printStackTrace();
            EventBus.getDefault().postSticky(new ExceptionEvent(e));
        }
    }

    @OnClick(R.id.bt_date_from)
    public void setDateFromClick() {
        datePickerDialog = new DatePickerDialog(getActivity(), dateFromSetListener, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.bt_date_through)
    public void setDateThroughClick() {
        datePickerDialog = new DatePickerDialog(getActivity(), dateThroughSetListener, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void showSum(float sum) {
        mSpendingsSum.setText(Float.toString(sum));
    }

    private void setDate() {
        Date currentDate = new Date(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
    }
}
