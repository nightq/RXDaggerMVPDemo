package dev.nightq.wts.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import dev.nightq.wts.R;
import dev.nightq.wts.app.UserSessionHelper;
import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.app.baseView.activity.MVPActivityBase;
import dev.nightq.wts.model.user.User;
import dev.nightq.wts.repository.GlobalSPRepository;
import dev.nightq.wts.repository.UserSPRepository;
import dev.nightq.wts.tools.ViewHelper;
import dev.nightq.wts.ui.article.list.ArticlesListActivity;
import dev.nightq.wts.ui.article.publish.PublishArticleActivity;
import dev.nightq.wts.ui.login.LoginActivity;

public class MainActivity extends MVPActivityBase<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener,
        MainContract.View {

    @Inject
    GlobalSPRepository mGlobalSPRepository;
    //
    @Inject
    UserSPRepository mUserSPRepository;

    @Inject
    User mUser;

    View navHeaderView;
    ImageView imgAvatar;
    TextView tvName;
    TextView tvInfo;

    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    public void getIntentDataInActivityBase(Bundle savedInstanceState) {

    }

    @Override
    public int onCreateBase() {
        return R.layout.main;
    }

    @Override
    public boolean createDefaultToolBar() {
        return false;
    }

    @Override
    public boolean reinjectWhenSessionChange() {
        return true;
    }

    @Override
    public void initActivityBaseView() {

        getActionbarBase().setTitle(R.string.home_title);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout,
                mToolBarAsActionBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        navHeaderView = navView.getHeaderView(0);
        navHeaderView.findViewById(R.id.layoutNavHeader)
                .setOnClickListener(onClickListener);
    }

    @Override
    public void loadDataOnCreate() {
        mPresenter.loadAfterCreated();
        refreshNavUserInfo();
    }

    @Override
    public void setupComponent() {
        DaggerMainComponent.builder()
                .userComponent(WTSApplication.getInstance().getUserComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.this.onClick(view);
        }
    };

    @OnClick({
            R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutNavHeader:
                if (mUser.checkValidSession(false)) return;
                LoginActivity.startLoginActivity(this);
                break;
            case R.id.fab:
                if (!mUser.checkValidSession(true)) return;
                PublishArticleActivity.startPublishArticleActivity(this);
                break;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navArticlesList) {
            ArticlesListActivity.startArticlesActivity(this);
        } else if (id == R.id.navLoginout) {
            UserSessionHelper.logout();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 刷新 nav 的 user 信息
     */
    public void refreshNavUserInfo() {
        imgAvatar = (ImageView) navHeaderView.findViewById(R.id.imgAvatar);
        tvName = (TextView) navHeaderView.findViewById(R.id.tvName);
        tvInfo = (TextView) navHeaderView.findViewById(R.id.tvInfo);

        ViewHelper.showTextToView(mUser.getUsername(), tvName);
        ViewHelper.showTextToView(mUser.getId(), tvInfo);
    }

    @Override
    protected void onSessionChange() {
        super.onSessionChange();
        refreshNavUserInfo();
    }
}
