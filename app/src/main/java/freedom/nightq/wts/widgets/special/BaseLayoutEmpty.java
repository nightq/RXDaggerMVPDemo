package freedom.nightq.wts.widgets.special;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import freedom.nightq.wts.R;

/**
 * Created by Nightq on 16/9/13.
 */

public class BaseLayoutEmpty extends LinearLayout {

    public int imageResource;
    public String tvText;

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.text)
    TextView text;

    public BaseLayoutEmpty(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.BaseLayoutEmpty);

        //获取自定义属性和默认值
        imageResource = mTypedArray.getResourceId(R.styleable.BaseLayoutEmpty_imgResource, Color.RED);
        tvText = mTypedArray.getString(R.styleable.BaseLayoutEmpty_tvText);
        mTypedArray.recycle();

        LayoutInflater.from(context).inflate(R.layout.base_layout_empty, this);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        try {
            image.setImageResource(imageResource);
        } catch (Exception e) {

        }
        text.setText(tvText);
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
        image.setImageResource(imageResource);
    }

    public void setText(String tvText) {
        this.tvText = tvText;
        text.setText(tvText);
    }
}