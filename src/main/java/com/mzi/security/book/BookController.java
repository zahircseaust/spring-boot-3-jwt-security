package com.mzi.security.book;

import com.mzi.security.advice.BookNotFoundException;
import com.mzi.security.advice.ResourceNotFoundException;
import com.mzi.security.response.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody BookRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<ResponseApi<List<Book>>> getAllBooks() {
        List<Book> books = service.findAll();
        // Throw exception if no books are found
        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found");
        }
        // Return the list of books with a success message
        ResponseApi<List<Book>> response = new ResponseApi<>(
                true,
                HttpStatus.OK,
                "Data fetched successfully",
                books
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<Book>> getBookById(@PathVariable Integer id) {
        // Now findById returns Optional<Book>, allowing orElseThrow
        Book book = service.findById(id);

        // If the book is found, return the success response
        ResponseApi<Book> response = new ResponseApi<>(
                true,
                HttpStatus.OK,
                "Book fetched successfully",
                book
        );
        return ResponseEntity.ok(response);
    }
}
