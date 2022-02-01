package monprojet.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {

    /**
     * Calcule la population d'un pays quand son id est entré
     * @param id id du pays
     * @return la population du pays
     */
    @Query("SELECT SUM(city.population) AS unites FROM City city GROUP BY country_id HAVING country_id = :id ")
    public Integer population(Integer id);

}
