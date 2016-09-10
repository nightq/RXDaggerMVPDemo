package dev.nightq.wts.app.baseView.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import dev.nightq.wts.R;
import dev.nightq.wts.app.baseView.fragment.FragmentStartUpImpl;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by NightQ
 */

public class ProgressBaseDialog
        extends DialogFragment
        implements FragmentStartUpImpl {

    @Bind(R.id.basePgb)
    ProgressBar basePgb;
    @Bind(R.id.baseTvMsg)
    TextView baseTvMsg;
    @Bind(R.id.baseLayout)
    LinearLayout baseLayout;

    private String content;

    public static ProgressBaseDialog newInstance() {
        return newInstance(null);
    }

    public static ProgressBaseDialog newInstance(String content) {
        ProgressBaseDialog frag = new ProgressBaseDialog();
        if (!TextUtils.isEmpty(content)) {
            Bundle args = new Bundle();
            args.putString("content", content);
            frag.setArguments(args);
        }

        return frag;
    }

    public ProgressBaseDialog() {
        setStyle(STYLE_NORMAL, onCtreateStyle());
        setCancelable(true);
    }


    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        getIntentDataInActivityBase(savedInstanceState);
        View view = inflater.inflate(onCreateBase(), null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initActivityBaseView();
    }

    public int onCtreateStyle() {
        return R.style.DialogTheme_Float_Transparent;
    }

    @Override
    public void getIntentDataInActivityBase(Bundle savedInstanceState) {
        if (getArguments() != null) {
            this.content = getArguments().getString("content");
        } else {
            this.content = null;
        }
    }

    @Override
    public int onCreateBase() {
        return R.layout.base_dialog_process;
    }

    @Override
    public void initActivityBaseView() {
        if (TextUtils.isEmpty(content)) {
            baseTvMsg.setText(R.string.base_loading);
        } else {
            baseTvMsg.setText(Html.fromHtml(content));
        }
    }

    @Override
    public void loadDataOnCreate() {

    }

    @Override
    public void setupComponent() {
        // nothing
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * 简约调用
     *
     * @param manager
     */
    public void show(FragmentManager manager) {
        show(manager, toString());
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (manager == null || manager.isDestroyed()) return;
        super.show(manager, tag);
        // 如果显示太久都是能取消的
        if (!isCancelable()) {
            AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                @Override
                public void call() {
                    try {
                        setCancelable(true);
                    } catch (Exception e) {
                    }
                }
            }, 5, TimeUnit.SECONDS);
        }
    }
}
