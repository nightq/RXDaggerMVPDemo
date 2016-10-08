package freedom.nightq.wts.model.article;

import com.avos.avoscloud.AVObject;

import java.util.List;

/**
 * Created by Nightq on 16/9/13.
 */

public class Articles {

    public List<AVObject> articles;

    public Articles(List<AVObject> articles) {
        this.articles = articles;
    }
}
