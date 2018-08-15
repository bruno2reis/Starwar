package br.com.starwars.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.starwars.models.Planeta;

@Controller
public class PlanetaController {
	
	@GetMapping("/planeta/cadastrar")
	public String cadastrar(Model model)
	{
		model.addAttribute("planeta", new Planeta());
		return "planeta/cadastrar";
	}
}
