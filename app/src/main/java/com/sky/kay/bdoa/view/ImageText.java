
package com.sky.kay.bdoa.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.kay.bdoa.R;
import com.sky.kay.bdoa.model.Constant;
  

  
  
public class ImageText extends LinearLayout {
    private Context mContext = null;
    private ImageView mImageView = null;
    private TextView mTextView = null;
    private final static int DEFAULT_IMAGE_WIDTH = 64;
    private final static int DEFAULT_IMAGE_HEIGHT = 64;
    private int CHECKED_COLOR = Color.rgb(29, 118, 199); //选中蓝色  
    private int UNCHECKED_COLOR = Color.GRAY;   //自然灰色  

    public ImageText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub  
        mContext = context;
    }

    public ImageText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub  
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View parentView = inflater.inflate(R.layout.image_text_layout, this, true);
        mImageView = (ImageView) findViewById(R.id.image_iamge_text);
        mTextView = (TextView) findViewById(R.id.text_iamge_text);
    }

    public void setImage(int id) {
        if (mImageView != null) {
            mImageView.setImageResource(id);
            setImageSize(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
        }
    }

    public void setText(String s) {
        if (mTextView != null) {
            mTextView.setText(s);
            mTextView.setTextColor(UNCHECKED_COLOR);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub  
        return true;
    }

    private void setImageSize(int w, int h) {
        if (mImageView != null) {
            ViewGroup.LayoutParams params = mImageView.getLayoutParams();
            params.width = w;
            params.height = h;
            mImageView.setLayoutParams(params);
        }
    }

    public void setChecked(int itemID) {
        if (mTextView != null) {
            mTextView.setTextColor(CHECKED_COLOR);
        }
        int checkDrawableId = -1;
        switch (itemID) {
            case Constant.BTN_FLAG_MESSAGE:
                checkDrawableId = R.mipmap.message;
                break;
            case Constant.BTN_FLAG_PERSON:
                checkDrawableId = R.mipmap.person_blue;
                break;
            case Constant.BTN_FLAG_HOME:
                checkDrawableId = R.mipmap.home_blue;
                break;
            case Constant.BTN_FLAG_COMMANY:
                checkDrawableId = R.mipmap.commany_blue;
                break;
            case Constant.BTN_FLAG_MORE:
                checkDrawableId = R.mipmap.more_blue;
                break;
            default:
                break;
        }
        if (mImageView != null) {
            mImageView.setImageResource(checkDrawableId);
        }
    }
}
  