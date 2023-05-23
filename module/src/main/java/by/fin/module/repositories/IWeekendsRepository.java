package by.fin.module.repositories;

import by.fin.module.entity.WeekendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWeekendsRepository extends JpaRepository<WeekendEntity, Long> {
    @Query(value = "select * from weekends where month(calendar_date) in(:month) and is_day_off is false order by calendar_date",
            nativeQuery = true)
    List<WeekendEntity> findAllWorkDaysInMonth(@Param("month") int month);
}
