package br.com.foguete.rpgproject.adapter.in;

import br.com.foguete.rpgproject.adapter.in.dto.PersonagemDto;
import br.com.foguete.rpgproject.core.PersonagemPortIn;
import br.com.foguete.rpgproject.domain.Personagem;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/rpg/v1/personagem")
 public class HttpPersonagemAdapterIn {

    public PersonagemPortIn personagemPortIn;
    public HttpPersonagemAdapterIn(PersonagemPortIn personagemPortIn){

        this.personagemPortIn = personagemPortIn;
    }



    @GetMapping
    public ResponseEntity<String> findAllPersonagem(){
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public ResponseEntity<Void> createPersongem(@RequestBody @Valid PersonagemDto personagemDto,
                                                         @RequestHeader(name = "player-id", required = true)
                                                         String playerId){

        Personagem personagemObjeto = new Personagem();

        personagemObjeto.setNome(personagemDto.getName());
        personagemObjeto.setRaca(personagemDto.getRace());
        personagemObjeto.setForca(personagemDto.getStrength());
        personagemObjeto.setDestreza(personagemDto.getDexterity());
        personagemObjeto.setConstituicao(personagemDto.getConstitution());
        personagemObjeto.setInteligencia(personagemDto.getIntelligence());
        personagemObjeto.setSabedoria(personagemDto.getWisdom());
        personagemObjeto.setCarisma(personagemDto.getCharisma());

        personagemObjeto.setIdJogador(playerId);

       String idDoPersonagem = this.personagemPortIn.createPersonagem((personagemObjeto));

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(idDoPersonagem).toUri()).build();

    }

    @GetMapping("/{id}")
    public  ResponseEntity<PersonagemDto> findPersonagem(@PathVariable("id") String id,
                                                         @RequestHeader(name= "player-id", required = true)String playerId) {

        return  ResponseEntity.ok(PersonagemDto.from(this.personagemPortIn.findPersonagemPorIdEPlayerId(id, playerId)));
    }

        //Criar rota PUT, dado um id
    // onde o personagem será atualizado
    // regra 1 - só retornar o personagem do player-id correspondente
    // regra 1.1 - caso não encontre retornar 404 (not found) com body vazio
    // regra 2 - caso encontre, atualizar todos os valores do personagem
    // regra 3 - retornar status 204, no_content, sem body.
        @PutMapping("/{id}")
        public ResponseEntity<PersonagemDto> atualizaPersonagem(@RequestBody @Valid PersonagemDto personagemDto,
                                                                @PathVariable("id") String id,
                                                                @RequestHeader(name = "player-id", required = true)String playerId){
            Personagem personagem = new Personagem(personagemDto.getName()
                    ,personagemDto.getRace()
                    ,playerId
                    ,personagemDto.getStrength()
                    ,personagemDto.getDexterity()
                    ,personagemDto.getConstitution()
                    ,personagemDto.getIntelligence()
                    ,personagemDto.getWisdom()
                    ,personagemDto.getCharisma());

            this.personagemPortIn.atualizaPersonagem(id, personagem);

            return ResponseEntity.noContent().build();

        }

    //    Criar rota DELETE recebendo o id do personagem, e o playerIdok

    //    regra 1 - buscar o personagem no repositorio
    //    regra 1.1 - caso não exista retornar 404
    //    regra 2 - deletar o personagem

    //    regra 3 - em caso de sucesso, retornar status 204, no_content, e body vaziook

    @DeleteMapping("/{id}")
    public ResponseEntity<Personagem> deletaPersonagem(@PathVariable("id") String id,
                                                       @RequestHeader(name = "player-id", required = true)String playerId){
       this.personagemPortIn.deletaPersonagem(id, playerId);
        return ResponseEntity.noContent().build();
    }



}