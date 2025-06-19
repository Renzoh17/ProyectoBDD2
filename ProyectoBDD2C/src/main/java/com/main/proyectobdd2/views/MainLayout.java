package com.main.proyectobdd2.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        // Crear las pestañas
        Tabs tabs = new Tabs();

        // Crear links de navegación
        tabs.add(createTab("Create", CreateView.class));
        tabs.add(createTab("Retrieve", RetrieveView.class));
        tabs.add(createTab("Update", UpdateView.class));
        tabs.add(createTab("Delete", DeleteView.class));
        // Aquí puedes agregar más pestañas según necesites

        // Agregar las pestañas al layout
        addToNavbar(tabs);
    }

    private Tab createTab(String text, Class<? extends com.vaadin.flow.component.Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        return tab;
    }
}
