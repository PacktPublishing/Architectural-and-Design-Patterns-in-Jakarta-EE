package com.packtpub.jakarta.patterns.structural;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

@FacesComponent("com.packtpub.jakarta.patterns.structural.TogglePanelView")
public class TogglePanelView extends UINamingContainer {

	enum PropertyKeys {
		active, linkText
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {

		TogglePanel togglePanel = getContainingTogglePanel(this);
		int activeIndex = togglePanel.getActiveIndex();

		if (activeIndex == togglePanel.getTabIndexMap().get(this.getClientId())
				.intValue()) {
			setActive(true);
		} else {
			setActive(false);
		}

		super.encodeBegin(context);
	}

	private TogglePanel getContainingTogglePanel(UIComponent component) {
		UIComponent parent = component.getParent();
		if (parent == null) {
			throw new RuntimeException(
					"TogglePanelView was not used inside a TogglePanel!");
		}
		if (parent instanceof TogglePanel) {
			return (TogglePanel) parent;
		}
		return getContainingTogglePanel(parent);
	}

	public boolean getActive() {
		return (Boolean) getStateHelper().eval(PropertyKeys.active, true);
	}

	public void setActive(boolean active) {
		getStateHelper().put(PropertyKeys.active, active);
	}

	public String getLinkText() {
		return (String) getStateHelper().eval(PropertyKeys.linkText);
	}

	public void setLinkText(String linkText) {
		getStateHelper().put(PropertyKeys.linkText, linkText);
	}

}
