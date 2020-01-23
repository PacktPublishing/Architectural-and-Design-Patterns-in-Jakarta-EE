package com.packtpub.jakarta.patterns.structural;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

@FacesComponent("com.packtpub.jakarta.patterns.structural.TogglePanel")
@ResourceDependency(library = "bl", name = "css/togglepanel.css", target = "head")
public class TogglePanel extends UINamingContainer {

	private static final String FORM_ID = "tpForm";
	private static final String BUTTONS_PANEL_ID = "tpBtns";
	private static final String TABS_PANEL_ID = "tpTabs";

	enum PropertyKeys {
		activeIndex, ajaxEnabled, tabIndexMap
	}

	@Override
	public void encodeBegin(final FacesContext context) throws IOException {

		final UIComponent buttonPanel = findComponent(FORM_ID).findComponent(
				BUTTONS_PANEL_ID);
		final Map<String, Integer> tabIndexMap = getTabIndexMap();

		buttonPanel.getChildren().clear();
		tabIndexMap.clear();

		VisitCallback callback = new VisitCallback() {

			private int tabIndex = 1;

			@Override
			public VisitResult visit(VisitContext visitContext,
					UIComponent component) {

				if (component instanceof TogglePanelView) {

					HtmlCommandLink button = (HtmlCommandLink) context
							.getApplication().createComponent(
									HtmlCommandLink.COMPONENT_TYPE);

					tabIndexMap.put(component.getClientId(), tabIndex);

					if (getAjaxEnabled()) {
						button.setId("tbBtn" + tabIndex);
						AjaxBehavior ajax = new AjaxBehavior();
						List<String> executes = new LinkedList<String>();
						List<String> renders = new LinkedList<String>();
						executes.add("@form");
						ajax.setExecute(executes);
						renders.add("@form");
						renders.add(getSeparatorChar(context)
								+ TogglePanel.this.findComponent(TABS_PANEL_ID)
										.getClientId());
						ajax.setRender(renders);
						button.addClientBehavior(button.getDefaultEventName(),
								ajax);
					}

					button.setValue(((TogglePanelView) component)
							.getLinkText());
					button.setActionExpression(context
							.getApplication()
							.getExpressionFactory()
							.createMethodExpression(context.getELContext(),
									"#{cc.setActiveIndex(" + tabIndex + ")}",
									String.class, new Class<?>[] {}));

					buttonPanel.getChildren().add(button);

					HtmlOutputText separator = (HtmlOutputText) context
							.getApplication().createComponent(
									HtmlOutputText.COMPONENT_TYPE);
					separator.setValue("-");
					buttonPanel.getChildren().add(separator);

					tabIndex++;
					return VisitResult.REJECT;
				}
				return VisitResult.ACCEPT;
			}
		};

		this.visitTree(VisitContext.createVisitContext(context), callback);

		setTabIndexMap(tabIndexMap);

		// Remove last separator
		buttonPanel.getChildren().remove(buttonPanel.getChildren().size() - 1);

		super.encodeBegin(context);
	}

	public int getActiveIndex() {
		return (Integer) getStateHelper().eval(PropertyKeys.activeIndex, 1);
	}

	public void setActiveIndex(int activeIndex) {
		getStateHelper().put(PropertyKeys.activeIndex, activeIndex);
		ELContext ctx = FacesContext.getCurrentInstance().getELContext();
		ValueExpression ve = getValueExpression(PropertyKeys.activeIndex.name());
		if (ve != null) {
			ve.setValue(ctx, activeIndex);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Integer> getTabIndexMap() {
		return (Map<String, Integer>) getStateHelper().eval(
				PropertyKeys.tabIndexMap, new HashMap<>());
	}

	private void setTabIndexMap(Map<String, Integer> tabIndexMap) {
		getStateHelper().put(PropertyKeys.tabIndexMap, tabIndexMap);
	}

	public boolean getAjaxEnabled() {
		return (Boolean) getStateHelper().eval(PropertyKeys.ajaxEnabled, false);
	}

	public void setAjaxEnabled(boolean ajaxEnabled) {
		getStateHelper().put(PropertyKeys.ajaxEnabled, ajaxEnabled);
	}

	public String getButtonsPanelId() {
		return BUTTONS_PANEL_ID;
	}

	public String getFormId() {
		return FORM_ID;
	}

	public String getTabsPanelId() {
		return TABS_PANEL_ID;
	}

}
