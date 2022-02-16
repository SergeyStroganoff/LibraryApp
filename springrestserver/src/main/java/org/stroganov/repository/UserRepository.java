package org.stroganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stroganov.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    default void unblockUser(User user) {
        user.setBlocked(Boolean.FALSE);
        save(user);
    }

    default void blockUser(User user) {
        user.setBlocked(Boolean.TRUE);
        save(user);
    }
}
