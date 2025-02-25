    package com.netcracker.library.controller;

    import com.netcracker.library.exceptions.NotFoundException;
    import com.netcracker.library.model.BorrowingDto;
    import com.netcracker.library.model.SaveBorrowingRequest;
    import com.netcracker.library.service.BorrowingService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.Optional;

    @RequiredArgsConstructor
    @RestController
    @RequestMapping("/api/borrowings")
    public class BorrowingController {
        private final BorrowingService borrowingService;

        @GetMapping
        public ResponseEntity<?> getAllBorrowings() {
            try {
                var borrowings = borrowingService.getAll();
                return ResponseEntity.ok(borrowings);
            } catch (Exception e) {
                return createInternalServerErrorStatus(e.getMessage());
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<BorrowingDto> getBorrowingById(@PathVariable Long id) {
            Optional<BorrowingDto> borrowing = borrowingService.getById(id);
            return borrowing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<?> saveBorrowing(@RequestBody SaveBorrowingRequest request) {
            try {
                borrowingService.save(request);
                return ResponseEntity.status(HttpStatus.OK).build();
           } catch (NotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (Exception e) {
                return createInternalServerErrorStatus(e.getMessage());
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> updateBorrowing(@PathVariable Long id, @RequestBody SaveBorrowingRequest request) {
            try {
                borrowingService.update(id, request);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (NotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (Exception e) {
                return createInternalServerErrorStatus(e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteBorrowing(@PathVariable Long id) {
            try {
                borrowingService.deleteById(id);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return createInternalServerErrorStatus(e.getMessage());
            }
        }

        private ResponseEntity<?> createInternalServerErrorStatus(String message) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + message);
        }
    }
