package fr.laposte.plantdextexo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.laposte.plantdextexo.Model.Categorie;
import fr.laposte.plantdextexo.Model.Plante;
import fr.laposte.plantdextexo.Repository.CategorieRepository;
import fr.laposte.plantdextexo.Repository.PlanteRepository;
import fr.laposte.plantdextexo.service.ServicePlante;
import fr.laposte.plantdextexo.service.dto.CategorieDto;
import fr.laposte.plantdextexo.service.dto.PlanteDto;

@Service
public class ServicePlanteImpl implements ServicePlante {

	@Autowired
	private PlanteRepository planteRepository;

	@Autowired
	private CategorieRepository categorieRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<PlanteDto> listerLesPlantes() {
		List<Plante> allPlantes = planteRepository.findAll();
		List<PlanteDto> listDto = allPlantes.stream().map(p -> modelMapper.map(p, PlanteDto.class))
				.collect(Collectors.toList());
		return listDto;
	}

	@Override
	public PlanteDto getOneById(Long id) throws Exception {
		Plante plante = planteRepository.findById(id).orElseThrow(() -> new Exception("pas de plante " + id));
		PlanteDto planteDto = modelMapper.map(plante, PlanteDto.class);
		return planteDto;

	}

	@Override
	public PlanteDto ajouterPlante(PlanteDto planteDto, Long id) throws Exception {
		Categorie categorie = categorieRepository.findById(id)
				.orElseThrow(() -> new Exception("pas de categorie " + id));

		Plante planteModel = modelMapper.map(planteDto, Plante.class);

		planteModel.setCategorie(categorie);
		planteRepository.save(planteModel);
		return modelMapper.map(planteModel, PlanteDto.class);
	}

	@Override
	public void supprimerPlanteById(Long id) {
		planteRepository.deleteById(id);

	}

	@Override
	public PlanteDto modifierPlanteById(Long id, PlanteDto planteModifier) throws Exception {

		Plante plante = planteRepository.findById(id).orElseThrow(() -> new Exception("pas de plante " + id));
		Categorie categorie = categorieRepository.findByLibelle(planteModifier.getCategorieLibelle());

		plante = modelMapper.map(planteModifier, Plante.class);

		plante.setCategorie(categorie);
		plante.setId(id);

		return modelMapper.map(planteRepository.save(plante), PlanteDto.class);

	}

}
