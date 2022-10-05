package ru.javamentor.java_pp_313.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javamentor.java_pp_313.model.Role;


@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {
}
