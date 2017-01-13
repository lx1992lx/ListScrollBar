package com.yyxk.listscrollbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by LX on 2017/1/11.
 */
/* ----------BigGod be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * 项目名称:CustomView
 * 包名:com.example.lx.customview
 * 类描述:联系人导航侧边栏
 * 创建人:LX
 * 创建时间:2017/1/11
 * 修改人:LX
 * 修改时间:
 * 修改备注
 */
public class ListScrollBar extends View {
    private List<String> mDataList;
    private float mWidth, mHeight;//控件宽高
    private OnScrollListener mOnScrollListener;
    private List<Integer> mPositions;

    private ListView mListView;

    public ListScrollBar(Context context) {
        super(context);
        init();
    }

    public ListScrollBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ListScrollBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mMainPaint;//文字主画笔

    /**
     * 初始化
     */
    private void init() {
        mMainPaint = new Paint();
        mMainPaint.setColor(Color.BLACK);
        mMainPaint.setTextSize(40);
        mMainPaint.setStyle(Paint.Style.STROKE);
        mMainPaint.setAntiAlias(true);
    }

    /**
     * 传入监听器
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.mOnScrollListener=onScrollListener;
    }

    /**
     * 传入画笔颜色
     *
     * @param color
     */
    public void setColor(int color) {
        mMainPaint.setColor(color);
    }

    /**
     * 传入文字大小
     *
     * @param size
     */
    public void setSize(int size) {
        mMainPaint.setTextSize(size);
    }

    /**
     * 传入数据
     *
     *  @param list
     */
    public void setData(List<String> list) {
        mDataList = list;
    }

    /**
     * 绑定ListView
     * @param listView  list实例
     * @param positions  list的selectionHeader的下标集合
     */
    public void setUpWithListView(ListView listView,List<Integer> positions){
        mListView=listView;
        mPositions=positions;
    }

    /**
     * 获取控件宽高
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
    private float mTextHeight;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTextHeight= mHeight / mDataList.size();//单个文字高度
        mTextHeight = mTextHeight / 3 * 2;
        for (int i = 0; i < mDataList.size(); i++) {
            float cosAngle= (float) Math.cos((2 * Math.PI / 360 * (mY-(mTextHeight * i + mHeight / 5))));
            if ((mTextHeight * i) + mHeight / 5 <= mY + mTextHeight * 2 && (mTextHeight * i + mHeight / 5) >= mY - mTextHeight * 2) {
                if(mOnScrollListener!=null){
                    mOnScrollListener.onScrolled(mY);
                }
                canvas.drawText(mDataList.get(i),mWidth/2-mTextHeight*2*cosAngle,mTextHeight * i + mHeight / 5,mMainPaint);
            } else {
                canvas.drawText(mDataList.get(i), mWidth / 2, mTextHeight * i + mHeight / 5, mMainPaint);
            }
        }
    }

    private float mY;//手指触碰的垂直坐标
    private float mStart;//手指落下时的纵坐标

    /**
     * 触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                mY = event.getY();
                invalidate();
                if(mListView!=null&&mPositions!=null&&mY>=mHeight/5-mTextHeight&&mY<=mHeight*4/5+mTextHeight*2){
                    int position= (int) ((mY-mHeight/5)/mTextHeight);
                    mListView.smoothScrollToPosition(mPositions.get(position)+1);
                    Log.i("tagggg","position ="+mPositions.get(position));
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mY = event.getY();
                mStart = event.getY();
                invalidate();
                if(mListView!=null&&mPositions!=null&&mY>=mHeight/5-mTextHeight&&mY<=mHeight*4/5+mTextHeight*2){
                    int position= (int) ((mY-mHeight/5)/mTextHeight);
                    mListView.smoothScrollToPosition(mPositions.get(position)+1);
                }
                break;
            case MotionEvent.ACTION_UP:
                mY = 0;
                mStart = 0;
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 移动距离监听器
     */
    public interface OnScrollListener{
        void onScrolled(float position);
    }
}
