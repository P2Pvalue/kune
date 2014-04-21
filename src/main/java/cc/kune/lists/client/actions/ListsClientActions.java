/*
 *
 * Copyright (C) 2007-2014 Licensed to the Comunes Association (CA) under
 * one or more contributor license agreements (see COPYRIGHT for details).
 * The CA licenses this file to you under the GNU Affero General Public
 * License version 3, (the "License"); you may not use this file except in
 * compliance with the License. This file is part of kune.
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
package cc.kune.lists.client.actions;

import static cc.kune.gspace.client.actions.ActionGroups.BOTTOMBAR;
import static cc.kune.gspace.client.actions.ActionGroups.ITEM_MENU;
import static cc.kune.gspace.client.actions.ActionGroups.TOPBAR;
import static cc.kune.lists.shared.ListsToolConstants.TOOL_NAME;
import static cc.kune.lists.shared.ListsToolConstants.TYPE_LIST;
import static cc.kune.lists.shared.ListsToolConstants.TYPE_POST;
import static cc.kune.lists.shared.ListsToolConstants.TYPE_ROOT;
import cc.kune.chat.client.actions.ChatAboutContentBtn;
import cc.kune.common.client.actions.ui.descrip.MenuDescriptor;
import cc.kune.common.shared.i18n.I18n;
import cc.kune.core.client.actions.ActionRegistryByType;
import cc.kune.core.client.invitation.ListInvitationMenuItem;
import cc.kune.core.client.invitation.ListInvitationShareMenuItem;
import cc.kune.core.client.invitation.ListInviteUserMenuItem;
import cc.kune.core.client.registry.NewMenusForTypeIdsRegistry;
import cc.kune.core.client.resources.CoreResources;
import cc.kune.core.client.state.Session;
import cc.kune.gspace.client.actions.AbstractFoldableToolActions;
import cc.kune.gspace.client.actions.ContentViewerOptionsMenu;
import cc.kune.gspace.client.actions.CopyContentMenuItem;
import cc.kune.gspace.client.actions.MoveContentMenuItem;
import cc.kune.gspace.client.actions.ParticipateInContentBtn;
import cc.kune.gspace.client.actions.PurgeContainerBtn;
import cc.kune.gspace.client.actions.PurgeContainerMenuItem;
import cc.kune.gspace.client.actions.PurgeContentBtn;
import cc.kune.gspace.client.actions.PurgeContentMenuItem;
import cc.kune.gspace.client.actions.RefreshContentMenuItem;
import cc.kune.gspace.client.actions.SetAsHomePageMenuItem;
import cc.kune.gspace.client.actions.TutorialBtn;
import cc.kune.gspace.client.actions.WriteToParticipantsMenuItem;
import cc.kune.gspace.client.actions.share.AddAdminMembersToContentMenuItem;
import cc.kune.gspace.client.actions.share.AddAllMembersToContentMenuItem;
import cc.kune.gspace.client.actions.share.AddCollabMembersToContentMenuItem;
import cc.kune.gspace.client.actions.share.ShareDialogMenuItem;
import cc.kune.gspace.client.actions.share.ShareInHelper;
import cc.kune.gspace.client.actions.share.ShareMenu;
import cc.kune.gspace.client.actions.share.ShareMenu;
import cc.kune.trash.shared.TrashToolConstants;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ListsClientActions extends AbstractFoldableToolActions {

  final String[] all = { TYPE_ROOT, TYPE_LIST, TYPE_POST };
  final String[] allExceptRoot = { TYPE_LIST, TYPE_POST };
  final String[] containers = { TYPE_ROOT, TYPE_LIST };
  final String[] containersNoRoot = { TYPE_LIST };
  final String[] contents = { TYPE_POST };
  final String[] noRoot = { TYPE_LIST, TYPE_POST };

  @SuppressWarnings("unchecked")
  @Inject
  public ListsClientActions(final Session session, final ActionRegistryByType registry,
      final CoreResources res, final Provider<GoParentFolderBtn> folderGoUp,
      final Provider<NewListPostIconMenuItem> newPostItem,
      final Provider<NewListPostIconBtn> newPostIconBtn,
      final Provider<NewListMenuItem> newListMenuItem, final Provider<NewListBtn> newListBtn,
      final Provider<OpenFolderMenuItem> openContentMenuItem,
      final Provider<RefreshListMenuItem> refreshList, final Provider<SubscribeToListBtn> subscribeBtn,
      final Provider<OptionsListMenu> optionsMenuContainer,
      final Provider<ContentViewerOptionsMenu> optionsMenuContent,
      final Provider<ShareMenu> shareMenuContent,
      final Provider<ShowSubscribersOfListBtn> subscribersCount,
      final Provider<TutorialBtn> tutorialBtn,
      final Provider<AddAllMembersToContentMenuItem> addAllMenuItem,
      final Provider<AddAdminMembersToContentMenuItem> addAdminMembersMenuItem,
      final Provider<AddCollabMembersToContentMenuItem> addCollabMembersMenuItem,
      final Provider<ParticipateInContentBtn> participateBtn,
      final Provider<CopyContentMenuItem> copyContent,
      final Provider<RefreshContentMenuItem> refreshPost,
      final Provider<PurgeContentMenuItem> purgeMenuItem, final Provider<PurgeContentBtn> purgeBtn,
      final Provider<PurgeContainerMenuItem> purgeFolderMenuItem,
      final Provider<PurgeContainerBtn> purgeFolderBtn,
      final Provider<WriteToParticipantsMenuItem> writeToParticipants, final ListsNewMenu listNewMenu,
      final PostNewMenu postNewMenu, final NewMenusForTypeIdsRegistry newMenusRegistry,
      final Provider<ChatAboutContentBtn> chatAbout, final Provider<DelListMenuItem> delFolderMenuItem,
      final Provider<DelPostMenuItem> delPostMenuItem,
      final Provider<MoveContentMenuItem> moveContentMenuItem,
      final Provider<SetAsHomePageMenuItem> setAsHomePage,
      final Provider<ShareDialogMenuItem> shareDialog,
      final Provider<ConfigureListMenuItem> configureList,      
      final Provider<ListInvitationShareMenuItem> shareInvitation,
      final Provider<ListInvitationMenuItem> inviteMenuItem,
      final Provider<ListInviteUserMenuItem> inviteUserMenuItem,
      final ShareInHelper shareIHelper) {
    super(TOOL_NAME, session, registry);
    // add(NAME, TOOLBAR, newListMenuItem,
    // TYPE_ROOT);
    add(TOPBAR, containers, optionsMenuContainer);
    add(TOPBAR, contents, optionsMenuContent);
    add(TOPBAR, noRoot, newPostIconBtn);
    add(TOPBAR, newListBtn, TYPE_ROOT);
    add(TOPBAR, containersNoRoot, listNewMenu, subscribeBtn, newPostItem);
    add(TOPBAR, containers, refreshList);
    add(TOPBAR, contents, postNewMenu, refreshPost);
    add(BOTTOMBAR, contents, folderGoUp);
    add(BOTTOMBAR, containers, folderGoUp);
    add(TOPBAR, all, tutorialBtn, shareMenuContent);
    add(TOPBAR, containersNoRoot, configureList);
    add(TOPBAR, contents, addAllMenuItem, addAdminMembersMenuItem, addCollabMembersMenuItem);
    add(TOPBAR, allExceptRoot, shareInvitation);
    add(TOPBAR, contents, shareIHelper.getShareInWaves());
    add(TOPBAR, all, shareIHelper.getShareInAll());
    add(TOPBAR, contents, shareDialog);
    add(TOPBAR, containersNoRoot, subscribersCount);
    add(ITEM_MENU, containersNoRoot, openContentMenuItem, delFolderMenuItem);
    add(TOPBAR, contents, participateBtn, copyContent, chatAbout, writeToParticipants);
    add(ITEM_MENU, contents, openContentMenuItem);
    add(ITEM_MENU, allExceptRoot, inviteMenuItem);
    add(ITEM_MENU, allExceptRoot, inviteUserMenuItem);
    add(ITEM_MENU, contents, delPostMenuItem, addAllMenuItem, addAdminMembersMenuItem,
        addCollabMembersMenuItem, copyContent, writeToParticipants);
    add(TrashToolConstants.TOOL_NAME, TOPBAR, contents, purgeBtn);
    add(TrashToolConstants.TOOL_NAME, TOPBAR, containersNoRoot, purgeFolderBtn);
    add(TrashToolConstants.TOOL_NAME, ITEM_MENU, contents, purgeMenuItem, moveContentMenuItem);
    add(TrashToolConstants.TOOL_NAME, ITEM_MENU, containersNoRoot, purgeFolderMenuItem,
        moveContentMenuItem);
    newMenusRegistry.register(TYPE_LIST, listNewMenu.get());
    newMenusRegistry.register(TYPE_ROOT, listNewMenu.get());
    newMenusRegistry.register(TYPE_POST,
        (MenuDescriptor) postNewMenu.get().withText(I18n.t("Add Gadget")));
  }

  @Override
  protected void createPostSessionInitActions() {
  }
}
