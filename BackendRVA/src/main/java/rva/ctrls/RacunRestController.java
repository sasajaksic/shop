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
import rva.jpa.Racun;
import rva.repository.RacunRepository;

@CrossOrigin
@Api(tags = {"Račun CRUD operacije"})
@RestController
public class RacunRestController {

	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraća kolekciju svih računa iz baze podataka")
	@GetMapping("racun")
	public Collection<Racun> getRacuni() {
		return racunRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća račun iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("racun/{id}")
	public Racun getRacun(@PathVariable("id") Integer id) {
		return racunRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća kolekciju svih računa iz baze podataka čiji načini plaćanja sadrže string prosleđen kao path varijabla")
	@GetMapping("racunNacinPlacanja/{nacinPlacanja}")
	public Collection<Racun> getByNacinPlacanja(@PathVariable("nacinPlacanja") String nacinPlacanja){
		return racunRepository.findByNacinPlacanjaContainingIgnoreCase(nacinPlacanja);
	}
	
	@ApiOperation(value = "Upisuje račun u bazu podataka")
	@PostMapping("racun")
	public ResponseEntity<Racun> insertracun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<Racun>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Modifikuje postojeći račun u bazi podataka")
	@PutMapping("racun")
	public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun){
		if(racunRepository.existsById(racun.getId())){
			racunRepository.save(racun);
			return new ResponseEntity<Racun>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Briše račun iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("racun/{id}")
	public ResponseEntity<Racun> deleteRacun(@PathVariable Integer id){
		if(racunRepository.existsById(id)) {
			racunRepository.deleteById(id);
			if(id == -100)
				jdbcTemplate.execute("INSERT INTO \"racun\" (\"id\", \"datum\", \"nacin_placanja\")"
					+ "VALUES (-100, to_date('01.01.9999', 'dd.mm.yyyy.'), 'Test')");
			return new ResponseEntity<Racun>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Racun>(HttpStatus.NO_CONTENT);
		}
	}
}
