package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class GenreService {

	@Autowired
	private GenreRepository repository;

	@Transactional(readOnly = true)
	public List<GenreDTO> findAll() {
		try {
		return repository.findAll().stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
		}
		catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Genres not found.");
		}
	}

}
