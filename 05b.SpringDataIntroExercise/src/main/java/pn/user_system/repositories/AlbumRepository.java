package pn.user_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.user_system.models.entities.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
