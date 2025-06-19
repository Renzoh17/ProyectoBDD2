package com.main.proyectobdd2.views;

import com.main.proyectobdd2.controller.ReviewController;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Retrieve")
@Route(value = "retrieve", layout = MainLayout.class)
public class RetrieveView extends BaseView {

    private Button btnFind;

    public RetrieveView(ReviewController reviewController) {
        super(reviewController);
    }

    @Override
    protected void initializeComponents(){
        title = new H1("Find by id");
        id = new TextField("Id");
        name = new TextField("Name");
        name.setReadOnly(true);
        email = new TextField("Email");
        email.setReadOnly(true);
        movieId = new TextField("Movie id");
        movieId.setReadOnly(true);
        text = new TextField("Text");
        text.setReadOnly(true);
        datePicker = new DatePicker("Date");
        datePicker.setReadOnly(true);
        btnFind = new Button("Find");
        btnLayout = new HorizontalLayout(btnFind);
    }

    @Override
    protected void setupEventHandlers(){
        btnFind.addClickListener(e -> onFindClicked());
        btnFind.addClickShortcut(Key.ENTER);
    }

    @Override
    protected void onFindClicked(){
        try{
            super.onFindClicked();
        }
        catch (Error e){
            Notification.show("Error: " + e.getMessage());
            clearFields();
        }
    }

}
