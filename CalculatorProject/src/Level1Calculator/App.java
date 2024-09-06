package Level1Calculator;

//클래스 없이 연산하기
// 필수 기능
// 양의 정수 (0 포함) 입력받기
// 사칙연산 +, -, *, / 입력받기
// 위의 입력받은 양의 정수 2개와 사칙연산 기호를 사용해 연산하기
// exit 문자열을 입력하기 전까지 계속해서 계산을 진행하기

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String [] operators = {"+", "-", "*", "/"};
        String operator = null;
        int answer = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("계산식을 입력해주세요. (ex 12+34)");

        while(true){
            // replaceAll을 사용하여 계산식의 공백 제거
            // "\\s+" : 모든 공백을 찾는다.
            // "\\s+", "" : 모든 공백을 찾아 ""로 치환 즉, 모든 공백을 제거한다.
            String input = sc.nextLine().replaceAll("\\s+", "");

            // exit 입력 시 계산기 종료
            if(input.equals("exit")){
                System.out.println("사용자의 요청으로 계산기가 종료됩니다.");
                break;
            }

            // 사칙연산 찾기
            for (String op : operators) {
                // contains를 사용하여 input에 operators에 들어있는 사칙연산이 있다면 해당 연산자를 operator 값으로 설정
                if (input.contains(op)){
                    operator = op;
                    break;
                }
            }
            // operator 사칙연산을 기준으로 앞/뒤로 나누어 배열화
            String[] input2 = input.split("\\s*\\Q" + operator + "\\E\\s*");
            // num1과 num2를 지정
            int num1 = Integer.parseInt(input2[0]);
            int num2 = Integer.parseInt(input2[1]);

            // 오류 메세지
            // num1이나 num2 둘 중 하나의 값이라도 음수인 경우 메세지 출력
            if (num1<0 || num2<0){
                System.out.println("양의 정수만 입력이 가능합니다.");
                continue;
            }
            // 나눗셈 연산 분모 0인 경우 메세지 출력
            if (operator.equals("/") && num2==0){
                System.out.println("나눗셈 연산에서 분모에 0이 입력될 수 없습니다.");
                continue;
            }

            // 입력된 계산식 표기
            System.out.println("입력된 계산식 : " + num1 + operator + num2);

            // 입력된 사칙연산 기호에 따라 switch를 통하여 각 계산식을 수행
            switch (operator) {
                case "+":
                    answer = num1 + num2;
                    break;
                case "-":
                    answer = num1 - num2;
                    break;
                case "*":
                    answer = num1 * num2;
                    break;
                case "/":
                    answer = num1 / num2;
                    break;
                default:
            }

            // 출력값 표기
            System.out.println("결과 출력 :" + answer);
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");

        }
    }
}
