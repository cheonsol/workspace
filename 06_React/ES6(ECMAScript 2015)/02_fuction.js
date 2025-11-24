//함수표현식

//1. 기본함수
function hello(){
    console.log("hellow!");
}

hello();

//js에서는 함수도 하나의 값(변수)으로 취급한다.
let _hello = hello;
console.log(_hello);
_hello();

//2. 익명함수
const printMsg = function(){
    console.log("this is a function");
}

printMsg();

//기본함수는 호이스팅이 된다.
tmp();
function tmp(){
    console.log("나는 함수다.");
}

// let tmp2; -> tdz()

//tmp2();
let tmp2 = function(){
    console.log("나도 함수다.")
}

//함수의 기본 매개변수
function greet(name = "방문자", msg = "안녕"){
    console.log(`${name}님 ${msg}`);
}

//js의 함수는 호출시 이름으로만 함수를 판단함.
greet("최지원", "안녕하세요");
greet("김수민");

greet(null); //-> 개발자가 명시적으로 표현한 빈값
greet(undefined, "누구십니까?"); // -> 시스템이 정해준 빈값(개발자가 선언 후 사용하지 않은 값)

//기본값이 있는 매개변수는 뒤쪽에 배치하자.
function greet2(name = "방문자", msg){
    console.log(`${name}님 ${msg}`);
}

greet2(undefined, "안녕하세요.");

function greet3(msg, name = "방문자"){
    console.log(`${name}님 ${msg}`);
}

greet3("안녕하세요.");

//2. 화살표 함수
//코드가 짧고 가독성이 좋다.
function add1(a, b){
    return a + b;
}

const add2 = (a, b) => { 
    console.log(a, b);
    return a + b; 
}

//함수의 구현부에 리턴값만 있다면 return키워드와 {}를 생략가능
const add3 = (a, b) => a+b;
//매개변수가 한개라면 ()도 생략 가능
const print = msg => console.log(msg);

//this의 바인딩차이
// 화살표함수는 자신만의 this를 바인딩하지 않는다.
const human1 = {
    name: "지원",
    age: 55,
    info: function(){
        console.log(`안녕 나는 ${this.name}이야`);
        setTimeout(function(){
           console.log(`안녕 나는 ${this.name}이야`);     
        }, 500);
    }
}

// human1.info();

//Lexical scope 
//함수를 선언위치에 따라 this가 결정되는 방식
const human2 = {
    name: "수민",
    age: 55,
    info: function(){
        console.log(`안녕 나는 ${this.name}이야`);
        setTimeout(() => {
           console.log(`안녕 나는 ${this.name}이야`);     
        }, 500);
    }
}

human2.info();

//3. 콜백 함수
//특정 함수를 실행할 때 실행하는 사람이 특정 기능을 완료한 후에 실행하고싶은 코드를 정의하는 용도
const run = (callback) => {
    //특정기능 후에
    callback();
}

run(() => {
    console.log("run이 실행됨.");
})

run(() => {
    console.log("run이 실행됨.22222");
})

run(() => {
    console.log("run이 실행됨.33333");
})
