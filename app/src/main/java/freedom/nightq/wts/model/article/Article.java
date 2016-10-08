package freedom.nightq.wts.model.article;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

import java.util.Date;

import freedom.nightq.wts.app.UserSessionHelper;
import freedom.nightq.wts.model.leancloudTable.TableNames;
import freedom.nightq.wts.model.user.User;
import freedom.nightq.wts.tools.ModelHelper;

/**
 * Created by Nightq on 16/9/13.
 */

public class Article extends AVObject {

    public AVObject avObject;

    public Article(AVObject avObject) {
        this.avObject = avObject;
    }

    public String getTitle() {
        return ModelHelper.getDataFromAVO(avObject, TableNames.Article.Title, null);
    }

    public String getContent() {
        return ModelHelper.getDataFromAVO(avObject, TableNames.Article.Content, null);
    }

    public User getAuthor() {
        AVUser avUser = ModelHelper.getDataFromAVO(avObject, TableNames.Article.Author, null);
        return UserSessionHelper.getUserFromAVUser(avUser);
    }

    public String getAuthorName() {
        User user = getAuthor();
        if (user == null) {
            return User.DEFAULT_USER_NAME;
        } else {
            return user.getUsername();
        }
    }


    public Date getWriteOn() {
        return ModelHelper.getDataFromAVO(avObject, TableNames.Article.WriteOn, null);
    }
}
