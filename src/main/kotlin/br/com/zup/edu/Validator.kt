package br.com.zup.edu

public fun validaCpf(cpf:String): Boolean {
    val verificadoresPrimeiroDigito = arrayListOf(10, 9, 8, 7, 6, 5, 4, 3, 2)
    val verificadoresSegundoDigito = arrayListOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)
    val digitoverficadorOne = cpf.substring(12, 13)
    val one = retornainteiro(digitoverficadorOne[digitoverficadorOne.length - 1])
    val digitoverficadortwo = cpf.substring(13, 14)
    val two = retornainteiro(digitoverficadortwo[digitoverficadortwo.length - 1])

    val primeiroDigito = cpf
        .replace(".", "")
        .replace("-", "").substring(0, 9)
    val digitos = toIntList(primeiroDigito)

    var valor = 0
    digitos.forEachIndexed { index, i ->
        valor += i * verificadoresPrimeiroDigito[index]
    }

    val segundoDigito = cpf
        .replace(".", "")
        .replace("-", "").substring(0, 10)

    val digitosVerificadorDois = toIntList(segundoDigito)

    var valorDois = 0
    digitosVerificadorDois.forEachIndexed { index, i ->
        valorDois += i * verificadoresSegundoDigito[index]
    }
    var validOneDigit=false
    var validTwoDigit=false
    if ((valor * 10) % 11 == one ) {
        validOneDigit=true
        println(valor)
    }
    if ((valorDois * 10) % 11 == two){
        validTwoDigit=true
        println(valorDois)
    }
    return validOneDigit && validTwoDigit

}

private fun toIntList(replace: String): MutableList<Int> {
    val a= mutableListOf<Int>()
    replace.forEach {
       a.add( retornainteiro(it))
    }
    return a;
}
fun retornainteiro(it: Char):Int{
    if (it.equals('0')) {
       return 0
    } else if (it.equals('1')) {
       return 1
    } else if (it.equals('2')) {
       return 2
    } else if (it.equals('3')) {
       return 3
    } else if (it.equals('4')) {
       return 4
    } else if (it.equals('5')) {
       return 5
    } else if (it.equals('6')) {
       return 6
    } else if (it.equals('7')) {
       return 7
    } else if (it.equals('8')) {
       return 8
    } else {
        return 9
    }

}






