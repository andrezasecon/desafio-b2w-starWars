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

        Planet tatooine = new Planet(null, "Tatooine", "arid", "desert", 5);
        Planet alderaan = new Planet(null, "Alderaan", "temperate", "grasslands, mountains", 2);
        Planet yavin = new Planet(null, "Yavin IV", "temperate, tropical", "jungle, rainforests", 1);
        Planet hoth = new Planet(null, "Hoth", "frozen", "tundra, ice caves, mountain ranges", 6);
        Planet degobah = new Planet(null, "Dagobah", "murky", "swamp, jungles", 5);
        Planet bespin = new Planet(null, "Bespin", "temperate", "gas giant", 2);
        Planet endor = new Planet(null, "Bespin", "temperate", "forests, mountains, lakes", 4);
        Planet naboo = new Planet(null, "Naboo", "temperate", "grassy hills, swamps, forests, mountains", 0);
        Planet coruscant = new Planet(null, "Coruscant", "temperate", "cityscape, mountains", 4);
        Planet kamino = new Planet(null, "Kamino", "temperate", "ocean", 2);



        planetRepository.saveAll(Arrays.asList(tatooine, alderaan, yavin, hoth, degobah, bespin, endor, naboo, coruscant, kamino));

    }
}
