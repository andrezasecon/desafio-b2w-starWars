package br.com.andrezasecon.b2w.apiplanet.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "planets") //nome da collection no banco MongoDB
public class Planet implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    private String id;
    @NotNull(message = "Enter a planet name")
    @NotEmpty(message = "Field name cannot be empty")
    private String name;


    @NotNull(message = "Enter a climate")
    @NotEmpty(message = "Field climate cannot be empty")
    private String climate;

    @NotNull(message = "Enter a terrain")
    @NotEmpty(message = "Field terrain cannot be empty")
    private String terrain;


    private Integer filmsAppearances;

}
