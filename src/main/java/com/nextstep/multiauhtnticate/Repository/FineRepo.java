package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.FineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FineRepo extends JpaRepository<FineModel,String> {
}
