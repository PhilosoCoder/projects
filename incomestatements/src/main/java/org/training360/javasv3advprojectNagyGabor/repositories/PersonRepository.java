package org.training360.javasv3advprojectNagyGabor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.training360.javasv3advprojectNagyGabor.model.Person;

@Repository
public interface PersonRepository extends JpaRepository <Person, Long> {
}
