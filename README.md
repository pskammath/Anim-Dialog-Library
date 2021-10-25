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

            Animation animation = AnimationUtils.loadAnimation(c, R.anim.slide_left);
            Animation animation2 = AnimationUtils.loadAnimation(c, R.anim.side_slide_right);
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


# custom_dialog_layout.xml
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="400dp"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <ImageView
            android:id="@+id/icon"
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:layout_margin="10dp" />

        <TextView
            android:id="@+id/cus_dlg_title"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center_vertical"
            android:text="TextView"
            android:textColor="#2E3F65"
            android:textSize="@dimen/text_large" />
    </LinearLayout>

    <TextView
        android:id="@+id/cus_dlg_content"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:layout_margin="10dp"
        android:layout_weight="1"
        android:gravity="center_vertical"
        android:padding="5dp"
        android:text="TextView"
        android:textColor="#000000"
        android:textSize="@dimen/text_small" />

    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        app:cardCornerRadius="20dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginTop="10dp"
            android:background="@drawable/bottom_round_square"
            android:orientation="horizontal">

            <TextView
                android:id="@+id/cus_dlg_cancel"
                android:layout_width="wrap_content"
                android:layout_height="50dp"
                android:layout_weight="1"
                android:background="@color/design_default_color_on_secondary"
                android:foreground="?attr/selectableItemBackgroundBorderless"
                android:gravity="center"
                android:text="Cancel"
                android:textColor="#FFFFFF" />

            <TextView
                android:layout_width="2dp"
                android:layout_height="match_parent"
                android:background="#4C4A4A"
                android:gravity="center" />

            <TextView
                android:id="@+id/cus_dlg_ok"
                android:layout_width="wrap_content"
                android:layout_height="50dp"
                android:layout_weight="1"
                android:background="@color/design_default_color_on_secondary"
                android:foreground="?android:attr/selectableItemBackground"
                android:gravity="center"
                android:text="Ok"
                android:textColor="#FFFFFF" />
        </LinearLayout>
    </androidx.cardview.widget.CardView>

    </LinearLayout>
    
# progressdialogbg.xml

    <?xml version="1.0" encoding="utf-8"?>
    <shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="#FFFFFF" />
    <padding
        android:bottom="7dp"
        android:left="7dp"
        android:right="7dp"
        android:top="7dp" />
    <stroke
        android:width="0.5dp"
        android:color="#FFFFFF" />
    <!--android:dashGap="1dp"
    android:dashWidth="4dp"-->
    <corners android:radius="15dp" />

    </shape>
    
# slide_left.xml    (animation)

    <?xml version="1.0" encoding="utf-8"?>
    <set
    xmlns:android="http://schemas.android.com/apk/res/android">

    <!--THIS CODE IS FOR SIDE ANIMATION-->
    <translate
        android:duration="1500"
        android:fromXDelta="-50%"
        android:fromYDelta="0%" />

    <alpha
        android:duration="500"
        android:fromAlpha="0.1"
        android:toAlpha="1.0" />
    </set>
    
  # slide_right.xml
  
     <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android"
    android:shareInterpolator="false" >
    <translate android:duration="500" android:fromXDelta="100%" android:toXDelta="0%" />
    <alpha  android:fromAlpha="0.0" android:toAlpha="1.0" />
    </set>

