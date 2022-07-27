package com.example.report_20220726

abstract open class Hand (){

    abstract fun fight( value_com : String) : String


}

data class Paper (var value : String) : Hand() {

    val paper = value

    override fun fight(value_com: String): String {

        return when (value_com) {
            "가위" -> "졌다"
            "바위" -> "이겼다"
            "보" -> "비겼다"
            else -> throw IllegalArgumentException("문제발생")
        }
    }




}


data class Rock(var value: String) : Hand() {

    val rock = value

    override fun fight(value_com: String): String {

        return when (value_com) {
            "가위" -> "이겼다"
            "바위" -> "비겼다"
            "보" -> "졌다"
            else -> throw IllegalArgumentException("문제발생")
        }

    }
}

data class Scissors (var value : String) : Hand() {

    val scissors = value

    override fun fight(value_com: String): String {

        return when (value_com) {
            "가위" -> "비겼다"
            "바위" -> "졌다"
            "보" -> "이겼다"
            else -> throw IllegalArgumentException("문제발생")
        }

    }
}