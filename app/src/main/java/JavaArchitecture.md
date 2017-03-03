App Java 结构 
======
结构概述
------
使用 Dagger 构建的按组件和功能划分的 MVP 结构。

结构详情
------
包目录下面按组件分为：

1.  activities: 所有的 Activities ，子目录使用功能模块划分。
    如：main：主页 Activity
    每个具体的 Activity 的子目录 一定包含(以下的\<Main>表示功能名，因为是使用功能Activity划分的): 
    1.  \<Main>Activity: Activity 的类，可以继承 MVPActivityBase（已经使用 Dagger 封装的 MVP Activity）
    2.  \<Main>Component: Dagger 的 Component；用于将 Module 中的数据注入到需要的地方；具体功能请查看 Dagger
    3.  \<Main>Module: Dagger 的 Module；包含了数据的本地，数据库，网络的获取存储分发；具体功能请查看 Dagger
    4.  \<Main>Contract: 用于 Presenter 和 View 的关联
    5.  \<Main>Presenter: 用户数据的读取处理，调用 Activity 显示数据。关联 View 和 Module
    6.  viewPager: Activity 包含的 Viewpager，里面包含了 ViewPagerAdapter 和 ChildFragment 目录（结构和 下面的 Fragment 一样）;
    7.  recyclerView: Activity 包含的 RecyclerView，里面包含了 RecyclerViewAdapter 和 ViewHolder;
    8.  fragments: 可以包含一些子 Fragments。
        如：home: 主页列表的 Fragment:
        1.  \<Home>Fragment: Fragment 的类，可以继承 FragmentBase (已经封装了具有一定的基础功能，可以扩展)
        2.  \<Home>Component: 同 Activity
        3.  \<Home>Module: 同 Activity
        4.  \<Home>Contract: 同 Activity
        5.  \<Home>Presenter: 同 Activity
        6.  viewPager: 同 Activity
        7.  recyclerView: 同 Activity
        8.  fragments: 根据需求可以嵌套多层。    
2.  app: 整个 App 的基础配置等: 
    1.  baseView: Activity, Fragment, Dialog, ActionBar, MVP相关结构 等的封装的基类
    2.  component: App 基础的 Component
    3.  module: App 基础的 Module 
    4.  qualifier: 基础需要使用的 Qualifier: GlobalQualifier, UserQualifier, Local, Remote
    5.  scope: 基础需要使用的 
        Scope: ActivityScope, ApplicationScope, FragmentChildScope, FragmentScope, UserScope
    6.  BaseApplication: 基础 App 可自行扩展
    7.  <AppName>Application: App 可自定义
3.  bcReceivers: BroadCastReceivers
4.  dialogs: 所有的 Dialogs ，可以继承于 DialogFragmentBase；结构和上面的 Fragment 一样。
5.  extraLibs: 一些耦合比较强的包。更独立的包还可以放在其他 Android Module，甚至上传到 JCenter 和 Maven Central
6.  models: 数据模型，包含:（大概介绍：http://blog.csdn.net/alabadazi/article/details/50075759 更详细的请自行 Google）
    1.  bean: 基础的数据结构，可保存到数据库，或从服务器获取。
    2.  display: 用于显示，处理，传输等，根据 bean 封装而来。
    3.  eventBus: 第三方库 EventBus 的事件类
    4.  others: 一些其他的无法很好归类的数据
7.  notifications: 需要自定义通知的地方
8.  repository: 数据处理仓库，包括本地仓库和网络仓库
9.  services: 所有的 Services ，包括 远程服务。
10. tools: 工具包
11. widgets: 包含所有的 Widgets
