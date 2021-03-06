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

package com.elifbyte.dimovies3.mvp.di.module;

import android.app.Application;
import android.content.Context;

import com.elifbyte.dimovies3.mvp.BuildConfig;
import com.elifbyte.dimovies3.mvp.data.AppDataManager;
import com.elifbyte.dimovies3.mvp.data.DataManager;
import com.elifbyte.dimovies3.mvp.data.db.AppDbHelper;
import com.elifbyte.dimovies3.mvp.data.db.DbHelper;
import com.elifbyte.dimovies3.mvp.data.network.ApiHeader;
import com.elifbyte.dimovies3.mvp.data.network.ApiHelper;
import com.elifbyte.dimovies3.mvp.data.network.AppApiHelper;
import com.elifbyte.dimovies3.mvp.data.prefs.AppPreferencesHelper;
import com.elifbyte.dimovies3.mvp.data.prefs.PreferencesHelper;
import com.elifbyte.dimovies3.mvp.di.ApiInfo;
import com.elifbyte.dimovies3.mvp.di.ApplicationContext;
import com.elifbyte.dimovies3.mvp.di.DatabaseInfo;
import com.elifbyte.dimovies3.mvp.di.PreferenceInfo;
import com.elifbyte.dimovies3.mvp.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by amitshekhar on 03/02/17.
 */
@Module
public class ApplicationTestModule {

    private final Application mApplication;

    public ApplicationTestModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    // TODO : Mock all below for UI testing

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(com.elifbyte.dimovies3.mvp.R.attr.fontPath)
                .build();
    }
}
