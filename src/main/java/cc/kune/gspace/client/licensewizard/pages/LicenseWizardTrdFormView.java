/*
 *
 * Copyright (C) 2007-2014 Licensed to the Comunes Association (CA) under
 * one or more contributor license agreements (see COPYRIGHT for details).
 * The CA licenses this file to you under the GNU Affero General Public
 * License version 3, (the "License"); you may not use this file except in
 * compliance with the License. This file is part of kune.
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
package cc.kune.gspace.client.licensewizard.pages;

import cc.kune.common.shared.utils.SimpleCallback;

import com.google.gwt.user.client.ui.IsWidget;

// TODO: Auto-generated Javadoc
/**
 * The Interface LicenseWizardTrdFormView.
 * 
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
public interface LicenseWizardTrdFormView extends IsWidget {

  /**
   * Checks if is allow comercial.
   * 
   * @return true, if is allow comercial
   */
  boolean isAllowComercial();

  /**
   * Checks if is allow modif.
   * 
   * @return true, if is allow modif
   */
  boolean isAllowModif();

  /**
   * Checks if is allow modif share alike.
   * 
   * @return true, if is allow modif share alike
   */
  boolean isAllowModifShareAlike();

  /**
   * On change.
   * 
   * @param callback
   *          the callback
   */
  void onChange(SimpleCallback callback);

  /**
   * Reset.
   */
  void reset();

  /**
   * Sets the flags.
   * 
   * @param isCopyleft
   *          the is copyleft
   * @param isAppropiateForCulturalWorks
   *          the is appropiate for cultural works
   * @param isNonComercial
   *          the is non comercial
   */
  void setFlags(boolean isCopyleft, boolean isAppropiateForCulturalWorks, boolean isNonComercial);
}
