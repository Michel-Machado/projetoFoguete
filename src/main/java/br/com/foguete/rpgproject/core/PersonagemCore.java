package br.com.foguete.rpgproject.core;

import br.com.foguete.rpgproject.domain.Personagem;

public class PersonagemCore {
    public void ciacaoPersonagem(){
        Personagem personagem1 = new Personagem()
                .setCarisma(5)
                .setConstituicao(7)
                .setDestreza(15)
                .setForca(13)
                .setInteligencia(11)
                .setSabedoria(10)
                .setRaca("halfling")
                .setNomeDoJogador("Michel")
                .setNome("Frodo")
                ;

    }
    Personagem personagem2 = new Personagem("Conan", "Humano","Michel",15, 15, 12, 10, 9,8)
}
