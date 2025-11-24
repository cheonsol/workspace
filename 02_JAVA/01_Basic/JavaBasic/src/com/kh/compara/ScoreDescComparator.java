package com.kh.compara;

import java.util.Comparator;

public class ScoreDescComparator implements Comparator<Student>{

	//점수 내림차순
	@Override
	public int compare(Student o1, Student o2) {
		//내림차순은 o2 vs o1
		return o2.score - o1.score;
	}

}
