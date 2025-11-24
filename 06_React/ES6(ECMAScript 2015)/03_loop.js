//1. for문
for(let i=0; i<5; i++){
    console.log("for in i : ", i);
}

//2. while문
let j=0;
while(j < 5){
    console.log("for in j : ", j);
    j++;
}

//3. do while문
j=0;
do {
    console.log("for in j : ", j);
    j++;
} while(j < 5);

//4. for ... of
let fruits = ["사과", "배", "딸기", "바나나"];
for(const fruit of fruits){
    console.log(fruit);
}

fruits = [{
    id: 1,
    name: "바나나",
    price: 2000
}, {
    id: 2,
    name: "감",
    price: 5000
}, {
    id: 3,
    name: "배",
    price: 3500
}, {
    id: 4,
    name: "사과",
    price: 3000
}];

for(const fruit of fruits){
    console.log(fruit.id + " : " + fruit.name);
}

//4. for ... in

const apple = {
    id: 4,
    name: "사과",
    price: 3000
}

for(let k in apple){
    console.log(k + " : " + apple[k]);
}

// 5. forEach
// -배열 순회 전용 메서드

fruits = [{
    id: 1,
    name: "바나나",
    price: 2000
}, {
    id: 2,
    name: "감",
    price: 5000
}, {
    id: 3,
    name: "배",
    price: 3500
}, {
    id: 4,
    name: "사과",
    price: 3000
}];

fruits.forEach((obj, index) => {
    console.log(`forEach : ${index} -> ${obj.name}`);
});


const numbers = [1,3,5,7,9];
// 6. map()
// 기존배열을 가지고 새로운 배열을 만들고 실을 때 -> 변형된 새로운 배열을 반환
// 서버로부터 받은 데이터를 통해서 대칭되는 UI를 만들어낼 때 사용
//[1, 9, 25 ...]
const squared = numbers.map((num) => num*num); //내부함수의 리턴값을 통한 새로운 배열을 반환.
console.log(`map의 결과 : ${squared}`);

// 7. filter()
// 조건에 맞는 요소만 추출하고 싶을 때 -> 조건에 맞는 값만 모아서 새로운 배열을 반환
// 서버로부터 데이터를 삭제하고 이를 ui 상태에 반영해 줄때 많이 사용함
//[3,9]
const squared2 = numbers.filter((num) => num % 3 === 0); //내부함수의 리턴값이 true인 것만 모아서 반환
console.log(squared2);

//8. find()
// 조건에 맞는 "첫번째"요소만 추출하고 싶을 때 -> 조건에 맞는 값 하나 검색
const squared3 = numbers.find((num) => num % 3 === 0);
console.log(squared3);

//9. some()
//하나라도 조건을 만족하면 true
const hasSquared = numbers.some((num) => num % 3 === 0);
console.log(`하나라도 조건을 만족하면 : ${hasSquared}`);

const hasSquared2 = numbers.some((num) => num % 2 === 0);
console.log(`하나라도 조건을 만족하면 : ${hasSquared2}`);

//10. every()
const allSquared = numbers.every((num) => num % 3 === 0);
console.log(`하나라도 조건을 만족하면 : ${allSquared}`);

const allSquared2 = numbers.every((num) => num % 1 === 0);
console.log(`하나라도 조건을 만족하면 : ${allSquared2}`);

//11. reduce()
// 배열의 값을 누적하여 하나의 결과값을 도출
// 배열.reduce((누적값, 배열요소) => {실행할 코드 return 누적값;}, 누적값의 초기값);

const result = numbers.reduce((sum, num) => {
    console.log("합 : " + sum + " 값 : " + num);
    sum.push(num);
    return sum;
}, []);

console.log(result);

const stdList = [{
    name: "최지원",
    age: 17,
    score: 80
},{
    name: "김지원",
    age: 17,
    score: 20
},{
    name: "박지원",
    age: 17,
    score: 50
}];

let scoreMap = stdList.reduce((scoreMap, std) => {
    scoreMap[std.name] = std.score;
    return scoreMap;
}, {});

console.log(scoreMap);
