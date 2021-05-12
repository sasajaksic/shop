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

@Api(tags = {"ProizvoÄ‘aÄ� CRUD operacije"})
@RestController
@CrossOrigin
public class ProizvodjacRestContorller {

	@Autowired
	private ProizvodjacRepository proizvodjacRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "VraÄ‡a kolekciju svih proizvodjaÄ�a iz baze podataka")
	@GetMapping("proizvodjac")
	public Collection<Proizvodjac> getProizvodjaci() {
		return proizvodjacRepository.findAll();
	}
	
	@ApiOperation(value = "VraÄ‡a proizvodjaÄ�a iz baze podataka Ä�iji je id vrednost prosleÄ‘ena kao path varijabla")
	@GetMapping("proizvodjac/{id}")
	public Proizvodjac getProizvodjac(@PathVariable("id") Integer id) {
		return proizvodjacRepository.getOne(id);
	}
	
	@ApiOperation(value = "VraÄ‡a kolekciju svih proizvodjaÄ�a iz baze podataka koji u nazivu sadrÅ¾e string prosleÄ‘en kao path varijabla")
	@GetMapping("proizvodjacNaziv/{naziv}")
	public Collection<Proizvodjac> getProizvodjacByNaziv(@PathVariable("naziv") String naziv){
		return proizvodjacRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Upisuje proizvodjaÄ�a u bazu podataka")
	@PostMapping("proizvodjac")
	
	public ResponseEntity<Proizvodjac> insertProizvodjac(@RequestBody Proizvodjac proizvodjac){
		if(!proizvodjacRepository.existsById(proizvodjac.getId())) {
			proizvodjacRepository.save(proizvodjac);
			return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Proizvodjac>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Modifikuje postojeÄ‡eg proizvodjaÄ�a u bazi podataka")
	@PutMapping("proizvodjac")
	public ResponseEntity<Proizvodjac> updateProizvodjac(@RequestBody Proizvodjac proizvodjac){
		if(proizvodjacRepository.existsById(proizvodjac.getId())){
			proizvodjacRepository.save(proizvodjac);
			return new ResponseEntity<Proizvodjac>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Proizvodjac>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "BriÅ¡e proizvodjaÄ�a iz baze podataka Ä�iji je id vrednost prosleÄ‘ena kao path varijabla")
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
