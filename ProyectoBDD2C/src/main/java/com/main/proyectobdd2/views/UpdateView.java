package com.main.proyectobdd2.views;

import com.main.proyectobdd2.controller.ReviewController;
import com.main.proyectobdd2.model.Review;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;


@PageTitle("Update")
@Route(value = "update", layout = MainLayout.class)
public class UpdateView extends BaseView {

    private Button btnUpdate, btnFind, btnCancel;

    public UpdateView(ReviewController reviewController) {
        super(reviewController);
    }

    @Override
    protected void initializeComponents(){
        title = new H1("Update");
        id = new TextField("Id");
        name = new TextField("Name");
        email = new TextField("Email");
        movieId = new TextField("Movie id");
        text = new TextField("Text");
        datePicker = new DatePicker("Date");
        datePicker.setMax(LocalDate.now());
        datePicker.setMin(LocalDate.of(1900, 1, 1));
        datePicker.setErrorMessage("La fecha debe ser entre 1900 y hoy");
        btnFind = new Button("Find");
        btnUpdate = new Button("Update");
        btnUpdate.setVisible(false);
        btnCancel = new Button("Cancel");
        btnCancel.setVisible(false);
        btnLayout = new HorizontalLayout(btnFind, btnUpdate, btnCancel);
    }

    @Override
    protected void setupEventHandlers(){
        btnFind.addClickListener(e -> onFindClicked());
        btnFind.addClickShortcut(Key.ENTER);
        btnUpdate.addClickListener(e -> onUpdateClicked());
        btnCancel.addClickListener(e -> onCancelClicked());
    }

    private void onUpdateClicked(){
        if(!id.getValue().isEmpty() && validateFields()){
            Review user = new Review();
            user.setName(name.getValue());
            user.setEmail(email.getValue());
            user.setMovieId(new ObjectId(movieId.getValue()));
            user.setText(text.getValue());
            user.setDate(datePicker.getValue());
            ResponseEntity<Review> res = reviewController.update(new ObjectId(id.getValue()), user);
            Notification.show(res.getStatusCode().toString());
            onCancelClicked();
        }
    }

    @Override
    protected void onFindClicked(){
        try{
            super.onFindClicked();
            id.setReadOnly(true);
            btnUpdate.setVisible(true);
            btnCancel.setVisible(true);
        } catch (Error e) {
            Notification.show("Error: " + e.getMessage());
            clearFields();
        }
    }

    private void onCancelClicked(){
        clearFields();
        btnUpdate.setVisible(false);
        btnCancel.setVisible(false);
        id.setReadOnly(false);
    }


}
