package xyuxiao.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

public class MyHorizontalScrollView extends HorizontalScrollView {
    private View view;
    public void setView(View view){
        this.view = view;
    }

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return false;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(this.view == null)
            return;
        if(l <= 1 && this.view.getVisibility() == View.VISIBLE)
            this.view.setVisibility(View.GONE);
        else if(l > 1 && this.view.getVisibility() == View.GONE)
            this.view.setVisibility(View.VISIBLE);
    }
}