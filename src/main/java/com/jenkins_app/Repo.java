package com.jenkins_app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<Exemple,Integer> {
}
