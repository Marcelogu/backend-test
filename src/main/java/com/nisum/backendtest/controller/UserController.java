package com.nisum.backendtest.controller;

import com.nisum.backendtest.dto.UserRequestDto;
import com.nisum.backendtest.dto.UserResponseDto;
import com.nisum.backendtest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@Tag(name="Usuarios", description = "API para manejar la creación de usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(description = "Recupera todos los usuarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "No autorizado para obtener la información",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuarios no encontrados",
                    content = @Content) })
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "Permite guardar los datos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario guardado con éxito",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "No autorizado para guardar usuarios",
                    content = @Content)})
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userDto){

        UserResponseDto user = userService.saveOrUpdateUser(null,userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @Operation(description = "Permite la edición de los datos de un usuario ya registrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado con éxito",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "No autorizado para editar usuarios",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content) })
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String id, @RequestBody UserRequestDto userDto){
        UserResponseDto user = userService.saveOrUpdateUser(id, userDto);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK): ResponseEntity.notFound().build();
    }
}
