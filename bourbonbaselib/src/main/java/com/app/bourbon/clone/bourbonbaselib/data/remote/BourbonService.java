package com.app.bourbon.clone.bourbonbaselib.data.remote;

import com.app.bourbon.clone.bourbonbaselib.data.model.Comment;
import com.app.bourbon.clone.bourbonbaselib.data.model.Shot;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin on 06/04/18.
 */

public interface BourbonService {


    /**
     * This service retrieves the list fo shots
     * @param accessToken
     * @param perPage
     * @param page
     * @return
     */
    @GET("shots")
    Single<List<Shot>> getShots(@Query("access_token") String accessToken,
                               @Query("per_page") int perPage,
                               @Query("page") int page);


    /**
     * This service retrieves the coomments based on Id of the shots
     * @param shotId
     * @param accessToken
     * @param perPage
     * @param page
     * @return
     */
    @GET("shots/{shot_id}/comments")
    Single<List<Comment>> getComments(@Path("shot_id") int shotId,
                              @Query("access_token") String accessToken,
                              @Query("per_page") int perPage,
                              @Query("page") int page);
}
