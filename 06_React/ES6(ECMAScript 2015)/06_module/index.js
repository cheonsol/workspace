//utils에서 작성한 기능을 import트해서 사용가능

import {add, pi} from './utils.js';
import helloFunc from './utils.js';

console.log("2+3 = ", add(2,3));
console.log("pi = ", pi);

helloFunc("최지원");

//모듈시스템에서는 각 파일에 독립된 scope를 제공함.
//다른 파일의 변수나 함수는 반드시 import를 해서 사용.