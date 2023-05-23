package by.fin.module.repositories;

import by.fin.module.entity.CurrencyEntity;
import by.fin.module.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRatesRepository extends JpaRepository<RateEntity, Long> {
    @Query(value = "select * from rates where currency_id = :currency_id and date between :start and :end order by date",
            nativeQuery = true)
    List<RateEntity> findByIdWhereDateBetweenStartAndEnd(@Param("currency_id") int currencyId,
                                                         @Param("start") LocalDate start,
                                                         @Param("end") LocalDate end);

    List<RateEntity> findByCurrencyOrderByDate(CurrencyEntity currency);
}
