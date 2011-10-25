package com.gwtmobile.ui.client.widgets;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class ToolbarHeader extends FlowPanel {
	
	private Image headerImage;
	private ImageResource headerImageRes;
	private ImageResource highlightImageRes;
	private Label headerText;
	
	public @UiConstructor ToolbarHeader(String headerText, ImageResource headerImage, ImageResource highlightImage) {
		assert headerImage != null : "Header image must be set for ToolbarHeader";
		this.headerImageRes = headerImage;
		this.headerImage = new Image(headerImage);
		Image.prefetch(headerImage.getSafeUri()); // prevent flicker of images on initial display
		add(this.headerImage);
		this.highlightImageRes = highlightImage;
		if (highlightImage != null) {
			Image.prefetch(highlightImage.getSafeUri()); // prevent flicker of images on initial display
		}
		if (headerText != null) {
			this.headerText = new Label(headerText);
			add(this.headerText);
		}
	}
			
	public void selectImage(boolean isHighlighted) {
		if (isHighlighted && (highlightImageRes != null)) {
			headerImage.setResource(highlightImageRes);
		} else if (headerImageRes != null) {
			headerImage.setResource(headerImageRes);
		}
	}
	
}
