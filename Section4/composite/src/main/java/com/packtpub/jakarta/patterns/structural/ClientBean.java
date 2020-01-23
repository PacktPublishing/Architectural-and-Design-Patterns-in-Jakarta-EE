package com.packtpub.jakarta.patterns.structural;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ClientBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Item> items;
	private int activeIndex;

	@PostConstruct
	private void init() {
		activeIndex = 2;
		items = new ArrayList<>();
		Item item = new Item();
		item.setLinkText("Section 1");
		item.setHeaderText("Header - Section 1");
		item.setContentText("This is the content from section one");
		items.add(item);
		item = new Item();
		item.setLinkText("Section 2");
		item.setHeaderText("Header - Section 2");
		item.setContentText("This is the content from section two");
		items.add(item);
		item = new Item();
		item.setLinkText("Section 3");
		item.setHeaderText("Header - Section 3");
		item.setContentText("This is the content from section three");
		items.add(item);
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public List<Item> getItems() {
		return items;
	}

	public static class Item {
		private String headerText;
		private String contentText;
		private String linkText;

		public String getHeaderText() {
			return headerText;
		}

		public void setHeaderText(String headerText) {
			this.headerText = headerText;
		}

		public String getContentText() {
			return contentText;
		}

		public void setContentText(String contentText) {
			this.contentText = contentText;
		}

		public String getLinkText() {
			return linkText;
		}

		public void setLinkText(String linkText) {
			this.linkText = linkText;
		}

	}

}
