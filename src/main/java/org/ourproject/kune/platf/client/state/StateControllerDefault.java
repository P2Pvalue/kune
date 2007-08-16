package org.ourproject.kune.platf.client.state;

import org.ourproject.kune.platf.client.app.Application;
import org.ourproject.kune.platf.client.dto.GroupDTO;
import org.ourproject.kune.platf.client.dto.StateToken;
import org.ourproject.kune.platf.client.tool.ClientTool;
import org.ourproject.kune.sitebar.client.Site;
import org.ourproject.kune.workspace.client.Workspace;
import org.ourproject.kune.workspace.client.dto.StateDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StateControllerDefault implements StateController {
    private final Application app;
    private final ApplicationState applicationState;
    private final ContentProvider provider;

    public StateControllerDefault(final ContentProvider provider, final Application app,
	    final ApplicationState applicationState) {
	this.provider = provider;
	this.app = app;
	this.applicationState = applicationState;
    }

    public void reload() {
	onHistoryChanged(History.getToken());
    }

    public void onHistoryChanged(final String historyToken) {
	GWT.log("State: " + historyToken, null);
	onHistoryChanged(new StateToken(historyToken));
    }

    private void onHistoryChanged(final StateToken newState) {
	Site.showProgress("cargando...");
	provider.getContent(applicationState.user, newState, new AsyncCallback() {
	    public void onFailure(final Throwable caught) {
		Site.hideProgress();
	    }

	    public void onSuccess(final Object result) {
		GWT.log("State response: " + result, null);
		loadContent((StateDTO) result);
	    }

	});
    }

    public void setState(final StateDTO content) {
	StateToken state = content.getState();
	provider.cache(state, content);
	setState(state);
    }

    public void setState(final StateToken state) {
	History.newItem(state.getEncoded());
    }

    private void loadContent(final StateDTO state) {
	applicationState.setCurrent(state);
	GroupDTO group = state.getGroup();
	app.setGroupState(group.getShortName());
	Workspace workspace = app.getWorkspace();
	workspace.showGroup(group);
	String toolName = state.getToolName();
	workspace.setTool(toolName);

	ClientTool clientTool = app.getTool(toolName);
	clientTool.setContent(state);
	workspace.setContent(clientTool.getContent());
	workspace.setContext(clientTool.getContext());
	Site.hideProgress();
    }

    public String getUser() {
	return applicationState.user;
    }

}
