package dev.nightq.wts.app.baseView.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import dev.nightq.wts.R;
import dev.nightq.wts.app.baseView.impl.DialogControllerImpl;
import dev.nightq.wts.app.baseView.widgets.actionBar.ActionbarBase;
import dev.nightq.wts.app.baseView.widgets.actionBar.ToolbarCustom;

/**
 * Created by H3c on 12/19/14.
 */
public abstract class FragmentBase
        extends RxFragment
        implements FragmentStartUpImpl,
        DialogControllerImpl {

    @Nullable
    @Bind(R.id.action_bar)
    ToolbarCustom mToolBarAsActionBar;

    /**
     * actionbar 的封装
     */
    private ActionbarBase mActionbarBase;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionbarBase = new ActionbarBase();
    }

    @Override
    public void onDestroy() {
        mActionbarBase.onDestroy();
        super.onDestroy();
    }


    public int currentLayoutId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getIntentDataInActivityBase(savedInstanceState);
        currentLayoutId = onCreateBase();
        View view;
        if (currentLayoutId > 0) {
            view = inflater.inflate(currentLayoutId, container, false);
        } else {
            view = super.onCreateView(inflater, container, savedInstanceState);
        }
        ButterKnife.bind(this, view);
        // 如果layout中有叫action_bar的Toolbar，就将其设为support actionbar
        initToolbar();
        return view;
    }

    /**
     * 设置 toolbar 到 activity
     * 需要在bind view 之后调用
     */
    protected void initToolbar() {
        // 如果layout中有叫action_bar的Toolbar，就将其设为support actionbar
        if (mToolBarAsActionBar != null) {
            if (getActivity() instanceof AppCompatActivity) {
                ((AppCompatActivity) getActivity()).setSupportActionBar(mToolBarAsActionBar);
                // 设置 actionbar
                mActionbarBase.setToolbarCustom(mToolBarAsActionBar);
                mActionbarBase.setActionBar(((AppCompatActivity) getActivity()).getSupportActionBar());
                mActionbarBase.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    /**
     * 获取 actionbar 用来设置 设置 title 等
     *
     * @return
     */
    public ActionbarBase getActionbarBase() {
        if (mActionbarBase == null) {
            mActionbarBase = new ActionbarBase();
        }
        return mActionbarBase;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initActivityBaseView();
        setupComponent();
        loadDataOnCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void hideDialog() {
        if (getActivity() != null
                && getActivity() instanceof DialogControllerImpl) {
            ((DialogControllerImpl) getActivity()).hideDialog();
        }
    }

    @Override
    public void showLoadDialog() {
        if (getActivity() != null
                && getActivity() instanceof DialogControllerImpl) {
            ((DialogControllerImpl) getActivity()).showLoadDialog();
        }
    }

    @Override
    public void showWaitDialog() {
        if (getActivity() != null
                && getActivity() instanceof DialogControllerImpl) {
            ((DialogControllerImpl) getActivity()).showWaitDialog();
        }
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//    }

}
