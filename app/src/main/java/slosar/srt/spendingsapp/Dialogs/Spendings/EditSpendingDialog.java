package slosar.srt.spendingsapp.Dialogs.Spendings;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

import slosar.srt.spendingsapp.Commons.Commons;
import slosar.srt.spendingsapp.Commons.DecimalDigitsFilter;
import slosar.srt.spendingsapp.DataProviders.SpendingsDataValidator;
import slosar.srt.spendingsapp.DbModule.Spending;
import slosar.srt.spendingsapp.Dialogs.CustomDialog;
import slosar.srt.spendingsapp.Exceptions.ExceptionEvent;
import slosar.srt.spendingsapp.R;

/**
 * Created by Rafal on 2016-05-01.
 */
public class EditSpendingDialog extends CustomDialog {

    private EditText mTitle;
    private EditText mValue;
    private TextView mDate;
    private EditSpendingDialogListener mListener;
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

            Calendar calendar = Calendar.getInstance();
            calendar.set(mYear, mMonth, mDay);
            Date newDate = calendar.getTime();
            mDate.setText(Commons.dateFormat.format(newDate));

            datePickerDialog.dismiss();
        }
    };
    private Spending spending;

    public EditSpendingDialog(Context context, final EditSpendingDialogListener listener, final Spending spending) {
        super(context, R.layout.dialog_spending);
        mListener = listener;
        this.spending = spending;
        TextView info = (TextView) findViewById(R.id.info);
        info.setText(context.getResources().getString(R.string.edit_spending));
        mTitle = (EditText) findViewById(R.id.et_title);
        mValue = (EditText) findViewById(R.id.et_value);
        mDate = (TextView) findViewById(R.id.et_date);
        Button mButtonDateInput = (Button) findViewById(R.id.dialogDateInput);
        mTitle.setText(spending.getTitle());
        mValue.setText(Float.toString(spending.getValue()));
        mDate.setText(Commons.dateFormat.format(spending.getDate()));

        mValue.setFilters(new InputFilter[]{new DecimalDigitsFilter()});

        LinearLayout layout = (LinearLayout) findViewById(R.id.button_layout);
        Button mDeleteButton = new Button(context);
        mDeleteButton.setText(context.getString(R.string.delete_text));
        mDeleteButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(mDeleteButton);

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteSpending(spending);
                dismiss();
            }
        });

        setDate(spending.getDate());

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
            Date dateGiven = Commons.dateFormat.parse(mDate.getText().toString());
            SpendingsDataValidator.validate(mValue.getText().toString(), mTitle.getText().toString(), dateGiven);
            float value = Float.parseFloat(mValue.getText().toString());
            mListener.editSpending(spending, value, mTitle.getText().toString(), dateGiven);
        } catch (Exception e) {
            e.printStackTrace();
            EventBus.getDefault().postSticky(new ExceptionEvent(e));
        } finally {
            dismiss();
        }
    }

    private void setDate(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
    }
}
