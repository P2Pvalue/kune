/*
 *
 * Copyright (C) 2007-2009 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.ourproject.kune.platf.server.content;

import java.util.Date;

import org.ourproject.kune.platf.server.domain.Container;
import org.ourproject.kune.platf.server.domain.Content;
import org.ourproject.kune.platf.server.domain.ContentStatus;
import org.ourproject.kune.platf.server.domain.I18nLanguage;
import org.ourproject.kune.platf.server.domain.RateResult;
import org.ourproject.kune.platf.server.domain.User;
import org.ourproject.kune.platf.server.manager.Manager;
import org.ourproject.kune.platf.server.manager.impl.SearchResult;

import cc.kune.core.client.errors.DefaultException;

public interface ContentManager extends Manager<Content, Long> {

    String[] DEF_GLOBAL_SEARCH_FIELDS = new String[] { "authors.name", "authors.shortName", "container.name",
            "language.code", "language.englishName", "language.nativeName", "lastRevision.body", "lastRevision.title" };

    String[] DEF_GLOBAL_SEARCH_FIELDS_WITH_MIME = new String[] { "authors.name", "authors.shortName", "container.name",
            "language.code", "language.englishName", "language.nativeName", "lastRevision.body", "lastRevision.title",
            "mimeType.mimetype" };

    String[] DEF_GROUP_SEARCH_FIELDS_WITH_MIME = new String[] { "lastRevision.title", "container.owner_shortName",
            "mimeType.mimetype" };

    void addAuthor(User user, Long contentId, String authorShortName) throws DefaultException;

    Content createContent(String title, String body, User author, Container container, String typeId);

    boolean findIfExistsTitle(Container container, String title);

    Double getRateAvg(Content content);

    Long getRateByUsers(Content content);

    Double getRateContent(User user, Content content);

    RateResult rateContent(User rater, Long contentId, Double value) throws DefaultException;

    void removeAuthor(User user, Long contentId, String authorShortName) throws DefaultException;

    Content renameContent(User user, Long contentId, String newName) throws DefaultException;

    Content save(User editor, Content content, String body);

    SearchResult<Content> search(String search);

    SearchResult<Content> search(String search, Integer firstResult, Integer maxResults);

    SearchResult<Content> searchMime(String search, Integer firstResult, Integer maxResults, String group,
            String mimetype);

    SearchResult<?> searchMime(String search, Integer firstResult, Integer maxResults, String group, String mimetype,
            String mimetype2);

    I18nLanguage setLanguage(User user, Long contentId, String languageCode) throws DefaultException;

    void setPublishedOn(User user, Long contentId, Date publishedOn) throws DefaultException;

    Content setStatus(Long contentId, ContentStatus contentStatus);

    void setTags(User user, Long contentId, String tags) throws DefaultException;

}
