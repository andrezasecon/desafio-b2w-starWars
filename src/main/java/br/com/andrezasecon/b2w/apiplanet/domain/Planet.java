package br.com.andrezasecon.b2w.apiplanet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.io.Serializable;

 // Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "planets") //nome da collection no banco de dados
public class Planet implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
}
