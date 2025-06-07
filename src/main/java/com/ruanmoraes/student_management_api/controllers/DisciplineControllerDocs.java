package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.request.DisciplineRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.DisciplineResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DisciplineControllerDocs {

    @Operation(summary = "Find all disciplines", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all disciplines",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DisciplineResponseDTO.class)
                    )
            )
    })
    ResponseEntity<List<DisciplineResponseDTO>> findAll();

    @Operation(summary = "Find discipline by Id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved discipline",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DisciplineResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Discipline not found",
                    content = @Content()
            )
    })
    ResponseEntity<DisciplineResponseDTO> findById(@PathVariable Long id);


    @Operation(summary = "Create a new discipline", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully created discipline",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DisciplineResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Discipline name already exists",
                    content = @Content()
            )
    })
    ResponseEntity<DisciplineResponseDTO> create(@Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO);


    @Operation(summary = "Update discipline by Id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated discipline",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DisciplineResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Discipline not found",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Discipline name already exists",
                    content = @Content()
            )
    })
    ResponseEntity<DisciplineResponseDTO> updateById(
            @PathVariable Long id,
            @Valid @RequestBody DisciplineRequestDTO disciplineRequestDTO);


    @Operation(summary = "Delete discipline by Id", responses = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully deleted discipline",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Discipline not found",
                    content = @Content()
            )
    })
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}
