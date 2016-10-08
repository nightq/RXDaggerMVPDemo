package freedom.nightq.wts.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.RestAdapter;

/**
 * Created by Nightq on 16/9/7.
 */

@Singleton
public class ServerRepository {

    RestAdapter mRestAdapter;

    @Inject
    public ServerRepository(RestAdapter restAdapter) {
        this.mRestAdapter = restAdapter;
    }

}
