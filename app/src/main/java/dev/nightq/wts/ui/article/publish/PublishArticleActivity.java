package dev.nightq.wts.ui.article.publish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import com.avos.avoscloud.AVException;

import butterknife.Bind;
import dev.nightq.wts.R;
import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.app.baseView.activity.MVPActivityBase;
import dev.nightq.wts.model.article.Article;
import dev.nightq.wts.tools.Constants;
import dev.nightq.wts.tools.ToastHelper;
import dev.nightq.wts.tools.ViewHelper;

/**
 * session 状态变化会有全局的 event 发出
 */
public class PublishArticleActivity
        extends MVPActivityBase<PublishArticlePresenter>
        implements PublishArticleContract.View {

    @Bind(R.id.txtArticleTitle)
    EditText txtArticleTitle;
    @Bind(R.id.txtArticleContent)
    EditText txtArticleContent;

    @Override
    public void getIntentDataInActivityBase(Bundle savedInstanceState) {

    }

    @Override
    public int onCreateBase() {
        return R.layout.article_publish_layout;
    }

    @Override
    public void initActivityBaseView() {
        getActionbarBase().setTitle(R.string.publish_article_title);
    }

    @Override
    public void loadDataOnCreate() {

    }

    @Override
    public void setupComponent() {
        DaggerPublishArticleComponent.builder()
                .userComponent(WTSApplication.getInstance().getUserComponent())
                .publishArticleModule(new PublishArticleModule(this))
                .build().inject(this);
    }

    @Override
    public void publishSuccess(Article article) {
        ToastHelper.show(R.string.publish_article_toast_success);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void publishFailed(AVException e) {
        ToastHelper.show(R.string.publish_article_toast_failed);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base_actionbar_menu_tv, menu);
        ViewHelper.setMenuTVContent(
                menu, R.id.textView,
                R.string.publish_article_menu_publish,
                menuClickListener);
        return true;
    }

    View.OnClickListener menuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textView:
                    mPresenter.publishArticle(
                            PublishArticleActivity.this,
                            txtArticleTitle.getText().toString(),
                            txtArticleContent.getText().toString());
            }
        }
    };

    /**
     * login
     */
    public static void startPublishArticleActivity(Activity activity) {
        Intent intent = new Intent(activity, PublishArticleActivity.class);
        activity.startActivityForResult(intent, Constants.ActivityRequest.AR_ARTICLE_PUBLISH);
    }
}

