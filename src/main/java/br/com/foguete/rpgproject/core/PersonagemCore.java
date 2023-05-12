package br.com.foguete.rpgproject.core;

import br.com.foguete.rpgproject.adapter.in.exception.BusinessException;
import br.com.foguete.rpgproject.adapter.out.PersonagemAdapterOut;
import br.com.foguete.rpgproject.domain.Personagem;
import br.com.foguete.rpgproject.repository.entity.PersonagemEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class  PersonagemCore implements PersonagemPortIn {

    private final PersonagemAdapterOut personagemAdapterOut;
    public PersonagemCore(PersonagemAdapterOut personagemAdapterOut){
        this.personagemAdapterOut = personagemAdapterOut;
    }

    public void criacaoPersonagem(){
    }

    @Override
    public String createPersonagem(Personagem personagem) {
        Optional<PersonagemEntity> personagemPorPlayerIdENome  = this.personagemAdapterOut.findPersonagemByPlayerIdENome(personagem.getIdJogador(),
                personagem.getNome());
        if(personagemPorPlayerIdENome.isPresent())    {
            throw new BusinessException("Personagem " + personagem.getNome() + "já existe.");
        }
        return  this.personagemAdapterOut.criaPersonagem(personagem);

    }

    @Override
    public Personagem findPersonagemPorIdEPlayerId(String id, String playerId) {
        Personagem personagem = this.personagemAdapterOut.findPersonagemPorIdEPlayerId(id, playerId);
        return personagem;
    }

    @Override
    public void atualizaPersonagem(String id, Personagem personagem) {

        Optional<PersonagemEntity> personagemPorPlayerIdENome =
                this.personagemAdapterOut.findPersonagemByPlayerIdENome(personagem.getIdJogador(), personagem.getNome());

        if (personagemPorPlayerIdENome.isEmpty()){
            this.personagemAdapterOut.atualizaPersonagemPorId(id, personagem);
            return;
        }
        if (personagemPorPlayerIdENome.get().getId().equals(id)){
            this.personagemAdapterOut.atualizaPersonagemPorId(id, personagem);
            return;
        }
        throw new BusinessException("Personagem "+ personagemPorPlayerIdENome.get().getName() + " já existe");


    }

    //    regra 1 - buscar o personagem no repositorio
    //    regra 1.1 - caso não exista retornar 404
    @Override
    public void deletaPersonagem(String id, String playerId) {
               this.personagemAdapterOut.deletaPersonagem(id, playerId);
    }
}


