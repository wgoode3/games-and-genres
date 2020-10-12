package com.hygogg.games.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hygogg.games.models.Game;
import com.hygogg.games.models.Genre;
import com.hygogg.games.services.GameService;


@Controller
public class HomeController {
	
	private static GameService gameServ;
	
	public HomeController(GameService gameServ) {
		this.gameServ = gameServ;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newGame", new Game());
		model.addAttribute("newGenre", new Genre());
		model.addAttribute("allGames", gameServ.getGames());
		model.addAttribute("allGenres", gameServ.getGenres());
		return "index.jsp";
	}
	
	@PostMapping("/games")
	public String newGame(@Valid @ModelAttribute("newGame") Game newGame, BindingResult result) {
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			gameServ.create(newGame);
			return "redirect:/";
		}
	}
	
	@PostMapping("/genres")
	public String newGenre(@Valid @ModelAttribute("newGenre") Genre newGenre, BindingResult result) {
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			gameServ.create(newGenre);
			return "redirect:/";
		}
	}
	
	@PostMapping("/add_genre")
	public String addGenreToGame(@RequestParam("game_id") Long gameId, @RequestParam("genre_id") Long genreId) {
		Game theGame = gameServ.getGame(gameId);
		Genre theGenre = gameServ.getGenre(genreId);
		List<Genre> gameGenres = theGame.getGenres();
		gameGenres.add(theGenre);
		gameServ.saveGame(theGame);
		return "redirect:/";
	}
	
	// alternative / better? way to create games with genres
	@GetMapping("/games/new")
	public String newGamePlus(Model model) {
		model.addAttribute("newGamePlus", new Game());
		model.addAttribute("allGames", gameServ.getGames());
		return "game.jsp";
	}
	
	@PostMapping("/games/new")
	public String createGameWithGenres(@Valid @ModelAttribute("newGamePlus") Game newGamePlus, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("allGames", gameServ.getGames());
			return "game.jsp";
		} else {
			gameServ.createGameWithGenres(newGamePlus);
			return "redirect:/games/new";
		}
	}
	
}
