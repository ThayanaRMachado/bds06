package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieDTOGenre;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;

	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		Genre genre = genreRepository.getOne(entity.getGenre().getId());
		entity.setGenre(genre);
		return new MovieDTOGenre(entity);
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
		try {
			Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
			Page<Movie> page = repository.find(genre, pageable);
			repository.findMoviesAndGenres(page.getContent());
			return page.map(m -> new MovieDTO(m));
		} 
		catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Genre id not found!");
		}
	}

}
