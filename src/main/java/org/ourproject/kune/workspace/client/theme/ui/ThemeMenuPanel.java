package org.ourproject.kune.workspace.client.theme.ui;

import org.ourproject.kune.platf.client.services.ColorTheme;
import org.ourproject.kune.platf.client.services.Images;
import org.ourproject.kune.platf.client.services.Kune;
import org.ourproject.kune.workspace.client.theme.ThemeMenuPresenter;
import org.ourproject.kune.workspace.client.theme.ThemeMenuView;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;

public class ThemeMenuPanel extends MenuBar implements ThemeMenuView {

    private final MenuBar themesMB;
    private final ThemeMenuPresenter presenter;

    public ThemeMenuPanel(final ThemeMenuPresenter presenter) {
	this.presenter = presenter;
	themesMB = new MenuBar(true);
	this.addItem(Images.App.getInstance().themeChoose().getHTML(), true, themesMB);
	setStyleName("kune-ThemeMenuPanel");
	// i18n
	setTitle("Select Workspace theme for this group");
    }

    public void setThemes(final String[] themes) {
	for (int i = 0; i < themes.length; i++) {
	    final String theme = themes[i];
	    final ColorTheme colorTheme = Kune.getInstance().theme;
	    colorTheme.setTheme(theme);
	    String themeName = colorTheme.getThemeName();
	    String mainColor = colorTheme.getContentMainBorder();
	    themesMB.addItem("<span style=\"color: " + mainColor + ";\">" + themeName + "</span>", true, new Command() {
		public void execute() {
		    presenter.chooseTheme(theme);
		}
	    });
	}
    }

    public void setVisible(final boolean visible) {
	super.setVisible(visible);
    }
}