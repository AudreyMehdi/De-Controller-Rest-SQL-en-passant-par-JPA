package fr.laposte.plantdextexo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.laposte.plantdextexo.Model.Categorie;
import fr.laposte.plantdextexo.service.dto.CategorieDto;



public interface ServiceCategorie {

	List<CategorieDto> listerLesCategories();
	CategorieDto getOneById(Long id) throws Exception;
	CategorieDto ajouterCategorie(CategorieDto categorie);
	void supprimerCategorieById(Long id);
	void modifierCategorieById(Long id, Categorie categorieAModifier) throws Exception;
	
}
