package com.kh.example.collection1;

import java.util.ArrayList;
import java.util.List;

public class MusicController {
	private List<Music> list = new ArrayList<>();
	
	public int addList(Music music) {
		if(music == null) return 0;
		list.add(music);
		return 1;
	}
	
	public int addAtZero(Music music) {
		if(music == null) return 0;
		list.add(0, music);
		return 1;
	}
	
	public List<Music> printAll(){
		return list;
	}
	
	public Music searchMusic(String title) {
		for(Music m : list) {
			if(m.getTitle().equals(title))
				return m;
		}
		return null;
	}
	
	public Music removeMusic(String title) {
		for(Music m : list) {
			if(m.getTitle().equals(title)) {
				Music removeMusic = m;
				list.remove(m);
				return removeMusic;
			}
		}
		return null;
	}
	
	public Music setMusic(String title, Music music) {
		for(int i=0; i<list.size(); i++) {
			Music m = list.get(i);
			if(m.getTitle().equals(title)) {
				list.set(i, music);
				return m;
			}
		}
		
		return null;
	}
}
