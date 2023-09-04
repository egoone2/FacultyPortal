package ru.osokin.portalfbi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.osokin.portalfbi.models.Message;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

}
