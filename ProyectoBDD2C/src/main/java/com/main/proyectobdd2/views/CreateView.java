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
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@PageTitle("Create")
@Route(value = "", layout = MainLayout.class)
public class CreateView extends BaseView {

    private Button btnSave;
    private Button btnCopy;
    private HorizontalLayout idLayout;

    public CreateView(ReviewController reviewController) {
        super(reviewController);
    }

    @Override
    protected void initializeComponents(){
        title = new H1("Create New");
        id = new TextField("Last Generated ID");
        id.setReadOnly(true);
        btnCopy = new Button("Copy");
        btnCopy.setVisible(false);
        idLayout = new HorizontalLayout(id, btnCopy);
        idLayout.setAlignItems(Alignment.BASELINE);
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
    protected void setupEventHandlers()
    {
        btnSave.addClickListener(e -> onSaveClicked());
        btnCopy.addClickListener(e-> onCopyClicked());
    }

    @Override
    protected void setupLayout(){
        add(title, idLayout, name, email, movieId, text, datePicker, btnLayout);
    }

    private void onSaveClicked(){
        if(validateFields()){
            try{
                String s = saveData();
                if(!s.isEmpty()){
                    id.setValue(s);
                    btnCopy.setVisible(true);
                    Notification.show("Data saved!");
                }
                else Notification.show("Error saving data");
                clearFields();
            } catch(Exception ex){
                Notification.show("Error: " + ex.getMessage());
            }
        }
    }

    private void onCopyClicked(){
        if (id.getValue() != null && !id.getValue().isEmpty()) {
            id.focus();
            // Usando el API de Vaadin para el portapapeles
            getElement().executeJs("navigator.clipboard.writeText($0)", id.getValue());
            Notification.show("ID copiado al portapapeles!");
        } else {
            Notification.show("No hay ID para copiar");
        }

    }

    private String saveData(){
        Review user = new Review();
        user.setName(name.getValue());
        user.setEmail(email.getValue());
        user.setMovieId(new ObjectId(movieId.getValue()));
        user.setText(text.getValue());
        user.setDate(datePicker.getValue());
        ResponseEntity<Review> us = reviewController.save(user);
        return us.getBody() != null ? us.getBody().getId().toString() : "";
    }

    @Override
    protected void clearFields(){
        name.clear();
        email.clear();
        movieId.clear();
        text.clear();
        datePicker.clear();
    }
}
