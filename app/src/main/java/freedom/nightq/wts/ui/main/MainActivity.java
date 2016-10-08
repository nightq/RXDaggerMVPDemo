package freedom.nightq.wts.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import freedom.nightq.wts.R;
import freedom.nightq.wts.app.UserSessionHelper;
import freedom.nightq.wts.app.WTSApplication;
import freedom.nightq.wts.app.baseView.activity.MVPActivityBase;
import freedom.nightq.wts.model.user.User;
import freedom.nightq.wts.repository.GlobalSPRepository;
import freedom.nightq.wts.repository.UserSPRepository;
import freedom.nightq.wts.tools.AndroidComponentHelper;
import freedom.nightq.wts.tools.ViewHelper;
import freedom.nightq.wts.ui.article.list.activity.ArticlesListActivity;
import freedom.nightq.wts.ui.article.list.fragmet.ArticlesListFragment;
import freedom.nightq.wts.ui.article.publish.PublishArticleActivity;
import freedom.nightq.wts.ui.login.LoginActivity;

public class MainActivity extends MVPActivityBase<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener,
        MainContract.View {

    /**
     * 当前是文章列表
     */
    public static final int CONTANT_ARTICLES_LIST = 1;

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

    /**
     * 当前界面内容是啊哈
     */
    public int contentIndex = CONTANT_ARTICLES_LIST;

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
        initNavView();
    }

    /**
     * 初始化
     */
    public void initNavView() {
        navView.setNavigationItemSelectedListener(this);

        navHeaderView = navView.getHeaderView(0);
        navHeaderView.findViewById(R.id.layoutNavHeader)
                .setOnClickListener(onClickHeaderListener);

        Menu menu = navView.getMenu();
        ViewHelper.setMenuContent(
                menu,
                R.id.navArticlesList,
                R.id.textView,
                R.id.imageView,
                R.string.home_nav_articles_list,
                R.drawable.ic_menu_gallery);
        ViewHelper.setMenuContent(
                menu,
                R.id.navArticlesList,
                R.id.textView,
                R.id.imageView,
                R.string.home_nav_articles_list,
                R.drawable.ic_menu_gallery);
        ViewHelper.setMenuContent(
                menu,
                R.id.navLoginout,
                R.id.textView,
                R.id.imageView,
                R.string.home_nav_logout,
                R.drawable.ic_menu_manage);
    }

    @Override
    public void loadDataOnCreate() {
        mPresenter.loadAfterCreated();
        refreshNavUserInfo();
        refreshContentUI();
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

    public View.OnClickListener onClickHeaderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mUser.checkValidSession(false)) return;
            LoginActivity.startLoginActivity(MainActivity.this);
        }
    };

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (CONTANT_ARTICLES_LIST == contentIndex) {
            return true;
        } else {
            return false;
        }
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
                    if (!mUser.checkValidSession(true)) return;
                    PublishArticleActivity.startPublishArticleActivity(MainActivity.this);
                    break;
            }
        }
    };

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navArticlesList) {
            contentIndex = CONTANT_ARTICLES_LIST;
            refreshContentUI();
        } else if (id == R.id.navSkipArticlesList) {
            ArticlesListActivity.startArticlesActivity(this);
        } else if (id == R.id.navLoginout) {
            UserSessionHelper.logout();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 切换到文章列表 主界面
     */
    public void refreshContentUI() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.baseActivityContentLayoutContainer);
        if (fragment == null) {
            if (contentIndex == CONTANT_ARTICLES_LIST) {
                fragment = ArticlesListFragment.newInstance();
            }
        } else if (fragment instanceof ArticlesListFragment) {
            return;
        }
        AndroidComponentHelper.addFragmentToActivity(getSupportFragmentManager(),
                fragment, R.id.baseActivityContentLayoutContainer);
        getActionbarBase().setTitle(R.string.articles_list_title);
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
