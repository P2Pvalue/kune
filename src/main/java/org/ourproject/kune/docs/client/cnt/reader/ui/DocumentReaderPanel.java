/*
 *
 * Copyright (C) 2007-2008 The kune development team (see CREDITS for details)
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

package org.ourproject.kune.docs.client.cnt.reader.ui;

import org.ourproject.kune.docs.client.cnt.reader.DocumentReaderView;
import org.ourproject.kune.workspace.client.skel.WorkspaceSkeleton;

import com.google.gwt.user.client.ui.HTML;

public class DocumentReaderPanel implements DocumentReaderView {

    private final WorkspaceSkeleton ws;

    public DocumentReaderPanel(final WorkspaceSkeleton ws) {
	this.ws = ws;
    }

    public void setContent(final String content) {
	final HTML html = new HTML(content);
	html.setStyleName("kune-Content-Main");
	html.addStyleName("kune-Margin-7-trbl");
	ws.getEntityWorkspace().setContent(html);
    }
}
