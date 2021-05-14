package br.com.andrezasecon.b2w.apiplanet.configuration;


import br.com.andrezasecon.b2w.apiplanet.domain.Planet;
import br.com.andrezasecon.b2w.apiplanet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public void run(String... args) throws Exception {

        planetRepository.deleteAll();

        Planet tatooine = new Planet(null, "Tatooine", "arid", "desert", 0);
        Planet alderaan = new Planet(null, "Alderaan", "temperate", "grasslands, mountains", 0);
        Planet yavin = new Planet(null, "Yavin IV", "temperate, tropical", "jungle, rainforests", 0);
        Planet hoth = new Planet(null, "Hoth", "frozen", "tundra, ice caves, mountain ranges", 0);
        Planet degobah = new Planet(null, "Dagobah", "murky", "swamp, jungles", 0);
        Planet bespin = new Planet(null, "Bespin", "temperate", "gas giant", 0);
        Planet endor = new Planet(null, "Bespin", "temperate", "forests, mountains, lakes", 0);
        Planet naboo = new Planet(null, "Naboo", "temperate", "grassy hills, swamps, forests, mountains", 0);
        Planet coruscant = new Planet(null, "Coruscant", "temperate", "cityscape, mountains", 0);
        Planet kamino = new Planet(null, "Kamino", "temperate", "ocean", 0);

        planetRepository.saveAll(Arrays.asList(tatooine, alderaan, yavin, hoth, degobah, bespin, endor, naboo, coruscant, kamino));

    }
}
