package org.ourproject.kune.platf.server.manager;

import org.ourproject.kune.platf.server.domain.AccessLists;
import org.ourproject.kune.platf.server.domain.Group;
import org.ourproject.kune.platf.server.model.AccessRights;

public interface AccessRightsManager {
    public AccessRights get(final Group userGroup, final AccessLists accessList);
}
