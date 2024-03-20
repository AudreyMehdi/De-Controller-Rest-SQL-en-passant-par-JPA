package fr.laposte.plantdextexo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.laposte.plantdextexo.Model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long>{

	Categorie findByLibelle(String libelleExact);
	Categorie findById(long id);
	
}
