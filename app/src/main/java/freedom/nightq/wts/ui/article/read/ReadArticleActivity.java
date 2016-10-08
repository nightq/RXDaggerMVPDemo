package freedom.nightq.wts.ui.article.read;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.avos.avoscloud.AVException;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import freedom.nightq.wts.R;
import freedom.nightq.wts.app.WTSApplication;
import freedom.nightq.wts.app.baseView.activity.MVPActivityBase;
import freedom.nightq.wts.model.article.Article;
import freedom.nightq.wts.tools.Constants;
import freedom.nightq.wts.tools.DateHelper;
import freedom.nightq.wts.tools.EventBusHelper;
import freedom.nightq.wts.tools.ToastHelper;

/**
 * A login screen that offers login via email/password.
 * session 状态变化会有全局的 event 发出
 */
public class ReadArticleActivity
        extends MVPActivityBase<ReadArticlePresenter>
        implements ReadArticleContract.View {

    @Bind(R.id.txtArticleTitle)
    TextView txtArticleTitle;
    @Bind(R.id.txtArticleDate)
    TextView txtArticleDate;
    @Bind(R.id.txtArticleContent)
    TextView txtArticleContent;
    @Bind(R.id.txtArticleAuthor)
    TextView txtArticleAuthor;

    public Article mData;

    @Override
    public void getIntentDataInActivityBase(Bundle savedInstanceState) {
        mData = EventBusHelper.getStickEventAndRemove(Article.class);
    }

    @Override
    public int onCreateBase() {
        return R.layout.article_read_layout;
    }

    @Override
    public void initActivityBaseView() {

    }

    @Override
    public void loadDataOnCreate() {
        if (mData != null) {
            mPresenter.loadDetail(this, mData.getObjectId());
        } else {
            ToastHelper.show(R.string.base_load_failed);
            finish();
        }
    }

    @Override
    public void setupComponent() {
        DaggerReadArticleComponent.builder()
                .userComponent(WTSApplication.getInstance().getUserComponent())
                .readArticleModule(new ReadArticleModule(this))
                .build().inject(this);
    }

    @Override
    public void loadSuccess(Article article) {
        txtArticleTitle.setText(article.getTitle());
        txtArticleContent.setText(article.getContent());
        txtArticleDate.setText(DateHelper.formatYMDDate(article.getWriteOn()));
        txtArticleAuthor.setText(article.getAuthorName());
    }

    @Override
    public void loadFailed(AVException e) {
        // nothing
    }

    /**
     * read
     */
    public static void startReadArticleActivity(
            Context activity,
            @NonNull Article article) {
        Intent intent = new Intent(activity, ReadArticleActivity.class);
        EventBus.getDefault().postSticky(article);
        if (activity instanceof Activity) {
            ((Activity) activity).startActivityForResult(intent, Constants.ActivityRequest.AR_ARTICLE_READ);
        } else {
            activity.startActivity(intent);
        }
    }
}

