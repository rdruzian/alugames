import com.google.gson.annotations.SerializedName

class Jogo(
    @SerializedName("title")
    val titulo: String,
    @SerializedName("thumb")
    val capa: String
) {
    var descricao = ""
    override fun toString(): String {
        return "Jogo(titulo='$titulo', capa='$capa', descricao='$descricao')"
    }


}