package org.ourproject.kune.platf.client.actions.ui;

import cc.kune.core.client.errors.UIException;

public class MenuBinding extends GuiBindingAdapter {

    public static final String UI_MENU = "UI_MENU";

    private Boolean isSubmenu;

    @Override
    public AbstractGuiItem create(final GuiActionDescrip descriptor) {
        AbstractGuiItem item;
        if (descriptor.isChild()) {
            final SubMenuGui submenu = new SubMenuGui(descriptor);
            final AbstractMenuGui parentMenu = ((AbstractMenuGui) descriptor.getParent().getValue(UI_MENU));
            final int position = descriptor.getPosition();
            if (position == GuiActionDescrip.NO_POSITION) {
                parentMenu.add(submenu.getMenuItem());
            } else {
                parentMenu.insert(position, submenu.getMenuItem());
            }
            descriptor.putValue(UI_MENU, submenu);
            item = submenu;
            isSubmenu = true;
        } else {
            // Is main parent menu
            final MenuGui menu = new MenuGui((MenuDescriptor) descriptor);
            descriptor.putValue(UI_MENU, menu);
            item = menu;
            isSubmenu = false;
        }
        return item;
    }

    @Override
    public boolean isAttachable() {
        if (isSubmenu == null) {
            throw new UIException("Please create element before to check if is attachable");
        }
        return !isSubmenu;
    }

}
