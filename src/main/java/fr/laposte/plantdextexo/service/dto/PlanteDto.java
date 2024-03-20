package fr.laposte.plantdextexo.service.dto;

import lombok.Data;

@Data
public class PlanteDto {

	
	private Long id;
	private String nom;
	private String categorieLibelle;
	private String soleil;
	private String arrosage;
	private String imageUrl;
}
