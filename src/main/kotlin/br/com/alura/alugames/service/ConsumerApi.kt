package br.com.alura.alugames.service

import br.com.alura.alugames.model.InfoGame
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumerApi {
    fun getGame(id: String): InfoGame? {
        var meuInfoGame: InfoGame? = null
        var request: HttpRequest? = null
        val gson = Gson()
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client = HttpClient.newHttpClient()

        //Requisição http
        val resultrequest = runCatching {
            request = HttpRequest.newBuilder().uri(URI.create(endereco)).build()
        }
        resultrequest.onFailure {
            print("Erro ao buscar a url")
        }
        resultrequest.onSuccess {
            val response = client.send(request, HttpResponse.BodyHandlers.ofString()).body()
            meuInfoGame = gson.fromJson(response, InfoGame::class.java)
        }

        return meuInfoGame
    }
}