package br.com.andrezasecon.b2w.apiplanet.services;

import br.com.andrezasecon.b2w.apiplanet.domains.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class PlanetModelListener extends AbstractMongoEventListener<Planet> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public PlanetModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Planet> event) {
        if (event.getSource().getIdPlanet() < 1) {
            event.getSource().setIdPlanet(sequenceGenerator.generateSequence(Planet.SEQUENCE_NAME));
        }
    }
}

