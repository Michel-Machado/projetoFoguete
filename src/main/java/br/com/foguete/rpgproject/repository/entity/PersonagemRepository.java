package br.com.foguete.rpgproject.repository.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonagemRepository extends MongoRepository<PersonagemEntity, String> {

    Optional<PersonagemEntity> findByIdAndPlayer(String id, String playerId);
    Optional<PersonagemEntity> findByPlayerAndName(String player, String name);
}

