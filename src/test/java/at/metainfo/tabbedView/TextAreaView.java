package at.metainfo.tabbedView;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextArea;

import at.metainfo.color.Css3Color;
import at.metainfo.utilities.IGuiUtilities;

public class TextAreaView implements IEnhancedView, IGuiUtilities {

	private TextArea textArea;
	private Css3Color color;
	private Faker faker;

	public TextAreaView(Css3Color color, Locale locale) {
		this.textArea = new TextArea();
		this.color = color;
		this.faker = new Faker(locale);

		if(color != null) { 
			textArea.getStyle().set("color", color.matchingTextColor().hexCode());
			textArea.getStyle().set("background-color", color.hexCode());
		}
		textArea.setValue(faker.chuckNorris().fact() + "\n");
		textArea.setSizeFull();
	}

	@Override
	public String title() {
		return color.name() + " Text Area";
	}

	@Override
	public Object titleIcon() {
		return icon(VaadinIcon.TEXT_INPUT);
	}

	@Override
	public boolean close(Closeable closeable) {
		if(closeable != null) {
			new ConfirmDialog("Close " + title(), "Do you want to close the " + title() + "?", "Close", event -> {
				closeable.close();
			}).open();
		}
		return true;
	}

	@Override
	public Component createToolbar() {
		return toolbar(
			icon(VaadinIcon.EDIT, click -> textArea.setValue(textArea.getValue() + faker.chuckNorris().fact() + "\n")),
			icon(VaadinIcon.ERASER, click -> textArea.clear())
		);
	}

	@Override
	public Component createContent() {
		return textArea;
	}
}
