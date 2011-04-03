/*
 *
 * Copyright (C) 2007-2011 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package cc.kune.core.server.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpServletRequestMocked implements HttpServletRequest {

    public Object getAttribute(final String arg0) {
        return null;
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getAttributeNames() {
        return null;
    }

    public String getAuthType() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public int getContentLength() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public String getContextPath() {
        return null;
    }

    // @PMD:REVIEWED:ReturnEmptyArrayRatherThanNull: by vjrj on 21/05/09 15:17
    public Cookie[] getCookies() {
        return null;
    }

    public long getDateHeader(final String arg0) {
        return 0;
    }

    public String getHeader(final String arg0) {
        return null;
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getHeaderNames() {
        return null;
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getHeaders(final String arg0) {
        return null;
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    public int getIntHeader(final String arg0) {
        return 0;
    }

    public String getLocalAddr() {
        return null;
    }

    public Locale getLocale() {
        return null;
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getLocales() {
        return null;
    }

    public String getLocalName() {
        return null;
    }

    public int getLocalPort() {
        return 0;
    }

    public String getMethod() {
        return null;
    }

    public String getParameter(final String arg0) {
        return null;
    }

    @SuppressWarnings("rawtypes")
    public Map getParameterMap() {
        return null;
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getParameterNames() {
        return null;
    }

    // @PMD:REVIEWED:ReturnEmptyArrayRatherThanNull: by vjrj on 21/05/09 15:17
    public String[] getParameterValues(final String arg0) {
        return null;
    }

    public String getPathInfo() {
        return null;
    }

    public String getPathTranslated() {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public String getQueryString() {
        return null;
    }

    public BufferedReader getReader() throws IOException {
        return null;
    }

    public String getRealPath(final String arg0) {
        return null;
    }

    public String getRemoteAddr() {
        return null;
    }

    public String getRemoteHost() {
        return null;
    }

    public int getRemotePort() {
        return 0;
    }

    public String getRemoteUser() {
        return null;
    }

    public RequestDispatcher getRequestDispatcher(final String arg0) {
        return null;
    }

    public String getRequestedSessionId() {
        return null;
    }

    public String getRequestURI() {
        return null;
    }

    public StringBuffer getRequestURL() {
        return null;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public String getServletPath() {
        return null;
    }

    public HttpSession getSession() {
        return null;
    }

    public HttpSession getSession(final boolean arg0) {
        return null;
    }

    public Principal getUserPrincipal() {
        return null;
    }

    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    public boolean isRequestedSessionIdFromUrl() {
        return false;
    }

    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    public boolean isRequestedSessionIdValid() {
        return false;
    }

    public boolean isSecure() {
        return false;
    }

    public boolean isUserInRole(final String arg0) {
        return false;
    }

    public void removeAttribute(final String arg0) {

    }

    public void setAttribute(final String arg0, final Object arg1) {

    }

    public void setCharacterEncoding(final String arg0) throws UnsupportedEncodingException {

    }

}