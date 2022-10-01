package at.metainfo.tabbedView;

import com.vaadin.flow.component.Component;

import at.metainfo.utilities.IGuiUtilities;

public interface IEnhancedView extends IGuiUtilities {

	default String title() {
		return object() == null ? null : object().getClass().getSimpleName();
	}

	default Object titleIcon() {
		return null;
	}

	default Object object() {
		return this;
	}

	default boolean close(Closeable closeable) {
		return false;
	}

	default void resize() {
	}

	default void reset() {
	}

	default Component createContent() {
		return this instanceof Component ? (Component)this : null;
	}

	default Component createToolbar() {
		return null;
	}

	default Component createHeader() {
		return null;
	}

	default Component createFooter() {
		return null;
	}
}
