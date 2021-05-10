package br.com.andrezasecon.b2w.apiplanet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PlanetDTO implements Serializable {
    private final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer filmsCount;


}