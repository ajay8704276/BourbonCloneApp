package com.app.bourbon.clone.bourbonbaselib.data;

import com.app.bourbon.clone.bourbonbaselib.data.model.Comment;
import com.app.bourbon.clone.bourbonbaselib.data.model.Shot;
import com.app.bourbon.clone.bourbonbaselib.data.remote.BourbonService;
import com.app.bourbon.clone.bourbonbaselib.utils.BourbonServiceConstants;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 *
 * This class manage request and response of the service
 * Created by admin on 06/04/18.
 */

@Singleton
public class DataManager {

    private final BourbonService mBourbonService;


    @Inject
    public DataManager(BourbonService mBourbonService){
        this.mBourbonService = mBourbonService;
    }

    public Single<List<Shot>> getShots(int perPage , int page){

       return  mBourbonService.getShots(BourbonServiceConstants.DRIBBLE_ACCESS_TOKEN,perPage,page);
    }


    public Single<List<Comment>> getComments(int id, int perPage, int page) {
        return mBourbonService.getComments(id, BourbonServiceConstants.DRIBBLE_ACCESS_TOKEN, perPage, page);
    }

}
