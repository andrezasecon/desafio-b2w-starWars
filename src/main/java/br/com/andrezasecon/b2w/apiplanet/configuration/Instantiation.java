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

        Planet pandora = new Planet(null, "Pandora", "arid", "desert");
        Planet hoth = new Planet(null, "Hoth", "temperate", "grasslands, mountains");
        Planet naboo = new Planet(null, "Naboo", "arid", "mountains");

        planetRepository.saveAll(Arrays.asList(pandora, hoth, naboo));

    }
}
