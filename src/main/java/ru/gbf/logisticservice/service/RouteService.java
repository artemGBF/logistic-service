package ru.gbf.logisticservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gbf.logisticservice.dto.OrderDTO;
import ru.gbf.logisticservice.model.Route;

import java.util.HashMap;
import java.util.Map;

@Service
public class RouteService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GOOGLE_KEY = "AIzaSyAmsdty1p_vwop7fHXYgMDwMKXNhwCXIME";

    public Route getInitialRoute(OrderDTO orderDTO) throws JsonProcessingException {
        Map<String, Object> params = new HashMap<>();
        params.put("origin", orderDTO.getOriginAddr().getAddressString());
        params.put("destination", orderDTO.getOriginAddr().getAddressString());
        params.put("key", orderDTO.getOriginAddr().getAddressString());

        String jsonRoute = restTemplate.getForObject(
                "https://maps.googleapis.com/maps/api/directions/json",
                String.class,
                params
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonRoute);

        return null;

    }
}
