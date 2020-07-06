package org.acme.repository;

import org.acme.entity.Reviews;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Qualifier("reviews")
@Transactional
public interface ReviewRepo extends JpaRepository<Reviews,Integer> {//, JpaSpecificationExecutor {

    public Reviews findByPersonId(int id);

}
