package org.ourproject.kune.app.server;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ourproject.kune.app.server.servlet.Application;
import org.ourproject.kune.app.server.servlet.ApplicationBuilder;
import org.ourproject.kune.app.server.servlet.ApplicationListener;
import org.ourproject.kune.docs.server.KuneDocumentModule;
import org.ourproject.kune.platf.client.rpc.ContentService;
import org.ourproject.kune.platf.client.rpc.KuneService;
import org.ourproject.kune.platf.server.KunePlatformModule;
import org.ourproject.kune.platf.server.LoggerMethodInterceptor;
import org.ourproject.kune.platf.server.properties.PropertiesFileName;
import org.ourproject.kune.sitebar.client.rpc.SiteBarService;

import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.SessionScoped;
import com.wideplay.warp.jpa.JpaUnit;

public class KuneApp {
    private final String jpaUnit;
    private final String propertiesFileName;
    private final Scope sessionScope;

    public KuneApp(final String jpaUnit, final String propertiesFileName, final Scope sessionScope) {
	this.jpaUnit = jpaUnit;
	this.propertiesFileName = propertiesFileName;
	this.sessionScope = sessionScope;
    }

    public void configure(final ApplicationBuilder builder) {
	builder.use(new KunePlatformModule());
	builder.use(new KuneDocumentModule());

	builder.use(new AbstractModule() {
	    public void configure() {
		bindInterceptor(Matchers.any(), new NotInObject(), new LoggerMethodInterceptor());
		bindScope(SessionScoped.class, sessionScope);
		bindConstant().annotatedWith(JpaUnit.class).to(jpaUnit);
		bindConstant().annotatedWith(PropertiesFileName.class).to(propertiesFileName);
	    }
	});

	Application app = builder.create("kune", "Kune.html", "gwt/org.ourproject.kune.app.Kune");
	app.useService("KuneService", KuneService.class);
	app.useService("ContentService", ContentService.class);
	app.useService("SiteBarService", SiteBarService.class);
	app.with(new ApplicationListener() {
	    public void onApplicationStart(final HttpServletRequest request, final HttpServletResponse response) {
		String userHash = request.getSession().getId();
		response.addCookie(new Cookie("userHash", userHash));
	    }
	});
	app.add(new KuneLifeCycleListener());

    }

}