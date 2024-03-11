package fr.laposte.plantdextexo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.laposte.plantdextexo.Model.Categorie;
import fr.laposte.plantdextexo.Model.Ensoleillement;
import fr.laposte.plantdextexo.Model.Plante;
import fr.laposte.plantdextexo.Repository.CategorieRepository;
import fr.laposte.plantdextexo.Repository.PlanteRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class InitData implements ApplicationRunner {

	@Autowired
	private PlanteRepository planteRepo;

	@Autowired
	private CategorieRepository categorieRepo;


	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (args.getNonOptionArgs().contains("clean")) {
			log.info("Option clean : nettoyage des données");
			planteRepo.deleteAll();
			categorieRepo.deleteAll();
		}

		if (args.getNonOptionArgs().contains("demo")) { // ce if dépend du mot que je mets dans "option config"
			log.info("Option demo : nettoyage des données et création de données");

			planteRepo.deleteAll();
			categorieRepo.deleteAll();

			Categorie planteVerte = new Categorie("planteVerte");
			Categorie cactus = new Categorie("cactus");

			categorieRepo.save(planteVerte);
			categorieRepo.save(cactus);

		

			Plante plante1 = new Plante("Plante1", planteVerte, Ensoleillement.PEU, 2, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTlhMB3ZrjdCZjaRvnSHIsbq-LQjqxCx0w4-EENnzJZg&s");
			Plante plante2 = new Plante("Plante2", planteVerte, Ensoleillement.MOYEN, 3, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTlhMB3ZrjdCZjaRvnSHIsbq-LQjqxCx0w4-EENnzJZg&s");
			Plante plante3 = new Plante("Plante3", cactus, Ensoleillement.BEAUCOUP, 4, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTlhMB3ZrjdCZjaRvnSHIsbq-LQjqxCx0w4-EENnzJZg&s");


			planteRepo.saveAll(Arrays.asList(plante1, plante2, plante3));

		}
	}
}
