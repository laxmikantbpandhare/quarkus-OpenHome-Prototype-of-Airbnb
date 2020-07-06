package org.acme.repository;

import org.acme.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestJpaRepo extends JpaRepository<Test, Integer> {

}
