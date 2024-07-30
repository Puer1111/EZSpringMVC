package com.EZ.spring.notice.Service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EZ.spring.notice.Service.NoticeService;
import com.EZ.spring.notice.Store.NoticeStore;
import com.EZ.spring.notice.domain.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private SqlSession session;
	@Autowired
	private NoticeStore nStore;

	@Override
	public int insertNotice(NoticeVO notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	@Override
	public List<NoticeVO> selectNoticeList(int currentPage) {
		List<NoticeVO>nList = nStore.selectList(session, currentPage);
		if(nList.size()==0) {
			nList=null;
		}
		return nList;
	}

	@Override
	public int getTotalCount() {
		int result = nStore.getTotalCount(session);
		return result;
	}

	@Override
	public NoticeVO selectOneByNo(int noticeNo) {
		NoticeVO notice = nStore.selectOneByNo(session, noticeNo);
		return notice;
	}

	@Override
	public int deleteNotice(Integer noticeNo) {
		int result = nStore.deleteNotice(noticeNo, session);
		return result;
	}

	@Override
	public int updateNotice(NoticeVO notice) {
		int result = nStore.updateNotice(notice, session);
		return result;
	}

	@Override
	public List<NoticeVO> searchNoticeByKeyword(Map<String, String> paramMap) {
		List<NoticeVO>nList = nStore.selectSearchList(session,paramMap);
		if(nList.size()==0) {
			nList=null;
		}
		return nList;
	}

}
