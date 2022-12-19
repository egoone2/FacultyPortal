package ru.osokin.carpetwash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.osokin.carpetwash.models.Client;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByPhoneNumber(String phoneNumber);
}
