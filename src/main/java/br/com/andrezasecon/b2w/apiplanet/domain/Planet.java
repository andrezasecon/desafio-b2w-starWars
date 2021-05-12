package br.com.andrezasecon.b2w.apiplanet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "planets") //nome da collection no banco de dados
public class Planet implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer filmCount = 0;

}
