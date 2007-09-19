/*
 *
 * Copyright (C) 2007 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * Kune is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kune is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.ourproject.kune.chat.client;

import org.ourproject.kune.chat.client.cnt.ChatContent;
import org.ourproject.kune.chat.client.cnt.ChatContentPresenter;
import org.ourproject.kune.chat.client.cnt.info.ChatInfo;
import org.ourproject.kune.chat.client.cnt.info.ui.ChatInfoPanel;
import org.ourproject.kune.chat.client.cnt.room.ChatRoom;
import org.ourproject.kune.chat.client.cnt.room.ChatRoomListener;
import org.ourproject.kune.chat.client.cnt.room.ChatRoomPresenter;
import org.ourproject.kune.chat.client.cnt.room.ui.ChatRoomPanel;
import org.ourproject.kune.chat.client.ctx.ChatContext;
import org.ourproject.kune.chat.client.ctx.ChatContextPresenter;
import org.ourproject.kune.chat.client.ctx.rooms.RoomsAdmin;
import org.ourproject.kune.chat.client.ctx.rooms.RoomsAdminPresenter;
import org.ourproject.kune.chat.client.rooms.MultiRoom;
import org.ourproject.kune.chat.client.rooms.MultiRoomListener;
import org.ourproject.kune.chat.client.rooms.MultiRoomPresenter;
import org.ourproject.kune.chat.client.rooms.Room;
import org.ourproject.kune.chat.client.rooms.RoomListener;
import org.ourproject.kune.chat.client.rooms.RoomPresenter;
import org.ourproject.kune.chat.client.rooms.RoomUserList;
import org.ourproject.kune.chat.client.rooms.RoomUserListPresenter;
import org.ourproject.kune.chat.client.rooms.RoomUser.UserType;
import org.ourproject.kune.chat.client.rooms.ui.MultiRoomPanel;
import org.ourproject.kune.chat.client.rooms.ui.RoomPanel;
import org.ourproject.kune.chat.client.rooms.ui.RoomUserListPanel;
import org.ourproject.kune.workspace.client.component.WorkspaceDeckPanel;
import org.ourproject.kune.workspace.client.WorkspaceFactory;
import org.ourproject.kune.workspace.client.ui.ctx.items.ContextItems;

public class ChatFactory {

    public static ChatContent createChatContent() {
	WorkspaceDeckPanel panel = new WorkspaceDeckPanel();
	ChatContentPresenter presenter = new ChatContentPresenter(panel);
	return presenter;
    }

    public static ChatContext createChatContext() {
	WorkspaceDeckPanel panel = new WorkspaceDeckPanel();
	ChatContextPresenter presenter = new ChatContextPresenter(panel);
	return presenter;
    }

    public static RoomsAdmin createRoomsAdmin() {
	ContextItems contextItems = WorkspaceFactory.createContextItems();
	RoomsAdminPresenter presenter = new RoomsAdminPresenter(contextItems);
	return presenter;
    }

    public static MultiRoom createChatMultiRoom(final MultiRoomListener listener) {
	MultiRoomPresenter presenter = new MultiRoomPresenter(listener);
	MultiRoomPanel panel = new MultiRoomPanel(presenter);
	presenter.init(panel);
	return presenter;
    }

    public static ChatRoom createChatRoomViewer(final ChatRoomListener listener) {
	ChatRoomPanel panel = new ChatRoomPanel(listener);
	ChatRoomPresenter presenter = new ChatRoomPresenter(panel);
	return presenter;
    }

    public static RoomUserList createUserList() {
	RoomUserListPresenter userListPresenter = new RoomUserListPresenter();
	RoomUserListPanel panel = new RoomUserListPanel();
	userListPresenter.init(panel);
	return userListPresenter;
    }

    public static Room createRoom(final RoomListener listener, final String roomName, final String userAlias,
	    final UserType type) {
	RoomUserList userList = ChatFactory.createUserList();
	RoomPresenter presenter = new RoomPresenter(listener, roomName, userAlias, type, userList);
	RoomPanel panel = new RoomPanel(presenter);
	presenter.init(panel);
	return presenter;
    }

    public static ChatInfo createChatInfo(final ChatRoomListener listener) {
	ChatInfoPanel panel = new ChatInfoPanel(listener);
	return panel;
    }
}