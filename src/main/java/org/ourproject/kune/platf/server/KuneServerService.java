package org.ourproject.kune.platf.server;

import java.util.List;

import org.ourproject.kune.platf.client.dto.GroupDTO;
import org.ourproject.kune.platf.client.rpc.KuneService;
import org.ourproject.kune.platf.server.domain.User;
import org.ourproject.kune.platf.server.mapper.Mapper;

import com.google.gwt.user.client.rpc.SerializableException;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class KuneServerService implements KuneService {
    private Mapper mapper;
    private UserSession userSession;

    public GroupDTO getDefaultGroup(final String userHash) {
	User user = userSession.getUser();
	return mapper.map(user.getUserGroup(), GroupDTO.class);
    }

    @Inject
    public void setUserSession(final UserSession userSession) {
	this.userSession = userSession;
    }

    public void createNewGroup(final GroupDTO group) throws SerializableException {
    }

    public List getAllLicenses() throws SerializableException {
	// TODO Auto-generated method stub
	return null;
    }

    public List getNotCCLicenses() throws SerializableException {
	// TODO Auto-generated method stub
	return null;
    }

    @Inject
    public void setMapper(final Mapper mapper) {
	this.mapper = mapper;
    }

}