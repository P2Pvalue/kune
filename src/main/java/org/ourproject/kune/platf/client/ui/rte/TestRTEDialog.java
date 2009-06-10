package org.ourproject.kune.platf.client.ui.rte;

import org.ourproject.kune.platf.client.actions.ui.ComplexToolbar;
import org.ourproject.kune.platf.client.ui.dialogs.BasicDialog;
import org.ourproject.kune.platf.client.ui.rte.basic.RTEditorNew;
import org.ourproject.kune.platf.client.ui.rte.basic.RTEditorPanelNew;
import org.ourproject.kune.platf.client.ui.rte.saving.RTESavingEditor;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.WindowListenerAdapter;

public class TestRTEDialog {

    private final RTESavingEditor editor;
    private final BasicDialog dialog;

    public TestRTEDialog(final RTESavingEditor editor) {
        this.editor = editor;
        dialog = new BasicDialog("Testing RTE", false, false, 650, 200);
        final VerticalPanel vp = new VerticalPanel();

        final RTEditorNew basicEditor = editor.getBasicEditor();
        vp.add((ComplexToolbar) basicEditor.getTopBar());
        vp.add((ComplexToolbar) basicEditor.getSndBar());
        final RTEditorPanelNew editorPanel = (RTEditorPanelNew) basicEditor.getEditorArea();
        vp.add(editorPanel);
        vp.setWidth("100%");
        dialog.add(vp);
        dialog.addListener(new WindowListenerAdapter() {
            @Override
            public void onResize(final Window source, final int width, final int height) {
                final int newHeight = height - 26 * 2 - 40;
                editorPanel.adjustSize(newHeight);
                vp.setCellHeight(editorPanel, "" + newHeight);
            }
        });
    }

    public void setExtended(final boolean extended) {
        editor.getBasicEditor().setExtended(extended);
    }

    public void show() {
        editor.getBasicEditor().attach();
        dialog.show();
    }
}
