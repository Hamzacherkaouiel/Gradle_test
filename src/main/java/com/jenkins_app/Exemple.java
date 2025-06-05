package com.jenkins_app;

import jakarta.persistence.*;

@Entity
@Table(name = "EXEMPLE")
public class Exemple {

    @Id
    public int id;

    public Exemple() {
    }

    public Exemple(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
