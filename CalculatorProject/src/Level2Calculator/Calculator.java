package Level2Calculator;

// 사칙연산을 수행 후, 결과값 반환 메서드 구현 & 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
//  * 사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
//  * 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
//  * 1) 양의 정수 2개(0포함)와 연산 기호를 매개변수로 삼아 사칙연산 기능을 수행한 후
//  * 2) 결과 값을 반환하는 매서드와 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성

// Lv1에서 구현한 App 클래스의 main 메서드에 Calculator 클래스가 활용될 수 있도록 수정
//  * 연산 수행 역할은 Calculator 클래스가 담당
//  ** 연산 결과는 Calculator 클래스의 연산 결과를 저장하는 필드에 저장
//  * 소스 코드 수정 후에도 수정 전의 기능들이 반드시 똑같이 동작해야 합니다.

// App 클래스의 main 메서드에서 Calculator 클래스의 연산 결과를 저장하고 있는 컬렉션 필드에 직접 접근하지 못하도록 수정(캡슐화)
//  * 간접 접근을 통해 필드에 접근하여 가져올 수 있도록 구현합니다. (Getter 메서드)
//  * 간접 접근을 통해 필드에 접근하여 수정할 수 있도록 구현합니다. (Setter 메서드)
//  * 위 요구사할을 모두 구현했다면 App 클래스의 main 메서드에서 위에서 구현한 메서드를 활용해봅니다.

// Calculator 클래스에 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능을 가진 메서드를 구현한 후
// App 클래스의 main 메서드에 삭제 메서드가 활용될 수 있도록 수정
//  * 키워드 : 컬렉션
//  ** 컬렉션에서 '값을 넣고 제거하는 방법을 이해한다'가 중요합니다!

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    // 연산결과를 저장할 컬렉션 타입 필드
    private List<Integer> answers;

    //생성자
    public Calculator() {
        answers = new ArrayList<>();
    }

    // 입력된 사칙연산 기호에 따라 switch를 통하여 각 계산식을 수행
    public int Calculate(int num1, char operator, int num2){
        int answer = 0;
        switch (operator) {
            case '+':
                answer = num1 + num2;
                break;
            case '-':
                answer = num1 - num2;
                break;
            case '*':
                answer = num1 * num2;
                break;
            case '/':
                answer = num1 / num2;
                break;
            default:
        }
        // 결과값을 컬랙션에 저장
        addAnswer(answer);
        return answer;
    }

    //결과값 저장 메서드
    private void addAnswer(int answer) {
        answers.add(answer);
    }

    // Getter 메서드 구현
    public List<Integer> getAnswers() {
        // 새로운 리스트를 반환하여 캡슐화
        return new ArrayList<>(answers);
    }

    // Setter 메서드 구현
    public void SetAnswers(List<Integer> newAnswers) {
        // 새로운 리스트를 반환하여 캡슐화
        this.answers = new ArrayList<>(newAnswers);
    }

    // 가장 먼저 기록된 연산 결과 제거
    public void removeAnswer(){
        answers.remove(0);
    }
}
