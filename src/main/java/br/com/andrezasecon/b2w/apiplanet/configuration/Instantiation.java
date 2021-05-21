package br.com.andrezasecon.b2w.apiplanet.configuration;


import br.com.andrezasecon.b2w.apiplanet.domains.Planet;
import br.com.andrezasecon.b2w.apiplanet.repositories.PlanetRepository;
import br.com.andrezasecon.b2w.apiplanet.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private SequenceGeneratorService generatorService;

    @Override
    public void run(String... args) throws Exception {

        planetRepository.deleteAll();

        generatorService.deleteSequence();


        Planet tatooine = new Planet("Tatooine", "arid", "desert");
        Planet alderaan = new Planet("Alderaan", "temperate", "grasslands, mountains");
        Planet yavin = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
        Planet hoth = new Planet("Hoth", "frozen", "tundra, ice caves, mountain ranges");
        Planet degobah = new Planet("Dagobah", "murky", "swamp, jungles");
        Planet bespin = new Planet("Bespin", "temperate", "gas giant");
        Planet endor = new Planet("Endor", "temperate", "forests, mountains, lakes");
        Planet naboo = new Planet("Naboo", "temperate", "grassy hills, swamps, forests, mountains");
        Planet coruscant = new Planet("Coruscant", "temperate", "cityscape, mountains");
        Planet kamino = new Planet("Kamino", "temperate", "ocean");

        planetRepository.saveAll(Arrays.asList(tatooine, alderaan, yavin, hoth, degobah, bespin, endor, naboo, coruscant, kamino));

    }
}
