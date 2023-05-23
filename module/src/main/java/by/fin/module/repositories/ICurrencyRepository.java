package by.fin.module.repositories;

import by.fin.module.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ICurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {
    Optional<CurrencyEntity>findBySymbol(String symbol);

}
