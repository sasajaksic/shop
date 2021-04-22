package rva.ctrls;

import java.util.Collection;

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
import rva.jpa.Proizvodjac;
import rva.repository.ProizvodjacRepository;

@CrossOrigin
@Api(tags = {"Proizvođač CRUD operacije"})
@RestController
public class ProizvodjacRestContorller {

	@Autowired
	private ProizvodjacRepository proizvodjacRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraća kolekciju svih proizvodjača iz baze podataka")
	@GetMapping("proizvodjac")
	public Collection<Proizvodjac> getProizvodjaci() {
		return proizvodjacRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća proizvodjača iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("proizvodjac/{id}")
	public Proizvodjac getProizvodjac(@PathVariable("id") Integer id) {
		return proizvodjacRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća kolekciju svih proizvodjača iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	@GetMapping("proizvodjacNaziv/{naziv}")
	public Collection<Proizvodjac> getProizvodjacByNaziv(@PathVariable("naziv") String naziv){
		return proizvodjacRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Upisuje proizvodjača u bazu podataka")
	@PostMapping("proizvodjac")
	public ResponseEntity<Proizvodjac> insertProizvodjac(@RequestBody Proizvodjac proizvodjac){
		if(!proizvodjacRepository.existsById(proizvodjac.getId())) {
			proizvodjacRepository.save(proizvodjac);
			return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Proizvodjac>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Modifikuje postojećeg proizvodjača u bazi podataka")
	@PutMapping("proizvodjac")
	public ResponseEntity<Proizvodjac> updateProizvodjac(@RequestBody Proizvodjac proizvodjac){
		if(proizvodjacRepository.existsById(proizvodjac.getId())){
			proizvodjacRepository.save(proizvodjac);
			return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Proizvodjac>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Briše proizvodjača iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("proizvodjac/{id}")
	public ResponseEntity<Proizvodjac> deleteProizvodjac(@PathVariable Integer id){
		if(proizvodjacRepository.existsById(id)) {
			proizvodjacRepository.deleteById(id);
			if(id == -100)
				jdbcTemplate.execute("INSERT INTO \"proizvodjac\" (\"id\", \"naziv\", \"adresa\", \"kontakt\")"
					+ "VALUES (-100, 'Test', 'Test', 'Test')");
			return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Proizvodjac>(HttpStatus.NO_CONTENT);
		}
	}
}
