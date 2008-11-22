/*
 *
 * Copyright (C) 2007-2008 The kune development team (see CREDITS for details)
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
 */package org.ourproject.kune.workspace.client.socialnet;

import org.ourproject.kune.platf.client.actions.ActionItem;
import org.ourproject.kune.platf.client.actions.ActionItemCollection;
import org.ourproject.kune.platf.client.actions.ActionManager;
import org.ourproject.kune.platf.client.actions.ActionToolbarPosition;
import org.ourproject.kune.platf.client.actions.MenuItemsContainer;
import org.ourproject.kune.platf.client.actions.toolbar.ActionToolbarPanel;
import org.ourproject.kune.platf.client.actions.toolbar.ActionToolbarView;
import org.ourproject.kune.platf.client.dto.UserSimpleDTO;
import org.ourproject.kune.platf.client.services.I18nTranslationService;
import org.ourproject.kune.platf.client.services.Images;
import org.ourproject.kune.workspace.client.skel.SimpleToolbar;
import org.ourproject.kune.workspace.client.skel.SummaryPanel;
import org.ourproject.kune.workspace.client.skel.WorkspaceSkeleton;

import com.calclab.suco.client.listener.Listener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.menu.Menu;

public class BuddiesSummaryPanel extends SummaryPanel implements BuddiesSummaryView {

    public class BuddieWidget extends Composite {
        private final Image avatar;
        private final Label nick;

        public BuddieWidget(String nickName, String avatarUrl, ClickListener clickListener) {
            // For Drag& drop, see gwt-ext-ux image:
            // http://www.gwt-ext.com:8080/demo-ux/#imageDDSample
            VerticalPanel vp = new VerticalPanel();
            avatar = new Image();
            if (avatarUrl.equals(NOAVATAR)) {
                Images.App.getInstance().personAvatarDef().applyTo(avatar);
            } else {
                avatar.setUrl(avatarUrl);
            }
            avatar.setPixelSize(AVATARSIZE, AVATARSIZE);
            nick = new Label(nickName);
            vp.add(avatar);
            vp.add(nick);
            vp.addStyleName("k-bsp-buddie");
            vp.addStyleName("kune-Margin-Small-trbl");
            vp.addStyleName("kune-pointer");
            vp.addStyleName("kune-floatleft");
            nick.addClickListener(clickListener);
            vp.setCellHorizontalAlignment(nick, VerticalPanel.ALIGN_CENTER);
            avatar.addClickListener(clickListener);
            initWidget(vp);
        }

        public void setAvatarUrl(String url) {
            avatar.setUrl(url);
        }

        public void setNick(String nickName) {
            nick.setText(nickName);
        }
    }
    private final MenuItemsContainer<UserSimpleDTO> menuItemsContainer;
    private final FlowPanel flowPanel;
    private final Label otherBuddiesLabel;
    private final I18nTranslationService i18n;
    private final ActionManager actionManager;
    private final SimpleToolbar toolbar;

    public BuddiesSummaryPanel(final BuddiesSummaryPresenter presenter, final WorkspaceSkeleton ws,
            I18nTranslationService i18n, ActionManager actionManager, ActionToolbarView<UserSimpleDTO> actionToolbarView) {
        super(i18n.t("Buddies"), i18n.t("Buddies of this user"), ws);
        this.actionManager = actionManager;
        menuItemsContainer = new MenuItemsContainer<UserSimpleDTO>();
        this.i18n = i18n;
        VerticalPanel vp = new VerticalPanel();
        flowPanel = new FlowPanel();
        otherBuddiesLabel = new Label();
        otherBuddiesLabel.addStyleName("kune-Margin-Small-trbl");
        vp.add(flowPanel);
        vp.add(otherBuddiesLabel);
        toolbar = ((ActionToolbarPanel<UserSimpleDTO>) actionToolbarView).getToolbar(ActionToolbarPosition.bottombar);
        toolbar.setCleanStyle();
        vp.add(toolbar);
        super.add(vp);
        addInSummary();
    }

    public void addBuddie(final UserSimpleDTO user, ActionItemCollection<UserSimpleDTO> actionCollection,
            String avatarUrl) {
        ClickListener listener = new ClickListener() {
            public void onClick(Widget sender) {
                Menu menu = menuItemsContainer.get(user.getShortName());
                if (menu.getItems().length > 0) {
                    menu.showAt(sender.getAbsoluteLeft(), sender.getAbsoluteTop() + 5);
                }
            }
        };
        menuItemsContainer.createItemMenu(user.getShortName(), actionCollection,
                new Listener<ActionItem<UserSimpleDTO>>() {
                    public void onEvent(ActionItem<UserSimpleDTO> actionItem) {
                        doAction(actionItem);
                    }
                });

        flowPanel.add(new BuddieWidget(user.getShortName(), avatarUrl, listener));
    }

    @Override
    public void clear() {
        flowPanel.clear();
        clearOtherUsers();
        menuItemsContainer.clear();
        toolbar.removeAll();
        super.doLayoutIfNeeded();
    }

    public void clearOtherUsers() {
        otherBuddiesLabel.setText("");
    }

    public void setNoBuddies() {
        otherBuddiesLabel.setText(i18n.t("This user has no buddies"));
    }

    public void setOtherUsers(String text) {
        otherBuddiesLabel.setText(text);
    }

    private void doAction(final ActionItem<UserSimpleDTO> actionItem) {
        actionManager.doAction(actionItem);
    }
}
