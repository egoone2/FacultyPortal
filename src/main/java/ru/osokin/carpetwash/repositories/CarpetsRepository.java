package ru.osokin.carpetwash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.osokin.carpetwash.models.Carpet;
@Repository
public interface CarpetsRepository extends JpaRepository<Carpet, Integer> {
}
