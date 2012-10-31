/*
 *
 * Copyright (C) 2007-2012 The kune development team (see CREDITS for details)
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
package cc.kune.core.client.rpcservices;

import java.util.HashMap;
import java.util.List;

import cc.kune.core.shared.dto.I18nLanguageDTO;
import cc.kune.core.shared.dto.I18nTranslationDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface I18nServiceAsync {

  void getInitialLanguage(String localeParam, AsyncCallback<I18nLanguageDTO> callback);

  void getLexicon(String language, AsyncCallback<HashMap<String, String>> callback);

  void getTranslatedLexicon(String userHash, String language, String languageFrom, boolean toTranslate,
      AsyncCallback<List<I18nTranslationDTO>> callback);

  void getTranslation(String userHash, String language, String text, String noteForTranslators,
      AsyncCallback<String> callback);

  void setTranslation(String userHash, Long id, String translation, AsyncCallback<String> asyncCallback);

}
