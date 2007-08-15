package org.ourproject.kune.platf.client.state;

import org.ourproject.kune.platf.client.app.Application;
import org.ourproject.kune.platf.client.dto.StateToken;
import org.ourproject.kune.platf.client.tool.Tool;
import org.ourproject.kune.sitebar.client.Site;
import org.ourproject.kune.workspace.client.Workspace;
import org.ourproject.kune.workspace.client.dto.ContentDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StateControllerDefault implements StateController {
    private final Application app;
    private final State state;
    private final ContentProvider provider;

    public StateControllerDefault(final ContentProvider provider, final Application app, final State state) {
	this.provider = provider;
	this.app = app;
	this.state = state;
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
	provider.getContent(state.user, newState, new AsyncCallback() {
	    public void onFailure(final Throwable caught) {
		Site.hideProgress();
	    }

	    public void onSuccess(final Object result) {
		GWT.log("State response: " + result, null);
		loadContent((ContentDTO) result);
	    }

	});
    }

    public void setState(final ContentDTO content) {
	StateToken state = content.getState();
	provider.cache(state, content);
	setState(state);
    }

    public void setState(final StateToken state) {
	History.newItem(state.getEncoded());
    }

    private void loadContent(final ContentDTO content) {
	Workspace workspace = app.getWorkspace();
	workspace.showGroup(content.getGroup());
	String toolName = content.getToolName();
	workspace.setTool(toolName);

	Tool tool = app.getTool(toolName);
	tool.setContent(content);
	workspace.setContent(tool.getContent());
	workspace.setContext(tool.getContext());
	Site.hideProgress();
    }

    public String getUser() {
	return state.user;
    }

}
