package cc.kune.core.client.sitebar;

import cc.kune.common.client.actions.AbstractExtendedAction;
import cc.kune.common.client.actions.ActionEvent;
import cc.kune.common.client.actions.ActionStyles;
import cc.kune.common.client.actions.ui.descrip.ButtonDescriptor;
import cc.kune.common.shared.i18n.I18nTranslationService;
import cc.kune.core.client.events.UserSignInOrSignOutEvent;
import cc.kune.core.client.events.UserSignInOrSignOutEvent.UserSignInOrSignOutHandler;
import cc.kune.core.client.resources.CoreMessages;
import cc.kune.core.client.state.Session;
import cc.kune.core.client.state.StateManager;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

/**
 * The class SitebarGotoPersonalPageLink
 *
 * @author Antonio Tenorio Fornés <antoniotenorio@ucm.es>
 *
 */
public class SitebarGotoPersonalPageLink extends ButtonDescriptor {

  /**
   * The class SitebarGotoPersonalPageAction
   *
   * @author Antonio Tenorio Fornés <antoniotenorio@ucm.es>
   *
   */
  public static class SitebarGotoPersonalPageAction extends AbstractExtendedAction {

    private final Session session;
    /** The state manager. */
    private final StateManager stateManager;

    /**
     * Instantiates a new sitebar new group action.
     *
     * @param stateManager
     *          the state manager
     * @param i18n
     *          the i18n
     */
    @Inject
    public SitebarGotoPersonalPageAction(final StateManager stateManager,
        final I18nTranslationService i18n, final Session session) {
      super();
      this.session = session;
      this.stateManager = stateManager;
      withText(i18n.t(CoreMessages.YOUR_HOMEPAGE));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * cc.kune.common.client.actions.ActionListener#actionPerformed(cc.kune.
     * common.client.actions.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
      stateManager.gotoHistoryToken(session.getCurrentUserInfo().getShortName());
    }
  }
  /** The constant GOTO_PERSONAL_BTN_ID */
  private static final String GOTO_PERSONAL_BTN_ID = null;

  /**
   * Instantiates a new sitebar goto personal page link
   *
   * @param action
   *          the action
   * @param sitebarActions
   *          the sitebar actions
   * @param i18n
   *          the i18n
   * @param eventBus
   *          the event bus
   * @param session
   *          the session
   */
  @Inject
  public SitebarGotoPersonalPageLink(final SitebarGotoPersonalPageAction action,
      final SitebarActions sitebarActions, final I18nTranslationService i18n, final EventBus eventBus,
      final Session session) {
    super(action);
    withId(GOTO_PERSONAL_BTN_ID).withStyles(ActionStyles.SITEBAR_STYLE_FL);
    withParent(SitebarActions.LEFT_TOOLBAR);
    setPosition(2);
    session.onUserSignInOrSignOut(true, new UserSignInOrSignOutHandler() {
      @Override
      public void onUserSignInOrSignOut(final UserSignInOrSignOutEvent event) {
        final boolean logged = event.isLogged();
        setVisible(logged);
      }
    });
  }

}
