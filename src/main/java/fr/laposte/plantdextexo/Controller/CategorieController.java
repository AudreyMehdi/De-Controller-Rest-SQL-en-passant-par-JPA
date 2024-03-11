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

import fr.laposte.plantdextexo.Model.Categorie;
import fr.laposte.plantdextexo.Model.Plante;
import fr.laposte.plantdextexo.Repository.CategorieRepository;



@RestController
@RequestMapping("/categorie")

public class CategorieController {

	@Autowired
	private CategorieRepository categorieRepo;
	
	@GetMapping
	public List<Categorie> getAll(){
		return categorieRepo.findAll();
		}
	
	@GetMapping("/{id}")
	public Categorie getOne(@PathVariable Long id) {
		return categorieRepo.findById(id).orElseThrow();
	}
	
	@PostMapping("/{id}")
	   public void add(@RequestBody Categorie categorie) {
		   categorieRepo.save(categorie);
	   }
	
	 @DeleteMapping("/{id}")
	   public void delete(@PathVariable Long id) {
		   categorieRepo.deleteById(id);
	   }
	 
	   @PutMapping("/{id}")
	   public void update(@PathVariable long id, @RequestBody Categorie categorieUpdate) {
		   
		   Categorie categorie = categorieRepo.findById(id).orElseThrow();
		   categorieRepo.delete(categorie);
		   
		   categorie.setLibelle(categorieUpdate.getLibelle());
		  
		   
		   categorieRepo.save(categorie);
		   
	   }
}
	    
	
