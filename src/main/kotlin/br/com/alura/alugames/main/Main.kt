package br.com.alura.alugames.main

import br.com.alura.alugames.model.Game
import br.com.alura.alugames.model.InfoGame
import br.com.alura.alugames.service.ConsumerApi
import java.util.*

fun main() {
    //Declarações
    var meuGame: Game? = null
    var meuInfoGame: InfoGame?
    val service = ConsumerApi()

    val leitura = Scanner(System.`in`)
    print("Digite um código de jogo para buscar: ")
    val busca = leitura.nextLine()

    meuInfoGame = service.getGame(busca)

    //Cria objeto br.com.alura.alugames.modelo.Jogo
    val result = runCatching {
        if(meuGame != null) {
            meuGame = Game(meuInfoGame!!.info.title, meuInfoGame!!.info.thumb)
        }
    }
    result.onSuccess {
        print("Deseja inserir descrição: ")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {
            print("Insira a descrição: ")
            val desc = leitura.nextLine()
            meuGame?.description = desc
        } else {
            meuGame?.description = meuGame!!.title
        }
        println(meuGame)
    }

}