package br.com.alura.alugames.model

data class Game(
    val title: String,
    val thumb: String
) {
    var description: String? = ""
    override fun toString(): String {
        return "Game(titulo='$title', capa='$thumb', descricao='$description')"
    }


}