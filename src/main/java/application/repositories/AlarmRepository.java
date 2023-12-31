package application.repositories;

import application.entities.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AlarmRepository extends JpaRepository<Alarm, UUID> {
    @Query("SELECT a FROM Alarm a where a.user.id=:id")
    List<Alarm> findByUser(UUID id);

    @Query("SELECT a FROM Alarm a where a.stock=:stockSymbol")
    List<Alarm> findByStock(String stockSymbol);

    @Query("SELECT a FROM Alarm a where a.user.id=:userId and a.stock=:stockSymbol")
    List<Alarm> findByUserAndStock(UUID userId, String stockSymbol);
}
