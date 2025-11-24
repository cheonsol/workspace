//var -> 더이상 사용하지 않는다.
console.log(tmp); //-> 선언이 됨, 호이스팅
var tmp = "여기서 부터 생성됨";

//let, const : 변수, 상수
//TDZ(Temporal Dead zone)를 통해서 호이스팅의 문제를 해결
//let과 const도 호이스팅이 되지만, 선언되는 시점의 코드가 실행되기 전까지는 TDZ에 등록하여 사용할 수 없게 관리한다.

//변수명 작성 규칙
//1. 변수명에는 $, _외의 특수문자는 사용안됨
//2. 숫자로 시작할 수 없음
//3. 예약어 사용 안됨

//올바른 변수명 예시
let $price = 10000; //보통 두가지의 변수타입이 있을 때 구분의 용도로 활용
let userName = "jiwon";
let _status = "active"; //보통 복사한 값을 표현할 때

//let : 변수(값 재할당 가능, 중복선언x)
let name = "최지원";
console.log(`name : ${name}`);

name = "김수민";
console.log(`name : ${name}`);

const age = 50;
console.log(`age : ${age}`);

try{
    age = 15;
    console.log(`name : ${age}`);
} catch(error){
    console.log(`오류 발생 : ${error.message}`);
}
