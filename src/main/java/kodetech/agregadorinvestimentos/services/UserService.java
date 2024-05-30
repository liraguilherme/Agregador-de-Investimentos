package kodetech.agregadorinvestimentos.services;

import kodetech.agregadorinvestimentos.controller.CreateUserDto;
import kodetech.agregadorinvestimentos.controller.UpdateUserDto;
import kodetech.agregadorinvestimentos.entity.User;
import kodetech.agregadorinvestimentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){

        //Converter DTO -> ENTITY (Instant.now = Data de criação)
        var entity = new User(UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

       var userSaved = userRepository.save(entity);

       return userSaved.getUserId();
    }
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId)); //Faz a conversão

    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }


public void updateUserById(String userId, UpdateUserDto updateUserDto) {
    //Como é uma entidade que já existe no banco de dados precisamos fazer a verificação
    var id = UUID.fromString(userId);

    var userEntity = userRepository.findById(id); //Verifica se o user existe

    if (userEntity.isPresent()) {
        var user = userEntity.get();

        //Lógica de atualização
        if (updateUserDto.username() != null) {
            user.setUsername(updateUserDto.username());
        }
        if (updateUserDto.password() != null) {
            user.setPassword(updateUserDto.password());
        }
        //Identifica que já existe e faz um update
        userRepository.save(user);

    }
}

    public void deleteById(String userId){

        var id = UUID.fromString(userId);

        //Verificação se existe
        var userExists =  userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }

}
