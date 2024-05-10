import java.lang.IllegalArgumentException

class Computer{
    val RANGE = (1..9)
    var first:Int
    var second:Int
    var third:Int
    var answer:Int = 0
    var win:Boolean = false

//    컴퓨터는 1에서 9까지 서로 다른 임의의 수 3개를 선택한다.
    init {
        first = RANGE.random()
        do {
            second = RANGE.random()
        } while (first == second)
        do {
            third = RANGE.random()
        } while (first == third || second == third)

    }

//    위 숫자 야구 게임에서 상대방의 역할을 컴퓨터가 한다. 컴퓨터는 1에서 9까지 서로 다른 임의의 수 3개를 선택한다.
//    게임 플레이어는 컴퓨터가 생각하고 있는 3개의 숫자를 입력하고, 컴퓨터는 입력한 숫자에 대한 결과를 출력한다.
//    이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료된다.
//    게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.
    fun run():Int{
        while (!win){
            input()
            win = printCheck(check(answer))
        }
        println("게임을 새로 시작하려면 1, 종료하려면 0을 제외한 다른 수를 입력하세요.")
        return readNum()
    }

//    잘못된 타입(널, 문자, 0이 맨 앞자리인 수)를 입력하는지 검사
    fun readNum():Int{
        val line:String? = readLine()
        if (line == null) error("잘못된 값을 입력하셨습니다")
        val answer:Int = line.toInt()
        if (Math.pow(10.0,line.length.toDouble()-1)>answer) error("잘못된 값을 입력하셨습니다")
        return answer
    }

//    게임 플레이어는 컴퓨터가 생각하고 있는 3개의 숫자를 입력.
//    범위를 벗어나거나 Int가 아닐 경우 에러 발생.
    fun input(){
        print("숫자를 입력해 주세요 : ")
        answer = readNum()
        if (answer>999 || answer <100){
//            println("IllegalArgumentException 잘못된 값을 입력하셨습니다.")
            error("잘못된 값을 입력하셨습니다")
        }
    }

//    볼판정을 한다.
    fun check(inputNum:Int):MutableList<Int>{
        val ballStrike = mutableListOf<Int>(0,0) //ball, strike
        var answer = inputNum
        var num:Int = answer/100
        if (first == num) ballStrike[1]+=1
        else if (num == second || num == third) ballStrike[0] +=1
        answer %= 100
        num = answer/10
        if (second == num) ballStrike[1]+=1
        else if (num == first || num == third) ballStrike[0] +=1
        answer%=10
        num = answer
        if (third == num) ballStrike[1]+=1
        else if (num == first || num == second) ballStrike[0] +=1

        return ballStrike
    }

    //볼판정 출력
    fun printCheck(ch:MutableList<Int>):Boolean{
        val ball:Int = ch[0]
        val strike:Int = ch[1]
        if (ball + strike == 0) {
            println("낫싱")
        }
        else if (ball == 0){
            println("${strike}스트라이크")
            if (strike == 3){
                println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
                return true
            }
        }
        else if (strike == 0){
            println("${ball}볼")
        }
        else {
            println("${ball}볼 ${strike}스트라이크")
        }
        return false
    }

}

//    게임의 시작과 반복, 종료 구현.
//    사용자가 잘못된 값을 입력할 경우 IllegalArgumentException 을 발생시킨 후 애플리케이션은 종료
fun main(){
    var start = 1
    while(start == 1){
        val computer = Computer()
        try {
            start = computer.run()
        }catch (e:Exception) {
            throw IllegalArgumentException("잘못된 값을 입력하셨습니다")
        }
    }
}


