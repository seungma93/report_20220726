package com.example.report_20220726


open class Hand(var value: String) : HandValue() {
    // 어떤 모양의 값을 가지는지 정하는 함수
    fun shape(handValue: HandValue) {
        when (handValue) {
            is Rock -> this.value = "바위"
            is Scissors -> this.value = "가위"
            is Paper -> this.value = "보"
        }
    }
}

// 모양은 가위,바위,보 3가지로 한정하기 위한 클래스
sealed class HandValue {
    object Rock : HandValue()
    object Paper : HandValue()
    object Scissors : HandValue()
}













