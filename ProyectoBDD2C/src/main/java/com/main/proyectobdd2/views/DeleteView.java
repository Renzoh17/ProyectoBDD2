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
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

@PageTitle("Delete")
@Route(value = "delete", layout = MainLayout.class)
public class DeleteView extends BaseView {

     private Button btnDelete, btnFind, btnCancel;

     public DeleteView(ReviewController reviewController) { super(reviewController); }

     @Override
     protected void initializeComponents(){
         title = new H1("Delete by id");
         id = new TextField("Id");
         name = new TextField("Name");
         email = new TextField("Email");
         movieId = new TextField("Movie id");
         text = new TextField("Text");
         datePicker = new DatePicker("Date");
         btnFind = new Button("Find");
         btnDelete = new Button("Delete");
         btnDelete.setVisible(false);
         btnCancel = new Button("Cancel");
         btnCancel.setVisible(false);
         btnLayout = new HorizontalLayout(btnFind, btnDelete, btnCancel);
     }

     @Override
     protected void setupEventHandlers(){
         btnFind.addClickListener(e -> onFindClicked());
         btnFind.addClickShortcut(Key.ENTER);
         btnDelete.addClickListener(e -> onDeleteClicked());
         btnCancel.addClickListener(e -> onCancelClicked());
     }

    @Override
    protected void onFindClicked(){
        try{
            super.onFindClicked();
            id.setReadOnly(true);
            btnDelete.setVisible(true);
            btnCancel.setVisible(true);
            isTextFieldReadOnly(true);
        } catch (Error e) {
            Notification.show("Error: " + e.getMessage());
            clearFields();
        }
    }

     private void onDeleteClicked(){
         if(!id.getValue().isEmpty()){
             ResponseEntity<?> res = reviewController.delete(new ObjectId(id.getValue()));
             Notification.show(res.getStatusCode().toString());
             onCancelClicked();
             isTextFieldReadOnly(false);
         }
     }

    private void onCancelClicked(){
        clearFields();
        btnDelete.setVisible(false);
        btnCancel.setVisible(false);
        id.setReadOnly(false);
        isTextFieldReadOnly(false);
    }

    private void isTextFieldReadOnly(boolean readOnly){
         id.setReadOnly(readOnly);
         name.setReadOnly(readOnly);
         email.setReadOnly(readOnly);
         movieId.setReadOnly(readOnly);
         text.setReadOnly(readOnly);
         datePicker.setReadOnly(readOnly);
    }

}
