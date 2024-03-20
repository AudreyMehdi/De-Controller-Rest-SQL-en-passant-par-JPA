package fr.laposte.plantdextexo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.laposte.plantdextexo.Model.Categorie;
import fr.laposte.plantdextexo.Repository.CategorieRepository;
import fr.laposte.plantdextexo.service.ServiceCategorie;
import fr.laposte.plantdextexo.service.dto.CategorieDto;

@Service
public class ServiceCategorieImpl implements ServiceCategorie {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CategorieDto> listerLesCategories() {
	
		List<Categorie> categorie = categorieRepository.findAll(); 
		List<CategorieDto> listDto = categorie.stream().map(p -> modelMapper.map(p, CategorieDto.class)) 
				.collect(Collectors.toList());
		return listDto;
	}

	@Override
	public CategorieDto getOneById(Long id) throws Exception {
		
		Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new Exception("pas de categorie"+ id));
		CategorieDto categorieDto = modelMapper.map(categorie, CategorieDto.class);
				return categorieDto;
	}

	@Override
	public CategorieDto ajouterCategorie(CategorieDto categorieDto) {
	   Categorie categorieModel = modelMapper.map(categorieDto, Categorie.class);
	   return modelMapper.map(categorieRepository.save(categorieModel), CategorieDto.class); // les repo ne connaissent que les type "normaux"
	}

	@Override
	public void supprimerCategorieById(Long id) {
		
	  categorieRepository.deleteById(id);
	
		
	}

	@Override
	public void modifierCategorieById(Long id, Categorie categorieAModifier) throws Exception {
		
		Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new Exception("pas de categorie"+ id));
		categorieRepository.delete(categorie);
		
		Categorie categorieNouvelle = modelMapper.map(categorieAModifier, Categorie.class);
		   modelMapper.map(categorieRepository.save(categorieNouvelle), CategorieDto.class);	
	}
	
	
	
	
}
