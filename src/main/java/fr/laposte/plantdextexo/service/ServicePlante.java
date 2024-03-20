package fr.laposte.plantdextexo.service;

import java.util.List;

import fr.laposte.plantdextexo.Model.Plante;
import fr.laposte.plantdextexo.service.dto.PlanteDto;

public interface ServicePlante {

	List<PlanteDto> listerLesPlantes();
	PlanteDto getOneById(Long id)throws Exception;
	PlanteDto ajouterPlante(PlanteDto planteDto, Long id) throws Exception;
	void supprimerPlanteById(Long id);
	PlanteDto modifierPlanteById(Long id, PlanteDto planteAModifier)throws Exception;
}