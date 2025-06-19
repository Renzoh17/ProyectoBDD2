package com.main.proyectobdd2.views;

import com.main.proyectobdd2.controller.ReviewController;
import com.main.proyectobdd2.model.Review;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.textfield.TextField;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@PageTitle("Create")
@Route(value = "", layout = MainLayout.class)
public class CreateView extends BaseView {

    private Button btnSave;

    public CreateView(ReviewController reviewController) {
        super(reviewController);
    }

    @Override
    protected void initializeComponents(){
        title = new H1("Create New");
        id = new TextField("Id");
        id.setVisible(false);
        name = new TextField("Name");
        email = new TextField("Email");
        movieId = new TextField("Movie id");
        text = new TextField("Text");
        datePicker = new DatePicker("Date");
        datePicker.setMax(LocalDate.now());
        datePicker.setMin(LocalDate.of(1900, 1, 1));
        datePicker.setErrorMessage("La fecha debe ser entre 1900 y hoy");
        btnSave = new Button("Save");
        btnLayout = new HorizontalLayout(btnSave);
    }

    @Override
    protected void setupEventHandlers(){
        btnSave.addClickListener(e -> onSaveClicked());
    }

    private void onSaveClicked(){
        if(validateFields()){
            try{
                saveData();
                Notification.show("Data saved!");
                clearFields();
            } catch(Exception ex){
                Notification.show("Error: " + ex.getMessage());
            }
        }
    }

    private void saveData(){
        Review user = new Review();
        user.setName(name.getValue());
        user.setEmail(email.getValue());
        user.setMovieId(new ObjectId(movieId.getValue()));
        user.setText(text.getValue());
        user.setDate(datePicker.getValue());
        reviewController.save(user);
    }

}
