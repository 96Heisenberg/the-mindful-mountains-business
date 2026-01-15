package com.themindfulmountains.business.api;

import com.themindfulmountains.business.dto.request.BookingRequest;
import com.themindfulmountains.business.dto.request.QueryRequest;
import com.themindfulmountains.business.dto.response.QueryResponse;
import com.themindfulmountains.business.mapper.QueryMapper;
import com.themindfulmountains.business.model.Booking;
import com.themindfulmountains.business.service.BookingService;
import com.themindfulmountains.business.service.PdfExportService;
import com.themindfulmountains.business.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryService service;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PdfExportService pdfExportService;

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

    @DeleteMapping("/{queryId}")
    public ResponseEntity<String> deleteQuery(@PathVariable String queryId) {
        service.deleteQuery(queryId);
        return ResponseEntity.ok("Query deleted successfully");
    }

    @PostMapping("/{queryId}/book")
    public ResponseEntity<Booking> bookQuery(
            @PathVariable String queryId,
            @RequestBody BookingRequest request
    ) {
        return ResponseEntity.ok(bookingService.bookQuery(queryId, request));
    }

    @PostMapping("/pdf/{queryId}")
    public ResponseEntity<String> generatePdf(@PathVariable String queryId) {
        try {
            return ResponseEntity.ok(pdfExportService.generateAndUploadPdf(queryId));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
