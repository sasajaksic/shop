package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Proizvod;
import rva.jpa.Proizvodjac;

public interface ProizvodRepository extends JpaRepository<Proizvod, Integer>{

	Collection<Proizvod> findByNazivContainingIgnoreCase(String naziv);
	
	Collection<Proizvod> findByProizvodjac(Proizvodjac proizvodjac);
}
