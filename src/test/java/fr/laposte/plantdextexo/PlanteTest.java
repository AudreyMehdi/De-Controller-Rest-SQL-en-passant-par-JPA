package fr.laposte.plantdextexo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.laposte.plantdextexo.Model.Categorie;
import fr.laposte.plantdextexo.Model.Ensoleillement;
import fr.laposte.plantdextexo.Model.Plante;
import fr.laposte.plantdextexo.Repository.CategorieRepository;
import fr.laposte.plantdextexo.Repository.PlanteRepository;

@SpringBootTest
public class PlanteTest {

	@Autowired
	private CategorieRepository categorieRepo;

	@Autowired
	private PlanteRepository planteRepo;

	@BeforeEach
	void clean() {

		planteRepo.deleteAll();
		categorieRepo.deleteAll();

		Categorie cactus = new Categorie("cactus");
		Categorie fleur = new Categorie("fleur");
		Categorie palmier = new Categorie("palmier");

		categorieRepo.save(cactus);
		categorieRepo.save(fleur);
		categorieRepo.save(palmier);

		Plante astrophytum = new Plante("astrophytum", cactus, Ensoleillement.BEAUCOUP, 4, "mon image");
		Plante craspédia = new Plante("craspédia", fleur, Ensoleillement.MOYEN, 1, "mon image");
		Plante chamaerops = new Plante("chamaerops", palmier, Ensoleillement.MOYEN, 1, "mon image");

		planteRepo.save(astrophytum);
		planteRepo.save(craspédia);
		planteRepo.save(chamaerops);

	}

	@Test
	void testFindByNom() {

		assertEquals("astrophytum", planteRepo.findByNom("astrophytum").getNom());
		assertEquals("craspédia", planteRepo.findByNom("craspédia").getNom());
		assertEquals("chamaerops", planteRepo.findByNom("chamaerops").getNom());
	}

	@Test
	public void soleil() {
		for (Plante p : planteRepo.findBySoleil(Ensoleillement.MOYEN)) {
			System.out.println("Moyen de soleil" + p);
		}
	}

	@Test
	public void findBySoleil() {
		assertEquals(1, planteRepo.findBySoleil(Ensoleillement.BEAUCOUP).size());
		assertEquals(2, planteRepo.findBySoleil(Ensoleillement.MOYEN).size());
		assertEquals(0, planteRepo.findBySoleil(Ensoleillement.PEU).size());
	}

	@Test
	public void findByArrosageLessThanEqual() {
		assertEquals(2, planteRepo.findByArrosageLessThanEqual(3).size());
	}

	@Test
	public void findByCategorieOrderByCategorie() {
		assertEquals(1, planteRepo.findByCategorieOrderByCategorie(categorieRepo.findByLibelle("cactus")).size());
	}

	@Test
	public void categorie() {
		for (Plante p : planteRepo.findByCategorieOrderByCategorie(categorieRepo.findByLibelle("cactus"))) {
			System.out.println("Categories" + p);
		}
	}

}
