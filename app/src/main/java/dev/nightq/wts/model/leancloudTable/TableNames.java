package dev.nightq.wts.model.leancloudTable;

/**
 * Created by Nightq on 16/9/13.
 * 存储在leanclud 中保存的对象名
 */

public interface TableNames {

    /**
     * 文章
     */

    interface Article {
        /**
         * 对象名
         */
        String Name = "articles";

        String Title = "title";

        String Content = "content";

        String Author = "author";

        String WriteOn = "write_on";
    }

}
