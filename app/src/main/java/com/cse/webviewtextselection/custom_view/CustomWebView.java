package com.cse.webviewtextselection.custom_view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.cse.webviewtextselection.R;
import com.cse.webviewtextselection.webviewmaker.TextSelectionSupport;

/**
 * Created by everestek22 on 22-09-2017.
 */

public class CustomWebView extends WebView {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;

    private TextSelectionSupport mTextSelectionSupport;

    private PopupWindow mPopupWindow;

    private int currentTop;

    public CustomWebView(Context context) {
        super(context);
        mContext = context;
        initSetUp();
        preparePopupWindow();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initSetUp();
        preparePopupWindow();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initSetUp();
        preparePopupWindow();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initSetUp();
        preparePopupWindow();
    }

    private void initSetUp() {

        mTextSelectionSupport = TextSelectionSupport.support((AppCompatActivity) mContext, this);
        mTextSelectionSupport.setSelectionListener(new TextSelectionSupport.SelectionListener() {
            @Override
            public void startSelection() {

            }

            @Override
            public void selectionChanged(String text, Rect rect) {
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                showPopAtLocation(mPopupWindow, rect.left, rect.top);
            }

            @Override
            public void endSelection() {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
    }

    private void preparePopupWindow() {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customPopupView =  layoutInflater.inflate(R.layout.custom_popup_layout, null);
        mPopupWindow = new PopupWindow(customPopupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);

    }

    private void showPopAtLocation(PopupWindow mPopupWindow, int x, int y) {

        if (mPopupWindow != null) {

            if (currentTop != 0 || currentTop > ((AppCompatActivity)mContext).getWindow().getDecorView().getHeight()) {

                    if (y > currentTop) {

                        y -= currentTop;

                    }

            }

            Log.d("Current Top : ", String.valueOf(currentTop));
            Log.d("Y : ", String.valueOf(y));

            //mPopupWindow.showAtLocation(((AppCompatActivity)mContext).findViewById(R.id.parentRelativeLayout), Gravity.NO_GRAVITY, x, y);
            mPopupWindow.showAtLocation(((AppCompatActivity)mContext).getWindow().getDecorView(), Gravity.NO_GRAVITY, x, y);


        }

    }

    @Override
    protected void onScrollChanged(int newLeft, int newTop, int oldLeft, int oldTop) {

        currentTop = newTop;


        super.onScrollChanged(newLeft, newTop, oldLeft, oldTop);
    }
}
