package rva.ctrls;

import java.math.BigDecimal;
import java.util.Collection;

import org.hibernate.metamodel.model.convert.internal.StandardBasicValueConverter;
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
import rva.jpa.StavkaRacuna;
import rva.repository.ProizvodRepository;
import rva.repository.StavkaRacunaRepository;

@CrossOrigin
@Api(tags = {"Stavka računa CRUD operacije"})
@RestController
public class StavkaRacunaRestController {

	
	@Autowired
	private StavkaRacunaRepository stavkaRacunaRepository;
	
	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraća kolekciju svih stavki računa iz baze podataka")
	@GetMapping("stavkaRacuna")
	public Collection<StavkaRacuna> getStavkeRacuna(){
		return stavkaRacunaRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća stavku računa iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("stavkaRacuna/{id}")
	public StavkaRacuna getStavkaRacuna(@PathVariable("id") Integer id) {
		return stavkaRacunaRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća kolekciju svih stavki računa iz baze podataka koji u jedinici mere sadrže string prosleđen kao path varijabla")
	@GetMapping("stavkeRacunaJedinicaMere/{jedinicaMere}")
	public Collection<StavkaRacuna> getStavkaRacunaByJedinicaMere(@PathVariable("jedinicaMere") String jedinicaMere){
		return stavkaRacunaRepository.findByJedinicaMereIgnoreCase(jedinicaMere);
	}
	
	@ApiOperation(value = "Vraća kolekciju stavki računa iz baze podataka na osnovu proizvoda čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("stavkeRacunaByProizvodId/{id}")
	public Collection<StavkaRacuna> getStavkeRacunaByProizvodId(@PathVariable("id") Integer id){
		Proizvod p = proizvodRepository.getOne(id);
		return stavkaRacunaRepository.findByProizvod(p);
	}
	
	@ApiOperation(value = "Vraća kolekciju stavki računa iz baze podataka na osnovu cene čija je vrednost prosleđena kao path varijabla")
	@GetMapping("stavkeRacunaByCena/{cena}")
	public Collection<StavkaRacuna> getStavkeRacunaByCena(@PathVariable("cena") BigDecimal cena){
		return stavkaRacunaRepository.findByCenaLessThanOrderById(cena);
	}
	
	@ApiOperation(value = "Upisuje stavku računa u bazu podataka")
	@PostMapping("stavkaRacuna")
	public ResponseEntity<StavkaRacuna> insertStavkaRacuna(@RequestBody StavkaRacuna stavkaRacuna){
		if(!stavkaRacunaRepository.existsById(stavkaRacuna.getId())) {
			stavkaRacuna.setRedniBroj(stavkaRacunaRepository.nextRbr(stavkaRacuna.getProizvod().getId()));
			stavkaRacunaRepository.save(stavkaRacuna);
			return new ResponseEntity<StavkaRacuna>(HttpStatus.OK);
		} else {
			return new ResponseEntity<StavkaRacuna>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Modifikuje postojeću stavku računa u bazi podataka")
	@PutMapping("stavkaRacuna")
	public ResponseEntity<StavkaRacuna> updateStavkaRacuna(@RequestBody StavkaRacuna stavkaRacuna){
		if(stavkaRacunaRepository.existsById(stavkaRacuna.getId())) {
			stavkaRacunaRepository.save(stavkaRacuna);
			return new ResponseEntity<StavkaRacuna>(HttpStatus.OK);
		} else {
			return new ResponseEntity<StavkaRacuna>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Briše stavku računa iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("stavkaRacuna/{id}")
	public ResponseEntity<StavkaRacuna> deleteStavkaRacuna(@PathVariable Integer id){
		if(stavkaRacunaRepository.existsById(id)) {
			stavkaRacunaRepository.deleteById(id);
			if(id == -100)
				jdbcTemplate.execute("INSERT INTO \"stavka_racuna\" (\"id\", \"racun\", \"proizvod\", \"redni_broj\", \"kolicina\", \"jedinica_mere\", \"cena\")"
					+ "VALUES (-100, -101, -101, 1, 1,'Test', 1000)");
			return new ResponseEntity<StavkaRacuna>(HttpStatus.OK);
		} else {
			return new ResponseEntity<StavkaRacuna>(HttpStatus.NO_CONTENT);
		}
	}
}
