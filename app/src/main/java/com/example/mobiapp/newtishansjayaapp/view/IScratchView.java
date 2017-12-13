package com.example.mobiapp.newtishansjayaapp.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by mobi app on 27.09.2017.
 */

public interface IScratchView {

    public boolean isScratchable();
    public void setScratchable(boolean flag);
    public void setOverlayColor(int ResId);
    public void setRevealSize(int size);
    public void setAntiAlias(boolean flag);
    public void resetView();
    public void setScratchDrawable(Drawable drawable);
    public void setScratchBitmap(Bitmap b);
    public float getScratchedRatio();

    public float getScratchedRatio(int speed);

    public void setOnScratchCallback(ScratchView.OnScratchCallback callback);

    public void setScratchAll(boolean scratchAll);

    public void setBackgroundClickable(boolean clickable);

}
