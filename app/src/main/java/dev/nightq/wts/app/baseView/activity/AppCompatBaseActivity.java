package dev.nightq.wts.app.baseView.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.WindowManager;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.Bind;
import dev.nightq.wts.R;
import dev.nightq.wts.app.baseView.helper.ActivityBaseHelper;
import dev.nightq.wts.app.baseView.helper.ActivityDialogHelper;
import dev.nightq.wts.app.baseView.impl.DialogControllerImpl;
import dev.nightq.wts.app.baseView.widgets.actionBar.ActionbarBase;
import dev.nightq.wts.app.baseView.widgets.actionBar.ToolbarCustom;

/**
 * 这个不能直接继承
 * 可以继承 下面两个子类
 * <p>
 * 包含了  权限 对话框 actionbar 切换动画
 * ActivityBaseHelper 的调用
 * <p>
 */
public abstract class AppCompatBaseActivity
        extends RxAppCompatActivity
        implements DialogControllerImpl {

    @Nullable
    @Bind(R.id.action_bar)
    public ToolbarCustom mToolBarAsActionBar;

    /**
     * actionbar 的封装
     */
    @NonNull
    private ActionbarBase mActionbarBase;

    public enum PendingTransitionStyle {
        AnimSliding,
        AnimFade
    }

    /**
     * 默认是侧滑
     */
    public PendingTransitionStyle mPendingTransitionStyle = PendingTransitionStyle.AnimSliding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransitionStyle(
                getPendingTransitionStyle());
        super.onCreate(savedInstanceState);
        mActionbarBase = new ActionbarBase();
        ActivityBaseHelper.onCreate(this, savedInstanceState);
    }

    /**
     * 设置 toolbar 到 activity
     *
     * @warn 需要在bind view 之后调用
     */
    protected void initToolbar() {
        // 如果layout中有叫action_bar的Toolbar，就将其设为support actionbar
        if (mToolBarAsActionBar != null) {
            setSupportActionBar(mToolBarAsActionBar);
            // 设置 actionbar
            mActionbarBase.setToolbarCustom(mToolBarAsActionBar);
            mActionbarBase.setActionBar(getSupportActionBar());
            mActionbarBase.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 隐藏状态栏
     */
    public void hideToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    /**
     * 获取 actionbar 用来设置 设置 title 等
     *
     * @return
     */
    public ActionbarBase getActionbarBase() {
        return mActionbarBase;
    }


    @Override
    protected void onStart() {
        super.onStart();
        ActivityBaseHelper.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityBaseHelper.onStop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityBaseHelper.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityBaseHelper.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRequestPermissionListener = null;
        ActivityBaseHelper.onDestroy(this);
        mActionbarBase.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideDialog() {
        ActivityDialogHelper.hideDialog(this);
    }

    @Override
    public void showLoadDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ActivityDialogHelper.showLoadDialog(AppCompatBaseActivity.this);
            }
        });
    }

    @Override
    public void showWaitDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ActivityDialogHelper.showWaitDialog(AppCompatBaseActivity.this);
            }
        });
    }

    public void setActivityHeaderViewVisible(boolean visible) {
        if (mActionbarBase != null) {
            if (mActionbarBase.isShowing() != visible) {
                if (visible) {
                    mActionbarBase.show();
                } else {
                    mActionbarBase.hide();
                }
            }
        }
    }

    public boolean getActivityHeaderViewVisible() {
        return mActionbarBase != null && mActionbarBase.isShowing();
    }

    public void setActivityHeaderLabel(CharSequence str) {
        mActionbarBase.setTitle(str);
    }

    public void setActivityHeaderCanBack(boolean canBack) {
        if (mActionbarBase != null) {
            if (!canBack) {
                mActionbarBase.setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    public void setActivityHeaderLabel(CharSequence str, boolean canBack) {
        setActivityHeaderLabel(str);
        setActivityHeaderCanBack(canBack);
    }

    public void setPrimaryDarkColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(color);
        }
    }

    public static final int REQUEST_WRITE_STORAGE = 112;
    public static final int REQUEST_CAMERA = 113;
    public static final int REQUEST_READ_CONTACTS = 114;
    public static final int REQUEST_LOCATION = 115;
    public static final int REQUEST_RECORD = 116;
    private RequestPermissionListener mRequestPermissionListener;

    // 请求SD卡写权限
    public boolean requestWriteStoragePermission(RequestPermissionListener listener) {
        return requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_WRITE_STORAGE, listener);
    }

    // 请求相机权限
    public boolean requestCameraPermission(RequestPermissionListener listener) {
        return requestPermission(
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE}, REQUEST_CAMERA, listener);
    }

    // 请求联系人读权限
    public boolean requestReadContactsPermission(RequestPermissionListener listener) {
        return requestPermission(Manifest.permission.READ_CONTACTS, REQUEST_READ_CONTACTS, listener);
    }

    // 请求定位权限
    public boolean requestLocationPermission(RequestPermissionListener listener) {
        return requestPermission(
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_LOCATION, listener);
    }

    // 请求麦克风权限
    public boolean requestRecodePermission(RequestPermissionListener listener) {
        return requestPermission(Manifest.permission.RECORD_AUDIO, REQUEST_RECORD, listener);
    }


    private boolean requestPermission(String permissionName, int permissionTag, RequestPermissionListener listener) {
        return requestPermission(new String[]{permissionName}, permissionTag, listener);
    }

    private boolean requestPermission(String[] permissionNames, int permissionTag, RequestPermissionListener listener) {
        mRequestPermissionListener = listener;
        boolean hasPermission = true;

        for (String permissionName : permissionNames) {
            boolean flag = (ContextCompat.checkSelfPermission(this, permissionName)
                    == PackageManager.PERMISSION_GRANTED);
            if (!flag) {
                hasPermission = false;
                break;
            }
        }

        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, permissionNames, permissionTag);
        } else {
            if (listener != null) {
                listener.requestPermissionSuccess(permissionTag);
            }
        }

        return hasPermission;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (mRequestPermissionListener != null) {
                mRequestPermissionListener.requestPermissionSuccess(requestCode);
            }
        } else {
            if (mRequestPermissionListener != null) {
                mRequestPermissionListener.requestPermissionFail(requestCode);
            }
        }
    }

    public interface RequestPermissionListener {
        void requestPermissionSuccess(int permission);

        void requestPermissionFail(int permission);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionStyle(
                getPendingTransitionStyle());
    }


    /**
     * 可以复写跳转动画
     */
    protected PendingTransitionStyle getPendingTransitionStyle() {
        return PendingTransitionStyle.AnimSliding;
    }

    private void overridePendingTransitionStyle(PendingTransitionStyle style) {
        if (style == null) {
            mPendingTransitionStyle = PendingTransitionStyle.AnimSliding;
        } else {
            mPendingTransitionStyle = style;
        }
        updatePendingTransitionStyle();
    }

    private void updatePendingTransitionStyle() {
        switch (mPendingTransitionStyle) {
            case AnimFade:
                overridePendingTransition(
                        R.anim.fade_in,
                        R.anim.fade_out);
//                getWindow().setWindowAnimations(R.style.activityFadeAnimation);
                break;
            case AnimSliding:
            default:
//                    overridePendingTransition(
//                            R.anim.slide_in_from_right,
//                            R.anim.slide_out_to_left);
//                    getWindow().setWindowAnimations(R.style.activitySlidingAnimation);
                break;
        }
    }

}
