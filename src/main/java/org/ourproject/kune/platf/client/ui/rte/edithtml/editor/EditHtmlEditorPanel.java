package org.ourproject.kune.platf.client.ui.rte.edithtml.editor;

import org.ourproject.kune.platf.client.ui.dialogs.DefaultForm;
import org.ourproject.kune.platf.client.ui.rte.edithtml.EditHtmlDialogView;

import cc.kune.core.client.i18n.I18nUITranslationService;

import com.gwtext.client.widgets.form.TextArea;

public class EditHtmlEditorPanel extends DefaultForm implements EditHtmlEditorView {

    public static final String HTML_FIELD = "ehtp-html-f";
    private final TextArea editorField;

    public EditHtmlEditorPanel(I18nUITranslationService i18n, final EditHtmlEditorPresenter presenter) {
        super("HTML");
        super.setAutoWidth(true);
        super.setHideLabels(true);
        super.setHeight(EditHtmlDialogView.HEIGHT - 20);
        editorField = new TextArea();
        editorField.setHeight(EditHtmlDialogView.HEIGHT - 70);
        editorField.setWidth("98%");
        editorField.setTabIndex(4);
        editorField.setName(HTML_FIELD);
        editorField.setId(HTML_FIELD);
        add(editorField);
    }

    public String getHtml() {
        return editorField.getRawValue();
    }

    public void setHtml(String html) {
        editorField.setValue(html);
    }
}
