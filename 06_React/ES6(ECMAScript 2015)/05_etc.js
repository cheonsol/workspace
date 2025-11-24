//1. 템플릿 리터럴
//문자열과 변수 결합시 편리하고 여러줄의 문자열도 간단하게 처리가 가능하다.
const userName = "최지원";
console.log(`안녕하세요. ${userName}님`);

const multiStr = `이 변수는
여러줄로 작성된
문자열을 가지고 있습니다.`;
console.log(multiStr);

//2. 구조분해할당
// 객체에서 필요한 값을 바로 변수로 추출할 때 사용.
const userInfo = {
    name: "최지투",
    age: 55,
    job: "dev"
}

//const name = userInfo.name;
//const age = userInfo.age;
//const {name, age} = userInfo;
//console.log(name, age);

//변수 이름을 변경해서 추출하고 싶을 때
//const name = userInfo.name;
//const newJob = userInfo.job;
const {name, job:newJob} = userInfo;
console.log(name, newJob);

//React에서는 props라는 객체를 전달하고 매개변수로 내부의 값들을 받을 때 해당 문법을 주로 사용함.
function myInfo({name, age}){
    console.log(name, age);
}

myInfo(userInfo);

//3. 배열 구조 분해할당
//배열 요소를 순서대로 변수에 담아줌
const numbers = [10,20,30];
const [num1, num2] = numbers;
console.log(num1, num2);

//필요없는 값은 생략 가능
const [,, num] = numbers;
console.log(num)

//react에서는 state라는 값을 생성할 때 배열에 값과 해당ㄱ밧을 변경하는 함수가 순차적으로 전달된다.
//useState() return [값, 값을변경할 때 사용하는 setter]
//const [count, setCount] = useState();

//4. 스프레드연산자(...)
// 배열/객체 복사, 병합, 수정, 나머지값을 처리등등...
// 

let user = {
    name: "지원",
    age: 55,
    job: "dev"
}

user.job = "chef"; //이렇게 변경시 실제 객체의 내부 값만 변경이되기 때문에 객체자체의 주소값이 변경되지않음

user = {
    ...user,
    job: "chef"
}
// let tmp1 = {
//     name : "jiwon"
// }

// let tmp2 = {
//     name : "jiwon"
// }
// console.log(tmp1 === tmp2);
