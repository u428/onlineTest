package com.omad.lee.damo.Repository;

import com.omad.lee.damo.Model.Entity.Priviliges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriviligesRepository extends JpaRepository<Priviliges, Long> {
}
