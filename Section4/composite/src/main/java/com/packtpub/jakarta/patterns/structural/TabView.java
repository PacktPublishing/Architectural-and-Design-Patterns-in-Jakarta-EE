package com.packtpub.jakarta.patterns.structural;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

@FacesComponent("com.packtpub.jakarta.patterns.structural.TabView")
public class TabView extends UINamingContainer {

	enum PropertyKeys {
		active, linkText
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {

		TabComposite tabComposite = getComposite(this);
		int activeIndex = tabComposite.getActiveIndex();

		if (activeIndex == tabComposite.getTabIndexMap().get(this.getClientId())
				.intValue()) {
			setActive(true);
		} else {
			setActive(false);
		}

		super.encodeBegin(context);
	}

	private TabComposite getComposite(UIComponent component) {
		UIComponent parent = component.getParent();
		if (parent == null) {
			throw new RuntimeException(
					"TabView was not used inside a TabComposite!");
		}
		if (parent instanceof TabComposite) {
			return (TabComposite) parent;
		}
		return getComposite(parent);
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
