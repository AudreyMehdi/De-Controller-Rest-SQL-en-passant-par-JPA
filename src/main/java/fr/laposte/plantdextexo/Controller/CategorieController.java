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
import fr.laposte.plantdextexo.service.ServiceCategorie;
import fr.laposte.plantdextexo.service.dto.CategorieDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorie")

public class CategorieController {

	@Autowired(required = true)
	private ServiceCategorie serviceCategorie;

	@GetMapping
	public List<CategorieDto> getAll() {
		return serviceCategorie.listerLesCategories();
	}

	@GetMapping("/{id}")
	public CategorieDto getOne(@PathVariable Long id) throws Exception {
		return serviceCategorie.getOneById(id);
	}

	@PostMapping("/{id}")
	public void add(@RequestBody @Valid CategorieDto categorie) {
		serviceCategorie.ajouterCategorie(categorie);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		serviceCategorie.supprimerCategorieById(id);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable long id, @RequestBody Categorie categorieUpdate) throws Exception {
		serviceCategorie.modifierCategorieById(id, categorieUpdate);

	}

}
