# Anim-Dialog-Library
A library for showing animated popup dialog
You can customize its color, your own button animation.
This is a simple popup dialog


# Anim Dialog Class

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
            Animation animation2 = AnimationUtils.loadAnimation(c, R.anim.side_slide);
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

# DialogTheme Class

    package com.psoft.animdialog;



    public class DialogTheme {
    int button1Bg;
    int button2Bg;
    int button1Fg;
    int button2Fg;
    int titleFg;
    int messageFg;


    public DialogTheme(int button1Bg, int button2Bg, int button1Fg, int button2Fg, int titleFg, int messageFg) {
        this.button1Bg = button1Bg;
        this.button2Bg = button2Bg;
        this.button1Fg = button1Fg;
        this.button2Fg = button2Fg;
        this.titleFg = titleFg;
        this.messageFg = messageFg;
    }

    public int getButton1Bg() {
        return button1Bg;
    }

    public void setButton1Bg(int button1Bg) {
        this.button1Bg = button1Bg;
    }

    public int getButton2Bg() {
        return button2Bg;
    }

    public void setButton2Bg(int button2Bg) {
        this.button2Bg = button2Bg;
    }

    public int getButton1Fg() {
        return button1Fg;
    }

    public void setButton1Fg(int button1Fg) {
        this.button1Fg = button1Fg;
    }

    public int getButton2Fg() {
        return button2Fg;
    }

    public void setButton2Fg(int button2Fg) {
        this.button2Fg = button2Fg;
    }

    public int getTitleFg() {
        return titleFg;
    }

    public void setTitleFg(int titleFg) {
        this.titleFg = titleFg;
    }

    public int getMessageFg() {
        return messageFg;
    }

    public void setMessageFg(int messageFg) {
        this.messageFg = messageFg;
    }
}


# Using Custom themed Dialog

    new AnimDialog(context, new DialogTheme(color1,color2,color3,color4,color5,color6)).makeDialog(
                    R.drawable.ic_info, "Popup Title", "Popup Message",
                    "Button 1 caption", "Button 2 caption", false, true, new ButtonClickListener() {
                        @Override
                        public void onOk() {
                            //on button 1 click
                        }

                        @Override
                        public void onCancel() {
                            
                            //on button2 click

                        }
                    }, true, 5000
            );
# Using default dialog

     new AnimDialog(context, null).makeDialog(
                    R.drawable.ic_info, "Popup Title", "Popup Message",
                    "Button 1 caption", "Button 2 caption", false, true, new ButtonClickListener() {
                        @Override
                        public void onOk() {
                            //on button 1 click
                        }

                        @Override
                        public void onCancel() {
                            
                            //on button2 click

                        }
                    }, true, 5000
            );


