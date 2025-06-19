package com.main.proyectobdd2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    private String name;
    private String email;
    @Field("movie_id")
    private ObjectId movieId;
    private String text;
    private LocalDate date;
}
