package rva.repository;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rva.jpa.Proizvod;
import rva.jpa.Racun;
import rva.jpa.StavkaRacuna;

public interface StavkaRacunaRepository extends JpaRepository<StavkaRacuna, Integer>{

	Collection<StavkaRacuna> findByJedinicaMereIgnoreCase(String jedinicaMere);
	
	Collection<StavkaRacuna> findByProizvod(Proizvod proizvod);
	
	Collection<StavkaRacuna> findByRacun(Racun racun);
	
	Collection<StavkaRacuna> findByCenaLessThanOrderById(BigDecimal cena);
	
	@Query(value = "select coalesce(max(redni_broj)+1, 1) from stavka_racuna where proizvod = ?1", nativeQuery = true)
	Integer nextRbr(Integer id);
}
