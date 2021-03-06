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

package com.elifbyte.dimovies3.mvp.data.db;

import com.elifbyte.dimovies3.mvp.data.db.model.Favorite;
import com.elifbyte.dimovies3.mvp.data.db.model.User;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<Long> insertFavorite(final Favorite favorite);

    Observable<List<Favorite>> getAllFavorite();

    Observable<Boolean> saveFavorite(Favorite favorite);

    Observable<Boolean> deleteFavorite(Favorite favorite);

    Observable<Boolean> saveFavoriteList(List<Favorite> favoriteList);

    Observable<Favorite> getFavoriteById(Long id);

    Observable<Boolean> isFavoriteEmpty();

    Observable<Long> insertUser(final User user);

    Observable<List<User>> getAllUsers();
}
