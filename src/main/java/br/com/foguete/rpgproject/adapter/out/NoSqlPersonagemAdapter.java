package br.com.foguete.rpgproject.adapter.out;

import br.com.foguete.rpgproject.adapter.in.exception.NotFoundException;
import br.com.foguete.rpgproject.domain.Personagem;
import br.com.foguete.rpgproject.repository.entity.PersonagemEntity;
import br.com.foguete.rpgproject.repository.entity.PersonagemRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class NoSqlPersonagemAdapter implements PersonagemAdapterOut {

    private final PersonagemRepository personagemRepository;

    public NoSqlPersonagemAdapter(PersonagemRepository personagemRepository) {
        this.personagemRepository = personagemRepository;
    }


    @Override
    public String criaPersonagem(Personagem personagem) {

       PersonagemEntity personagemEntity = PersonagemEntity.recebePersonagem(personagem);
       personagemEntity.setCreatedAt(Instant.now());
       PersonagemEntity personagemSalvo = this.personagemRepository.save(personagemEntity);

       return  personagemSalvo.getId();

    }

    @Override
    public Personagem findPersonagemPorIdEPlayerId(String id, String playerId) {
        Optional<PersonagemEntity> personagemEntityOptional = this.personagemRepository.findByIdAndPlayer(id, playerId);
        if (personagemEntityOptional.isEmpty()){
            throw new NotFoundException();

        }
        PersonagemEntity personagemEntity = personagemEntityOptional.get();
        return Personagem.from(personagemEntity);

    }

    public Optional<PersonagemEntity> findPersonagemByPlayerIdENome(String playerId, String nome){
        return this.personagemRepository.findByPlayerAndName(playerId, nome );

    }

    @Override
    public void atualizaPersonagemPorId(String id, Personagem personagem) {
        PersonagemEntity personagemEntity = this.personagemRepository.findByIdAndPlayer(id, personagem.getIdJogador())
                .orElseThrow(NotFoundException::new);

        PersonagemEntity personagemAtualizado = PersonagemEntity.atualizaPersonagem(personagemEntity, personagem);
        this.personagemRepository.save(personagemAtualizado);

    }

    @Override
    public void deletaPersonagem(String id, String playerId) {
        PersonagemEntity personagemEntity = this.personagemRepository.findByIdAndPlayer(id, playerId)
                .orElseThrow(NotFoundException::new);
        this.personagemRepository.delete(personagemEntity);

    }
}
