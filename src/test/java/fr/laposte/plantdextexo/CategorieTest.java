package fr.laposte.plantdextexo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.laposte.plantdextexo.Model.Categorie;
import fr.laposte.plantdextexo.Repository.CategorieRepository;
import fr.laposte.plantdextexo.Repository.PlanteRepository;

@SpringBootTest
public class CategorieTest {

	@Autowired
	private CategorieRepository categorieRepo;

	@Autowired
	private PlanteRepository planteRepo;
	
	  
	  @BeforeEach void clean() {
		 
		  planteRepo.deleteAll();
		  categorieRepo.deleteAll();
		 
		  }
	  
	 
	@Test
	void testCategorie() {
		categorieRepo.save(new Categorie("cactus"));
		categorieRepo.save(new Categorie("Rouges Plantes"));
		categorieRepo.save(new Categorie("Jaunes Plantes"));

		assertEquals(3, categorieRepo.count());
	}

	
	  @Test void testFindByCat() {
		  categorieRepo.save(new Categorie("cactus"));
		  assertEquals("cactus", categorieRepo.findByLibelle("cactus").getLibelle());
		  }
	 
	
}
