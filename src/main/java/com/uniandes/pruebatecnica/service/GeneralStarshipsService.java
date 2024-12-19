package com.uniandes.pruebatecnica.service;

import com.uniandes.pruebatecnica.controller.dto.GeneralStarshipDTO;
import com.uniandes.pruebatecnica.mapper.GeneralStarshipsMapper;
import com.uniandes.pruebatecnica.model.StarShip;
import com.uniandes.pruebatecnica.model.response.StarshipsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class GeneralStarshipsService {

    private final String urlBase = "https://swapi.py4e.com/api/starships/";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GeneralStarshipsMapper generalStarshipsMapper;

    public GeneralStarshipDTO getSpecificStarchip (int id){
        ResponseEntity<StarShip> response = restTemplate.getForEntity(urlBase + id, StarShip.class);
        return generalStarshipsMapper.toGeneralStarshipDTO(response.getBody());
    }
    
    public Page<GeneralStarshipDTO> getGeneralStarships(Pageable pageable) {

        Pageable fixedPageable = PageRequest.of(pageable.getPageNumber(), 10);
        int page = fixedPageable.getPageNumber() + 1;
        String nextUrl = urlBase + "?page=" + page;
        StarshipsResponse allStarships = getAllStarships(nextUrl);

        return new PageImpl<>(
                generalStarshipsMapper.toGeneralStarshipsDTO(allStarships.getResults()),
                fixedPageable,
                allStarships.getCount());
    }

    //Metodo para consumir la pagina actual de la Api
    private StarshipsResponse getAllStarships(String nextPageUrl) {
        ResponseEntity<StarshipsResponse> response = restTemplate.getForEntity(nextPageUrl, StarshipsResponse.class);
        return response.getBody();
    }

//    public Page<GeneralStarshipDTO> getGeneralStarships(Pageable pageable) {
//
//        List<GeneralStarshipDTO> allStarshipsDTO = generalStarshipsMapper.toGeneralStarshipDTO(getAllStarships());
//        allStarshipsDTO = pagination(pageable, allStarshipsDTO);
//        return new PageImpl<>(allStarshipsDTO, pageable, allStarshipsDTO.size());
//    }
//
//    //Metodod para entregar respuesta paginada
//    private List<GeneralStarshipDTO> pagination(Pageable pageable, List<GeneralStarshipDTO> allStarshipsDTO) {
//
//        // Calcular total de páginas
//        int totalElements = allStarshipsDTO.size();
//        int totalPages = (int) Math.ceil((double) totalElements / pageable.getPageSize());
//
//        // Validar si el número de página supera el total de páginas
//        if (pageable.getPageNumber() >= totalPages) {
//            int pageNumber = pageable.getPageNumber() + 1;
//            throw new BadRequestException("La página solicitada " + pageNumber
//                    + " no existe. El total de páginas es " + totalPages + ".");
//        }
//
//        //Si es valido, construir paginación
//        int start = (int) pageable.getOffset();
//        int end = Math.min((start + pageable.getPageSize()), allStarshipsDTO.size());
//        List<GeneralStarshipDTO> paginatedList = allStarshipsDTO.subList(start, end);
//
//        return paginatedList;
//    }
//
//    //Metodo para consumir todas las paginas e la Api
//    private List<StarShip> getAllStarships() {
//        String nextPageUrl = url;
//        List<StarShip> allStarships = new ArrayList<>();
//        while (nextPageUrl != null) {
//            ResponseEntity<StarshipsResponse> response = restTemplate.getForEntity(nextPageUrl, StarshipsResponse.class);
//            if (response != null) {
//                allStarships.addAll(response.getBody().getResults());
//                nextPageUrl = response.getBody().getNext();
//                continue;
//            }
//            break;
//        }
//        return allStarships;
//    }
}
