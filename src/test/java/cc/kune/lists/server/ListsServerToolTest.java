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
package cc.kune.lists.server;

import static cc.kune.lists.shared.ListsToolConstants.*;

import org.junit.Before;
import org.junit.Test;

import cc.kune.core.client.errors.ContainerNotPermittedException;
import cc.kune.core.client.errors.ContentNotPermittedException;

// TODO: Auto-generated Javadoc
/**
 * The Class ListsServerToolTest.
 * 
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
public class ListsServerToolTest { // extends PersistenceTest {

  /** The server tool. */
  private ListsServerTool serverTool;

  /**
   * Before.
   */
  @Before
  public void before() {
    serverTool = new ListsServerTool(null, null, null, null, null, null);
  }

  /**
   * Test create container in correct container.
   */
  @Test
  public void testCreateContainerInCorrectContainer() {
    serverTool.checkTypesBeforeContainerCreation(TYPE_ROOT, TYPE_LIST);
  }

  /**
   * Test create container in incorrect container7.
   */
  @Test(expected = ContainerNotPermittedException.class)
  public void testCreateContainerInIncorrectContainer7() {
    serverTool.checkTypesBeforeContainerCreation(TYPE_LIST, TYPE_LIST);
  }

  /**
   * Test create content in correct container.
   */
  @Test
  public void testCreateContentInCorrectContainer() {
    serverTool.checkTypesBeforeContentCreation(TYPE_LIST, TYPE_POST);
  }

  /**
   * Test create content in incorrect container1.
   */
  @Test(expected = ContentNotPermittedException.class)
  public void testCreateContentInIncorrectContainer1() {
    serverTool.checkTypesBeforeContentCreation(TYPE_ROOT, TYPE_POST);
  }

}
