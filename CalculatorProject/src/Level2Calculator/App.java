package Level2Calculator;

//클래스 없이 연산하기
// 필수 기능
// 양의 정수 (0 포함) 입력받기
// 사칙연산 +, -, *, / 입력받기
// 위의 입력받은 양의 정수 2개와 사칙연산 기호를 사용해 연산하기
// exit 문자열을 입력하기 전까지 계속해서 계산을 진행하기

// 사칙연산을 수행 후, 결과값 반환 메서드 구현 & 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
//  * 사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
// Lv1에서 구현한 App 클래스의 main 메서드에 Calculator 클래스가 활용될 수 있도록 수정
// App 클래스의 main 메서드에서 Calculator 클래스의 연산 결과를 저장하고 있는 컬렉션 필드에 직접 접근하지 못하도록 수정(캡슐화)
// Calculator 클래스에 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능을 가진 메서드를 구현한 후
// App 클래스의 main 메서드에 삭제 메서드가 활용될 수 있도록 수정

import java.util.Scanner;
import java.util.List;

public class App {

    public static void main(String[] args) {

        String [] operators = {"+", "-", "*", "/"};

        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
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
            // List 입력 시 연산결과 리스트 출력
            if(input.equals("list")){
                List<Integer> answers = calculator.getAnswers();
                System.out.println("연산 결과 기록 : ");
                for (int num : answers) {
                    System.out.println(num);
                }
                System.out.println("계산기 종료 : exit");
                System.out.println("가장 오래된 연산기록 삭제 : remove");
                System.out.println("계속 계산하려면 계산식을 입력하여 주세요.");
                continue;
            }
            // remove 입력 시 가장 먼저 연산된 기록 삭제12
            if(input.equals("remove")){
                List<Integer> answers = calculator.getAnswers();
                if (answers.isEmpty()) {
                    System.out.println("삭제할 기록이 존재하지 않습니다.");
                } else {
                    int oldAnswer = answers.get(0);
                    System.out.println("가장 오래된 연산기록 " + oldAnswer + "이(가) 삭제되었습니다.");
                    calculator.removeAnswer();
                }
                System.out.println("계산기 종료 : exit");
                System.out.println("연산 결과 기록 보기 : list");
                System.out.println("가장 오래된 연산기록 삭제 : remove");
                System.out.println("계속 계산하려면 계산식을 입력하여 주세요.");
                continue;
            }

            // 사칙연산 찾기
            int operatorIndex = -1;
            char operator = '\0';
            for (String op : operators) {
                // contains를 사용하여 input에 operators에 들어있는 사칙연산이 있다면 해당 연산자를 operator 값으로 설정
                if (input.contains(op)){
                    // indexOf를 사용하여 해당 연산자의 위치를 operatorIndex 값으로 설정
                    operatorIndex = input.indexOf(op);
                    operator = op.charAt(0);
                    break;
                }
            }

            // 오류 메세지
            // 연산자가 없는 경우
            if (operatorIndex == -1){
                System.out.println("연산자가 존재하지 않습니다.");
                continue;
            }
            // 연산자가 두 개 이상 포함된 경우
            // 연산자를 기준으로 문자열을 분리
            // 주어진 수가 음수인 경우에도 연산자(-)가 들어가므로 같이 처리
            String[] parts = input.split("[+\\-*/]");
            if (parts.length != 2) {
                System.out.println("입력된 피연산자가 음수이거나 두 개 이상의 계산식이 입력되었습니다.");
                continue;
            }

            // operator 사칙연산을 기준으로 앞/뒤로 나누어 각 객체에 저장
            String firstNumber = input.substring(0, operatorIndex);
            String secondNumber = input.substring(operatorIndex + 1);
            // number data를 int 형변황
            int num1 = Integer.parseInt(firstNumber);
            int num2 = Integer.parseInt(secondNumber);

            // 오류 메세지
            // 나눗셈 연산 분모 0인 경우 메세지 출력
            if (operator == '/' && num2==0){
                System.out.println("나눗셈 연산에서 분모에 0이 입력될 수 없습니다.");
                continue;
            }

            // 입력된 계산식 표기
            System.out.println("입력된 계산식 : " + num1 + operator + num2);

            // 출력값 표기
            System.out.println("결과 출력 :" + calculator.Calculate(num1, operator, num2));

            System.out.println("계산기 종료 : exit");
            System.out.println("연산 결과 기록 보기 : list");
            System.out.println("가정 먼저 연산된 기록 삭제 : remove");
            System.out.println("계속 계산하려면 계산식을 입력하여 주세요.");
        }
    }
}
