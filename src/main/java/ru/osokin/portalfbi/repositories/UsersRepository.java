package ru.osokin.portalfbi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.osokin.portalfbi.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM Users u WHERE u.name LIKE %:keyword%", nativeQuery = true)
    List<User> findByKeyword(@Param("keyword") String keyword);
}
