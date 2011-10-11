package cc.kune.hspace.client;

import java.util.List;

import org.waveprotocol.wave.client.common.util.DateUtils;

import cc.kune.common.client.ui.DottedTabPanel;
import cc.kune.core.client.services.FileDownloadUtils;
import cc.kune.core.shared.domain.utils.StateToken;
import cc.kune.core.shared.dto.ContentSimpleDTO;
import cc.kune.core.shared.dto.GroupDTO;
import cc.kune.core.shared.i18n.I18nTranslationService;
import cc.kune.gspace.client.GSpaceArmor;
import cc.kune.hspace.client.HSpacePresenter.HSpaceView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.ViewImpl;

public class HSpacePanel extends ViewImpl implements HSpaceView {

  interface HSpacePanelUiBinder extends UiBinder<Widget, HSpacePanel> {
  }
  public static final String K_HOME_GLOBAL_STATS = "k-home-global-stats";

  public static final String K_HOME_GROUP_STATS = "k-home-group-stats";

  private static HSpacePanelUiBinder uiBinder = GWT.create(HSpacePanelUiBinder.class);

  private final FileDownloadUtils downUtils;
  @UiField
  FlowPanel globalStats;
  @UiField
  public Label globalStatsTitle;
  @UiField
  public InlineLabel globalStatsTotalGroupsCount;
  @UiField
  public InlineLabel globalStatsTotalGroupsTitle;
  @UiField
  public InlineLabel globalStatsTotalUsersCount;
  @UiField
  public InlineLabel globalStatsTotalUsersTitle;
  @UiField
  public FlowPanel lastActivityInYourGroup;
  @UiField
  public Label lastActivityInYourGroupTitle;
  @UiField
  FlowPanel lastActivityPanel;
  @UiField
  public FlowPanel lastGroups;
  @UiField
  FlowPanel lastGroupsPanel;
  @UiField
  public Label lastGroupsTitle;
  @UiField
  public FlowPanel lastPublishedContents;
  @UiField
  public Label lastPublishedContentsTitle;
  @UiField
  FlowPanel lastPublishedPanel;

  private final Provider<GroupContentHomeLink> linkProv;

  private final DottedTabPanel tabPanel;

  private final Widget widget;

  @Inject
  public HSpacePanel(final I18nTranslationService i18n, final GSpaceArmor armor,
      final Provider<GroupContentHomeLink> linkProv, final FileDownloadUtils downUtils) {
    this.linkProv = linkProv;
    this.downUtils = downUtils;
    widget = uiBinder.createAndBindUi(this);
    globalStatsTitle.setText(i18n.t("Stats"));
    globalStatsTotalGroupsTitle.setText(i18n.t("Hosted groups:"));
    globalStatsTotalUsersTitle.setText(i18n.t("Registered users:"));
    lastGroupsTitle.setText(i18n.t("Latest created groups"));
    lastPublishedContentsTitle.setText(i18n.t("Latest publications"));
    lastActivityInYourGroupTitle.setText(i18n.t("Latest activity in your groups"));
    tabPanel = new DottedTabPanel("465px", "200px");
    tabPanel.addTab(lastGroupsPanel);
    // tabPanel.addTab(lastActivityPanel);
    tabPanel.addTab(lastPublishedPanel);
    globalStats.removeFromParent();

    final RootPanel globalStatsParent = RootPanel.get(K_HOME_GLOBAL_STATS);
    final RootPanel groupStatsParent = RootPanel.get(K_HOME_GROUP_STATS);
    if (globalStatsParent != null) {
      globalStatsParent.add(globalStats);
    }
    if (groupStatsParent != null) {
      groupStatsParent.add(tabPanel);
    }
    armor.getHomeSpace().add(RootPanel.get("k-home-wrapper"));
  }

  @Override
  public Widget asWidget() {
    return widget;
  }

  private String format(final Long modifiedOn, final String name) {
    final String modOn = DateUtils.getInstance().formatPastDate(modifiedOn);
    return modOn + " ~ " + name;
  }

  @Override
  public HasText getGlobalStatsTotalGroupsCount() {
    return globalStatsTotalGroupsCount;
  }

  @Override
  public HasText getGlobalStatsTotalUsersCount() {
    return globalStatsTotalUsersCount;
  }

  @Override
  public void setLastContentsOfMyGroup(final List<ContentSimpleDTO> lastContentsOfMyGroupsList) {
    lastActivityInYourGroup.clear();
    for (final ContentSimpleDTO content : lastContentsOfMyGroupsList) {
      final GroupContentHomeLink link = linkProv.get();
      final StateToken token = content.getStateToken();
      link.setValues(downUtils.getLogoImageUrl(token.copy().clearDocument().clearFolder()),
          format(content.getModifiedOn(), content.getName()), token.toString());
      lastActivityInYourGroup.add(link);
    }
  }

  @Override
  public void setLastGroups(final List<GroupDTO> lastGroupsList) {
    lastGroups.clear();
    for (final GroupDTO group : lastGroupsList) {
      final GroupContentHomeLink link = linkProv.get();
      link.setValues(downUtils.getLogoImageUrl(group.getStateToken()),
          format(group.getCreatedOn(), group.getLongName()), group.getShortName());
      lastGroups.add(link);
    }
  }

  @Override
  public void setLastPublishedContents(final List<ContentSimpleDTO> lastPublishedContentsList) {
    lastPublishedContents.clear();
    for (final ContentSimpleDTO content : lastPublishedContentsList) {
      final GroupContentHomeLink link = linkProv.get();
      final StateToken token = content.getStateToken();
      link.setValues(downUtils.getLogoImageUrl(token.copy().clearDocument().clearFolder()),
          format(content.getModifiedOn(), content.getName()), token.toString());
      lastPublishedContents.add(link);
    }
  }

  @Override
  public void setStatsVisible(final boolean visible) {
    globalStats.setVisible(visible);
    tabPanel.setVisible(visible);
  }

  @Override
  public void setUserGroupsActivityVisible(final boolean visible) {
    final boolean isAttached = tabPanel.getWidgetIndex(lastActivityPanel) != -1;
    if (visible && !isAttached) {
      tabPanel.addTab(lastActivityPanel);
    } else if (!visible && isAttached) {
      tabPanel.removeTab(lastActivityPanel);
    }
  }

}