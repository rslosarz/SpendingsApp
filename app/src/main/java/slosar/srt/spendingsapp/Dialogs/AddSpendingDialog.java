package slosar.srt.spendingsapp.Dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import slosar.srt.spendingsapp.Commons.Commons;
import slosar.srt.spendingsapp.Exceptions.ExceptionEvent;
import slosar.srt.spendingsapp.R;

/**
 * Created by Rafal on 2016-04-24.
 */
public class AddSpendingDialog extends CustomDialog {

    private EditText mTitle;
    private EditText mValue;
    private TextView mDate;
    private Button mButtonDateInput;
    private SpendingDialogListener mListener;
    private DatePickerDialog datePickerDialog = null;
    private int mYear;
    private int mMonth;
    private int mDay;
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            datePickerDialog.dismiss();
        }
    };
    private int mHour;
    private int mMinutes;

    public AddSpendingDialog(Context context, SpendingDialogListener listener) {
        super(context, R.layout.dialog_add_spending);
        mListener = listener;
        mTitle = (EditText) findViewById(R.id.et_title);
        mValue = (EditText) findViewById(R.id.et_value);
        mDate = (TextView) findViewById(R.id.et_date);
        mButtonDateInput = (Button) findViewById(R.id.dialogDateInput);

        setDate();
        datePickerDialog = new DatePickerDialog(context, dateSetListener, mYear, mMonth, mDay);
        mButtonDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    @Override
    protected void okAction() {
        try {
            float value = Float.parseFloat(mValue.getText().toString());
            Date dateGiven = Commons.dateFormat.parse(mDate.getText().toString());
            mListener.addSpending(value, mTitle.getText().toString(), dateGiven);
        } catch (ParseException e) {
            e.printStackTrace();
            EventBus.getDefault().postSticky(new ExceptionEvent(e));
        } finally {
            dismiss();
        }
    }

    private void setDate() {
        Date currentDate = new Date(System.currentTimeMillis());
        String currentDateString = Commons.dateFormat.format(currentDate);
        mDate.setText(currentDateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinutes = calendar.get(Calendar.MINUTE);
    }
}
