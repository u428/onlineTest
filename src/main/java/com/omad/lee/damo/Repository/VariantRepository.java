package com.omad.lee.damo.Repository;

import com.omad.lee.damo.Model.Entity.Variants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variants, Long> {

    Variants findVariantsByVariantid(String variantid);
}
