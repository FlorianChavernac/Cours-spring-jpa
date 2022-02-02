package monprojet.dao;


import java.util.*;

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


    /**
     * Retourne la liste des pays
     * @return liste des pays
     */
    @Query(value="SELECT name FROM Country country", nativeQuery=true)
    public List<String> listePays();

    /**
     * Renvoie la liste des pays avec leur population
     * @return une map(pays, id)
     * 
     */
    public default Map<String, Integer> listePopulationPays(){
        Map<String, Integer> m = new HashMap<String, Integer>();
        List<String> liste = listePays();
        for (int i=0; i<liste.size(); i++) {
            m.put(liste.get(i), population(i+1));
        }
        return m;
    }

}
