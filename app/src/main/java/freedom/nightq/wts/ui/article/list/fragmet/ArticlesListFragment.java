package freedom.nightq.wts.ui.article.list.fragmet;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.avos.avoscloud.AVException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import freedom.nightq.wts.R;
import freedom.nightq.wts.app.WTSApplication;
import freedom.nightq.wts.app.baseView.fragment.FragmentBase;
import freedom.nightq.wts.model.article.Article;
import freedom.nightq.wts.tools.DeviceHelper;
import freedom.nightq.wts.tools.ResourceHelper;
import freedom.nightq.wts.tools.ToastHelper;
import freedom.nightq.wts.ui.article.list.fragmet.recyclerView.ArticlesAdapter;
import freedom.nightq.wts.widgets.recyclerView.RecycleViewDivider;
import freedom.nightq.wts.widgets.recyclerView.RecyclerViewBase;
import freedom.nightq.wts.widgets.special.BaseLayoutEmpty;

/**
 * A login screen that offers login via email/password.
 * session 状态变化会有全局的 event 发出
 */
public class ArticlesListFragment
        extends FragmentBase
        implements ArticlesListContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    public ArticlesListPresenter mPresenter;

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
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    public static ArticlesListFragment newInstance() {
        return new ArticlesListFragment();
    }

    public ArticlesListFragment() {
    }

    @Override
    public void getIntentDataInActivityBase(Bundle savedInstanceState) {
        mData = new ArrayList<>();
    }

    @Override
    public int onCreateBase() {
        return R.layout.fragment_articles_list;
    }

    @Override
    public void initActivityBaseView() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void loadDataOnCreate() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RecycleViewDivider(
                LinearLayoutManager.VERTICAL,
                DeviceHelper.dpToPx(10),
                DeviceHelper.dpToPx(10),
                ResourceHelper.getColorResource(R.color.app_main_transparent_color)));
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setRefreshing(true);
        mPresenter.loadDetail();
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
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadFailed(AVException e) {
        swipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        mPresenter.loadDetail();
    }
}

