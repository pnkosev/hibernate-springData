package pn.user_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pn.user_system.models.entities.User;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByEmailEndingWith(String end);

    List<User> findAllByDeletedTrue();

    List<User> findAllByDeletedFalse();

    // COULD ALSO WORK WITH findAllByAgeBetween
    List<User> findAllByAgeAfterAndAgeBefore(int ageAfter, int ageBefore);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User as u set u.deleted = true where u.lastTimeLoggedIn >= :date")
    void updateAllInactiveUsersSinceGivenDate(@Param("date") Date date);
}
