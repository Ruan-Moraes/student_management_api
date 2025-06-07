package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.request.StudentRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.StudentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StudentControllerDocs {
    @Operation(summary = "Find all students",
            description = "Retrieves a list of all students in the system.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all students",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<StudentResponseDTO>> findAll();

    @Operation(summary = "Find student by Id",
            description = "Retrieves a student by their unique identifier.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved student",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Student not found",
                            content = @Content()
                    )
            }
    )
    public ResponseEntity<StudentResponseDTO> findById(@PathVariable Long id);

    @Operation(summary = "Create a new student",
            description = "Creates a new student in the system.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created student",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data",
                            content = @Content()
                    )
            }
    )
    public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentRequestDTO studentRequestDTO);

    @Operation(summary = "Update student by Id",
            description = "Updates an existing student's information.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated student",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Student not found",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data",
                            content = @Content()
                    )
            }
    )
    public ResponseEntity<StudentResponseDTO> updateById(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO studentRequestDTO);

    @Operation(summary = "Find students with low frequency",
            description = "Retrieves a list of students whose frequency is below a specified threshold.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved students with low frequency",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<StudentResponseDTO>> findByLowFrequency(@RequestParam("frequency_below") Double frequency);

    @Operation(summary = "Delete student by Id",
            description = "Deletes a student from the system by their unique identifier.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted student"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Student not found",
                            content = @Content()
                    )
            }
    )
    public ResponseEntity<Void> deleteById(@PathVariable Long id);
}
