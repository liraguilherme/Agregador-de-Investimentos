package kodetech.agregadorinvestimentos.controller;

//Nem sempre o que temos no BD queremos expor para nossa API por isso usamos DTO
//Definimos os campos que iremos receber na requisição para criação do usuario, colocamos só os campos que precisamos
public record CreateUserDto(String username, String email, String password) {




}
