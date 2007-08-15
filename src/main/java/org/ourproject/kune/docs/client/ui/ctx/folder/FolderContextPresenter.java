package org.ourproject.kune.docs.client.ui.ctx.folder;

import java.util.List;

import org.ourproject.kune.platf.client.View;
import org.ourproject.kune.platf.client.dto.AccessRightsDTO;
import org.ourproject.kune.platf.client.dto.ContentDescriptorDTO;
import org.ourproject.kune.platf.client.dto.FolderDTO;
import org.ourproject.kune.platf.client.dto.StateToken;

public class FolderContextPresenter implements FolderContext {
    private final FolderContentView view;

    public FolderContextPresenter(final FolderContentView view) {
	this.view = view;
    }

    public View getView() {
	return view;
    }

    public void setFolder(final StateToken state, final FolderDTO folder, final AccessRightsDTO rights) {
	state.setDocument(null);
	view.setCurrentName(folder.getName());
	view.clear();
	List folders = folder.getChilds();
	for (int index = 0; index < folders.size(); index++) {
	    FolderDTO child = (FolderDTO) folders.get(index);
	    state.setFolder(child.getId().toString());
	    view.add(child.getName(), "folder", state.getEncoded());
	}

	state.setFolder(folder.getId().toString());
	List contents = folder.getContents();
	for (int index = 0; index < contents.size(); index++) {
	    ContentDescriptorDTO dto = (ContentDescriptorDTO) contents.get(index);
	    state.setDocument(dto.getId().toString());
	    view.add(dto.getTitle(), "file", state.getEncoded());
	}

	view.setParentButtonVisible(folder.getParentFolderId() != null);
	view.setAddControlsVisibles(rights.isEditable, rights.isEditable);
    }
}
