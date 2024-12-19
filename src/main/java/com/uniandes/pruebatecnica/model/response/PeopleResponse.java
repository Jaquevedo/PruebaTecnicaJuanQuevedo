package com.uniandes.pruebatecnica.model.response;

import com.uniandes.pruebatecnica.model.Person;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PeopleResponse {

    private int count;
    private String next;
    private String previous;
    private List<Person> results;

}
