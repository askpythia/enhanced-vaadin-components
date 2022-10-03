package at.metainfo.tabbedView;

import static at.metainfo.color.Css3Color.*;

import java.util.Locale;
import java.util.function.Function;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;

import at.metainfo.color.Css3Color;
import at.metainfo.enhanced.EnhancedDialog;
import at.metainfo.enhanced.HasIconProvider.ViewIcon;
import at.metainfo.enhanced.TabbedView;
import at.metainfo.utilities.IGuiUtilities;

@SuppressWarnings("serial")
@Route("")
public class TabbedViewSample extends Div implements IGuiUtilities {

	public TabbedViewSample() {
		setSizeFull();

		TabbedView tv1 = createTabbedView(Red/*, Green, Blue*/);
		TabbedView tv2 = createTabbedView(Yellow/*, Cyan, Magenta*/);
		TabbedView tv3 = createTabbedView(Locale.ENGLISH, /*Black,*/ White);
		TabbedView tv4 = createTabbedView(Locale.ENGLISH);
		tv4.addView(new TerminalView());

		tv1.getStyle().set("overflow", "hidden");
		tv2.getStyle().set("overflow", "hidden");
		tv3.getStyle().set("overflow", "hidden");
		tv4.getStyle().set("overflow", "hidden");

		SplitLayout vs1 = vs(tv1, tv2);
		SplitLayout vs2 = vs(tv3, tv4);
		SplitLayout hs = hs(vs1,vs2);

		vs1.addSplitterDragendListener(event -> {
			tv1.reset();
			tv2.reset();
		});
		vs2.addSplitterDragendListener(event -> {
			tv3.resize();
			tv4.resize();
		});
		hs.addSplitterDragendListener(event -> {
			tv1.resize();
			tv2.resize();
			tv3.resize();
			tv4.resize();
		});

		add(hs);
	}

	private TabbedView createTabbedView(Css3Color... colors) {
		return createTabbedView(Locale.forLanguageTag(Locale.getDefault().getLanguage()), colors);
	}

	private TabbedView createTabbedView(Locale locale, Css3Color... colors) {
		TabbedView tabbedView = new TabbedView(locale == Locale.ENGLISH ? getIconProvider() : null);
		tabbedView.setSizeFull();
		tabbedView.setDraggable(true);
		tabbedView.setGlobalTarget(true);

		for(Css3Color color : colors) {
			tabbedView.addView(new TextAreaView(color, locale));
		}

		ComboBox<Css3Color> colorSelection = new ComboBox<>();
		colorSelection.setItems(Css3Color.values());
		colorSelection.setPlaceholder("Select color for new Tab");
		colorSelection.setWidth("14em");
		Button add = new Button("Add", click -> {
			if(colorSelection.isEmpty()) {
				Notification.show("Select a background color first!", 2000, Position.MIDDLE);
			} else {
				TextAreaView view = new TextAreaView(colorSelection.getValue(), locale);
				tabbedView.addView(view);
				tabbedView.selectTab(view);
				colorSelection.clear();
			}
		});
		Button term = new Button("Add Terminal", click -> {
			TerminalView terminal = new TerminalView();
			tabbedView.addView(terminal);
			tabbedView.selectTab(terminal);
		});
		Button dialog = new Button("Dialog", click -> {
			new EnhancedDialog(new TextAreaView(Cyan, Locale.ENGLISH)).open();
		});
		tabbedView.addToToolbar(colorSelection, add, term, dialog);
		tabbedView.setMinimizeable(true);

		return tabbedView;
	}

	private Function<ViewIcon, Component> getIconProvider() {
		return key -> {
			switch(key) {
			case tabsMinimizeIcon:
				return VaadinIcon.CARET_SQUARE_UP_O.create();
			case tabsMaximizeIcon:
				return VaadinIcon.CARET_SQUARE_DOWN_O.create();
			default:
				return null;
			}
		};
	}
}
