package org.ourproject.kune.workspace.client.ui.newtmp.sitebar.siteusermenu;

import org.ourproject.kune.platf.client.dto.GroupDTO;
import org.ourproject.kune.platf.client.ui.MenuItemCollection;
import org.ourproject.kune.workspace.client.i18n.I18nUITranslationService;
import org.ourproject.kune.workspace.client.ui.newtmp.skel.WorkspaceSkeleton;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.Item;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.MenuItem;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;

public class SiteUserMenuPanel implements SiteUserMenuView {

    private final PushButton loggedUserMenu;
    private final Widget separator;
    private final Menu userMenu;
    private final Menu userParticipation;

    public SiteUserMenuPanel(final SiteUserMenuPresenter presenter, final WorkspaceSkeleton ws,
	    final I18nUITranslationService i18n) {
	loggedUserMenu = new PushButton("");
	loggedUserMenu.setStyleName("k-sitebar-labellink");
	ws.getSiteBar().add(loggedUserMenu);
	separator = ws.getSiteBar().addSeparator();
	userMenu = new Menu();
	loggedUserMenu.addClickListener(new ClickListener() {
	    public void onClick(final Widget sender) {
		userMenu.showAt(sender.getAbsoluteLeft(), sender.getAbsoluteTop() + 10);
	    }
	});
	final Item userHomePage = new Item(i18n.t("Your homepage"));
	userHomePage.setIcon("images/group-home.gif");
	userHomePage.addListener(new BaseItemListenerAdapter() {
	    @Override
	    public void onClick(final BaseItem item, final EventObject e) {
		super.onClick(item, e);
		presenter.onUserHomePage();
	    }
	});
	userMenu.addItem(userHomePage);
	final Item userPreferences = new Item(i18n.t("Your preferences"));
	userPreferences.setIcon("images/kune-preferences.gif");
	userPreferences.addListener(new BaseItemListenerAdapter() {
	    @Override
	    public void onClick(final BaseItem item, final EventObject e) {
		super.onClick(item, e);
		presenter.onUserPreferences();
	    }
	});
	userMenu.addItem(userPreferences);
	userParticipation = new Menu();
	final MenuItem userParticipationItem = new MenuItem(i18n.t("Your groups"), userParticipation);
	userParticipationItem.setIcon("");
	userMenu.addItem(userParticipationItem);
    }

    public void setLoggedUserName(final String name) {
	loggedUserMenu.setHTML(name + "<img style=\"vertical-align: middle;\" src=\"images/arrowdown.png\" />");
    }

    public void setParticipation(final MenuItemCollection<GroupDTO> participateInGroups) {
	userParticipation.removeAll();
	for (final org.ourproject.kune.platf.client.ui.MenuItem<GroupDTO> groupItem : participateInGroups) {
	    final Item item = new Item(groupItem.getTitle(), new BaseItemListenerAdapter() {
		@Override
		public void onClick(BaseItem item, EventObject e) {
		    super.onClick(item, e);
		    groupItem.fire(null);
		}
	    });
	    item.setIcon(groupItem.getIcon());
	    userParticipation.addItem(item);
	}
    }

    public void setVisible(final boolean visible) {
	loggedUserMenu.setVisible(visible);
	separator.setVisible(visible);
    }
}