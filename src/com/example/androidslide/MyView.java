package com.example.androidslide;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MyView extends View{

	//����wrap_contentʱ��View�Ĵ�С
	private int defaultWidth = 100;
	private int defaultHeight = 100;
	//���� ���ڻ�Բ
	private Paint p = new Paint();
	
	//View��ǰ��λ��
	private int rawX = 0;
	private int rawY = 0;
	//View֮ǰ��λ��
	private int lastX = 0;
	private int lastY = 0;
	
	public MyView(Context context){
		super(context);
	}
	public MyView(Context context, AttributeSet set) {
		super(context, set);
	}
	
	//��һ����ɫ��Բ��ΪView���ĵ㣬�뾶ΪView��ȵ�Բ
	public void onDraw(Canvas canvas){
		//Log.e("onDrawִ��","true");
		p.setColor(Color.RED);
		int x = this.getLeft() + this.getWidth()/2;
		int y = this.getTop() + this.getHeight()/2;
		canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, p);
	}
	
	
	/**
	 * ʹ��layout����ʵ�ֻ���
	 * ע��onTouchEvent����Ӧ����ΪView�ķ�Χ
	 */
	public boolean onTouchEvent(MotionEvent event){
		//Log.e("onTouchEventִ��","true");
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			//Log.e("ACTION","down");
			//��ȡ��ָ���µ����겢����
			rawX = (int)(event.getRawX());
			rawY = (int)(event.getRawY());
			lastX = rawX;
			lastY = rawY;
			break;
		case MotionEvent.ACTION_MOVE:
			//Log.e("ACTION","move");
			//��ָ�϶�ʱ����õ�ǰλ��
			rawX = (int)event.getRawX();
			rawY = (int)event.getRawY();
			//��ָ�ƶ���x���y��ƫ�����ֱ�Ϊ��ǰ����-�ϴ�����
			int offsetX = rawX - lastX;
			int offsetY = rawY - lastY;
			//ͨ��View.layout������������������λ��
			//��õ�ǰ��left�����겢������Ӧƫ����
			layout(getLeft() + offsetX,
					getTop() + offsetY,
					getRight() + offsetX,
					getBottom() + offsetY);
			//�ƶ����󣬸���lastX��lastY
			lastX = rawX;
			lastY = rawY;
			break;
		}
		return true;
	}
	
	//�򵥵���дonMeasure
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
		if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
			setMeasuredDimension(defaultWidth,defaultHeight);
		}else if(widthSpecMode == MeasureSpec.AT_MOST){
			setMeasuredDimension(defaultWidth,heightSpecSize);
		}else if(heightSpecMode == MeasureSpec.AT_MOST){
			setMeasuredDimension(widthSpecSize, defaultHeight);
		}else{
			setMeasuredDimension(widthSpecSize, heightSpecSize);
		}
	}
}
