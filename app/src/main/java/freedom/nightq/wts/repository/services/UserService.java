package freedom.nightq.wts.repository.services;

import freedom.nightq.wts.model.user.User;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.PUT;
import rx.Observable;

/**
 * Created by Nightq on 16/9/7.
 */

public interface UserService {

    @FormUrlEncoded
    @PUT("/login")
    Observable<User> login(
            @Field("account") String account,
            @Field("password") String password
    );

}
