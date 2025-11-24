package com.kh.mybatis.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.Template;
import com.kh.mybatis.common.vo.PageInfo;
import com.kh.mybatis.model.dao.BoardDao;
import com.kh.mybatis.model.vo.Board;

public class BoardService {
	private BoardDao boardDao = new BoardDao();
	
	public int selectAllBoardCount() {
		SqlSession sqlSession = Template.getSqlSession();
		
		int listCount = boardDao.selectAllBoardCount(sqlSession);
		
		sqlSession.close();
		
		return listCount;
	}
	
	public int selectAllBoardCount(HashMap<String, String> searchMap) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int listCount = boardDao.selectAllBoardCount(sqlSession, searchMap);
		
		sqlSession.close();
		
		return listCount;
	}
	
	public ArrayList<Board> selectAllBoard(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> list = boardDao.selectAllBoard(sqlSession, pi);
		
		sqlSession.close();
		
		return list;
	}
	
	public ArrayList<Board> selectAllBoard(PageInfo pi, HashMap<String, String> searchMap) {
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> list = boardDao.selectAllBoard(sqlSession, pi, searchMap);
		
		sqlSession.close();
		
		return list;
	}

}
