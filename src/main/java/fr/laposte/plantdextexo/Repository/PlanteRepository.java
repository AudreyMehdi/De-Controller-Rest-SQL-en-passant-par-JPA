package fr.laposte.plantdextexo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.laposte.plantdextexo.Model.Categorie;
import fr.laposte.plantdextexo.Model.Ensoleillement;
import fr.laposte.plantdextexo.Model.Plante;



public interface PlanteRepository extends JpaRepository<Plante, Long> {
	
	List<Plante> findByCategorieOrderByCategorie(Categorie categorie);
	Plante findByNom(String nomExact);
	List<Plante> findBySoleil(Ensoleillement soleil);
	List<Plante> findByArrosageLessThanEqual(int arrosageMax);
    
}
