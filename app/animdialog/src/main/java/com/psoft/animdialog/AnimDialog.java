package com.psoft.animdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.psoft.*;

public class AnimDialog {

    private Dialog mDialog;
    private boolean visible;
    int animation;
    DialogTheme theme;

    Context c;

    public AnimDialog() {
    }

    public AnimDialog(Context c, DialogTheme theme) {
        this.c = c;
        this.theme = theme;
    }

    public void show() {

        visible = true;
        mDialog.show();
    }


    public void makeDialog(
            int drawableRes, String title, String message, String ok, String cancel, boolean cancelable, boolean cancelVisible, ButtonClickListener lstnr, boolean animate, int duration) {
        setDefaults(c, drawableRes, title, message, ok, cancel, cancelable, cancelVisible, lstnr, animate, duration);
        visible = true;
        mDialog.show();
    }

    public void dismiss() {
        if (mDialog != null) {
            visible = false;
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public boolean isShowing() {
        return visible;
    }


    public void setDefaults(
            Context c, int drawableRes, String title, String message, String ok, String cancel, boolean cancelable, boolean cancelVisible, ButtonClickListener lstnr, boolean animate, int duration) {
        mDialog = new Dialog(c);
        // no tile for the dialog
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        mDialog.getWindow().setBackgroundDrawableResource(R.drawable.progressdialogbg);


        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(cancelable);

        mDialog.setContentView(R.layout.custom_dialog_layout);

        ImageView imageView = mDialog.findViewById(R.id.icon);
        TextView titlet = mDialog.findViewById(R.id.cus_dlg_title);
        TextView content = mDialog.findViewById(R.id.cus_dlg_content);
        TextView okt = mDialog.findViewById(R.id.cus_dlg_ok);
        TextView cancelt = mDialog.findViewById(R.id.cus_dlg_cancel);


        if (theme != null) {
            titlet.setTextColor(theme.titleFg);
            content.setTextColor(theme.messageFg);
            okt.setTextColor(theme.button1Fg);
            okt.setBackgroundColor(theme.button1Bg);
            cancelt.setTextColor(theme.button2Fg);
            cancelt.setBackgroundColor(theme.button2Bg);
        }


        titlet.setText(title);
        content.setText(message);
        okt.setText(ok.equals("") ? "OK" : ok);
        cancelt.setText(cancel.equals("") ? "CANCEL" : cancel);
        if (!cancelVisible)
            cancelt.setVisibility(View.GONE);


        if (animate) {

            Animation animation = AnimationUtils.loadAnimation(c, R.anim.side_slide);
            Animation animation2 = AnimationUtils.loadAnimation(c, R.anim.side_slide_r);
            animation.setDuration(duration);
            animation2.setDuration(duration);
            cancelt.startAnimation(animation);
            okt.startAnimation(animation2);

        }

        imageView.setBackgroundResource(drawableRes);

        okt.setOnClickListener(v -> {
            if (mDialog != null)
                mDialog.dismiss();
            lstnr.onOk();
        });
        cancelt.setOnClickListener(v -> {
            if (mDialog != null)
                mDialog.dismiss();
            lstnr.onCancel();




        });

    }

    public interface ButtonClickListener {
        void onOk();

        void onCancel();
    }

}
