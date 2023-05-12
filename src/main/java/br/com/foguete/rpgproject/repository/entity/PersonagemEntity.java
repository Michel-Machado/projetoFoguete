package br.com.foguete.rpgproject.repository.entity;

import br.com.foguete.rpgproject.domain.Personagem;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.Instant;

@Document(collection = "rpg-personagens")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PersonagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id;
    private String name;
    private String race;
    private String player;
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;
    private Instant createdAt;
    private Instant updatedAt;

    public PersonagemEntity() {
    }

    public PersonagemEntity(String name, String race, String player, Integer strength, Integer dexterity,
                            Integer constitution, Integer intelligence, Integer wisdom, Integer charisma) {
        this.name = name;
        this.race = race;
        this.player = player;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    /*
* Criar método estático na classe PersonagemEntity, onde:
1 - retorno é do tipo PersonagemEntity
2 - recebe como parametro um objeto do tipo Personagem.
3 - o método deverá criar uma instancia de PersonagemEntity,
* com os valores do parametro recebido (Personagem)
* */

    public static PersonagemEntity recebePersonagem(Personagem personagem){
        String name = personagem.getNome();
        String race = personagem.getRaca();
        String player = personagem.getIdJogador();
        Integer strength = personagem.getForca();
        Integer dexterity = personagem.getDestreza();
        Integer constitution = personagem.getConstituicao();
        Integer intelligence = personagem.getInteligencia();
        Integer wisdom = personagem.getSabedoria();
        Integer charisma = personagem.getCarisma();

        PersonagemEntity personagemEntity = new PersonagemEntity(name,race, player, strength, dexterity,
                constitution, intelligence,wisdom,charisma);

        return personagemEntity;

    }

    public static PersonagemEntity atualizaPersonagem(PersonagemEntity personagemEntity, Personagem personagem){
        return personagemEntity
                .setName(personagem.getNome())
                .setRace(personagem.getRaca())
                .setPlayer(personagem.getIdJogador())
                .setStrength(personagem.getForca())
                .setDexterity(personagem.getDestreza())
                .setConstitution(personagem.getConstituicao())
                .setIntelligence(personagem.getInteligencia())
                .setWisdom(personagem.getSabedoria())
                .setCharisma(personagem.getCarisma());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public String getPlayer() {
        return player;
    }

    public Integer getStrength() {
        return strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public PersonagemEntity setId(String id) {
        id = id;
        return this;
    }

    public PersonagemEntity setName(String name) {
        this.name = name;
        return this;
    }

    public PersonagemEntity setRace(String race) {
        this.race = race;
        return this;
    }

    public PersonagemEntity setPlayer(String player) {
        this.player = player;
        return this;
    }

    public PersonagemEntity setStrength(Integer strength) {
        this.strength = strength;
        return this;
    }

    public PersonagemEntity setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
        return this;
    }

    public PersonagemEntity setConstitution(Integer constitution) {
        this.constitution = constitution;
        return this;
    }

    public PersonagemEntity setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public PersonagemEntity setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
        return this;
    }

    public PersonagemEntity setCharisma(Integer charisma) {
        this.charisma = charisma;
        return this;
    }

    public PersonagemEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PersonagemEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}


