package org.ourproject.kune.docs.server;

import com.google.inject.Binder;
import com.google.inject.Module;

public class DocumentServerModule implements Module {
    public void configure(final Binder binder) {
	binder.bind(DocumentServerTool.class).asEagerSingleton();
    }
}