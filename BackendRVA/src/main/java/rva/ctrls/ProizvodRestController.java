package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Proizvod;
import rva.jpa.Proizvodjac;
import rva.repository.ProizvodRepository;
import rva.repository.ProizvodjacRepository;

@CrossOrigin
@Api(tags = {"Proizvod CRUD operacije"})
@RestController
public class ProizvodRestController {
	
	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@Autowired
	private ProizvodjacRepository proizvodjacRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ApiOperation(value = "Vraća kolekciju svih proizvoda iz baze podataka")
	@GetMapping("proizvodi")
	public Collection<Proizvod> getProizvodi() {
		return proizvodRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća proizvode iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("proizvod/{id}")
	public Proizvod getProizvod(@PathVariable("id") Integer id) {
		return proizvodRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća kolekciju svih proizvoda iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	@GetMapping("proizvodiNaziv/{naziv}")
	public Collection<Proizvod> getProizvodByNaziv(@PathVariable("naziv") String naziv){
		return proizvodRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Vraća kolekciju proizvoda iz baze podataka na osnovu proizvodjača čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("proizvodiByProizvodjacId/{id}")
	public Collection<Proizvod> getProizvodByProizvodjacId(@PathVariable("id") Integer id){
		Proizvodjac p = proizvodjacRepository.getOne(id);
		return proizvodRepository.findByProizvodjac(p);
	}
	
	@ApiOperation(value = "Upisuje proizvod u bazu podataka")
	@PostMapping("proizvod")
	public ResponseEntity<Proizvod> insertProizvod(@RequestBody Proizvod proizvod){
		if(!proizvodRepository.existsById(proizvod.getId())) {
			proizvodRepository.save(proizvod);
			return new ResponseEntity<Proizvod>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Proizvod>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Modifikuje postojeći proizvod u bazi podataka")
	@PutMapping("proizvod")
	public ResponseEntity<Proizvod> updateProizvod(@RequestBody Proizvod proizvod){
		if(proizvodRepository.existsById(proizvod.getId())){
			proizvodRepository.save(proizvod);
			return new ResponseEntity<Proizvod>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Proizvod>(HttpStatus.CONFLICT);
		}
	}

	@Transactional
	@ApiOperation(value = "Briše proizvoda iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("proizvod/{id}")
	public ResponseEntity<Proizvod> deleteProizvod(@PathVariable Integer id){
		if(proizvodRepository.existsById(id)) {
			proizvodRepository.deleteById(id);
			jdbcTemplate.execute("DELETE FROM stavka_racuna WHERE proizvod= " + id);
			if(id == -100)
				jdbcTemplate.execute("INSERT INTO \"proizvod\" (\"id\", \"naziv\", \"proizvodjac\")"
						+ "VALUES (-100, 'Test', -101)");
			return new ResponseEntity<Proizvod>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Proizvod>(HttpStatus.NO_CONTENT);
		}
	}
}
