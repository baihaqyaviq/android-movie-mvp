/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.elifbyte.dimovies3.mvp.data.network;

import com.elifbyte.dimovies3.mvp.data.network.model.MovieResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by janisharali on 28/01/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<MovieResponse> getNowApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_NOW_PLAYING)
                .build()
                .getObjectSingle(MovieResponse.class);
    }

    @Override
    public Single<MovieResponse> getUpcomingApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_UPCOMING)
                .build()
                .getObjectSingle(MovieResponse.class);
    }

    @Override
    public Single<MovieResponse> getSearchApiCall(String query) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SEARCH)
                .addQueryParameter("query", query)
                .build()
                .getObjectSingle(MovieResponse.class);
    }

    @Override
    public Single<MovieResponse> getDiscoverApiCall() {
        return null;
    }
}

