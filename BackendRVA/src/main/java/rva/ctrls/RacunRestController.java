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
@Api(tags = {"RaÄ�un CRUD operacije"})
@RestController
public class RacunRestController {

	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "VraÄ‡a kolekciju svih raÄ�una iz baze podataka")
	@GetMapping("racun")
	public Collection<Racun> getRacuni() {
		return racunRepository.findAll();
	}
	
	@ApiOperation(value = "VraÄ‡a raÄ�un iz baze podataka Ä�iji je id vrednost prosleÄ‘ena kao path varijabla")
	@GetMapping("racun/{id}")
	public Racun getRacun(@PathVariable("id") Integer id) {
		return racunRepository.getOne(id);
	}
	
	@ApiOperation(value = "VraÄ‡a kolekciju svih raÄ�una iz baze podataka Ä�iji naÄ�ini plaÄ‡anja sadrÅ¾e string prosleÄ‘en kao path varijabla")
	@GetMapping("racunNacinPlacanja/{nacinPlacanja}")
	public Collection<Racun> getByNacinPlacanja(@PathVariable("nacinPlacanja") String nacinPlacanja){
		return racunRepository.findByNacinPlacanjaContainingIgnoreCase(nacinPlacanja);
	}
	
	@ApiOperation(value = "Upisuje raÄ�un u bazu podataka")
	@PostMapping("racun")
	@CrossOrigin
	public ResponseEntity<Racun> insertracun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<Racun>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Modifikuje postojeÄ‡i raÄ�un u bazi podataka")
	@PutMapping("racun")
	@CrossOrigin
	public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun){
		if(racunRepository.existsById(racun.getId())){
			racunRepository.save(racun);
			return new ResponseEntity<Racun>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "BriÅ¡e raÄ�un iz baze podataka Ä�iji je id vrednost prosleÄ‘ena kao path varijabla")
	@DeleteMapping("racun/{id}")
	@CrossOrigin
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
