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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.laposte.plantdextexo.Model.Categorie;
import fr.laposte.plantdextexo.Model.Plante;
import fr.laposte.plantdextexo.Repository.CategorieRepository;
import fr.laposte.plantdextexo.Repository.PlanteRepository;

@RestController
@RequestMapping("/plante")
public class PlanteController {

	@Autowired
	private PlanteRepository planterepo;
	
	@Autowired
	private CategorieRepository categorierepo;

	@GetMapping
	public List<Plante> getAll() {
		return planterepo.findAll();
	}

	@GetMapping("/{id}")
	public Plante getOne(@PathVariable Long id) {
		return planterepo.findById(id).orElseThrow();
	}

   @PostMapping
   public void add(@RequestBody Plante plante, @RequestParam long categorieId) {
	   Categorie categorie = categorierepo.findById(categorieId).orElseThrow();
	   plante.setCategorie(categorie);
	   planterepo.save(plante);
   }

   @DeleteMapping("/{id}")
   public void delete(@PathVariable Long id) {
	   planterepo.deleteById(id);
   }
   
   @PutMapping("/{id}")
   public void update(@PathVariable long id, @RequestBody Plante planteUpdate) {
	   
	   Plante plante = planterepo.findById(id).orElseThrow();
	   planterepo.delete(plante);
	   
	   plante.setNom(planteUpdate.getNom());
	   plante.setCategorie(planteUpdate.getCategorie());
	   plante.setArrosage(planteUpdate.getArrosage());
	   plante.setSoleil(planteUpdate.getSoleil());
	   plante.setImageUrl(planteUpdate.getImageUrl());
	   
	   planterepo.save(plante);
	   
   }
}
