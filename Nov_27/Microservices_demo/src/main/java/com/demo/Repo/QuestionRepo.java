package com.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.demo.entity.Question;
import org.springframework.data.domain.Pageable;   

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query("SELECT q.id FROM Question q WHERE q.category = :category ORDER BY function('RAND')")
    List<Integer> findRandomQuestionsByCategory(
            @Param("category") String category,int numQ
    );
}
