package com.jenkins_app;

import jakarta.persistence.*;

@Entity
@Table(name = "EXEMPLE")
public class Exemple {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    public int id;
    public String name;

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
