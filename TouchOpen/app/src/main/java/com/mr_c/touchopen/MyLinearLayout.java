package com.mr_c.touchopen;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.util.ArrayList;

/**
 * Created by ligenxun on 2015/7/24
 */
public class MyLinearLayout extends LinearLayout {


    private Context c;
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private LinearLayout layout1,layout2;

    protected void onFinishInflate() {
        super.onFinishInflate();
		layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        setLongClickable(true);
        scroller = new Scroller(getContext(),
                new AccelerateDecelerateInterpolator());
    }

    private boolean b1 = false,b2 = false;
    private int l1l,l1r,l2l,l2r;
    private int pid = 22;
    private Scroller scroller;
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                l1l = layout1.getLeft();
                l1r = layout1.getRight();
                l2l = layout2.getLeft();
                l2r = layout2.getRight();
                layout1.getChildAt(0).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bt1 = true;
                        if (b2){

                            scroller.startScroll(l1l + pid, layout1.getTop(), l2r - pid, layout1.getTop(),700);
                            layout2.layout(l1l + pid + 1, layout2.getTop(), layout2.getRight(), layout2.getBottom());
                        }
                        else {
                            if (!b1) {
                                //TODo layout1 c

//                            scroller.startScroll(layout1.getLeft(), layout1.getTop(), layout1.getRight(), layout1.getTop(),400);
//                            scroller.startScroll(l1r, layout1.getTop(), l2r -pid, layout1.getTop(),400);
                                scroller.startScroll(l1r, layout1.getTop(), l2r - l1r - pid, layout1.getTop(), 400);
                                layout2.layout(layout1.getRight() + 1, layout2.getTop(), layout2.getRight(), layout2.getBottom());
                                Log.d("left", "false");
                                Log.d("layout2.getRight()", Integer.toString(layout2.getRight()));
                            } else {

                                //TODo layout1 d
//                            scroller.startScroll(l2r -pid, layout1.getTop(), l1r, layout1.getTop(), 400);
                                scroller.startScroll(l1r, layout1.getTop(), l2r - l1r - pid, layout1.getTop(), 400);
//                            scroller.startScroll(l2r - l1r -pid, layout1.getTop(),l1r , layout1.getTop(),400);
                                layout2.layout(layout1.getRight() - 1, layout2.getTop(), layout2.getRight(), layout2.getBottom());
                                Log.d("left", "true");
                                Log.d("l1r", Integer.toString(l1r));
                            }
                        }
                    }
                });
                layout2.getChildAt(0).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bt2 = true;

                        if(b1){

                            scroller.startScroll(l1l + pid ,layout1.getTop(), l2r - pid,layout1.getTop(),700);
                            layout1.layout(layout1.getLeft(), layout1.getTop(), l2r - pid - 1, layout1.getBottom());

                        }else {

                            if (!b2) {
                                //TODo layout2 c
                                scroller.startScroll(l1l + pid, layout2.getTop(), l2l, layout2.getTop(), 400);
                                layout1.layout(layout1.getLeft(), layout1.getTop(), l1r - 1, layout1.getBottom());
                                Log.d("right", "false");
                                Log.d("layout1.getLeft()", Integer.toString(layout1.getLeft()));
                            } else {
                                //TODo layout2 d
                                scroller.startScroll(l1l + pid, layout2.getTop(), l2l, layout2.getTop(), 400);
                                layout1.layout(layout1.getLeft(), layout1.getTop(), layout2.getLeft() + 1, layout1.getBottom());
                                Log.d("right", "true");
                                Log.d("l2r", Integer.toString(l2r));
                            }
                        }
                    }
                });
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
    private boolean bt1 = false,bt2=false;
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            int x = scroller.getCurrX();
            int y = scroller.getCurrY();
            System.out.println("x=" + x);
            System.out.println("y=" + y);
            if (bt1) {
                if(b2){
                    if (x<l2r-pid){

                        LayoutParams l = new LayoutParams( x - (l1l),
                                layout1.getBottom() - layout1.getTop());
                        layout1.setLayoutParams(l);

                        LayoutParams l1 = new LayoutParams(l2r - layout1.getLayoutParams().width,layout1.getHeight());
                        layout2.setLayoutParams(l1);
                    }
                    else {
                        LayoutParams l = new LayoutParams(l2r - pid - (l1l),
                                layout1.getBottom() - layout1.getTop());
                        layout1.setLayoutParams(l);
                        LayoutParams l1 = new LayoutParams(l2r - layout1.getLayoutParams().width,layout1.getHeight());
                        layout2.setLayoutParams(l1);
                    }

                    if (scroller.isFinished()) {
                        b2 = false;
                        b1 = true;
                        bt1 = false;
                        Log.d("finish", "DDDDDDDD");
                        return;
                    }
                }
                else {
                    if (!b1) {
                        //ToDO l1 c
                        if (x < l2r - pid) {
                            LayoutParams l = new LayoutParams(x - (l1l),
                                    layout1.getBottom() - layout1.getTop());
                            layout1.setLayoutParams(l);

                            LayoutParams l1 = new LayoutParams(l2r - layout1.getLayoutParams().width, layout1.getHeight());
                            layout2.setLayoutParams(l1);

//                        layout1.layout(l1l,layout1.getTop(),l1r+x,layout1.getBottom());
                        } else {
                            LayoutParams l = new LayoutParams(l2r - pid - (l1l),
                                    layout1.getBottom() - layout1.getTop());
                            layout1.setLayoutParams(l);
                            LayoutParams l1 = new LayoutParams(l2r - layout1.getLayoutParams().width, layout1.getHeight());
                            layout2.setLayoutParams(l1);
                            Log.d("11", "c");
//                        layout1.layout(l1l,layout1.getTop(),l2r-pid,layout1.getBottom());
                        }
                        if (scroller.isFinished()) {
                            b1 = true;
                            bt1 = false;
                            Log.d("finish", "DDDDDDDD");
                            return;
                        }
                    }
                    if (b1) {
                        //ToDO l1 d
                        if (l2r - x - pid > 0) {
                            LayoutParams l = new LayoutParams(l1r + l2r - x - pid,
                                    layout1.getBottom() - layout1.getTop());
                            layout1.setLayoutParams(l);
                            LayoutParams l1 = new LayoutParams(l2r - layout1.getLayoutParams().width, layout1.getHeight());
                            layout2.setLayoutParams(l1);
//                        layout1.layout(l1l,layout1.getTop(),l1r + l2r - x - pid,layout1.getBottom());
                        } else {
//                        LayoutParams l = new LayoutParams(l1r - (l1l),
//                                layout1.getBottom() - layout1.getTop());
                            LayoutParams l = new LayoutParams(l1r - l1l,
                                    layout1.getBottom() - layout1.getTop());
                            layout1.setLayoutParams(l);

                            LayoutParams l1 = new LayoutParams(l2r - layout1.getLayoutParams().width, layout1.getHeight());
                            layout2.setLayoutParams(l1);
//                        LayoutParams l = new LayoutParams(-1,
//                                -1);
//                        layout1.setLayoutParams(l);
                            Log.d("11", "d");

//                        layout1.layout(l1l,layout1.getTop(),l1r,layout1.getBottom());
                        }
                        if (scroller.isFinished()) {
                            b1 = false;
                            bt1 = false;
                            Log.d("finish", "WWWWWW");
                            return;
                        }
                    }
                }
            }
            if (bt2) {
                if(b1){

                    if (x<l2r-pid){

                        LayoutParams l = new LayoutParams(l2r - pid - x ,
                                layout1.getBottom() - layout1.getTop());
                        layout1.setLayoutParams(l);

                        LayoutParams l1 = new LayoutParams(l2r - layout1.getLayoutParams().width,layout1.getHeight());
                        layout2.setLayoutParams(l1);
                    }
                    else {

                        LayoutParams l = new LayoutParams(l1l + pid, layout1.getHeight());
                        layout1.setLayoutParams(l);

                        LayoutParams l1 = new LayoutParams(l2r - layout1.getLayoutParams().width, layout1.getHeight());
                        layout2.setLayoutParams(l1);
                    }

                        if(scroller.isFinished()){
                        b1 = false;
                        b2 = true;
                        bt2 = false;
                        return;

                    }
                }
                else {
                    if (!b2) {
                        //ToDO l2 c
                        if (x < l2l) {
                            LayoutParams l = new LayoutParams(l2r - l2l + x,
                                    layout2.getBottom() - layout2.getTop());
                            layout2.setLayoutParams(l);

                            LayoutParams l1 = new LayoutParams(l2r - layout2.getLayoutParams().width, layout2.getHeight());
                            layout1.setLayoutParams(l1);

//                    layout2.layout(l2l-x,layout2.getTop(),l2r,layout2.getBottom());
                        } else {
                            LayoutParams l = new LayoutParams(l2r - l1l - pid, layout2.getHeight());
                            layout2.setLayoutParams(l);
//                    layout2.layout(l1l+pid,layout2.getTop(),l2r,layout2.getBottom());


                            LayoutParams l1 = new LayoutParams(l2r - layout2.getLayoutParams().width, layout2.getHeight());
                            layout1.setLayoutParams(l1);

                            Log.d("12", "c");
                        }
                        if (scroller.isFinished()) {
                            b2 = true;
                            bt2 = false;
                            return;

                        }
                    }
                    if (b2) {
                        //ToDO l2 d
                        if (x <= l2l) {
                            LayoutParams l = new LayoutParams(l2r - l2l + (l1r - l1l - x), layout2.getHeight());
                            layout2.setLayoutParams(l);

                            LayoutParams l1 = new LayoutParams(l2r - layout2.getLayoutParams().width, layout2.getHeight());
                            layout1.setLayoutParams(l1);
//                    layout2.layout(l1l+pid+x,layout2.getTop(),l2r,layout2.getBottom());
                        } else {
//                    LayoutParams l = new LayoutParams(l2r-le,layout2.getHeight());
                            LayoutParams l = new LayoutParams(l2r - l2l,
                                    layout2.getBottom() - layout2.getTop());
                            layout2.setLayoutParams(l);

                            LayoutParams l1 = new LayoutParams(l2r - layout2.getLayoutParams().width, layout2.getHeight());
                            layout1.setLayoutParams(l1);

                            Log.d("12", "d");

//                    layout2.layout(l2l,layout2.getTop(),l2r,layout2.getBottom());
                        }
                        if (scroller.isFinished()) {
                            b2 = false;
                            bt2 = false;
                            return;
                        }
                    }
                }
            }
            invalidate();
        }
    }
}
