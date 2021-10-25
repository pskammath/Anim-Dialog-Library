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
