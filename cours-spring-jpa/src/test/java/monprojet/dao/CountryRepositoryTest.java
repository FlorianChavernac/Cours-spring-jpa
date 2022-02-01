package monprojet.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import monprojet.entity.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryDAO;

    @Test
    void lesNomsDePaysSontTousDifferents() {
        log.info("On vérifie que les noms de pays sont tous différents ('unique') dans la table 'Country'");

        Country paysQuiExisteDeja = new Country("XX", "France");
        try {
            countryDAO.save(paysQuiExisteDeja); // On essaye d'enregistrer un pays dont le nom existe

            fail("On doit avoir une violation de contrainte d'intégrité (unicité)");
        } catch (DataIntegrityViolationException e) {
            // Si on arrive ici c'est normal, l'exception attendue s'est produite
        }
    }

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Country'");
        int combienDePaysDansLeJeuDeTest = 3 + 1; // 3 dans data.sql, 1 dans test-data.sql
        long nombre = countryDAO.count();
        assertEquals(combienDePaysDansLeJeuDeTest, nombre, "On doit trouver 4 pays");
    }

    @Test
    void onSaitCompterLaPopulation() {
        log.info("On compte la population d'un pays");
        Integer populationPays1 = 12; // 3 dans data.sql, 1 dans test-data.sql
        Integer populationPays2 = 18; // 3 dans data.sql, 1 dans test-data.sql
        Integer populationPays3 = 27; // 3 dans data.sql, 1 dans test-data.sql
        Integer nombre1 = countryDAO.population(1);
        Integer nombre2 = countryDAO.population(2);
        Integer nombre3 = countryDAO.population(3);

        assertEquals(populationPays1, nombre1, "On doit trouver 12 habitants");
        assertEquals(populationPays2, nombre2, "On doit trouver 18 habitants");
        assertEquals(populationPays3, nombre3, "On doit trouver 27 habitants");

    }

}
