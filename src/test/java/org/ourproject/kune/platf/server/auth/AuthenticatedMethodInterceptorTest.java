package org.ourproject.kune.platf.server.auth;

import java.lang.reflect.AccessibleObject;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.ourproject.kune.platf.integration.IntegrationTest;
import org.ourproject.kune.platf.integration.IntegrationTestHelper;

import cc.kune.core.client.errors.SessionExpiredException;
import cc.kune.core.client.errors.UserMustBeLoggedException;

public class AuthenticatedMethodInterceptorTest extends IntegrationTest {

    private AuthenticatedMethodInterceptor auth;
    private MethodInvocation invocation;
    private Authenticated authAnnotation;

    @Before
    public void before() {
        auth = new AuthenticatedMethodInterceptor();
        new IntegrationTestHelper(auth, this);
        invocation = Mockito.mock(MethodInvocation.class);
        final AccessibleObject accessibleObject = Mockito.mock(AccessibleObject.class);
        Mockito.when(invocation.getMethod()).thenReturn(this.getClass().getMethods()[0]);
        Mockito.when(invocation.getStaticPart()).thenReturn(accessibleObject);
        authAnnotation = Mockito.mock(Authenticated.class);
        Mockito.when(accessibleObject.getAnnotation(Authenticated.class)).thenReturn(authAnnotation);
    }

    @Test(expected = UserMustBeLoggedException.class)
    public void hashNullAndMandatoryMustDoNothing() throws Throwable {
        Mockito.when(authAnnotation.mandatory()).thenReturn(true);
        final Object[] arguments = { null };
        Mockito.when(invocation.getArguments()).thenReturn(arguments);
        auth.invoke(invocation);
    }

    @Test
    public void hashNullAndNotMandatoryMustDoNothing() throws Throwable {
        Mockito.when(authAnnotation.mandatory()).thenReturn(false);
        final Object[] arguments = { null };
        Mockito.when(invocation.getArguments()).thenReturn(arguments);
        auth.invoke(invocation);
    }

    @Test
    public void hashNullAsStringAndNotMandatoryMustDoNothing() throws Throwable {
        Mockito.when(authAnnotation.mandatory()).thenReturn(false);
        final Object[] arguments = { "null" };
        Mockito.when(invocation.getArguments()).thenReturn(arguments);
        auth.invoke(invocation);
    }

    @Test(expected = SessionExpiredException.class)
    public void otherHashAndMandatoryAndLoggedMustSessionExp() throws Throwable {
        doLogin();
        Mockito.when(authAnnotation.mandatory()).thenReturn(true);
        final Object[] arguments = { "other-hash" };
        Mockito.when(invocation.getArguments()).thenReturn(arguments);
        auth.invoke(invocation);
    }

    @Test
    public void sameHashAndMandatoryAndLoggedMustSessionExp() throws Throwable {
        doLogin();
        Mockito.when(authAnnotation.mandatory()).thenReturn(true);
        final Object[] arguments = { getHash() };
        Mockito.when(invocation.getArguments()).thenReturn(arguments);
        auth.invoke(invocation);
    }

    @Test(expected = SessionExpiredException.class)
    public void someHashAndMandatoryAndNotLoggedMustSessionExp() throws Throwable {
        Mockito.when(authAnnotation.mandatory()).thenReturn(true);
        final Object[] arguments = { "some-hash" };
        Mockito.when(invocation.getArguments()).thenReturn(arguments);
        auth.invoke(invocation);
    }
}
