package com.uniandes.pruebatecnica.models.response;

import com.uniandes.pruebatecnica.models.StarShip;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StarshipsResponse {
    
    private int count;
    private String next;
    private String previous;
    private List<StarShip> results;
    
}
