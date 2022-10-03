package at.metainfo.enhanced;

import java.util.function.Function;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import at.metainfo.utilities.NlsLabel;

@CssImport(value = "./styles/enhanced-dialog-styles.css", themeFor = "vaadin-dialog-overlay")
@SuppressWarnings("serial")
public class EnhancedDialog extends Dialog implements IEnhancedViewContainer, HasIconProvider {

	private EnhancedViewData data;

	public EnhancedDialog(IEnhancedView view) {
		addThemeName("meta");
		setDraggable(true);
		setResizable(true);
		
		setCloseOnOutsideClick(false);
		addView(view);
	}

	@Override
	public void addView(IEnhancedView view) {
		if(data != null) {
			if(data.getView() == view) {
				return;
			} else {
				data.getView().close(null);
				removeAll();
				getHeader().removeAll();
				getFooter().removeAll();
			}
		}
		data = new EnhancedViewData(view);
		HorizontalLayout toolbar = toolbar();
		HorizontalLayout title = hl(data.getTitleIcon(), new NlsLabel(data.getTitle()), toolbar);
		title.addClassNames("draggable");
		if(view.isCloseable()) {
			setCloseOnEsc(false);
			Component close = getIcon(ViewIcon.tabCloseIcon);
			close.getElement().addEventListener("click", click -> {
				data.close(() -> close());
			});
			close.getElement().getStyle().set("margin-left", "auto");
			title.add(close);
		} else {
			setCloseOnEsc(true);
		}
		add(data.content());
		VerticalLayout header = vl(title);
		Component viewHeader = data.header();
		if(viewHeader != null) header.add(header);
		getHeader().add(header);
		Component footer = data.footer();
		if(footer != null) getFooter().add(footer);
	}

	@Override
	public Function<ViewIcon, Component> iconProvider() {
		return null;
	}
}
