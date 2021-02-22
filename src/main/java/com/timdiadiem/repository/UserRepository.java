package com.timdiadiem.repository;

import com.timdiadiem.model.User;
import com.timdiadiem.model.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User a SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);

    @Modifying
    @Query("UPDATE User a SET a.locked = TRUE WHERE a.username = ?1")
    int lockUser(String username);

    @Modifying
    @Query("UPDATE User a SET a.locked = FALSE WHERE a.username = ?1")
    int unlockUser(String username);

    @Modifying
    @Query("UPDATE User a SET a.password = ?1 WHERE a.username = ?2")
    int changePassword(String password, Long id);

    @Modifying
    @Query("UPDATE User a SET a.email = ?1 WHERE a.username = ?2")
    int changeEmail(String email, Long id);

    @Modifying
    @Query("UPDATE User a SET a.userRole = ?1 WHERE a.username = ?2")
    int changeRole(UserRole role, Long id);

}
