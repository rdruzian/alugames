
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers
import java.util.*

fun main() {
    //Declarações
    var meuJogo: Jogo? = null
    var meuInfoJogo: InfoJogo? = null
    var request: HttpRequest? = null
    val gson = Gson()

    val leitura = Scanner(System.`in`)
    print("Digite um código de jogo para buscar: ")
    val busca = leitura.nextLine()
    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client = HttpClient.newHttpClient()

    //Requisição http
    val resultrequest = runCatching {
        request = HttpRequest.newBuilder().uri(URI.create(endereco)).build()
    }
    resultrequest.onFailure {
        print("Erro ao buscar a url")
    }
    resultrequest.onSuccess {
        val response = client.send(request, BodyHandlers.ofString()).body()
        meuInfoJogo = gson.fromJson(response, InfoJogo::class.java)
    }

    //Cria objeto Jogo
    val result = runCatching {
        if(meuJogo != null) {
            meuJogo = Jogo(meuInfoJogo!!.info.title, meuInfoJogo!!.info.thumb)
        }
    }
    result.onSuccess {
        print("Deseja inserir descrição: ")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {
            print("Insira a descrição: ")
            val desc = leitura.nextLine()
            meuJogo?.descricao = desc
        } else {
            meuJogo?.descricao = meuJogo!!.titulo
        }
        println(meuJogo)
    }

}