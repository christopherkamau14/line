package com.high.school.users.repo;

import com.high.school.users.model.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo  extends JpaRepository<User,Long>, PagingAndSortingRepository<User,Long> ,DataTablesRepository<User, Long> {
    User findByUsername(String username);
}
