package com.kh.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Run {
	/*
	 * 컬렉션
	 * 자료구조 개념이 내장되어있는 클래스로 자바에서 제공하는 라이브러리
	 * 
	 * - 자료구조 : 데이터를 보다 효율적으로 관리(추가, 삭제, 조회, 정렬, 수정)할 수 있도록 해주는 구조
	 */

	public static void main(String[] args) {

		//List(순서O, 중복 허용)
		//ArrayList(배열기반), LinkedList(노드 기반), Vector, Stack(Vector기반 LIFO구조)
		
		List list = new ArrayList();//크기를 지정하지 않아도 됨.
		
		System.out.println(list); //빈 배열상태
		
		//1. add(E e) : 리스트 공간 끝에 전달된 데이터를 추가
		list.add(new Human("이무진",25));
		list.add(new String("이무진")); //여러타입을 저장할 수 있음.
		list.add(new Human("이창섭",30));
		list.add(new Human("임재현",27));
		
		System.out.println(list);
		
		//지정된 크기보다 더 많이 추가해도 에러가 발생하지 않음. 장점1 크기제약x
		//다양한 타입의 데이터를 담을 수 있음. 장점2 여러타입 보관가능
		
		//2.add(int index, E e) : 직접 인덱스를 지정해서 해당위치에 데이터를 추가
		list.add(1, new Human("최지원", 20));
		System.out.println(list);
		
		//3.remove(int index) : 해당 인덱스 위치의 데이터를 삭제시켜주는 메서드
		list.remove(1);
		System.out.println(list);
		
		//4.remove(E e) : 객체를 직접 지정해서 삭제(사용을 위해서는 객체의 equals를 적절하게 오버라이딩해줘야함.)
		list.remove("이무진");
		list.remove(new Human("임재현",27));
		System.out.println(list);
		
		//5.set(int index, E e) : 해당 인덱스의 값을 전달받은 e객체로 덮어씌움
		list.set(0, new Human("임재현",27));
		System.out.println(list);
		
		//6.size() : 리스트의 사용중인 사이즈를 반환
		System.out.println(list.size());
		
		//7.get(int index) : 해당 인덱스의 객체를 반환
		System.out.println(list.get(1));
		
		//8. addAll(Collection c) : 컬렉션을 통째로 뒤에 추가할 수 있음
		List sub = new ArrayList();
		sub.add(new Human("이창섭",30));
		sub.add(new Human("임재현",27));
		list.addAll(sub);
		System.out.println(list);
		
		//9. isEmpty() : boolean -> 컬렉션이 비어있는지 확인
		System.out.println(list.isEmpty());
		
		//10. clear() : 컬렉션의 내용을 전부 비워버리는 메서드
		//list.clear();
		System.out.println(list.isEmpty());
		System.out.println(list);
		
		//리스트 전체 접근방법
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		//for-each
		for(Object h : list) {
			System.out.println(h);
		}
		
		//Stack
		Stack<String> stack = new Stack<>();
		
		//push() 데이터 추가 
		stack.push("첫번째");
		stack.push("두번째");
		stack.push("세번째");
		
		System.out.println(stack);
		
		//peek() 맨 위 데이터를 확인
		System.out.println(stack.peek());
		
		//pop() 데이터 꺼내기
		String data = stack.pop();
		System.out.println(data);
		System.out.println(stack);
		
		//검색 search(E e) 데이터 검색 후 존재하면 위치반환(위에서부터 센다) -> equals를 사용하여 비교검색
		System.out.println(stack.search("첫번째"));
		
		//stack전체꺼내기
		while(!stack.isEmpty()) {
			System.out.println("꺼낸 값 : " + stack.pop());
		}
	}

}
