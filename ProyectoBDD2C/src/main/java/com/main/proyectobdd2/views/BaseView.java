package com.main.proyectobdd2.views;

import com.main.proyectobdd2.controller.ReviewController;
import com.main.proyectobdd2.model.Review;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.bson.types.ObjectId;


public abstract class BaseView extends VerticalLayout {
    protected final ReviewController reviewController;

    protected H1 title;
    protected TextField id, name, email, movieId, text;
    protected DatePicker datePicker;
    protected HorizontalLayout btnLayout;

    public BaseView(ReviewController reviewController) {
        this.reviewController = reviewController;
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }

    protected void setupLayout(){
        add(title, id, name, email, movieId, text, datePicker, btnLayout);
    }

    protected abstract void initializeComponents();
    protected abstract void setupEventHandlers();

    protected void onFindClicked(){
            String ids = id.getValue();
            if(ids.isEmpty()){ throw new Error("Id is empty"); }
            ObjectId ido = new ObjectId(ids);
            Review user = reviewController.getOne(ido).getBody();
            if(user != null){
                name.setValue(user.getName());
                email.setValue(user.getEmail());
                movieId.setValue(user.getMovieId().toString());
                text.setValue(user.getText());
                datePicker.setValue(user.getDate());
            }
            else{
                throw new Error("No data found");
            }
    }

    protected boolean validateFields(){
        boolean r = true;
        boolean nameValid = name.getValue().isEmpty();
        boolean emailValid = email.getValue().isEmpty();
        boolean movieIdValid = movieId.getValue().isEmpty() || !movieId.getValue().matches("^[0-9a-fA-F]{24}$");
        boolean dateValid = datePicker.getValue() == null;

        if(nameValid || emailValid || movieIdValid || dateValid){
            Notification.show("Por favor rellene todas las casillas correctamente");
            r = false;
        }
        return r;
    }

    protected void clearFields(){
        id.clear();
        name.clear();
        email.clear();
        movieId.clear();
        text.clear();
        datePicker.clear();
    }
}
