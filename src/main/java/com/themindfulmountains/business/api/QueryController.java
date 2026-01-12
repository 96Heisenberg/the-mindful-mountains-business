package com.themindfulmountains.business.api;

import com.themindfulmountains.business.dto.request.QueryRequest;
import com.themindfulmountains.business.dto.response.QueryResponse;
import com.themindfulmountains.business.mapper.QueryMapper;
import com.themindfulmountains.business.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryService service;

    /**
     * Raise a new query for a customer
     */
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<QueryResponse> raiseQuery(
            @PathVariable String customerId,
            @RequestBody QueryRequest request
    ) {
        QueryResponse response = service.raiseQuery(request, customerId);
        return ResponseEntity.ok(response);
    }


    /**
     * Get all queries (Admin use)
     */
    @GetMapping("/all")
    public ResponseEntity<List<QueryResponse>> getAllQueries() {
        return ResponseEntity.ok(
                service.getAllQueries()
                        .stream()
                        .map(QueryMapper::toResponse)
                        .toList()
        );
    }

    /**
     * Get query by queryId
     */
    @GetMapping("/{queryId}")
    public ResponseEntity<QueryResponse> getQueryById(
            @PathVariable String queryId
    ) {
        return ResponseEntity.ok(
                QueryMapper.toResponse(service.getQueryById(queryId))
        );
    }


    /**
     * Get all queries raised by a customer
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<QueryResponse>> getQueriesByCustomerId(
            @PathVariable String customerId
    ) {
        return ResponseEntity.ok(service.getQueriesByCustomerId(customerId).stream()
                .map(QueryMapper::toResponse)
                .toList());
    }

    /**
     * Update query by queryId
     */
    @PutMapping("/{queryId}")
    public ResponseEntity<String> updateQuery(
            @PathVariable String queryId,
            @RequestBody QueryRequest queryItinerary
    ) {
        QueryResponse response = service.updateQuery(queryId, queryItinerary);
        return ResponseEntity.ok("Query updated successfully!");
    }
}
