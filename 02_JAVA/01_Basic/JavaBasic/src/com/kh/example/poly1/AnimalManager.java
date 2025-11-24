package com.kh.example.poly1;

import java.util.Scanner;

public class AnimalManager {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Animal[] animalArr = new Animal[5];
		
		for(int i=0; i<animalArr.length; i++) {
			System.out.print("1.강아지 2.고양이 :");
			int num = sc.nextInt();
			
			System.out.print("이름 : ");
			String name = sc.next();
			
			System.out.print("나이 : ");
			int age = sc.nextInt();
			
			if(num == 1) {
				System.out.print("견종 : ");
				String breed = sc.next();
				animalArr[i] = new Dog(name, age, breed);
			} else {
				System.out.print("색상 : ");
				String color = sc.next();
				animalArr[i] = new Cat(name, age, color);
			}
		}

		for(Animal ani : animalArr) {
			ani.speak();
			if(ani instanceof Dog) {
				String breed = ((Dog)ani).getBreed();
				System.out.println("이 강아지의 견종은 "+breed+"입니다.");
			} else  if(ani instanceof Cat) {
				String color = ((Cat)ani).getColor();
				System.out.println("이 고양이의 색상은 "+color+"입니다.");
			}
		}
	}

}
