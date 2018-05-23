package com.gph.commonbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义标题栏
 *
 * @author gpenghui
 *         date 2018/5/23
 *         email 961057759@qq.com
 */

public class TitleBar extends FrameLayout implements View.OnClickListener {
    private RelativeLayout parent;
    private ImageView ivLeft;
    private TextView tvLeft;
    private TextView tvTitle;
    private TextView tvRight;
    private ImageView ivRight;

    private OnLeftClickListener onLeftClickListener;
    private OnRightClickListener onRightClickListener;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }

    private void initView(Context context) {
        View view = inflate(context, R.layout.bar_layout, this);
        parent = view.findViewById(R.id.parent);
        ivLeft = view.findViewById(R.id.iv_left);
        tvLeft = view.findViewById(R.id.tv_left);
        tvTitle = view.findViewById(R.id.tv_title);
        tvRight = view.findViewById(R.id.tv_right);
        ivRight = view.findViewById(R.id.iv_right);

        ivLeft.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        ivRight.setOnClickListener(this);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray _typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        int backgroundColor = _typedArray.getColor(R.styleable.TitleBar_backgroundColor, Color.CYAN);
        int textColor = _typedArray.getColor(R.styleable.TitleBar_textColor, Color.WHITE);
        float titleSize = _typedArray.getDimensionPixelSize(R.styleable.TitleBar_titleSize, -1);
        float textSize = _typedArray.getDimensionPixelSize(R.styleable.TitleBar_textSize, -1);
        int leftResourceId = _typedArray.getResourceId(R.styleable.TitleBar_leftSrc, -1);
        int rightResourceId = _typedArray.getResourceId(R.styleable.TitleBar_rightSrc, -1);
        String leftText = _typedArray.getString(R.styleable.TitleBar_leftText);
        String titleText = _typedArray.getString(R.styleable.TitleBar_titleText);
        String rightText = _typedArray.getString(R.styleable.TitleBar_rightText);
        _typedArray.recycle();

        setBgColor(backgroundColor);
        setTextColor(textColor);
        setTitleSize(titleSize);
        setTextSize(textSize);
        setLeftResourceId(leftResourceId);
        setRightResourceId(rightResourceId);
        setLeftText(leftText);
        setTitleText(titleText);
        setRightText(rightText);
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor
     */
    public TitleBar setBgColor(@ColorInt int backgroundColor) {
        parent.setBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * 设置左边和右边字体的颜色
     *
     * @param textColor
     */
    public TitleBar setTextColor(@ColorInt int textColor) {
        tvLeft.setTextColor(textColor);
        tvTitle.setTextColor(textColor);
        tvRight.setTextColor(textColor);
        return this;
    }

    /**
     * 设置标题字体尺寸
     *
     * @param titleSize
     */
    public TitleBar setTitleSize(float titleSize) {
        if (titleSize <= 0) {
            return this;
        }
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        return this;
    }

    /**
     * 设置左边和右边字体的颜色
     *
     * @param textSize
     */
    public TitleBar setTextSize(float textSize) {
        if (textSize <= 0) {
            return this;
        }
        tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        return this;
    }

    /**
     * 设置左边图标资源
     *
     * @param leftResourceId
     */
    public TitleBar setLeftResourceId(@DrawableRes int leftResourceId) {
        if (leftResourceId <= 0) {
            return this;
        }
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setImageResource(leftResourceId);
        return this;
    }

    /**
     * 设置右边图标的资源
     *
     * @param rightResourceId
     */
    public TitleBar setRightResourceId(@DrawableRes int rightResourceId) {
        if (rightResourceId <= 0) {
            return this;
        }
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(rightResourceId);
        return this;
    }

    /**
     * 设置左边文字
     *
     * @param leftText
     */
    public TitleBar setLeftText(String leftText) {
        if (leftText == null) {
            return this;
        }
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText(leftText);
        return this;
    }

    /**
     * 设置标题文字
     *
     * @param titleText
     */
    public TitleBar setTitleText(String titleText) {
        if (titleText == null) {
            return this;
        }
        tvTitle.setText(titleText);
        return this;
    }

    /**
     * 设置右边文字
     *
     * @param rightText
     */
    public TitleBar setRightText(String rightText) {
        if (rightText == null) {
            return this;
        }
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(rightText);
        return this;
    }

    /**
     * 设置左边文字或者图片按钮的点击事件
     *
     * @param onLeftClickListener
     */
    public TitleBar setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
        return this;
    }

    /**
     * 设置右边文字或者图片按钮的点击事件
     *
     * @param onRightClickListener
     */
    public TitleBar setOnRightClickListener(OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivLeft.getId()) {
            if (onLeftClickListener != null) {
                onLeftClickListener.onLeftClick(v);
            }
        } else if (v.getId() == tvLeft.getId()) {
            if (onLeftClickListener != null) {
                onLeftClickListener.onLeftClick(v);
            }
        } else if (v.getId() == tvRight.getId()) {
            if (onRightClickListener != null) {
                onRightClickListener.onRightClick(v);
            }
        } else if (v.getId() == ivRight.getId()) {
            if (onRightClickListener != null) {
                onRightClickListener.onRightClick(v);
            }
        }
    }

    public interface OnLeftClickListener {
        void onLeftClick(View view);
    }

    public interface OnRightClickListener {
        void onRightClick(View view);
    }
}
