package dev.nightq.wts.ui.article.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.avos.avoscloud.AVException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import dev.nightq.wts.R;
import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.app.baseView.activity.MVPActivityBase;
import dev.nightq.wts.model.article.Article;
import dev.nightq.wts.tools.Constants;
import dev.nightq.wts.tools.DeviceHelper;
import dev.nightq.wts.tools.ResourceHelper;
import dev.nightq.wts.tools.ToastHelper;
import dev.nightq.wts.widgets.recyclerView.RecycleViewDivider;
import dev.nightq.wts.widgets.recyclerView.RecyclerViewBase;
import dev.nightq.wts.widgets.special.BaseLayoutEmpty;

/**
 * A login screen that offers login via email/password.
 * session 状态变化会有全局的 event 发出
 */
public class ArticlesListActivity
        extends MVPActivityBase<ArticlesListPresenter>
        implements ArticlesListContract.View {

    /**
     *
     */
    public List<Article> mData;

    @Inject
    public ArticlesAdapter mAdapter;

    @Bind(R.id.recyclerView)
    RecyclerViewBase recyclerView;
    @Bind(R.id.baseLayoutEmpty)
    BaseLayoutEmpty baseLayoutEmpty;

    @Override
    public void getIntentDataInActivityBase(Bundle savedInstanceState) {
        mData = new ArrayList<>();
    }

    @Override
    public int onCreateBase() {
        return R.layout.articles_layout;
    }

    @Override
    public void initActivityBaseView() {

    }

    @Override
    public void loadDataOnCreate() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDivider(
                LinearLayoutManager.VERTICAL,
                DeviceHelper.dpToPx(10),
                DeviceHelper.dpToPx(10),
                ResourceHelper.getColorResource(R.color.app_main_transparent_color)));
        recyclerView.setAdapter(mAdapter);
        mPresenter.loadDetail(this);
    }

    @Override
    public void setupComponent() {
        DaggerArticlesListComponent.builder()
                .userComponent(WTSApplication.getInstance().getUserComponent())
                .articlesListModule(new ArticlesListModule(this, mData))
                .build().inject(this);
    }

    @Override
    public void loadSuccess() {
        mAdapter.notifyDataSetChanged();
        checkEmpty();
    }

    @Override
    public void loadFailed(AVException e) {
        ToastHelper.show(R.string.base_load_failed);
    }

    /**
     * 检查
     */
    public void checkEmpty() {
        if (mData.size() == 0) {
            baseLayoutEmpty.setVisibility(View.VISIBLE);
        } else {
            baseLayoutEmpty.setVisibility(View.GONE);
        }
    }

    /**
     * read
     */
    public static void startArticlesActivity(
            Activity activity) {
        Intent intent = new Intent(activity, ArticlesListActivity.class);
        activity.startActivityForResult(intent, Constants.ActivityRequest.AR_ARTICLE_LIST);
    }
}

