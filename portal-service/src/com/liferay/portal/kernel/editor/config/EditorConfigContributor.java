/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.editor.config;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Map;

/**
 * Provides an interface responsible for setting the configuration and options
 * of the editor.
 *
 * <p>
 * Implementations of this class must be OSGi components that are registered in
 * the OSGi Registry.
 * </p>
 *
 * <p>
 * The configuration and options can be targeted for specific editors, based on
 * three different criteria: portlet name, editor config key, and
 * editor name. These criteria can be defined as OSGi properties with the
 * following names:
 * </p>
 *
 * <ul>
 * <li>
 * <code>javax.portlet.name</code>: The portlet name. If specified, the configuration and
 * options populated in the JSON object are applied to every editor used in that
 * portlet.
 * </li>
 * <li>
 * <code>editor.config.key</code>: The key used to identify the editor (the
 * <code>input-editor</code> taglib tag's <code>configKey</code>
 * attribute value). If specified, the configuration
 * and options populated in the JSON object are applied to every editor with the
 * specified <code>configKey</code>.
 * </li>
 * <li>
 * <code>editor.name</code>: The name of the editor (the
 * <code>input-editor</code> taglib tag's <code>editorName</code> attribute value:
 * <code>ckeditor</code>, <code>ckeditor_bbcode</code>, <code>alloyeditor</code>, etc.). If specified,
 * the configuration and options populated in the JSON object are applied to
 * every editor with that name.
 * </li>
 * </ul>
 *
 * <p>
 * In case there's more than one configuration,
 * they're prioritized by the following criteria combinations (the first
 * combination getting the highest priority):
 * </p>
 *
 * <ol>
 * <li>
 * portlet name, editor config key, editor name
 * </li>
 * <li>
 * portlet name, editor config key
 * </li>
 * <li>
 * editor config key, editor name
 * </li>
 * <li>
 * portlet name, editor name
 * </li>
 * <li>
 * editor config key
 * </li>
 * <li>
 * portlet name
 * </li>
 * <li>
 * editor name
 * </li>
 * </ol>
 *
 * <p>
 * If there are multiple configurations having the same criteria elements,
 * prioritization between them is based on service rank.
 * </p>
 *
 * @author Sergio González
 */
public interface EditorConfigContributor {

	/**
	 * Updates the original configuration JSON object to add some new
	 * configuration. It can even update or delete the original configuration,
	 * or any other configuration introduced by any other
	 * EditorConfigContributor.
	 *
	 * <p>
	 * The configuration object contains the configuration that will be directly
	 * used by the editor. Therefore, the config JSON object might be different
	 * for different editors.
	 * </p>
	 *
	 * @param jsonObject the original json object containing all the
	 *        configuration set by previous EditorConfigContributor modules.
	 * @param inputEditorTaglibAttributes the attributes specified to the input
	 *        taglib that is rendering the editor.
	 * @param themeDisplay the theme display
	 * @param liferayPortletResponse the liferay portlet response. Optionally
	 *        <code>null</code>. This should only be used to generate portlet
	 *        URLs.
	 */
	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		LiferayPortletResponse liferayPortletResponse);

	/**
	 * Updates the original options JSON object to add some new configuration.
	 * It can even update or delete the original options, or any other option
	 * introduced by any other EditorConfigContributor.
	 *
	 * <p>
	 * The options object is used by the input-editor taglib and it contains
	 * options to decide the behavior of the editor, but it's not directly used
	 * by the editor, so typically will be the same for all the editors.
	 * </p>
	 *
	 * @param jsonObject the original json object containing all the options set
	 *        by previous EditorConfigContributor modules.
	 * @param inputEditorTaglibAttributes the attributes specified to the input
	 *        taglib that is rendering the editor.
	 * @param themeDisplay the theme display
	 * @param liferayPortletResponse the liferay portlet response. Optionally
	 *        <code>null</code>. This should only be used to generate portlet
	 *        URLs.
	 */
	public void populateOptionsJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		LiferayPortletResponse liferayPortletResponse);

}