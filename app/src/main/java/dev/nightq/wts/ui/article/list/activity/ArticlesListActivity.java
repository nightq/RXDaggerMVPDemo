package dev.nightq.wts.ui.article.list.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import dev.nightq.wts.R;
import dev.nightq.wts.app.baseView.activity.ActivityBase;
import dev.nightq.wts.tools.AndroidComponentHelper;
import dev.nightq.wts.tools.Constants;
import dev.nightq.wts.ui.article.list.fragmet.ArticlesListFragment;

/**
 * Created by Nightq on 16/9/13.
 */

public class ArticlesListActivity extends ActivityBase {

    @Override
    public void getIntentDataInActivityBase(Bundle savedInstanceState) {

    }

    @Override
    public int onCreateBase() {
        return R.layout.base_fragment_container;
    }

    @Override
    public void initActivityBaseView() {
    }

    @Override
    public void loadDataOnCreate() {
        AndroidComponentHelper.addFragmentToActivity(getSupportFragmentManager(),
                ArticlesListFragment.newInstance(), R.id.layoutFragmentContainer);
        getActionbarBase().setTitle(R.string.articles_list_title);
    }

    @Override
    public void setupComponent() {

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
