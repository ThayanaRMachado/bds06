package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	@Query( nativeQuery = true, 
			value = "SELECT * FROM tb_review rev "
					+ "JOIN tb_movie mov ON rev.movie_id = mov.id "
					+ "JOIN tb_genre gen ON mov.genre_id = gen.id "
					+ "JOIN tb_user u ON rev.user_id = u.id "
					+ "JOIN tb_user_role urole ON u.id = urole.user_id "
					+ "JOIN tb_role rol ON urole.role_id = rol.id "
					+ "WHERE mov.id = :movieId")
	List<Review> findReviews (Long movieId);

}
