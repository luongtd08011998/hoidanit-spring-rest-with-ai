package vn.hoidanit.springrestwithai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.springrestwithai.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}