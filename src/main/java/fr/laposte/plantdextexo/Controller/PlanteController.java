package fr.laposte.plantdextexo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.laposte.plantdextexo.service.ServiceCategorie;
import fr.laposte.plantdextexo.service.ServicePlante;
import fr.laposte.plantdextexo.service.dto.PlanteDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plante")
public class PlanteController {

	@Autowired
	private ServicePlante servicePlante;
	
	@Autowired(required = true)
	private ServiceCategorie serviceCategorie;
	
	@GetMapping
	public List<PlanteDto> getAll() {
		return servicePlante.listerLesPlantes();
	}
	
	@GetMapping("/{id}")
	public PlanteDto getOne(@PathVariable Long id) throws Exception {
		return servicePlante.getOneById(id);
	}
	
	@PostMapping("/{id}")
	public void add(@RequestBody @Valid PlanteDto plante, Long id) throws Exception {
		servicePlante.ajouterPlante(plante, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		servicePlante.supprimerPlanteById(id);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable long id, @RequestBody PlanteDto planteUpdate) throws Exception {
		servicePlante.modifierPlanteById(id, planteUpdate);
}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*
	 * @GetMapping public List<Plante> getAll() { return planterepo.findAll(); }
	 * 
	 * @GetMapping("/{id}") public Plante getOne(@PathVariable Long id) { return
	 * planterepo.findById(id).orElseThrow(); }
	 * 
	 * @PostMapping public void add(@RequestBody Plante plante, @RequestParam long
	 * categorieId) { Categorie categorie = categorierepo.findById(categorieId);
	 * plante.setCategorie(categorie); planterepo.save(plante); }
	 * 
	 * @DeleteMapping("/{id}") public void delete(@PathVariable Long id) {
	 * planterepo.deleteById(id); }
	 * 
	 * @PutMapping("/{id}") public void update(@PathVariable long id, @RequestBody
	 * Plante planteUpdate) {
	 * 
	 * Plante plante = planterepo.findById(id).orElseThrow();
	 * planterepo.delete(plante);
	 * 
	 * plante.setNom(planteUpdate.getNom());
	 * plante.setCategorie(planteUpdate.getCategorie());
	 * plante.setArrosage(planteUpdate.getArrosage());
	 * plante.setSoleil(planteUpdate.getSoleil());
	 * plante.setImageUrl(planteUpdate.getImageUrl());
	 * 
	 * planterepo.save(plante);
	 * 
	 * }
	 */

