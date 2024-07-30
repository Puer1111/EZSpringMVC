package com.EZ.spring.notice.Store.Impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.EZ.spring.notice.Store.NoticeStore;
import com.EZ.spring.notice.domain.NoticeVO;
@Repository
public class NoticeStoreImpl implements NoticeStore {

	@Override
	public int insertNotice(SqlSession session, NoticeVO notice) {
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	@Override
	public List<NoticeVO> selectList(SqlSession session,int currentPage) {
		int limit=10;
		//몇개씩 보여주고
		int offset=(currentPage-1)*limit;
		//시작 지점~ limit개씩
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<NoticeVO>nList = session.selectList("NoticeMapper.selectList",currentPage,rowBounds);
		return nList;
	}

	@Override
	public int getTotalCount(SqlSession session) {
		int result = session.selectOne("NoticeMapper.getTotalCount");
		return result;
	}

	@Override
	public NoticeVO selectOneByNo(SqlSession session, int noticeNo) {
		NoticeVO notice=session.selectOne("NoticeMapper.selectOneByNo",noticeNo);
		
		return notice;
	}

	@Override
	public int deleteNotice(Integer noticeNo, SqlSession session) {
		int result = session.delete("NoticeMapper.deleteNotice",noticeNo);
		return result;
	}

	@Override
	public int updateNotice(NoticeVO notice, SqlSession session) {
		int result = session.update("NoticeMapper.updateNotice",notice);
		return result;
	}

	@Override
	public List<NoticeVO> selectSearchList(SqlSession session, Map<String, String> paramMap) {
		List<NoticeVO>nList = session.selectList("NoticeMapper.selectSearchList",paramMap);
		return nList;
	}

}
