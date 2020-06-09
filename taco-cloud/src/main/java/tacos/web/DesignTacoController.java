package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	@GetMapping
	public String showDesignForm(Model model) {
		List <Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP)
				);
		
		Type[] types = Ingredient.Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients, type));
		}

		model.addAttribute("taco", new Taco());
		
		return "design";
	}
	
	
	private List <Ingredient> filterByType(List <Ingredient> ingredients, Type type){
		return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
	}
	
	@PostMapping
	public String processDesign(Taco design) {
		log.info("Processing design: " + design);
		return "redirect:/orders/current";
	}
}