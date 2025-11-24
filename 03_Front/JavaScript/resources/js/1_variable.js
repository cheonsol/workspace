console.log("Hello world 안녕 js");
//console 브라우저의 개발자 콘솔 객체
//.log() console객체의 메서드중 하나로 메세지를 출력 -> 개발자도구에 console영역에 표시됨.

/*
    js의 변수

    1) var - 사용하지 않는다.
    2) let
    3) const
*/

//js는 동적타입언어
//변수의 타입을 명시적으로 지정하지않고, 실행중에 값에 따라서 자동으로 타입이 결정됨.
var name = "최지원";
var age = 55;
var height = 187.5;
var isTrue = true;
console.log(name);

age = "오십";
var name = "김수민";
console.log(name);

/**
 * let은 var에서 같은 이름의 중복생성을 하지 못하게 만든 자료형
 * const는 상수를 표현하기위한 자료형
 */

let name2 = "최지원";
name2 = "김수민";

// let name2 = "김수민";

/**
 * js네이밍규칙
 * 
 * 변수이름
 * 1) 일반적으로 영어를 사용하며 문자와 숫자 모두 사용할 수 있음.
 * 2) 특수문자는 언더스코어(_)와 달러($)를 사용할 수 있음.
 * 3) 숫자로 시작하면 안됨.
 * 4) 키워드를 변수명으로 사용하면 안됨.
 * 
 * 일반적으로 변수명, 함수명 -> camelCase
 * class, id같은 속성명 -> Kebab-case
 */

/*
    Data Type

    여섯개의 원시타입과 한개의 Object타입이 있다.

    1) Number
    2) String
    3) Boolean
    4) undifined
    5) null
    6) Symbol

    7) Object
    - Function
    - Array
    - object
    ...
*/

//Number타입
const age2 = 55;
const temp = -10.5;
const pi = 3.14;

console.log(age2, temp, pi);
console.log(typeof age2);
console.log(typeof temp);
console.log(typeof pi);

console.log(typeof Infinity);
console.log(typeof -Infinity);

//string
const name3 = "최지원 66살";
const age3 = 55;
console.log(typeof name3) 
console.log(age3 == "55");
console.log(age3 === "55");

//boolean 타입
const isTrue2 = true;
const isFalse = false;
console.log(typeof isTrue)

/**
 * undefined
 * 
 * 개발자가 직접 값을 초기화하지 않았을 때.
 * 즉, 변수는 선언하고 사용하지 않았을 때 지정되는 값.
 */

let num2;
console.log(num2)
console.log(typeof num2)

/**
 * null
 * undefined와 동일하게 값이 없음을 표시
 * 다만 js에서는 개발자가 직접 명시적으로 없는 값을 초기화할 때 사용
 */
let init = 10;
init = null;
console.log(init);

/**
 * Symbol타입
 * 
 * 유일무이한 값을 생성하는 타입
 * 다른 원시값들과는 다르게 symbol함수를 호출해서 사용
 */

const tmp1 = '1';
const tmp2 = '1';
console.log("tmp1 === tmp2 : ", tmp1 === tmp2);

const symbol1 = Symbol('1');
const symbol2 = Symbol('1');
console.log(symbol1, symbol2);

console.log("symbol1 === symbol2 : ", symbol1 === symbol2);

/**
 * Object -> 프로토타입체인의 최상위 object가 Obeject 
 * object타입
 * 
 * 키 : 벨류
 * key : value -> map의 구조
 * 
 * 예: DOM에 <input type="text" style="color:red;"/>
 * {
 *      elements : input,
 *      type: text,
 *      style : "color:red;"
 * }
 */

const jiwon = {
    name: "최지원",
    age: 47,
    address: "경기도 안양시",
    job: "강사",
}

console.log(jiwon.name);
console.log(jiwon.address);
jiwon.age = 15;
console.log(jiwon.age);

console.log(typeof jiwon);

/**
 * Array타입
 * 값을 리스트로 나열할 수 있는 타입
 */

const arr = ["초록색", "노란색"];
//arr.push(요소) -> 타입에 상관없이 배열의 맨 뒤에 값을 추가
arr.push("빨간색");
arr.push("파란색");
arr.push(50);
console.log(arr);

//arr.pop() 맨 마지막에 추가된 요소를 제거하고 반환한다.
console.log(arr.pop());
console.log(arr);

console.log(arr[0]);
console.log(arr[2]);
console.log(arr[10]);