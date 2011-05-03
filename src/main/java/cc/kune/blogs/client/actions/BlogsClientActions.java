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
 \*/
package cc.kune.blogs.client.actions;

import static cc.kune.blogs.shared.BlogsConstants.TYPE_BLOG;
import static cc.kune.blogs.shared.BlogsConstants.TYPE_POST;
import static cc.kune.blogs.shared.BlogsConstants.TYPE_ROOT;
import static cc.kune.blogs.shared.BlogsConstants.TYPE_UPLOADEDFILE;
import cc.kune.core.client.actions.ActionRegistryByType;
import cc.kune.core.client.i18n.I18nUITranslationService;
import cc.kune.core.client.resources.CoreResources;
import cc.kune.core.client.state.Session;
import cc.kune.core.client.state.StateManager;
import cc.kune.gspace.client.actions.AbstractFoldableToolActions;
import cc.kune.gspace.client.actions.ActionGroups;
import cc.kune.gspace.client.actions.ParticipateInContentBtn;
import cc.kune.gspace.client.actions.RefreshContentMenuItem;
import cc.kune.gspace.client.actions.SetAsHomePageMenuItem;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BlogsClientActions extends AbstractFoldableToolActions {

    final String[] all = { TYPE_ROOT, TYPE_BLOG, TYPE_POST, TYPE_UPLOADEDFILE };
    final String[] containers = { TYPE_ROOT, TYPE_BLOG };
    final String[] containersNoRoot = { TYPE_BLOG };
    final String[] contents = { TYPE_POST, TYPE_UPLOADEDFILE };
    final String[] contentsModerated = { TYPE_POST, TYPE_UPLOADEDFILE };

    @Inject
    public BlogsClientActions(final I18nUITranslationService i18n, final Session session,
            final StateManager stateManager, final ActionRegistryByType registry, final CoreResources res,
            final Provider<GoParentBlogBtn> folderGoUp, final Provider<NewPostBtn> newDocBtn,
            final Provider<NewBlogBtn> newFolderBtn, final Provider<OpenBlogMenuItem> openContentMenuItem,
            final Provider<DelPostMenuItem> delContentMenuItem, final Provider<RefreshContentMenuItem> refresh,
            final Provider<ContentViewerOptionsMenu> optionsMenuContent,
            final Provider<ParticipateInContentBtn> participateBtn, final Provider<DelBlogMenuItem> delFolderMenuItem,
            final Provider<SetAsHomePageMenuItem> setAsHomePage) {
        super(session, stateManager, i18n, registry);
        actionsRegistry.addAction(ActionGroups.VIEW, folderGoUp, contents);
        actionsRegistry.addAction(ActionGroups.VIEW, folderGoUp, containersNoRoot);
        actionsRegistry.addAction(ActionGroups.VIEW, optionsMenuContent, all);
        actionsRegistry.addAction(ActionGroups.VIEW, participateBtn, contents);
        actionsRegistry.addAction(ActionGroups.VIEW, refresh, all);
        actionsRegistry.addAction(ActionGroups.VIEW, newDocBtn, containersNoRoot);
        actionsRegistry.addAction(ActionGroups.VIEW, newFolderBtn, TYPE_ROOT);
        actionsRegistry.addAction(ActionGroups.MENUITEM, openContentMenuItem, contents);
        actionsRegistry.addAction(ActionGroups.MENUITEM, openContentMenuItem, containersNoRoot);
        actionsRegistry.addAction(ActionGroups.MENUITEM, delContentMenuItem, contents);
        actionsRegistry.addAction(ActionGroups.MENUITEM, delFolderMenuItem, containersNoRoot);
    }

    @Override
    protected void createPostSessionInitActions() {
    }
}