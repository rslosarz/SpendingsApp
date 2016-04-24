package slosar.srt.spendingsapp.Dialogs;

import android.content.Context;
import android.widget.EditText;

import slosar.srt.spendingsapp.R;

/**
 * Created by Rafal on 2016-04-24.
 */
public class AddSpendingDialog extends CustomDialog {

    private EditText mName;

    public AddSpendingDialog(Context context, CategoryDialogListener listener) {
        super(context, R.layout.dialog_add_spending, listener);
        mName = (EditText) findViewById(R.id.et_name);
    }

    @Override
    protected void okAction() {

    }
}
