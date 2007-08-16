package org.ourproject.kune.platf.integration.content;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.ourproject.kune.platf.client.dto.AccessRightsDTO;
import org.ourproject.kune.platf.client.dto.ContainerDTO;
import org.ourproject.kune.platf.client.dto.StateToken;
import org.ourproject.kune.platf.integration.IntegrationTestHelper;
import org.ourproject.kune.workspace.client.dto.StateDTO;

import com.google.gwt.user.client.rpc.SerializableException;

public class ContentServiceAddTest extends ContentServiceIntegrationTest {

    private StateDTO defaultContent;
    String groupName;

    @Before
    public void init() throws SerializableException {
	new IntegrationTestHelper(this);
	groupName = getDefGroupName();
    }

    @Test
    public void testAddContent() throws SerializableException {
	doLogin();
	defaultContent = getDefaultContent();
	assertEquals(1, defaultContent.getFolder().getContents().size());
	AccessRightsDTO cntRights = defaultContent.getContentRights();
	AccessRightsDTO ctxRight = defaultContent.getFolderRights();

	String title = "New Content Title";
	StateDTO added = contentService.addContent(session.getHash(), defaultContent.getFolder().getId(), title);
	assertNotNull(added);
	List contents = added.getFolder().getContents();
	assertEquals(title, added.getTitle());
	assertEquals(2, contents.size());
	assertEquals(cntRights, added.getContentRights());
	assertEquals(ctxRight, added.getFolderRights());

	StateToken newState = added.getState();
	StateDTO sameAgain = contentService.getContent(session.getHash(), newState);
	assertNotNull(sameAgain);
	assertEquals(2, sameAgain.getFolder().getContents().size());
    }

    @Test
    public void testAddFolder() throws SerializableException {
	doLogin();
	defaultContent = getDefaultContent();
	ContainerDTO parent = defaultContent.getFolder();
	String title = "folder name";
	StateDTO newState = contentService.addFolder(session.getHash(), groupName, parent.getId(), title);
	assertNotNull(newState);
	ContainerDTO child = newState.getFolder();
	assertEquals(parent.getAbsolutePath() + ContainerDTO.SEP + title, child.getAbsolutePath());
	assertEquals(parent.getId(), child.getParentFolderId());

	ContainerDTO parentAgain = getDefaultContent().getFolder();
	assertEquals(parent.getId(), parentAgain.getId());
	// FIXME: aaaaarrggggggggg!!!
	// assertEquals(1, parentAgain.getChilds().size());

    }
}