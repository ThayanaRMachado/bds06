package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Movie;

public class MovieDTOGenre extends MovieDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private GenreDTO genre;

	public MovieDTOGenre() {
	}

	public MovieDTOGenre(Movie entity) {
		super(entity);
		this.genre = new GenreDTO(entity.getGenre());
	}

	public MovieDTOGenre(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis,
			GenreDTO genre) {
		super(id, title, subTitle, year, imgUrl, synopsis);
		this.genre = new GenreDTO();
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}

}
