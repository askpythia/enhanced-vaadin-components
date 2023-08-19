package at.metainfo.utilities;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IconFactory;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

import at.metainfo.icons.EvaIcon;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;

public interface IGuiUtilities {
	public static final String DEFAULT_ICON_SIZE = "20px";
	public static final String DEFAULT_ICON_COLOR = "#99000080";

	default VerticalLayout vl(Component... components) {
		VerticalLayout layout = new VerticalLayout(components);
		layout.setMargin(false);
		layout.setPadding(false);
		layout.setSizeFull();
		return layout;
	}

	default HorizontalLayout hl(Component... components) {
		HorizontalLayout layout = new HorizontalLayout(components);
		layout.setMargin(false);
		layout.setPadding(false);
		layout.setWidthFull();
		return layout;
	}

	default SplitLayout vs(Component primaryComponent, Component secondaryComponent) {
		SplitLayout layout = new SplitLayout(primaryComponent, secondaryComponent);
		layout.setOrientation(Orientation.VERTICAL);
		layout.setSizeFull();
		return layout;
	}

	default SplitLayout hs(Component primaryComponent, Component secondaryComponent) {
		SplitLayout layout = new SplitLayout(primaryComponent, secondaryComponent);
		layout.setOrientation(Orientation.HORIZONTAL);
		layout.setSizeFull();
		return layout;
	}

	default HorizontalLayout toolbar(Component... components) {
		HorizontalLayout layout = new HorizontalLayout(components);
		layout.setMargin(false);
		layout.setPadding(false);
		layout.setAlignItems(Alignment.CENTER);
		return layout;
	}

	default	Component icon(VaadinIcon vaadinIcon) {
		return icon(vaadinIcon, null);
	}

	default	Component icon(IconFactory iconFactory, ComponentEventListener<ClickEvent<Icon>> clickEventHandler) {
		Icon icon = iconFactory.create();
		icon.setColor(DEFAULT_ICON_COLOR);
		icon.setSize(DEFAULT_ICON_SIZE);
		if(clickEventHandler != null) icon.addClickListener(clickEventHandler);
		return icon;
	}

	default	Component icon(EvaIcon evaIcon, ComponentEventListener<ClickEvent<Span>> clickEventHandler) {
		evaIcon.color(DEFAULT_ICON_COLOR);
		evaIcon.size(DEFAULT_ICON_SIZE);
		if(clickEventHandler != null) evaIcon.addClickListener(clickEventHandler);
		return evaIcon;
	}
}
