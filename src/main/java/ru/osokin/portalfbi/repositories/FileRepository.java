package ru.osokin.portalfbi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.osokin.portalfbi.models.ServerFile;

@Repository
public interface FileRepository extends CrudRepository<ServerFile, Long> {

}
