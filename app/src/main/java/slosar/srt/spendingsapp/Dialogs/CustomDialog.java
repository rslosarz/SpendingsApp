package slosar.srt.spendingsapp.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import slosar.srt.spendingsapp.R;

/**
 * Created by Rafal on 2016-04-24.
 */
public abstract class CustomDialog extends Dialog {

    protected Button mButtonOk;
    protected Button mButtonCancel;

    public CustomDialog(Context context, int layoutId) {
        super(context);
        setContentView(layoutId);

        mButtonOk = (Button) findViewById(R.id.bt_dialog_ok);
        mButtonCancel = (Button) findViewById(R.id.bt_dialog_cancel);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okAction();
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    protected abstract void okAction();
}
