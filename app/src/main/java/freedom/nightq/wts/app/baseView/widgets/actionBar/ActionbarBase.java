package freedom.nightq.wts.app.baseView.widgets.actionBar;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Nightq on 16/4/19.
 */
public class ActionbarBase {

    protected ToolbarCustom mToolbarCustom;
    protected ActionBar mActionBar;

    public ActionbarBase() {
    }

    /**
     * 设置actionbar
     * 一般在activity 设置 actionbar 之后取到设置进来
     * @param mActionBar
     */
    public void setActionBar(ActionBar mActionBar) {
        this.mActionBar = mActionBar;
    }

    /**
     * 设置 mToolbarCustom
     * @param mToolbarCustom
     */
    public void setToolbarCustom(ToolbarCustom mToolbarCustom) {
        this.mToolbarCustom = mToolbarCustom;
    }

    /**
     * 返回是否有有效的 actionbar
     * @return
     */
    public boolean checkValidActionbar () {
        return mActionBar != null;
    }

    /**
     * onDestroy
     */
    public void onDestroy() {
        mActionBar = null;
    }

    public void setTitle (CharSequence title) {
        if (!checkValidActionbar()) return;
        mActionBar.setTitle(title);
    }

    public void setTitle (int title) {
        if (!checkValidActionbar()) return;
        mActionBar.setTitle(title);
    }

    public CharSequence getTitle () {
        if (!checkValidActionbar()) return null;
        return mActionBar.getTitle();
    }


    public void setCustomView (View arg) {
        if (!checkValidActionbar()) return;
        mActionBar.setCustomView(arg);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(false);
        Toolbar parent =(Toolbar) mActionBar.getCustomView().getParent();
        parent.setContentInsetsAbsolute(0,0);
    }

    public void setDisplayHomeAsUpEnabled (boolean arg) {
        if (!checkValidActionbar()) return;
        mActionBar.setDisplayHomeAsUpEnabled(arg);
    }

    public void setHomeAsUpIndicator (int arg) {
        if (!checkValidActionbar()) return;
        mActionBar.setHomeAsUpIndicator(arg);
    }

    public void setDisplayShowTitleEnabled (boolean arg) {
        if (!checkValidActionbar()) return;
        mActionBar.setDisplayShowTitleEnabled(arg);
    }

    public boolean isShowing () {
        if (!checkValidActionbar()) return false;
        return mActionBar.isShowing();
    }

    public void hide () {
        if (!checkValidActionbar()) return;
        mActionBar.hide();
    }

    public void show () {
        if (!checkValidActionbar()) return;
        mActionBar.show();
    }

    public void setBackgroundDrawable (Drawable arg) {
        if (!checkValidActionbar()) return;
        mActionBar.setBackgroundDrawable(arg);
    }

    /**
     * 设置 toolbar 的 visible
     * @param visible
     */
    public void setToolbarVisible (int visible) {
        if (mToolbarCustom != null) {
            mToolbarCustom.setVisibility(visible);
        }
    }

}
